/*
 * EachBookDetailUI.java
 *
 * Created on 2006��5��26��, ����12:17
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import cn.edu.uestc.pandawireless.model.HttpHandler;
import javax.microedition.lcdui.*;
/**
 *
 * @author ��è��ϣ
 */
public class EachBookDetailUI extends Form implements CommandListener{
    private UIControler uicontroler;
    private HttpHandler httphandler;
    private static final Command backCommand = new Command("����",Command.BACK, 2);
    //private static final Command selectCommand = new Command("����",Command.OK, 1);
    //private DateField datefield;
    
    /** Creates a new instance of BorrowDetailUI */
    public EachBookDetailUI(UIControler uicontroler,String[] data,HttpHandler httphandler) {
        super(Title.eachbookDetailTitle);
        this.uicontroler = uicontroler;
        this.httphandler = httphandler;
        this.append("����:"+data[0]+"\n");
        this.append("�����:"+data[1]+"\n");
        this.append("״̬:"+data[2]+"\n");
        this.append("Ӧ������:"+data[3]+"\n");
        
        this.append("����:"+data[4]+"\n");
        this.append("������:"+data[5]+"\n");
        this.append("��������:"+data[6]+"\n");
        //this.append("\n\n"+"����������(���򽫻�����ʱ�����㻹��)");
        //datefield = new DateField("",DateField.DATE_TIME);
        //this.append(datefield);
        this.addCommand(backCommand);
        //this.addCommand(selectCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.libmidlet.setCurrent(httphandler.srUI);
        }
    }
    
}
