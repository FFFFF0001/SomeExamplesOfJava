/*
 * Table.java
 *
 * Created on 2006年5月23日, 上午12:21
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui.table;

import javax.microedition.lcdui.*;

/**
 * <P>The <CODE>Table</CODE> class creates a grid which shows data in columns, oriented either
 * horizontally or vertically. 
 *
 * <P><CODE>Table</CODE>s may also be inserted into <CODE>ScrollPane</CODE>s. This allows 
 * <CODE>Table</CODE>s to be larger than the physical display.
 *
 * <P>Orientation is set using the <CODE>ORIENT_VERTICAL</CODE> (which places columns along the
 * <B>X</B> axis), or <CODE>ORIENT_HORIZONTAL</CODE> (which places columns along the <B>Y</B>
 * axis.
 *
 * @see TableModel
 * @author Darryl L. Pierce,pandaxiaoxi
 * @version $Revision: 2.0 $
 */
public class Table extends Canvas implements Scrollable {
    public static final int ORIENT_VERTICAL   = 0;
    public static final int ORIENT_HORIZONTAL = 1;

    private TableModel model         = null;
    private Font       font           = Font.getDefaultFont();
    private int        columnWidth    = 85;
    private int        orientation    = ORIENT_VERTICAL;   //每列竖直显示
    private boolean    separatorLines = true;
    private int        currentRow = 1;

    /**
     * <P>Creates an instance with the associated model.
     */
    public Table(TableModel model) {
	if(null == model) {
	    throw new IllegalArgumentException("Cannot pass a null model to a table");
	}

	this.model = model;
    }
    public Table(){
        
    }
    public void setTableModel(TableModel model){
        if(null == model) {
	    throw new IllegalArgumentException("Cannot pass a null model to a table");
	}
        this.model = model;
    }
//    public TableModel getModel(){
//        return this.model;
//    }
    public TableModel getTableModel(){
        return this.model;
    }
    /**
     * <P>Sets the column width
     */
    public void setColumnWidth(int width) {
	if(0 < width) {
	    columnWidth = width;
	}
    }

    /**
     * <P>Returns the column width.
     */
    public int getColumnWidth() {
	return columnWidth;
    }

    /**
     * <P>Sets the table orientation.
     *
     * @param orient either <CODE>ORIENT_VERTICAL</CODE> or <CODE>ORIENT_HORIZONTAL</CODE>
     */
    public void setOrientation(int orient) {
	switch(orient) {
	case ORIENT_VERTICAL:
	case ORIENT_HORIZONTAL:
	    orientation = orient;
	    break;
	default:
	    System.out.println("Invalid orientation!");
	    break;
	}
    }

    /**
     * <P>Returns the table orientation.
     */
    public int getOrientation() {
	return orientation;
    }
    
    /**
     * <P>Toggles whether separator lines are used between rows and between columns.
     */
    public void setSeparatorLines(boolean sep) {
	separatorLines = sep;
    }

    /**
     * <P>Returns whether separator lines are used.
     */
    public boolean getSeparatorLines() {
	return separatorLines;
    }

