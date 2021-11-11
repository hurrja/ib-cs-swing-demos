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

package nested_panels;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Nested panels");

    // application toy data (see data type class below); notice that
    // this is a list of lists
    dataLists = new ArrayList<> ();

    // first data list
    ArrayList<MyDataType> dataList1 = new ArrayList<> ();
    dataList1.add (new MyDataType ("A", true));
    dataList1.add (new MyDataType ("B", false));
    dataLists.add (dataList1);
    
    // second data list
    ArrayList<MyDataType> dataList2 = new ArrayList<> ();
    dataList2.add (new MyDataType ("C", true));
    dataList2.add (new MyDataType ("D", false));
    dataList2.add (new MyDataType ("E", false));
    dataLists.add (dataList2);

    // topmost panel has border layout with just start and center areas
    Container pane = getContentPane ();
    pane.setLayout (new BorderLayout ());

    // start area has a panel with flow layout
    JPanel startPanel = new JPanel ();
    startPanel.setLayout (new FlowLayout ());
    startPanel.add (new JLabel ("some text here"));
    startPanel.add (new BasicArrowButton (SwingConstants.EAST));
    pane.add (startPanel, BorderLayout.PAGE_START);

    // center area contains another panel with flow layout
    JPanel centerPanel = new JPanel ();
    centerPanel.setLayout (new FlowLayout ());
    pane.add (centerPanel, BorderLayout.CENTER);

    // center panel contains a row of box panels (columns), one for each list
    for (int i = 0; i < dataLists.size (); i++)
    {
      JPanel listPanel = new JPanel ();
      listPanel.setLayout (new BoxLayout (listPanel, BoxLayout.PAGE_AXIS));
      centerPanel.add (listPanel);
      // each column contains panels with the actual data
      for (int j = 0; j < dataLists.get (i).size (); j++)
      {
        JPanel dataPanel = new JPanel ();
        dataPanel.setLayout (new BoxLayout (dataPanel, BoxLayout.PAGE_AXIS));

        // different borders are available, this one is "lowered"
        Border dataPanelBorder = BorderFactory.createBevelBorder (BevelBorder.LOWERED);
        dataPanel.setBorder (dataPanelBorder);
        listPanel.add (dataPanel);

        // data is shown with label and checkbox
        MyDataType data = dataLists.get (i).get (j);
        dataPanel.add (new JLabel (data.name));

        JCheckBox checkbox = new JCheckBox ("value", data.value);
        dataPanel.add (checkbox);
        // set value of boolean variable to match state of checkbox
        checkbox.addItemListener ((ItemEvent e)
                                  -> data.value = (e.getStateChange () == ItemEvent.SELECTED));
      }
    }
    
    pack ();
    setVisible (true);
  }

  List<List<MyDataType>> dataLists;
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
