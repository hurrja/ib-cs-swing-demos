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

package multiple_frames_panels;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// gui extends frame and inherits all its methods (e.g., for setting
// size, adding components)

public class GUI extends JFrame
{
  // constructor will create all GUI components and set their properties
  public GUI ()
  {
    super ("Multiple frames and switching panels");

    // frame properties
    setSize (400, 300);
    CardLayout cardLayout = new CardLayout (); // "card stack" layout of panels 
    setLayout (cardLayout);

    // two panels, one of which will be shown at a time
    final String RED_PANEL_NAME = "red";
    final String BLUE_PANEL_NAME = "blue";
    JPanel redPanel = new JPanel ();
    redPanel.setBackground (Color.red);
    add (redPanel, RED_PANEL_NAME);
    JPanel bluePanel = new JPanel ();
    bluePanel.setBackground (Color.blue);
    add (bluePanel, BLUE_PANEL_NAME);

    // menu bar
    JMenuBar menuBar = new JMenuBar ();
    JMenu fileMenu = new JMenu ("File");
    JMenuItem newItem = new JMenuItem ("New frame");
    newItem.addActionListener ((ActionEvent e) -> new GUI ());

    // menu item switching to one panel
    JMenuItem redItem = new JMenuItem ("Red panel");
    redItem.addActionListener ((ActionEvent e) -> cardLayout.show (getContentPane (), RED_PANEL_NAME));
    // menu item switching to other panel
    JMenuItem blueItem = new JMenuItem ("Blue panel");
    blueItem.addActionListener ((ActionEvent e) -> cardLayout.show (getContentPane (), BLUE_PANEL_NAME));
    fileMenu.add (newItem);
    fileMenu.add (redItem);
    fileMenu.add (blueItem);
    menuBar.add (fileMenu);
    setJMenuBar (menuBar);

    guiList.add (this);
    setVisible (true); // show frame
  }

  void close ()
  {
    for (GUI g : guiList)
      g.dispose ();
  }

  private static List<GUI> guiList = new ArrayList<> ();
}
