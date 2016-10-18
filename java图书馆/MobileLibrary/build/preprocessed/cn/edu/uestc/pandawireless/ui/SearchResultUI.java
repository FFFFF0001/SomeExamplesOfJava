/*
 * SearchResultUI.java
 *
 * Created on 2006��5��25��, ����12:35
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import cn.edu.uestc.pandawireless.ui.table.*;
import javax.microedition.lcdui.*;
/**
 *
 * @author pandaxiaoxi
 */
public class SearchResultUI extends ScrollPane implements CommandListener{
    private UIControler uicontroler;
    
    private static final Command backCommand = new Command("����",Command.BACK,2);
    private static final Command selectCommand = new Command("ѡ��",Command.OK, 1);
    
    /** Creates a new instance of SearchResultUI */
    public SearchResultUI(UIControler uicontroler,Scrollable c){
        super(c);
        this.uicontroler = uicontroler;
        this.addCommand(backCommand);
        this.addCommand(selectCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.SHOW_SEARCH_BOOK);
        }else if(cmd == selectCommand){
            //��ϸ��ʾ��ǰѡ�е��鼮����Ϣ
            String[] borrow = this.getScrollable().getTableModel().getValueAt(this.getScrollable().getCurrentRow()-1);
            uicontroler.handleEvent(UIControler.EventID.SHOW_EACH_BOOK,borrow);
        }
        
    }
    
}
