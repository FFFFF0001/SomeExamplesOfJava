/*
 * AlarmModel.java
 *
 * Created on 2006年7月8日, 下午6:19
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.model;


import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.*;
/**
 *
 * @author 熊猫晓希
 */
public class AlarmModel {
    
    private RecordStore rs = null;
    private Player player = null;
    
    public AlarmModel() {
        try{
           rs = RecordStore.openRecordStore("libalarm",true);
        }catch(RecordStoreException ex){
            ex.printStackTrace();
        }
    }
    //写入记录
    public void writeString(Date date,String bookname){
        //存入RMS中的数据格式 日期n书名
        String s = AlarmModel.getString(date)+"n"+bookname;
        
        try{
            RecordEnumeration re = rs.enumerateRecords(new SearchFilter(bookname),
                    null, false);
            if(re.numRecords() <= 0){
                rs.addRecord(s.getBytes(), 0, s.getBytes().length);
            }else {
                while(re.hasNextElement())
                {
                    rs.setRecord(re.nextRecordId(), s.getBytes(), 0, s.getBytes().length);
                }
            }
            
        }catch(RecordStoreException ex){
            ex.printStackTrace();
        }
    }
    //读取RMS中的记录
    public String[] readStrings(){
        String date = null;
        try{
            RecordEnumeration re = rs.enumerateRecords(null,null,false);
            String[] result = new String[re.numRecords()];
            int i = 0;
            while(re.hasNextElement())
            {
                result[i++] = new String(re.nextRecord());
            }
            return result;
        }catch(RecordStoreException ex){
            return null;
        }
        //return null;
    }
    //关闭rs
    public void closeRecordStore(){
        if(rs != null){
            try{
                rs.closeRecordStore();
            }catch(RecordStoreException ex){
               //处理异常 
            }
        }
    }
    
    public void release(){
        closeRecordStore();
        stopSound();
    }
    
    public void playSound(){
        InputStream is = this.getClass().getResourceAsStream("/audio/pattern.mid");
        if(is != null){
            try{
                //创建播放器，播放闹钟铃声
                player = Manager.createPlayer(is, "audio/midi");
                player.setLoopCount(-1);//无限次循环播放
                player.start();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            catch(MediaException mex){
                mex.printStackTrace();
            }
        }
    }
    
    public void stopSound(){
        if(player != null){
            try{
                //停止播放器
                player.stop();
                player = null;
            }catch(MediaException me){
                //处理异常
            } 
        }
    }
    //将Date转换为String，精确到分钟
    public static String getString(Date d){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int month = calendar.get(Calendar.MONTH)+1;
        String s = ""+calendar.get(Calendar.YEAR)+month+
                calendar.get(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.HOUR_OF_DAY)+
                calendar.get(Calendar.MINUTE);
        return s;
    }
    private class SearchFilter implements RecordFilter
    {
        private String query = null;
        public SearchFilter(String query)
        {
            this.query = query;
        }
        public boolean matches(byte[] data)
        {
            return new String(data).endsWith(query);
        }
    }
 }
