/*
 * BookBean.java
 *
 * Created on 2006年5月20日, 下午6:38
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless;
import java.util.Date;
import java.io.*;
/**
 *
 * @author 熊猫晓希
 */
public class BookBean {
    private String indexid ;
    private String bookname;
    private String author;
    private String publisher;
    private String pubdate;
    private String state;
    private String returndate;
    
    /** Creates a new instance of BookBean */
    public BookBean() {
    }

    public String getIndexid() {
        return indexid;
    }

    public void setIndexid(String indexid) {
        this.indexid = indexid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
            dos.writeUTF(this.state);
            dos.writeUTF(this.returndate);
            dos.writeUTF(this.author);
            dos.writeUTF(this.publisher);
            dos.writeUTF(this.pubdate);
            
            
        }catch(IOException ex){
            
        }
    }
    public static BookBean deserialize(DataInputStream dis){
        BookBean book = new BookBean();
        try{
            book.bookname = dis.readUTF();
            book.indexid = dis.readUTF();
            book.state = dis.readUTF();
            book.returndate = dis.readUTF();
            book.author = dis.readUTF();
            book.publisher = dis.readUTF();
            book.pubdate = dis.readUTF();
            
            
        }catch(IOException ex){
            
        }
        return book;
    }
    
}
