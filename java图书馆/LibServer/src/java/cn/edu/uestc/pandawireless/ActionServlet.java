/*
 * ActionServlet.java
 *
 * Created on 2006年5月17日, 下午4:56
 */

package cn.edu.uestc.pandawireless;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 熊猫晓希
 * @version
 */
public class ActionServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/octet-stream;charset=UTF-8");
        DataInputStream dis = new DataInputStream(request.getInputStream());
        DataOutputStream result = new DataOutputStream(response.getOutputStream());
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //DataOutputStream dos = new DataOutputStream(baos);
        byte op = dis.readByte();
        //System.out.println(op);
        switch(op)
        {
            case OperateConstants.CHANGE_PWD:
            {
                UserBean user = UserBean.deserialize(dis);
                String newpwd = dis.readUTF();
                UserDBBean userdb = new UserDBBean();
                boolean isExist = userdb.hasUser(user.getUID());
                System.out.println(isExist);
                //System.out.println(isExist);
                if (isExist){
                    UserBean user1 = userdb.getUser(user.getUID());
                    boolean isEqual = user.getPWD().equals(user1.getPWD());
                   // System.out.println(user1.getPWD());
                   // System.out.println(user.getPWD());
                   // System.out.println(isEqual);
                    if(isEqual){
                        //验证通过，可以修改密码
                       // System.out.println(isEqual);
                        user.setPWD(newpwd);
                        boolean isSuccess = userdb.updateUser(user);
                       // System.out.println(isSuccess);
                        if(isSuccess){
                            result.writeByte(OperateConstants.CHANGEPWD_SUCCESS);
                        }else{
                            result.writeByte(OperateConstants.CHANGEPWD_FAIL);
                        }
                        
                    }else{
                        //验证失败
                        result.writeByte(OperateConstants.CHANGEPWD_ERROR_PWD);
                    }
                        
                    
                }else{
                    //用户不存在
                    System.out.println("nouser");
                    result.writeByte(OperateConstants.CHANGEPWD_NO_USER);
                    //result.flush();
                }
                userdb.releaseConn();
                break;
            }
            case OperateConstants.LOGIN:
            {
                UserBean user = UserBean.deserialize(dis);
                UserDBBean userdb = new UserDBBean();
                BookDBBean bookdb = new BookDBBean();
                boolean isExist = userdb.hasUser(user.getUID());
                //System.out.println(isExist);
                if (isExist){
                    UserBean user1 = userdb.getUser(user.getUID());
                    boolean isEqual = user.getPWD().equals(user1.getPWD());
                   // System.out.println(user1.getPWD());
                   // System.out.println(user.getPWD());
                   // System.out.println(isEqual);
                    if(isEqual){
                        //验证通过，可以登陆
                       // System.out.println(isEqual);
                        //result.writeByte(OperateConstants.LOGIN_SUCCESS);
                        boolean hasBookforUser = bookdb.hasBookforUser(user.getUID());
                        if(hasBookforUser){
                            //有书
                            result.writeByte(OperateConstants.LOGIN_SUCCESS_HAS_BOOK);
                            List booksforuser = bookdb.getBooksforUser(user.getUID());
                            int size = booksforuser.size();
                            result.writeInt(size);
                            BorrowBean borrow = null;
                            for(int j=0;j<size;j++){
                                borrow = (BorrowBean)booksforuser.get(j);
                                borrow.serialize(result);
                            }
                        }else{
                            //无书
                            result.writeByte(OperateConstants.LOGIN_SUCCESS_NO_BOOK);
                        }
                        
                        
                    }else{
                        //验证失败
                        result.writeByte(OperateConstants.LOGIN_ERROR_PWD);
                    }
                        
                    
                }else{
                    //用户不存在
                    result.writeByte(OperateConstants.LOGIN_NO_USER);
                    //result.flush();
                }
                break;
            }
            case OperateConstants.SEARCH_BOOK:
            {
                String bookname = dis.readUTF();
                System.out.println(bookname);
                BookDBBean bookdb = new BookDBBean();
                boolean hasBook = bookdb.hasBook(bookname);
                if(hasBook){
                    result.writeByte(OperateConstants.SEARCH_HAS_BOOK);
                    List books = bookdb.getBooks(bookname);
                    result.writeInt(books.size());
                    BookBean book = null;
                    
                    for(int i=0;i<books.size();i++){
                        book = (BookBean)books.get(i);
                        book.serialize(result);
                    }
                }else{
                    result.writeByte(OperateConstants.SEARCH_NO_BOOK);
                }
                bookdb.releaseConn();
                break;
            }
        }
        int size = result.size();
        response.setContentLength(size);
        result.flush();
        result.close();
        
        
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
