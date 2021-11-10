package table_editable_boolean_column;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Table with editable combo box column");

    // application toy data (see data type class below)
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

        public String getColumnName (int column)
        {
          if (column == 0)
            return "Name";
          else
            return "Value";
        }

        // return class of column; with this, boolean column is drawn
        // automatically as checkbox
        public Class<?> getColumnClass (int column)
        {
          if (column == 0)
            return String.class;
          else
            return Boolean.class;
        }

        // only column with boolean values is editable
        public boolean isCellEditable (int row, int column)
        {
          return column == 1;
        }

        // this method is called if user changes value in table
        public void setValueAt (Object value, int row, int column)
        {
          dataList.get (row).value = (Boolean) value; // set new value in data
          fireTableDataChanged (); // tell table that model data has changed
        }
      };

    // create table
    table = new JTable (tableModel);
    add (new JScrollPane (table));

    pack ();
    setVisible (true);
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
