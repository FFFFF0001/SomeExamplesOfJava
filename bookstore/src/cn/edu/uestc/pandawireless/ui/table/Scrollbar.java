/*
 * Scrollbar.java
 *
 * Created on 2006年5月23日, 上午12:25
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui.table;

import javax.microedition.lcdui.*;

/**
 * <P>The <CODE>Scrollbar</CODE> class is used as an indicator.
 *
 * @author Darryl L. Pierce,pandaxiaoxi
 * @version $Revision: 2.0 $
 */
public class Scrollbar extends Canvas {
    public  static final int SCROLLBAR_VERTICAL   =  0;
    public  static final int SCROLLBAR_HORIZONTAL =  1;
    private static final int SCROLLBAR_WIDTH      = 10;

    private int which;
    private int ceiling;
    private int current;

    /**
     * <P>Creates an instance with the specified orientation. The range of the scrollbar is a positive
     * value from zero (0) through <CODE>ceiling</CODE>.
     *
     * @param which either <CODE>Scrollbar.SCROLLBAR_HORIZONTAL</CODE> or <CODE>Scrollbar.SCROLLBAR_VERTICAL</CODE>
     * @param max the maximum value
     */
    public Scrollbar(int which,int ceiling) {
	if(which == SCROLLBAR_VERTICAL || which == SCROLLBAR_HORIZONTAL) {
	    this.which   = which;
	    this.ceiling = ceiling;
	} else {
	    throw new IllegalArgumentException("Invalid orientation");
	}
    }

    /**
     * <P>Sets the current value for the scrollbar. The value must be within the range of zero (0)
     * through the ceiling value used to create the scrollbar.
     *
     * @param current the current value
     */
    public void setCurrent(int current) {
	if(current >= 0 && current <= ceiling) {
	    this.current = current;
	}
    }

    public void paint(Graphics g) {
	int x = 0,y = 0,w = 0,h = 0;
	int width = getWidth(), height = getHeight(); // grab the display size once

	// create the indicator track
	switch(which) {
	case SCROLLBAR_VERTICAL:
	    x = width - (SCROLLBAR_WIDTH + 1);
	    y = 0;
	    w = SCROLLBAR_WIDTH;
	    h = height - (SCROLLBAR_WIDTH + 1);
	    break;
	case SCROLLBAR_HORIZONTAL:
	    x = 0;
	    y = height - (SCROLLBAR_WIDTH + 1);
	    w = width - (SCROLLBAR_WIDTH + 1);
	    h = SCROLLBAR_WIDTH;
	    break;
	}

	g.setColor(255,255,255);
	g.fillRect(x,y,w,h);
	g.setColor(0,0,0);
	g.drawRect(x,y,w,h);

	// create the position indicator
	w = h = SCROLLBAR_WIDTH - 4; // the indicator is a square
	int position = (current * 100) / ceiling; // the percentage

	switch(which) {
	case SCROLLBAR_VERTICAL:
	    x = (width - (SCROLLBAR_WIDTH + 1)) + 2;
	    y = (position * (height - (SCROLLBAR_WIDTH + 1))) / 100;
	    if(y < 2) y = 2;
	    break;
	case SCROLLBAR_HORIZONTAL:
	    x = (position * (width - (SCROLLBAR_WIDTH + 1))) / 100;
	    y = (height - (SCROLLBAR_WIDTH + 1)) + 2;
	    if(x < 2) x = 2;
	    break;
	}
	g.fillRect(x,y,w,h);
    }
}
