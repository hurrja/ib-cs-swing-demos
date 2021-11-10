package table_changing_data;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Table with changing data");

    // application data
    dataList = new ArrayList<> ();
    dataList.add (new MyDataType ("A", true));
    dataList.add (new MyDataType ("B", false));

    // MVC-style table model that will return properties of data shown
    // in table
    tableModel = new AbstractTableModel ()
      {
        public int getColumnCount () { return 2; }
        public int getRowCount () { return dataList.size (); }
        public Object getValueAt (int row, int column)
        {
          MyDataType data = dataList.get (row);
          if (column == 0)
            return data.name;
          else
            return data.value;
        }
      };

    // create table
    table = new JTable (tableModel);
    add (table);

    // menu bar with one item for toggling data value change
    JMenuBar menuBar = new JMenuBar ();
    JMenu dataMenu = new JMenu ("Data");
    JMenuItem toggleItem = new JMenuItem ("Toggle");
    toggleItem.addActionListener ((ActionEvent e) -> toggleData ());
    dataMenu.add (toggleItem);
    menuBar.add (dataMenu);
    setJMenuBar (menuBar);

    pack ();
    setVisible (true);
  }

  private void toggleData ()
  {
    int row = table.getSelectedRow (); // returns -1 if none selected
    if (row >= 0)
    {
      MyDataType data = dataList.get (row);
      data.value = !data.value;
      tableModel.fireTableDataChanged ();
    }
  }
  
  private JTable table;
  private AbstractTableModel tableModel;
  List<MyDataType> dataList;
}

class MyDataType
{
  MyDataType (String name, boolean value)
  {
    this.name = name;
    this.value = value;
  }
    
  String name;
  boolean value;
}
