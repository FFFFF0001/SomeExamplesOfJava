

package cn.model;


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
   
    public void writeString(Date date,String bookname){
       
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
    //�ر�rs
    public void closeRecordStore(){
        if(rs != null){
            try{
                rs.closeRecordStore();
            }catch(RecordStoreException ex){
               //�����쳣 
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
                //������������������������
                player = Manager.createPlayer(is, "audio/midi");
                player.setLoopCount(-1);//���޴�ѭ������
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
                //ֹͣ������
                player.stop();
                player = null;
            }catch(MediaException me){
                //�����쳣
            } 
        }
    }
    //��Dateת��ΪString����ȷ������
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
