/*
 * TableModel.java
 *
 * Created on 2006年5月23日, 上午12:26
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui.table;

public interface TableModel {
    /**
     * <P>Returns the number of columns the model contains.
     */
    public int getColumnCount();

    /**
     * <P>Returns the number of rows the model contains.
     */
    public int getRowCount();

    /**
     * <P>Returns the text for the column header.
     */
    public String getColumnHeaderText(int col);

    /**
     * <P>Returns whether column headers are displayed.
     */
    public boolean hasColumnHeaders();

    /**
     * <P>Returns the value for the specified cell.
     */
    public String getValueAt(int row,int col);
    /**
     *<P>return column width
     */
   // public int getColumnWidth(int col);
    public String[] getValueAt(int row);
}
