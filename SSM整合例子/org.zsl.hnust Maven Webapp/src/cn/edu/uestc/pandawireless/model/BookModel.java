/*
 * BookModel.java
 *
 * Created on 2006年5月23日, 上午12:34
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.model;
import cn.edu.uestc.pandawireless.ui.table.*;
import java.util.*;
import cn.edu.uestc.pandawireless.ui.Title;


/**
 *
 * @author pandaxiaoxi
 */
public class BookModel implements TableModel{
    private Vector books = null;
    
    /** Creates a new instance of BookModel */
    public BookModel(Vector books) {
        this.books = books;
    }
    public int getColumnCount(){
        String[] book = (String[])books.elementAt(0);
        return book.length;
    }

    
    public int getRowCount(){
        return books.size();
    }

    
    public String getColumnHeaderText(int col){
        
        switch(col){
            case 0:
            {
                return Title.bookname;
                
            }
            case 1:
            {
                return Title.indexid;
                
            }
            case 2:
            {
                return Title.state;
                
            }
            case 3:
            {
                return Title.returndate;
                
            }
            case 4:
            {
                return Title.bookauthor;
                
            }
            case 5:
            {
                return Title.publisher;
            }
            case 6:
            {
                return Title.pubdate;
            }
            default:
            {
                return "";
            }
        }
    }

    
    public boolean hasColumnHeaders(){
        return true;
    }

    
    public String getValueAt(int row,int col){
        String[] book = (String[])books.elementAt(row);
        return book[col];
    }
    public String[] getValueAt(int row){
        String[] book = (String[])books.elementAt(row);
        return book;
    }
    
}
