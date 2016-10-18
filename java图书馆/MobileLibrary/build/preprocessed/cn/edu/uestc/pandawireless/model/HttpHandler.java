/*
 * HttpHandler.java
 *
 * Created on 2006��5��22��, ����7:58
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.model;
import java.lang.Thread;
import javax.microedition.io.*;
import java.io.*;
import cn.edu.uestc.pandawireless.ui.*;
import java.util.*;
import cn.edu.uestc.pandawireless.ui.table.*;

 

/**
 *
 * @author pandaxiaoxi
 */
public class HttpHandler extends Thread{
    private String serverURL = "";
    private HttpConnection conn = null;
    private UIControler uicontroler = null;
   // private byte[] requestData = null;
    //private byte[] responseData = null;
    private boolean done = false;
    
    private byte op ;
    private Object[] objs;
    
    private TableModel model;
    private Table table;
    public LoginSuccessUI lsUI;
    public SearchResultUI srUI;
    /** Creates a new instance of HttpHandler */
    public HttpHandler(UIControler uicontroler) {
        this.uicontroler = uicontroler;
    }
     public void setURL(String url){
        this.serverURL = url;
    }
//     public void setRequestData(byte[] data){
//         this.requestData = data;
//     }
//     public byte[] getResponseData(){
//         return responseData;
//     }
     public void setOP(byte op){
         this.op = op;
     }
     public void setObjects(Object[] objs){
         this.objs = objs;
     }
     public void stop(){
        done = true;
     }
     public void run(){
        while(!done){
            //�߳���������ȴ�״̬
            synchronized (uicontroler) {
                try {
                    uicontroler.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if(!done){
                try{
                    
                    //����HttpConnection����
                    conn = (HttpConnection)Connector.open(serverURL,Connector.READ_WRITE, true);
                    //midlet.setCurrent(progress);
                    uicontroler.displayProgress();
                                     
                    //System.out.println(conn.toString());
                    //conn.setRequestProperty("X-Online-Host", "218.194.34.156");
                    conn.setRequestProperty("User-Agent",System.getProperty("microedition.profiles"));
                    uicontroler.progressUI.append("\n����User-Agent:"+System.getProperty("microedition.profiles"));
                    conn.setRequestProperty("Content-Type","application/octet-stream");
                    uicontroler.progressUI.append("\n����Content-Type:application/octet-stream");
                    conn.setRequestMethod(HttpConnection.POST);
                    uicontroler.progressUI.append("\n��������ʽ:POST");
                    uicontroler.progressUI.append("\n��ʼ������������...");
                    DataOutputStream dos = conn.openDataOutputStream();
                    dos.writeByte(this.op);
                    int size = objs.length;
                    for (int i=0;i<size;i++){
                         dos.writeUTF((String)objs[i]);
                    }
                   
                    
                    //dos.write(this.requestData);
                    dos.close();
                    //�жϷ��ص�HTTP��Ӧ�� 200��ʾ���ӳɹ�
                    int responseCode = conn.getResponseCode();
                    if(responseCode != HttpConnection.HTTP_OK){
                        uicontroler.displayError("���ӷ�����ʱ��������!"+responseCode);
                        return;
                    }
                   
                        DataInputStream dis = conn.openDataInputStream();
                        
                        byte returnCode = dis.readByte();
                        //dis.close();
                        //conn.close();
                        switch (returnCode) {
                            case OperateConstants.CHANGEPWD_SUCCESS:
                            {
                                uicontroler.displaySuccess("��ϲ�㣬�޸�����ɹ���");
                                break;
                            }
                            case OperateConstants.CHANGEPWD_FAIL:
                            {
                                uicontroler.displayError("�޸�����ʧ�ܣ�");
                                break;
                            }
                            case OperateConstants.CHANGEPWD_ERROR_PWD:
                            {
                                uicontroler.displayError("���������������������룡");
                                break;
                             }
                            case OperateConstants.CHANGEPWD_NO_USER:
                            {
                                uicontroler.displayError("û�д��û������������룡");
                                break;
                            }
                            case OperateConstants.LOGIN_ERROR_PWD:
                            {
                                uicontroler.displayErrorforLogin("����������������������!");
                                break;
                            }
                            case OperateConstants.LOGIN_NO_USER:
                            {
                                uicontroler.displayErrorforLogin("û�д��û������������룡");
                                break;
                            }
                            case OperateConstants.LOGIN_SUCCESS_NO_BOOK:
                            {
                                uicontroler.handleEvent(UIControler.EventID.LOGIN_SUCCESS_NO_BOOK);
                                break;
                            }
                            case OperateConstants.LOGIN_SUCCESS_HAS_BOOK:
                            {
                                
                                int count = dis.readInt();
                                uicontroler.progressUI.append("\n��½�ɹ��ˣ��㵱ǰ����"+count+"���飡\n�Ժ�Ϳ��Կ����㶼������Щ����!\n���ĵȴ�Ŷ...");
                                try
                                {
                                    Thread.sleep(3000);
                                }catch(Exception ex){
                                    
                                }
                                Vector borrows = new Vector();
                                String[] borrow = null;
                                for(int i=0;i<count;i++){
                                    borrow = new String[3];
                                    for(int j=0;j<borrow.length;j++){
                                        borrow[j] = dis.readUTF();
                                    }
                                    borrows.addElement(borrow);
                                }
                               model = new BorrowModel(borrows);
                               table = new Table(model);
                               //uicontroler.handleEvent(UIControler.EventID.SHOW_USER_BOOKS,table);
                               lsUI = new LoginSuccessUI(uicontroler,table);
                               uicontroler.libmidlet.setCurrent(lsUI);
                               break;
                            }
                            case OperateConstants.SEARCH_NO_BOOK:
                            {
                                uicontroler.displayErrorforSearch("�Բ���,û����Ҫ���ҵ��鼮,�����²�ѯ!");
                                break;
                            }
                            case OperateConstants.SEARCH_HAS_BOOK:
                            {
                                
                                int count = dis.readInt();
                                uicontroler.progressUI.append("\nһ��Ϊ���ҵ���"+count+"��ƥ����飡\n�Ժ�Ϳ��Կ�����Щ����!\n���ĵȴ�Ŷ...");
                                try
                                {
                                    Thread.sleep(3000);
                                }catch(Exception ex){
                                    
                                }
                                Vector books = new Vector();
                                String[] book = null;
                                for(int j=0;j<count;j++){
                                   book =  new String[7]; //������Ը���,���Բ鿴�������˵�BookBean
                                   for(int n=0;n<book.length;n++){
                                       book[n] = dis.readUTF();
                                       //System.out.println(book[n]);
                                   }
                                   books.addElement(book);
                                }
               /***********************************************************************************/
               /*****����������ڲ���Table�Ƿ����,��ʽ�汾�������������******************************/
               /***********************************************************************************/
//                               TableModel model = new BookModel(books);
//                               Table table = new Table(model);
//                               uicontroler.libmidlet.setCurrent(new ScrollPane(table));
                                model = new BookModel(books);
                                table = new Table(model);
                                srUI = new SearchResultUI(uicontroler,table);
                                uicontroler.libmidlet.setCurrent(srUI);
                               break;
                            }
                    }
                        dis.close();
                        conn.close();
                    
                }catch(IOException ee){
                     switch(this.op){
                         case OperateConstants.LOGIN:
                         {
                             uicontroler.displayErrorforLogin("����IO�쳣��");
                             break;
                         }
                         case OperateConstants.SEARCH_BOOK:
                         {
                             uicontroler.displayErrorforSearch("����IO�쳣��");
                             break;
                         }
                         case OperateConstants.CHANGE_PWD:
                         {
                             uicontroler.displayError("����IO�쳣��");
                             break;
                         }
                     }
                     
                 }
            }
        }
    }
     
    
}
