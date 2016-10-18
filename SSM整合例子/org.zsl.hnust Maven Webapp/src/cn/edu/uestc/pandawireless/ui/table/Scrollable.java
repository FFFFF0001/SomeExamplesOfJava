/*
 * Scrollable.java
 *
 * Created on 2006年5月23日, 上午12:24
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui.table;

import javax.microedition.lcdui.Graphics;

/**
 * <P>The <CODE>Scrollable</CODE> interface defines the contract that is 
 * fulfilled by a component that can be larger than the physical display.
 *
 * @see ScrollPane
 * @author Darryl L. Pierce,pandaxiaoxi
 * @version $Revision: 2.0 $
 */
public interface Scrollable {
    /**
     * <P>Returns the full width of the component.
     */
    public int getFullWidth();

    /**
     * <P>Returns the full height of the component.
     */
    public int getFullHeight();

    /**
     * <P>The amount to scroll this component vertically.
     */
    public int getVerticalScrollAmount();

    /**
     * <P>The amount to scroll this component horizontally.
     */
    public int getHorizontalScrollAmount();

    /**
     * <P>Called when the component needs to repaint itself.
     */
    public void render(Graphics g);
    public void setCurrentRow(int row);
    public int getCurrentRow();
    public int getTotalRow();
    public int getTotalDisplayRow();
    public int getCurrentRowY();
    public TableModel getTableModel();
}
