/*
 * OperateConstants.java
 *
 * Created on 2006年5月17日, 下午8:36
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless;

/**
 *
 * @author 熊猫晓希
 */
public class OperateConstants {
    
    /** Creates a new instance of OperateConstants */
    private OperateConstants() {
    }
    //请求动作
    public static final byte CHANGE_PWD = 1;
    public static final byte SEARCH_BOOK = 2;
    public static final byte LOGIN = 3;
    
    //响应
    public static final byte CHANGEPWD_NO_USER = 100;
    public static final byte CHANGEPWD_ERROR_PWD = 101;
    public static final byte CHANGEPWD_SUCCESS = 102;
    public static final byte CHANGEPWD_FAIL = 103;
    //public static final byte LOGIN_SUCCESS = 104;
    public static final byte LOGIN_ERROR_PWD = 105;
    public static final byte LOGIN_NO_USER = 111;
    public static final byte LOGIN_SUCCESS_NO_BOOK = 109;
    public static final byte LOGIN_SUCCESS_HAS_BOOK = 110;
    public static final byte SEARCH_FAIL = 106;
    public static final byte SEARCH_NO_BOOK = 107;
    public static final byte SEARCH_HAS_BOOK = 108;
    
}
