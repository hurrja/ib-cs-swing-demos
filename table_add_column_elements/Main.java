package table_add_column_elements;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Adding elements to the column of a table");

    // application data: list of lists
    dataList = new ArrayList<> ();
    dataList.add (new ArrayList<> (Arrays.asList ("A", "B")));
    dataList.add (new ArrayList<> (Arrays.asList ("C")));

    // MVC-style table model that will return properties of data shown
    // in table; each list is shown in the column of the table
    tableModel = new AbstractTableModel ()
      {
        public int getColumnCount () { return dataList.size (); }
        public int getRowCount ()
        {
          int max = 0;
          for (List<String> list : dataList)
            max = Math.max (max, list.size ());
          return max;
        }
        
        public Object getValueAt (int row, int column)
        {
          List<String> list = dataList.get (column);
          if (row < list.size ())
            return list.get (row);
          else
            return null;
        }
        public String getColumnName (int column)
        {
          return "List " + (column + 1);
        }
      };

    // create table
    table = new JTable (tableModel);
    table.setRowSelectionAllowed (false);
    table.setColumnSelectionAllowed (true); // allow column selection only
    add (new JScrollPane (table));

    // menu bar with one item for adding data to a column
    JMenuBar menuBar = new JMenuBar ();
    JMenu columnMenu = new JMenu ("Column");
    JMenuItem addItem = new JMenuItem ("Add item");
    addItem.addActionListener ((ActionEvent e) -> addData ());
    columnMenu.add (addItem);
    menuBar.add (columnMenu);
    setJMenuBar (menuBar);

    pack ();
    setVisible (true);
  }

  private void addData ()
  {
    int column = table.getSelectedColumn (); // returns -1 if none selected
    if (column >= 0)
    {
      List<String> list = dataList.get (column);
      list.add ("X"); // add string to column
      tableModel.fireTableDataChanged (); // tell table to redraw
    }
  }

  private JTable table;
  private AbstractTableModel tableModel;
  private List<List<String>> dataList; // data is list of lists of strings
}
