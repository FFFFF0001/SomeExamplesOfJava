/*
 * SearchBookUI.java
 *
 * Created on 2006��5��13��, ����4:36
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.*;


/**
 *
 * @author ��è��ϣ
 */
public class SearchBookUI extends Form implements CommandListener{
    private UIControler uicontroler;
    
    private static final Command backCommand = new Command("����",Command.BACK,2);
    private static final Command searchCommand = new Command("��ʼ��ѯ",Command.OK,1);
    
    private TextField condition = null;
    /** Creates a new instance of SearchBookUI */
    public SearchBookUI(UIControler uicontroler) {
        super(Title.searchTitle);
        this.uicontroler = uicontroler;
        condition = new TextField(Title.condition, "", 30, TextField.ANY);
        this.append(condition);
        this.addCommand(backCommand);
        this.addCommand(searchCommand);
        this.setCommandListener(this);
    }
    
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.handleEvent(UIControler.EventID.SEARCH_BOOK_BACKTO_MAINMENU);
        }
        else if(cmd == searchCommand){
            String cond = condition.getString();
            uicontroler.handleEvent(UIControler.EventID.SEARCH_BOOK, new Object[]{cond});
        }
    }
    
}
