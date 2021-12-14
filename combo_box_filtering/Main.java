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

package combo_box_filtering;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Combo box filtering");

    // application data
    dataList = new ArrayList<> (Arrays.asList ("moose", "mouse", "cat", "dog"));

    comboBoxModel = new DefaultComboBoxModel<> ();
    comboBox = new JComboBox<> ();
    comboBox.setModel (comboBoxModel);
    comboBox.setEditable (true);
    Component component = comboBox.getEditor ().getEditorComponent ();
    if (component instanceof JTextField)
    {
      comboBoxTextField = (JTextField) component;
      comboBoxTextField.addKeyListener (new KeyAdapter ()
        {
          public void keyReleased (KeyEvent ev)
          {
            SwingUtilities.invokeLater (() -> setComboBoxContents (comboBoxTextField.getText ()));
          }
        });
    }
    add (comboBox);
    
    pack ();
    setVisible (true);
    setComboBoxContents ("");
  }

  public void setComboBoxContents (String filteringString)
  {
    List<String> filteredList = new ArrayList<> ();
    for (String s : dataList)
      if (s.startsWith (filteringString))
        filteredList.add (s);

    comboBoxModel.removeAllElements ();
    for (String s : filteredList)
      comboBoxModel.addElement (s);
    comboBoxTextField.setText (filteringString);

    if (!comboBox.isPopupVisible ())
      comboBox.showPopup ();
    comboBox.setMaximumRowCount (filteredList.size ());
  }
  
  private JComboBox<String> comboBox;
  private JTextField comboBoxTextField;
  private DefaultComboBoxModel<String> comboBoxModel;
  private List<String> dataList;
}

