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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Multiple frames and switching panels");
    setSize (400, 300);

    // "card stack" layout for having multiple panels and switching between them
    CardLayout cardLayout = new CardLayout ();
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
    JMenu fileMenu = new JMenu ("View");
    JMenuItem newItem = new JMenuItem ("New frame");
    newItem.addActionListener ((ActionEvent e) -> new Main ());

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

    // all frames are stored in a list and all are disposed when
    // initial "root" frame is disposed
    if (frameList.size () == 0)
    {
      rootFrame = this;
      addWindowListener (new WindowAdapter ()
        {
          @Override
          public void windowClosed (WindowEvent e)
          {
            for (JFrame f : frameList)
              if (f != rootFrame)
                f.dispose ();
            frameList.clear ();
          }
        }
        );
    }
    frameList.add (this);
    
    setVisible (true);
  }

  private static JFrame rootFrame; // initial frame for this demo
  private static List<JFrame> frameList = new ArrayList<> (); // list of all frames
}
