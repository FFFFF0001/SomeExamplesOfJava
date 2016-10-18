/*
 * WelcomeUI.java
 *
 * Created on 2006年5月11日, 下午10:32
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.*;
import java.io.IOException;

/**
 *
 * @author 熊猫晓希
 */
public class WelcomeUI extends Canvas {
    private int width = 0;
    private int height = 0;
    private Image image = null;
    
    /** Creates a new instance of WelcomeUI */
    public WelcomeUI() {
        width = this.getWidth();
        height = this.getHeight();
        image = getImage("/image/baby.png");
        image = scale(image, width, height);
        //repaint();
    }
    private Image getImage(String path){
        Image image = null;
        //Image img = null;
        try{
            image = Image.createImage(path);
            //image = zoomInImage(image);
            return image;
            //image = Image.createImage(image, Graphics.LEFT,Graphics.TOP, this.width,this.height,Sprite.TRANS_NONE); //这条语句出现IO异常
        }catch (IOException e){
            image = null;
            return image;
        }
        
    }
    public Image scale (Image srcImage, int newW, int newH) {//newW,newH就是实际需要缩放到的尺寸
   
      int srcW = srcImage.getWidth();  
      int srcH = srcImage.getHeight();
      //先做水平方向上的伸缩变换
      Image tmp = Image.createImage(newW, srcH);
      Graphics g = tmp.getGraphics();
      for (int x = 0; x < newW; x++) {
           g.setClip(x, 0, 1, srcH);
           //按比例放缩
           g.drawImage(srcImage, x-x * srcW / newW, 0, Graphics.LEFT | Graphics.TOP);
      }
     
      //再做垂直方向上的伸缩变换
      Image dst = Image.createImage(newW, newH);
      g = dst.getGraphics();
      for (int y = 0; y < newH; y++) {
         g.setClip(0, y, newW, 1);
         //按比例放缩
         g.drawImage(tmp, 0,y- y * srcH / newH, Graphics.LEFT | Graphics.TOP);
      }
    return dst;
    }
    
    private  Image zoomInImage(Image img) {

		int[] rgbOutput = null;
		int width0 = 0, height0 = 0;
		int[] rgbInput = null;
		try {

			width0 = img.getWidth();
			height0 = img.getHeight();
			rgbInput = new int[width0 * height0];
			rgbOutput = new int[(this.width) * (this.height)];
			img.getRGB(rgbInput, 0, width0, 0, 0, width0, height0);
			int i, j, k;
			k = 0;
			for (i = 0; i < (height0 << 1); i += 2) {
				for (j = 0; j < (width0 << 1); j += 2) {
					if (j < 3) {
						// System.out.println(i * (width << 1) + j);
						// System.out.println((i + 1) * (width << 1) + j);
						// System.out.println(i * (width << 1) + j + 1);
						// System.out.println((i + 1) * (width << 1) + j + 1);
					}
					rgbOutput[i * (width0 << 1) + j] = rgbInput[k];
					rgbOutput[(i + 1) * (width0 << 1) + j] = rgbInput[k];
					rgbOutput[i * (width0 << 1) + j + 1] = rgbInput[k];
					rgbOutput[(i + 1) * (width0 << 1) + j + 1] = rgbInput[k];
					k++;
				}
			}
			return Image.createRGBImage(rgbOutput, this.width, this.height,
					true);
		} catch (OutOfMemoryError e) {
			 e.printStackTrace();
			//ImageAlbum.showAlert("图像尺寸太大,不能完成此操作.");
			return img;
		} finally {
			rgbOutput = null;
			rgbInput = null;
		}

	}
    
    protected void paint(Graphics graphics){
        if (image != null)
            graphics.drawImage(image,(width-image.getWidth())/2,(height-image.getHeight())/2, Graphics.TOP|Graphics.LEFT);
        else
            graphics.drawString("sdfasf",0,0,Graphics.TOP|Graphics.LEFT  );
    }
    
}
