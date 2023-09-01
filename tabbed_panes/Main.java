// Copyright (C) 2023 Jarmo Hurri

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

package tabbed_panes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Tabbed panes");
    tabs = new LinkedList <> ();
    
    JMenuBar menuBar = new JMenuBar ();
    JMenu tabMenu = new JMenu ("Tabs");
    JMenuItem newTabItem = new JMenuItem ("New tab");
    newTabItem.addActionListener ((ActionEvent e) -> { });
    JMenuItem closeTabItem = new JMenuItem ("Close active tab");
    closeTabItem.addActionListener ((ActionEvent e) -> {});
    tabMenu.add (newTabItem);
    tabMenu.add (closeTabItem);
    menuBar.add (tabMenu);
    setJMenuBar (menuBar);

    pack ();
    setVisible (true);
  }

  List<JPanel> tabs;
  JTabbedPane tabbedPane;
}