    public void paint(Graphics g) {
	int width = getFullWidth(), height = getFullHeight();

	// clear the background
	g.setColor(255,255,255);
	g.fillRect(0,0,width,height);
	g.setColor(0,0,0);

	// draw the border for the table
	g.drawRect(0,0,width,height);
        
	if(separatorLines) {
	    int vbars = 0;
	    int hbars = 0;

	    // determine how many vertical and horizontal bars to draw
	    switch(orientation) {
	    case ORIENT_VERTICAL:
		vbars = model.getColumnCount();
		hbars = model.getRowCount();
		if(model.hasColumnHeaders()) hbars++;
		break;

	    case ORIENT_HORIZONTAL:
		vbars = model.getRowCount();
		hbars = model.getColumnCount();
		if(model.hasColumnHeaders()) vbars++;
		break;
	    }
            boolean flag = false;
            
	    // draw horizontal row with different color
	    for(int index = 0;index < hbars;index++) {
		int y = index * getVerticalSpacing();
		if(index == 0){
                    g.setColor(152, 152, 152);
                    g.fillRect(0,y, width-1,getVerticalSpacing());
                    continue;
                }
                    
                if(flag){
                    g.setColor(215,215,215);
                    flag = false;
                }else{
                    g.setColor(255,255,255);
                    flag = true;
                }
                g.fillRect(0,y, width-1,getVerticalSpacing());
            }
            g.setColor(70,95,182);
            if(currentRow > 0)
                g.fillRect(0,currentRow*getVerticalSpacing(), width-1,getVerticalSpacing());
            g.setColor(0,0,0);   
            // draw horizontal bars
	    for(int index = 0;index < hbars;index++) {
		int y = index * getVerticalSpacing();
		g.drawLine(0,y,width - 1,y);
                
            }
             // draw vertical bars
	    for(int index = 0;index < vbars;index++) {
		//int x = index * getHorizontalSpacing();
                int x = index * columnWidth;
		g.drawLine(x,0,x,height - 1);
                
	    }
            
	}
        
	int rowOffset = 0; // used to flag whether headers are displayed

	// if using column headers, let's show them first
	if(model.hasColumnHeaders()) {
	    for(int col = 0;col < model.getColumnCount();col++) {
		// the row should be -1 for this call 
		drawTableCell(g,0,col,model.getColumnHeaderText(col));
	    }
	    // set the row offset
	    rowOffset = 1;
	}
        
        
	// now draw each element of the table
	for(int row = 0;row < model.getRowCount();row++) {
	    for(int col = 0;col < model.getColumnCount();col++) {
		String value = model.getValueAt(row,col);
		// now use the rowOffset to adjust if column headers were used
		drawTableCell(g,row + rowOffset,col,value);
	    }
	}
        
    }

    private void drawTableCell(Graphics g,int row,int col,String value) {
	int valueSize = columnWidth - 4; // to make sure it fits in a column space
	if(0 < valueSize) {
	    while(valueSize < font.stringWidth(value)) {
		value = value.substring(0,value.length() - 1);
		// if the string is now empty, just exit... we can't display it...
		if(0 == value.length()) {
		    return;
		}
	    }

	    int x = getXFor(row,col) + 1;
	    int y = getYFor(row,col) + 2;
	    
	    g.drawString(value,x,y,Graphics.LEFT | Graphics.TOP);
	}
    }



    private int getXFor(int row,int col) {
	if(ORIENT_VERTICAL == orientation) {
	    return col * getHorizontalSpacing();
	} else {
	    return row * getHorizontalSpacing();
	}
    }

    private int getYFor(int row,int col) {
	if(ORIENT_VERTICAL == orientation) {
	    return row * getVerticalSpacing();
	} else {
	    return col * getVerticalSpacing();
	}
    }

    private int getVerticalSpacing() {
	return font.getHeight() + 3; // font height + 1 line above + 1 line below + 1 line for separator
    }

    private int getHorizontalSpacing() {
	int rightBorder = getFullWidth() - 1;

	if(ORIENT_VERTICAL == orientation) {
	    return rightBorder / model.getColumnCount();
	} else {
	    return rightBorder / model.getRowCount();
	}
    }

    // Scrollable interface

    public int getFullWidth() {
	int count = 0;
	if(ORIENT_VERTICAL == orientation) {
	    count = model.getColumnCount();
	} else {
	    count = model.getRowCount();
	    if(model.hasColumnHeaders()) count++;
	}
	return (count * columnWidth) + 1;
    }

    public int getFullHeight() {
	int count = 0;

	if(ORIENT_VERTICAL == orientation) {
	    count = model.getRowCount();
	    if(model.hasColumnHeaders()) count++;
	} else {
	    count = model.getColumnCount();
	}

	return (getVerticalSpacing() * count) + 1;
    }

    public int getVerticalScrollAmount() {
	return getVerticalSpacing();
    }

    public int getHorizontalScrollAmount() {
	return getHorizontalSpacing();
    }

    public void render(Graphics g) {
	paint(g);
    }
    public void setCurrentRow(int row){
        this.currentRow = row;
    }
    public int getCurrentRow(){
        return this.currentRow;
    }
    public int getTotalDisplayRow(){
        return getHeight()/getVerticalSpacing()-1;
        //return 10;
    }
    public int getTotalRow(){
        return model.getRowCount();
    }
    public int getCurrentRowY(){
        return currentRow*getVerticalSpacing();
    }
}


