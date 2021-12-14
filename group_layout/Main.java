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

package group_layout;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Group layout");
    GroupLayout layout = new GroupLayout (getContentPane ());
    getContentPane ().setLayout (layout);
    Font largeFont = new Font ("Calibri", Font.BOLD, 20);
    JLabel enterName = new JLabel ("Enter the name of the thingy");
    enterName.setFont (largeFont);

    JTextField name = new JTextField (15);

    JLabel choose = new JLabel ("Choose the category");
    choose.setFont (largeFont);

    JCheckBox great = new JCheckBox ("great");
    JCheckBox brilliant = new JCheckBox ("brilliant");

    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    
    // in group layout, both the horizontal and vertical grouping need
    // to be specified

    // horizontally there are four rows:
    // - three with individual components (label, text field, label) and one with two
    // components in sequence (check boxes)
    layout.setHorizontalGroup (
      layout.createParallelGroup ()
      .addComponent (enterName)
      .addComponent (name)
      .addComponent (choose)
      .addGroup (layout.createSequentialGroup ()
                 .addComponent (great)
                 .addComponent (brilliant)));
    
    // vertically there is one column (sequential group) containing 
    // - three individual components (label, text field, label)
    // - one parallel group with two components (check boxes)
    layout.setVerticalGroup (
      layout.createSequentialGroup ()
      .addComponent (enterName)
      .addComponent (name)
      .addComponent (choose)
      .addGroup (layout.createParallelGroup ()
                 .addComponent (great)
                 .addComponent (brilliant)));
    
    setSize (800,500);
    pack ();
    setVisible (true);
  }
}
