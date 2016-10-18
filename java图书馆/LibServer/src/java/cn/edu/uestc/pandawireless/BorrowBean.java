/*
 * BorrowBean.java
 *
 * Created on 2006年5月24日, 上午12:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless;
import java.io.*;

/**
 *
 * @author pandaxiaoxi
 */
public class BorrowBean {
    private String bookname;
    private String indexid;
    private String returndate;
    
    /** Creates a new instance of BorrowBean */
    public BorrowBean() {
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIndexid() {
        return indexid;
    }

    public void setIndexid(String indexid) {
        this.indexid = indexid;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
    public void serialize(DataOutputStream dos){
        try{
            dos.writeUTF(this.bookname);
            dos.writeUTF(this.indexid);
            dos.writeUTF(this.returndate);
         }catch(IOException ex){
            
        }
    }
    public static BorrowBean deserialize(DataInputStream dis){
        BorrowBean borrow = new BorrowBean();
        try{
            borrow.bookname = dis.readUTF();
            borrow.indexid = dis.readUTF();
            borrow.returndate = dis.readUTF();
        }catch(IOException ex){
            
        }
        return borrow;
    }
    
}
