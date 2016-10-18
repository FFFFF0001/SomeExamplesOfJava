/*
 * NoBookUI.java
 *
 * Created on 2006年5月24日, 上午12:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;

/**
 *
 * @author pandaxiaoxi
 */
public class NoBookUI extends Form implements CommandListener {
    private UIControler uicontroler;
    private static final Command backCommand = new Command("返回",Command.BACK, 1);
    
    /** Creates a new instance of NoBookUI */
    public NoBookUI(UIControler uicontroler) {
        super("提示信息");
        this.uicontroler = uicontroler;
        this.append("您当前没有借任何书籍！");
        this.addCommand(backCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.HELP_BACKTO_MAINMENU);
        }
        
    }
}
