/*
 * UserBean.java
 *
 * Created on 2006年5月17日, 下午5:19
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 *
 * @author 熊猫晓希
 */
public class UserBean {
    private String uid;
    private String pwd;
    /** Creates a new instance of UserBean */
    public UserBean() {
    }
    public void setUID(String uid)
    {
        this.uid = uid;
    }
    public String getUID()
    {
        return uid;
    }
    public void setPWD(String pwd)
    {
        this.pwd = pwd;
    }
    public String getPWD()
    {
        return pwd;
    }
    public void serialize(DataOutputStream dos)
    {
        try{
            dos.writeUTF(uid);
            dos.writeUTF(pwd);
        }catch(IOException ex){
            
        }
        
        
    }
    public static UserBean deserialize(DataInputStream dis)
    {
        UserBean user = new UserBean();
        try{
            user.uid = dis.readUTF();
            user.pwd = dis.readUTF();
        }catch(IOException ex){
            
        }
        return user;
        
    }
    
    
}
