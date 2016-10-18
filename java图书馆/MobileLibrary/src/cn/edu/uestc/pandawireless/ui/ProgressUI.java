/*
 * ProgressUI.java
 *
 * Created on 2006��5��22��, ����8:54
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
    private static Command exitCommand = new Command("����",Command.BACK, 1);
    /** Creates a new instance of ProgressUI */
    public ProgressUI(String title,HttpHandler httphandler) {
        super(title);
        this.httphandler = httphandler;
        this.append("�������ӷ�����,���Ժ�...");
        this.addCommand(exitCommand);
        this.setCommandListener(this);
    }
    
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == exitCommand){
            httphandler.stop();
        }
    }
    
}
