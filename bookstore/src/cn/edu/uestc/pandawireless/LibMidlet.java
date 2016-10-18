/*
 * LibMidlet.java
 *
 * Created on 2006年5月11日, 下午12:05
 */

package cn.edu.uestc.pandawireless;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import cn.edu.uestc.pandawireless.ui.*;
import cn.edu.uestc.pandawireless.model.*;
import java.util.Date;

/**
 *
 * @author  熊猫晓希
 * @version
 */
public class LibMidlet extends MIDlet implements CommandListener{
    private Display display = null;
    private UIControler uicontroler;
    public AlarmModel model = null;
    public static final Command stopCommand = new Command("停止",Command.EXIT, 1);
    public LibMidlet(){
        display = Display.getDisplay(this);
        uicontroler = new UIControler(this);
        model = new AlarmModel();
    }
    public void startApp() {
        //AlarmModel model = new AlarmModel();
        String[] strs = model.readStrings();
        int i = 0;
        Date tnow = new Date();
        String ss = AlarmModel.getString(tnow);
        if(strs == null){
            //非PUSH注册唤醒MIDlet情况下，显示主界面
            uicontroler.initUpdate();
            //return;
        }else{
        while(i<strs.length)
        {
            
            int index = strs[i].indexOf('n');
            String snow = strs[i].substring(0, index);
            String bookname = strs[i].substring(index+1);
            //System.out.println("snow:"+snow);
            if(snow != null){//RMS中存在以前注册的时间
                       
            //System.out.println("tnow:"+s);
            //判断当前时间是否与注册时间相同
            if(snow.equals(ss)){
                //相等，代表时PUSH注册唤醒了MIDlet
                Form f = new Form("提醒");
                //f.setString(" 老大，该还书了，要不然又要被罚钱了！\n提醒你：你的这本<<"+bookname+">>该还了！！");
                f.append(" 老大，该还书了，要不然又要被罚钱了！\n提醒你：你的这本<<"+bookname+">>该还了！！");
                f.addCommand(stopCommand);
                f.setCommandListener(this);
                //播放声音
                model.playSound();
                display.setCurrent(f);
                //return;
                break;
            }
        }
            i++;
        }
        if(i >= strs.length){
            //非PUSH注册唤醒MIDlet情况下，显示主界面
            uicontroler.initUpdate();
        }
        
        }
        
        
        
    
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public void exit(){
        this.destroyApp(false);
        this.notifyDestroyed();
    }
    public void commandAction(Command cmd,Displayable displayable){
       
       if(cmd == stopCommand){
           model.stopSound();
           uicontroler.initUpdate();
        }
    }
    public void setCurrent(Displayable displayable){
        display.setCurrent(displayable);
    }
    public void setCurrent(Alert alert,Displayable displayable){
        display.setCurrent(alert,displayable);
    }
    
}
