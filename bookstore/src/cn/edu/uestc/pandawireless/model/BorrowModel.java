/*
 * BorrowModel.java
 *
 * Created on 2006年5月24日, 上午1:32
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
public class BorrowModel implements TableModel{
    private Vector borrows = null;
    
    /** Creates a new instance of BookModel */
    public BorrowModel(Vector borrows) {
        this.borrows = borrows;
    }
    public int getColumnCount(){
        String[] borrow = (String[])borrows.elementAt(0);
        return borrow.length;
    }

    
    public int getRowCount(){
        return borrows.size();
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
                return Title.returndate;
                
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
        String[] borrow = (String[])borrows.elementAt(row);
        return borrow[col];
    }
    public String[] getValueAt(int row){
        String[] borrow = (String[])borrows.elementAt(row);
        return borrow;
    }
    
}
