/*
 * BorrowDetailUI.java
 *
 * Created on 2006��5��25��, ����10:50
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import cn.edu.uestc.pandawireless.model.*;
import javax.microedition.lcdui.*;
import java.util.Date;
import javax.microedition.io.PushRegistry;
/**
 *
 * @author ��è��ϣ
 */
public class BorrowDetailUI extends Form implements CommandListener{
    private UIControler uicontroler;
    private HttpHandler httphandler;
    private static final Command backCommand = new Command("����",Command.BACK, 2);
    private static final Command selectCommand = new Command("ע��",Command.OK, 1);
    public static final String className = "cn.edu.uestc.pandawireless.LibMidlet";
    private DateField datefield;
    private Date set = null;
    private String bookname = null;
    
    /** Creates a new instance of BorrowDetailUI */
    public BorrowDetailUI(UIControler uicontroler,String[] data,HttpHandler httphandler) {
        super(Title.borrowDetailTitle);
        this.uicontroler = uicontroler;
        this.httphandler = httphandler;
        this.uicontroler.borrowui = this;
        this.bookname = data[0];
        this.append("����:"+data[0]+"\n");
        this.append("�����:"+data[1]+"\n");
        this.append("Ӧ������:"+data[2]+"\n");
        this.append("\n\n"+"����������(���򽫻�����ʱ�����㻹��)");
        datefield = new DateField("",DateField.DATE_TIME);
        datefield.setDate(new Date());
        this.append(datefield);
        this.addCommand(backCommand);
        this.addCommand(selectCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.libmidlet.setCurrent(httphandler.lsUI);
        }else if(cmd == selectCommand)
        {
            
            set = datefield.getDate();
            System.out.println("set:"+set);
            String s = AlarmModel.getString(set);
            System.out.println("set:"+s);
            new Thread(new Runnable(){
                public void run(){
                try {
                //����������ʱ��洢��RMS��
                uicontroler.libmidlet.model.writeString(set,bookname);
                //ע��ʱ��
                PushRegistry.registerAlarm(className,set.getTime());
                Alert alert = new Alert("�����ʾ");
                alert.setTimeout(2000);
                alert.setString("���Ѿ��ɹ�����������");
                alert.setType(AlertType.CONFIRMATION);
                uicontroler.libmidlet.setCurrent(alert,uicontroler.borrowui);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
            }).start();
        }
    }
//    class AlarmSetter implements Runnable{
//        public void run(){
//            try {
//                //����������ʱ��洢��RMS��
//                model.writeDate(set);
//                //ע��ʱ��
//                PushRegistry.registerAlarm(className,set.getTime());
//                Alert alert = new Alert("�����ʾ");
//                alert.setTimeout(2000);
//                alert.setString("���Ѿ��ɹ�����������");
//                alert.setType(AlertType.CONFIRMATION);
//                uicontroler.libmidlet.setCurrent(alert, );
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
