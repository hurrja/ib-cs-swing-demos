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

import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.function.Supplier;

public class Main extends JFrame
{
  public static void main (String[] args)
  {
    SwingUtilities.invokeLater (() -> new Main ());
  }

  public Main ()
  {
    super ("Set of Swing demos");

    JMenuBar menuBar = new JMenuBar ();
    JMenu fileMenu = new JMenu ("File");
    JMenuItem quitItem = new JMenuItem ("Quit");
    quitItem.addActionListener ((ActionEvent e) -> System.exit (0));
    fileMenu.add (quitItem);
    menuBar.add (fileMenu);
    setJMenuBar (menuBar);

    Container contentPane = getContentPane ();
    contentPane.setLayout (new BoxLayout (contentPane, BoxLayout.PAGE_AXIS));

    addDemo ("multiple frames and switching panels",
             new Demo (() -> { return (new multiple_frames_panels.Main ()); }));
    addDemo ("nested panels",
             new Demo (() -> { return (new nested_panels.Main ()); }));
    addDemo ("grid of panels",
             new Demo (() -> { return (new panel_grid.Main ()); }));
    addDemo ("complex event handlers",
             new Demo (() -> { return (new complex_event_handlers.Main ()); }));
    addDemo ("table cell rendering",
             new Demo (() -> { return (new table_cell_rendering.Main ()); }));
    addDemo ("automatic gap resizing in group layout",
             new Demo (() -> { return (new group_layout_gap_resizing.Main ()); }));
    
    pack ();
    setVisible (true);
  }

  private void addDemo (String title, Demo demo)
  {
    JCheckBox checkBox = new JCheckBox (title, false);
    checkBox.addItemListener (
      (ItemEvent e) ->
      {
        if (e.getStateChange () == 1)
          demo.open ();
        else
          demo.close ();
      });

    add (checkBox);
  }
}

class Demo
{
  public Demo (Supplier<JFrame> supplier)
  {
    this.supplier = supplier;
    frame = null;
  }

  public void open ()
  {
    frame = supplier.get ();
  }

  public void close ()
  {
    frame.dispose ();
    frame = null;
  }

  Supplier<JFrame> supplier;
  private JFrame frame;
}
