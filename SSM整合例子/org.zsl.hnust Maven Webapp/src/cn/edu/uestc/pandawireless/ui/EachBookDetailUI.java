/*
 * EachBookDetailUI.java
 *
 * Created on 2006年5月26日, 上午12:17
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
 * @author 熊猫晓希
 */
public class EachBookDetailUI extends Form implements CommandListener{
    private UIControler uicontroler;
    private HttpHandler httphandler;
    private static final Command backCommand = new Command("返回",Command.BACK, 2);
    //private static final Command selectCommand = new Command("设置",Command.OK, 1);
    //private DateField datefield;
    
    /** Creates a new instance of BorrowDetailUI */
    public EachBookDetailUI(UIControler uicontroler,String[] data,HttpHandler httphandler) {
        super(Title.eachbookDetailTitle);
        this.uicontroler = uicontroler;
        this.httphandler = httphandler;
        this.append("书名:"+data[0]+"\n");
        this.append("索书号:"+data[1]+"\n");
        this.append("状态:"+data[2]+"\n");
        this.append("应还日期:"+data[3]+"\n");
        
        this.append("作者:"+data[4]+"\n");
        this.append("出版社:"+data[5]+"\n");
        this.append("出版日期:"+data[6]+"\n");
        //this.append("\n\n"+"请输入日期(程序将会在那时提醒你还书)");
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
