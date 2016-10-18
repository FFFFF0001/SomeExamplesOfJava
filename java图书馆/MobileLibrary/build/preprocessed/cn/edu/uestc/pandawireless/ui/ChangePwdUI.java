/*
 * ChangePwdUI.java
 *
 * Created on 2006年5月13日, 下午4:11
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;


/**
 *
 * @author 熊猫晓希
 */
public class ChangePwdUI extends Form implements CommandListener {
    private UIControler uicontroler;
    
    private static final Command backCommand = new Command("返回",Command.BACK, 2);
    private static final Command changeCommand = new Command("修改",Command.OK, 1);
    
    private TextField name = null;
    private TextField oldpwd = null;
    private TextField newpwd = null;
    /** Creates a new instance of ChangePwdUI */
    public ChangePwdUI(UIControler uicontroler) {
        super(Title.modifypwdTitle);
        this.uicontroler = uicontroler;
        name = new TextField(Title.username, "", 25, TextField.NUMERIC);
        oldpwd = new TextField(Title.oldpwd, "",25, TextField.ANY|TextField.PASSWORD);
        newpwd = new TextField(Title.newpwd, "",25, TextField.ANY|TextField.PASSWORD);
        this.append(name);
        this.append(oldpwd);
        this.append(newpwd);
        this.addCommand(backCommand);
        this.addCommand(changeCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.CHANGE_PWD_BACKTO_MAINMENU);
        }
        else if (cmd == changeCommand){
            Alert alert = new Alert("警告");
            String uid = name.getString();
            String oldpwd1 = oldpwd.getString();
            //System.out.println(oldpwd1);
            String newpwd1 = newpwd.getString();
            //System.out.println(newpwd1);
            if(uid.equals("")|| uid == null){
                alert.setString("读者条码不能为空！");
                alert.setTimeout(2000);
                alert.setType(AlertType.ALARM);
                uicontroler.libmidlet.setCurrent(alert, this);
            }else if(oldpwd1.equals("") || oldpwd1 == null){
                alert.setString("原密码不能为空！");
                alert.setTimeout(2000);
                alert.setType(AlertType.ALARM);
                uicontroler.libmidlet.setCurrent(alert, this);
            }else if(newpwd1.equals("") || newpwd1 == null){
                alert.setString("新密码不能为空！");
                alert.setTimeout(2000);
                alert.setType(AlertType.ALARM);
                uicontroler.libmidlet.setCurrent(alert, this);
            }else {
                uicontroler.handleEvent(UIControler.EventID.CHANGE_PWD, new Object[]{uid,oldpwd1,newpwd1});
            }
            //System.out.println(uid);
            
            
        }
    }
    
}
