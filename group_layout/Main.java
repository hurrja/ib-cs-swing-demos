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
    
    layout.setHorizontalGroup (
      layout.createParallelGroup ()
      .addComponent (enterName)
      .addComponent (name)
      .addComponent (choose)
      .addGroup (layout.createSequentialGroup ()
                 .addComponent (great)
                 .addComponent (brilliant)));
    
    layout.setVerticalGroup (
      layout.createSequentialGroup ()
      .addComponent (enterName)
      .addComponent (name)
      .addComponent (choose)
      .addGroup (layout.createParallelGroup (GroupLayout.Alignment.LEADING)
                 .addComponent (great)
                 .addComponent (brilliant)));
    
    setSize (800,500);
    pack ();
    setVisible (true);
  }
}
