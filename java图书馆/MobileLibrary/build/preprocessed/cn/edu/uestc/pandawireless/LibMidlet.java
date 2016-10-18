/*
 * LibMidlet.java
 *
 * Created on 2006��5��11��, ����12:05
 */

package cn.edu.uestc.pandawireless;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import cn.edu.uestc.pandawireless.ui.*;
import cn.edu.uestc.pandawireless.model.*;
import java.util.Date;

/**
 *
 * @author  ��è��ϣ
 * @version
 */
public class LibMidlet extends MIDlet implements CommandListener{
    private Display display = null;
    private UIControler uicontroler;
    public AlarmModel model = null;
    public static final Command stopCommand = new Command("ֹͣ",Command.EXIT, 1);
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
            //��PUSHע�ỽ��MIDlet����£���ʾ������
            uicontroler.initUpdate();
            //return;
        }else{
        while(i<strs.length)
        {
            
            int index = strs[i].indexOf('n');
            String snow = strs[i].substring(0, index);
            String bookname = strs[i].substring(index+1);
            //System.out.println("snow:"+snow);
            if(snow != null){//RMS�д�����ǰע���ʱ��
                       
            //System.out.println("tnow:"+s);
            //�жϵ�ǰʱ���Ƿ���ע��ʱ����ͬ
            if(snow.equals(ss)){
                //��ȣ�����ʱPUSHע�ỽ����MIDlet
                Form f = new Form("����");
                //f.setString(" �ϴ󣬸û����ˣ�Ҫ��Ȼ��Ҫ����Ǯ�ˣ�\n�����㣺����Ȿ<<"+bookname+">>�û��ˣ���");
                f.append(" �ϴ󣬸û����ˣ�Ҫ��Ȼ��Ҫ����Ǯ�ˣ�\n�����㣺����Ȿ<<"+bookname+">>�û��ˣ���");
                f.addCommand(stopCommand);
                f.setCommandListener(this);
                //��������
                model.playSound();
                display.setCurrent(f);
                //return;
                break;
            }
        }
            i++;
        }
        if(i >= strs.length){
            //��PUSHע�ỽ��MIDlet����£���ʾ������
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
