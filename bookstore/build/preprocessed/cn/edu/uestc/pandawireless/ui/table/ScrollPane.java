/*
 * ScrollPane.java
 *
 * Created on 2006年5月23日, 上午12:23
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui.table;


import javax.microedition.lcdui.*;

/**
 * <P>The <CODE>ScrollPane</CODE> class is a viewport for components which
 * implement the <CODE>Scrollable</CODE> interface. It provides scrollbars
 * that show the user where they are in relation to the full size of the
 * viewed component
 *
 * @see Scrollable
 * @author Darryl L. Pierce,pandaxiaoxi
 * @version $Revision: 2.0 $
 */
public class ScrollPane extends Canvas {
    private static final int SCROLLBAR_WIDTH = 2;
    private static final int INDICATOR_WIDTH = SCROLLBAR_WIDTH * 2;

    private Scrollable content = null   ;
    private int        currentTop  = 0;
    private int        currentLeft = 0;
    
    private int         tRow = 1;
    private int         dRow ;
    private int         currentRow = 1;

    /**
     * <P>Creates an instance containing the supplied component.
     */
    public ScrollPane(Scrollable c) {
        content = c;
        dRow = content.getTotalDisplayRow();
    }
    public ScrollPane(){
        dRow = content.getTotalDisplayRow();
    }
    public void setScrollable(Scrollable c){
        this.content = c;
    }
    public Scrollable getScrollable(){
        return this.content;
    }
    public void paint(Graphics g) {
	int width = getWidth(), height = getHeight();

	// clean the screen area
	g.setColor(255,255,255);
	g.fillRect(0,0,width,height);
	g.setColor(0,0,0);

	// translate the x,y coordinates
	g.translate((0 - currentLeft),(0 - currentTop));
	content.render(g);

	// undo the component translation and render the scrollpane as needed
	g.translate(currentLeft,currentTop);

	// if the content is wider than the display, then create a scrollbar
	if(content.getFullWidth() > width) {
	    Scrollbar hsb = new Scrollbar(Scrollbar.SCROLLBAR_HORIZONTAL,content.getFullWidth());
	    hsb.setCurrent(currentLeft);
	    hsb.paint(g);
	}

	// if the content is taller than the display, then create a scrollbar
	if(content.getFullHeight() > height) {
	    Scrollbar vsb = new Scrollbar(Scrollbar.SCROLLBAR_VERTICAL,content.getFullHeight());
	    vsb.setCurrent(currentTop);
	    vsb.paint(g);
	}
    }

    protected void keyPressed(int keyCode) {
	int width = getWidth(),height = getHeight();
	int ga = getGameAction(keyCode);
        
        
        System.out.println("开始：");
        System.out.println("tRow:"+tRow);
        System.out.println("dRow:"+dRow);
	switch(ga) {
	case UP:
            System.out.println("UP");
            System.out.println("currentRow:"+content.getCurrentRow());
        
            if(content.getCurrentRow()>1){
                content.setCurrentRow(content.getCurrentRow()-1);
                currentRow = content.getCurrentRow();
                System.out.println("currentRow:"+content.getCurrentRow());
                System.out.println("tRow:"+tRow);
                if(currentRow < tRow){
                    if(currentTop > 0) {
                        currentTop -= content.getVerticalScrollAmount();
                        tRow -= 1;
                        dRow -= 1;
                        System.out.println("tRow:"+tRow);
                        System.out.println("dRow:"+dRow);
                    }
                }
            }
//            if(content.getCurrentRow()>0){
//                content.setCurrentRow(content.getCurrentRow()-1);
//                currentRow = content.getCurrentRow();
//                if((content.getCurrentRowY()<0))
//                {
//                    //滚动条向上滚动
//                    if(currentTop > 0) {
//                    currentTop -= content.getVerticalScrollAmount();
//                    }
//                }
//            }
	    
	    break;

	case DOWN:
            System.out.println("DOWN");
            System.out.println("currentRow:"+content.getCurrentRow());
            if(content.getCurrentRow()<content.getTotalRow())
            {
                content.setCurrentRow(content.getCurrentRow()+1);
                currentRow = content.getCurrentRow();
                System.out.println("currentRow:"+content.getCurrentRow());
                System.out.println("dRow:"+dRow);
                if(currentRow > dRow){
                    //System.out.println("contentFullHeight:"+content.getFullHeight());
                
                    if(currentTop < content.getFullHeight()) {
                    currentTop += content.getVerticalScrollAmount();
                    }
                    if((content.getFullHeight() - currentTop) < content.getVerticalScrollAmount()) {
                    currentTop = content.getFullHeight() - content.getVerticalScrollAmount();
                    }
                    dRow +=1;
                    tRow +=1;
                    System.out.println("tRow:"+tRow);
                    System.out.println("dRow:"+dRow);
                }
            }
//            if(content.getCurrentRow()<content.getTotalRow()){
//                content.setCurrentRow(content.getCurrentRow()+1);
//                currentRow = content.getCurrentRow();
//              if((content.getCurrentRowY()<0)||(content.getCurrentRowY()>height-content.getVerticalScrollAmount()))
//              {
//                    //滚动条向下滚动
//                    if(currentTop < content.getFullHeight()) {
//                        currentTop += content.getVerticalScrollAmount();
//                    }
//                    if((content.getFullHeight() - currentTop) < content.getVerticalScrollAmount()) {
//                        currentTop = content.getFullHeight() - content.getVerticalScrollAmount();
//                    }
//              }
//            }
	    break;

	case LEFT:
	    if(currentLeft > 0) {
		currentLeft -= content.getHorizontalScrollAmount();
	    }
	    break;

	case RIGHT:
	    if(currentLeft < content.getFullWidth()) {
		currentLeft += content.getHorizontalScrollAmount();
	    }
	    if((content.getFullWidth() - currentLeft) < content.getHorizontalScrollAmount()) {
		currentLeft = content.getFullWidth() - content.getHorizontalScrollAmount();
	    }
	    break;
//         case FIRE:
//             if(currentLeft < content.getFullWidth()) {
//		currentLeft += content.getHorizontalScrollAmount();
//	    }
//	    if((content.getFullWidth() - currentLeft) < content.getHorizontalScrollAmount()) {
//		currentLeft = content.getFullWidth() - content.getHorizontalScrollAmount();
//	    }
//             break;   

	default:
	    // we're here so we'll exit and avoid the default processing
	    return;
	}

	/* ensure the values are okay
	 * check to see if the current left and top values are below 0 and, if so, set them to 0
	 */
	if(currentLeft < 0) {
	    currentLeft = 0;
	}
	if(currentTop < 0) {
	    currentTop = 0;
	}
	repaint();
    }
}

