/*
 * MainMenuUI.java
 *
 * Created on 2006年5月11日, 下午10:32
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
public class MainMenuUI extends List implements CommandListener {
    private UIControler uicontroler;
    
    private static final Command exitCommand = new Command("退出",Command.EXIT,2);
    private static final Command selectCommand = new Command("选择",Command.OK,1);
    
    /** Creates a new instance of MainMenuUI */
    public MainMenuUI(UIControler uicontroler) {
        super(Title.menuTitle,List.IMPLICIT,Title.menu, null);
        this.uicontroler = uicontroler;
        this.addCommand(selectCommand);
        this.addCommand(exitCommand);
        this.setSelectCommand(selectCommand);  //该函数只有MIDP2.0才有，所以在MIDP1.0的设备上无法使用
        this.setCommandListener(this);
    }
    
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == exitCommand){
            uicontroler.handleEvent(UIControler.EventID.EXIT);
        }else if(cmd == selectCommand){
            int selectedIndex = this.getSelectedIndex();
            switch(selectedIndex+100){
                case UIControler.EventID.SHOW_LOGIN:
                {
                    uicontroler.handleEvent(UIControler.EventID.SHOW_LOGIN);
                    break;
                }
                case UIControler.EventID.SHOW_CHANGE_PWD:
                {
                    uicontroler.handleEvent(UIControler.EventID.SHOW_CHANGE_PWD);
                    break;
                }
                case UIControler.EventID.SHOW_SEARCH_BOOK:
                {
                    uicontroler.handleEvent(UIControler.EventID.SHOW_SEARCH_BOOK);
                    break;
                }
                case UIControler.EventID.SHOW_HELP:
                {
                    uicontroler.handleEvent(UIControler.EventID.SHOW_HELP);
                    break;
                }
            }
            
        }
    }
    
}
