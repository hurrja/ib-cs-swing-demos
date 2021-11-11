// Copyright (C) 2021 Jarmo Hurri

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

package table_editable_combo_column;

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
    dataList.add (new MyDataType ("A", State.perhaps));
    dataList.add (new MyDataType ("B", State.could_be));

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
            return data.state;
        }

        public String getColumnName (int column)
        {
          if (column == 0)
            return "Name";
          else
            return "State";
        }

        // only column with boolean values is editable
        public boolean isCellEditable (int row, int column)
        {
          return column == STATE_COLUMN;
        }

        // this method is called if user changes value in table
        public void setValueAt (Object value, int row, int column)
        {
          dataList.get (row).state = (State) value; // set new value in data
          fireTableDataChanged (); // tell table that model data has changed
        }
      };

    // create table
    table = new JTable (tableModel);

    // set combo box as editor in state column
    TableColumn stateColumn = table.getColumnModel ().getColumn (STATE_COLUMN);
    JComboBox<State> stateComboBox = new JComboBox<> (State.values ());
    stateColumn.setCellEditor (new DefaultCellEditor (stateComboBox));
    
    // add table
    add (new JScrollPane (table));

    pack ();
    setVisible(true);
  }

  final int STATE_COLUMN = 1; // constant indicating which column is state
  private JTable table;
  private AbstractTableModel tableModel;
  List<MyDataType> dataList;
}

class MyDataType
{
  MyDataType (String name, State state)
  {
    this.name = name;
    this.state = state;
  }
    
  String name;
  State state;
}

// enum is a convenient datatype for variables which can take a finite
// number of possible values
enum State { perhaps, maybe, could_be }
