/*
 * ProgressUI.java
 *
 * Created on 2006年5月22日, 下午8:54
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;
import cn.edu.uestc.pandawireless.model.*;
/**
 *
 * @author pandaxiaoxi
 */
public class ProgressUI extends Form implements CommandListener{
    private HttpHandler httphandler = null;
    private static Command exitCommand = new Command("返回",Command.BACK, 1);
    /** Creates a new instance of ProgressUI */
    public ProgressUI(String title,HttpHandler httphandler) {
        super(title);
        this.httphandler = httphandler;
        this.append("正在连接服务器,请稍候...");
        this.addCommand(exitCommand);
        this.setCommandListener(this);
    }
    
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == exitCommand){
            httphandler.stop();
        }
    }
    
}
