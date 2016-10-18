/*
 * UIControler.java
 *
 * Created on 2006年5月11日, 下午12:20
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;

import cn.edu.uestc.pandawireless.LibMidlet;
import cn.edu.uestc.pandawireless.model.HttpHandler;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.IOException;
import java.io.*;
import java.lang.Thread;
import cn.edu.uestc.pandawireless.ui.table.*;


/**
 *
 * @author 熊猫晓希
 */
public class UIControler {
    public LibMidlet libmidlet;
    private HelpUI helpUI;
    private WelcomeUI welcomeUI;
    private MainMenuUI mainmenuUI;
    private LoginUI loginUI;
    private ChangePwdUI changepwdUI;
    private SearchBookUI searchUI;
    public ProgressUI progressUI;  //为了简单起见，设置成public
    private NoBookUI nobookUI;
    //private SearchResultUI searchresultUI;
    //private Table table;  //空Table,具体可用setTableModel方法给Table设置model
    //private LoginSuccessUI lsUI;
    private BorrowDetailUI bdUI;
    private EachBookDetailUI ebdUI;
    
    private HttpHandler httphandler = null;
    
    private Alert errorUI = null;
    private Alert successUI = null;
    public BorrowDetailUI borrowui = null;
    
    public static class EventID{
        private EventID(){
        }
        public static final byte SHOW_LOGIN = 100;
        public static final byte SHOW_CHANGE_PWD = 102;
        public static final byte SHOW_SEARCH_BOOK = 101;
        public static final byte SHOW_HELP = 103;
        
        public static final byte HELP_BACKTO_MAINMENU = 1;
        public static final byte EXIT = 2;
        public static final byte LOGIN_BACKTO_MAINNENU = 3;
        public static final byte LOGIN = 4;
        public static final byte CHANGE_PWD = 5;
        public static final byte SEARCH_BOOK = 6;
        public static final byte CHANGE_PWD_BACKTO_MAINMENU = 7;
        public static final byte SEARCH_BOOK_BACKTO_MAINMENU = 8;
        public static final byte LOGIN_SUCCESS_NO_BOOK = 9;
        public static final byte LOGIN_SUCCESS_HAS_BOOK = 10;
        public static final byte SHOW_SEARCH_RESULT = 11;
        public static final byte SHOW_USER_BOOK = 12;
        public static final byte SHOW_EACH_BOOK = 13;
        
    }
    
    /** Creates a new instance of UIControler */
    public UIControler(LibMidlet midlet) {
        this.libmidlet = midlet;
    }
    public void initUpdate(){
        welcomeUI = new WelcomeUI();
        mainmenuUI = new MainMenuUI(this);
        helpUI = new HelpUI(this);
        loginUI = new LoginUI(this);
        changepwdUI = new ChangePwdUI(this);
        searchUI = new SearchBookUI(this);
        httphandler = new HttpHandler(this);
        //httphandler.setURL("http://202.115.6.139:8080/LibServer/action");
        httphandler.setURL("http://mlib.xicp.net:8080/LibServer/action");
        httphandler.start();
        errorUI = new Alert("错误信息"); 
        successUI = new Alert("成功信息");
        progressUI = new ProgressUI("进程信息", httphandler);
        nobookUI = new NoBookUI(this);
        //searchresultUI = new SearchResultUI(this);
        //table = new Table();
        //lsUI = new LoginSuccessUI(this);
        //bdUI = new BorrowDetailUI(this, data)
        displayWelcome();
    }
    public void displayWelcome(){
        libmidlet.setCurrent(welcomeUI);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            
        }
        displayMainMenu();        
    }
    public void displayError(String message){
        errorUI.setType(AlertType.ERROR);
        errorUI.setString(message);
        errorUI.setTimeout(3000);
        libmidlet.setCurrent(errorUI, changepwdUI);
    }
    public void displayErrorforLogin(String message){
        errorUI.setType(AlertType.ERROR);
        errorUI.setString(message);
        errorUI.setTimeout(3000);
        libmidlet.setCurrent(errorUI, loginUI);
    }
    public void displayErrorforSearch(String message){
        errorUI.setType(AlertType.ERROR);
        errorUI.setString(message);
        errorUI.setTimeout(3000);
        libmidlet.setCurrent(errorUI, searchUI);
    }
    public void displaySuccess(String message){
        successUI.setType(AlertType.CONFIRMATION);
        successUI.setString(message);
        successUI.setTimeout(3000);
        libmidlet.setCurrent(successUI, mainmenuUI);
    }
    public void displayProgress(){
        progressUI.deleteAll();
        progressUI.append("正在连接服务器,请稍候...");
        libmidlet.setCurrent(progressUI);
    }
    private void displayMainMenu(){
        libmidlet.setCurrent(mainmenuUI);
    }
