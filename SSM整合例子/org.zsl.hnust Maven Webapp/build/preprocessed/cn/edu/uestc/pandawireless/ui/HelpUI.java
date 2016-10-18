/*
 * HelpUI.java
 *
 * Created on 2006年5月11日, 下午12:07
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author 熊猫晓希
 */
public class HelpUI extends Form implements CommandListener {
    private UIControler uicontroler;
    
    private static final Command backCommand = new Command("返回",Command.BACK,2);
    
    /** Creates a new instance of HelpUI */
    public HelpUI(UIControler uicontroler) {
        super(Title.helpTitle);
        this.uicontroler = uicontroler;
        Image image = buildImage("/image/baby.png");
        this.append(image);
        String text = "";
        try{
             text = getText("/text/welcome.txt");
        }catch (IOException ex){
             text = "读取文本出错";
        }
        this.append("\n"+text);
        this.addCommand(backCommand);
        this.setCommandListener(this);
        
    }
    
     private Image buildImage(String path){
        Image image = null;
        try{
            image = Image.createImage(path);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return image;
    }
     /** read help-text from .txt file*/
    private String getText(String path) throws IOException{
        InputStream is = getClass().getResourceAsStream(path);
        if(is != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch = 0;
            while((ch = is.read()) != -1){
                baos.write(ch);
            }
            byte[] text = baos.toByteArray();
            baos.close();
            return new String(text,"UTF-8");
        }else{
            return "无法读取文本";
        }
            
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.HELP_BACKTO_MAINMENU);
        }
        
    }
    
}
