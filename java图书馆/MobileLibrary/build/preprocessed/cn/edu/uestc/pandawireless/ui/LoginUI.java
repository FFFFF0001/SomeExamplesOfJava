/*
 * LoginUI.java
 *
 * Created on 2006��5��13��, ����3:36
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;


/**
 *
 * @author ��è��ϣ
 */
public class LoginUI extends Form implements CommandListener{
    private static final Command backCommand = new Command("����",Command.BACK, 2);
    private static final Command loginCommand = new Command("��½",Command.OK, 1);
    private TextField name = null;   
    private TextField pwd = null;
    private UIControler uicontroler;        //�����������֮��ĵ���
    
    /** Creates a new instance of LoginUI */
    public LoginUI(UIControler uicontroler) {
        super(Title.loginTitle);
        this.uicontroler = uicontroler;
        name = new TextField(Title.username, "", 25, TextField.NUMERIC);
        pwd = new TextField(Title.pwd, "", 25, TextField.ANY|TextField.PASSWORD);
        this.append(name);
        this.append(pwd);
        this.addCommand(backCommand);
        this.addCommand(loginCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.LOGIN_BACKTO_MAINNENU);
        }
        else if(cmd == loginCommand){
            Alert alert = new Alert("����");
            String uid = name.getString();
            
            String p = pwd.getString();
            //System.out.println(uid+"\n"+p);
            if(uid.equals("") || uid == null){
                alert.setString("�������벻��Ϊ�գ�");
                alert.setTimeout(2000);
                alert.setType(AlertType.ALARM);
                uicontroler.libmidlet.setCurrent(alert, this);
            }else if(p.equals("") || p == null){
                alert.setString("���벻��Ϊ�գ�");
                alert.setTimeout(2000);
                alert.setType(AlertType.ALARM);
                uicontroler.libmidlet.setCurrent(alert, this);
            }else{
                 uicontroler.handleEvent(UIControler.EventID.LOGIN, new Object[]{uid,p});
            }
           
        }
    }
    
}