//    public void setTableModel(TableModel model){
//        table.setTableModel(model);
//    }
//    public void setSearchResultTable(Scrollable c){
//        searchresultUI.setScrollable(c);
//    }
    
    public void handleEvent(int event){
        switch(event){
            case EventID.SHOW_HELP:
            {
                libmidlet.setCurrent(helpUI);
                break;
            }
            case EventID.HELP_BACKTO_MAINMENU:
            {
                displayMainMenu();
                break;
            }
            case EventID.LOGIN_SUCCESS_NO_BOOK:
            {
                libmidlet.setCurrent(nobookUI);
                break;
            }
            case EventID.LOGIN_SUCCESS_HAS_BOOK:
            {
                break;
            }
            case EventID.LOGIN_BACKTO_MAINNENU:
            {
                displayMainMenu();
                break;
            }
            case EventID.SHOW_LOGIN:
            {
                libmidlet.setCurrent(loginUI);
                break;
            }
            case EventID.SHOW_CHANGE_PWD:
            {
                libmidlet.setCurrent(changepwdUI);
                break;
            }
            case EventID.SHOW_SEARCH_BOOK:
            {
                libmidlet.setCurrent(searchUI);
                break;
            }
            case EventID.SHOW_SEARCH_RESULT:
            {
//                this.setSearchResultTable(table);
//                libmidlet.setCurrent(searchresultUI);
                break;
            }
            case EventID.SEARCH_BOOK_BACKTO_MAINMENU:
            {
                displayMainMenu();
                break;
            }
            case EventID.CHANGE_PWD_BACKTO_MAINMENU:
            {
                displayMainMenu();
                break;
            }
            case EventID.EXIT:
            {
                libmidlet.exit();
                break;
            }
         
        }
    }
//    public void handleEvent(int event,Table table){
//        switch(event){
//            case EventID.SHOW_USER_BOOKS:
//            {
//                lsUI.setScrollable(table);
//                this.libmidlet.setCurrent(lsUI);
//                break;
//            }
//        }
//    }
    public void handleEvent(int event,final Object[] objs){
        //HttpConnection conn;
        switch(event){
            case EventID.CHANGE_PWD:
            {

               httphandler.setOP(OperateConstants.CHANGE_PWD);
               httphandler.setObjects(objs);
               synchronized(this){
                notify();
               }
               break;
            }
            case EventID.LOGIN:
            {
                httphandler.setOP(OperateConstants.LOGIN);
                httphandler.setObjects(objs);
                synchronized(this){
                    notify();
                }
                break;
            }
            case EventID.SEARCH_BOOK:
            {
                httphandler.setOP(OperateConstants.SEARCH_BOOK);
                httphandler.setObjects(objs);
                synchronized(this){
                notify();
               }
               break;
            }
            case EventID.SHOW_USER_BOOK:
            {
                bdUI = new BorrowDetailUI(this,(String[])objs, httphandler);
                libmidlet.setCurrent(bdUI);
                break;
            }
            case EventID.SHOW_EACH_BOOK:
            {
                ebdUI = new EachBookDetailUI(this,(String[])objs, httphandler);
                libmidlet.setCurrent(ebdUI);
                break;
            }
        }
    }
    
    
}
