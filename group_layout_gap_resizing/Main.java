package group_layout_gap_resizing;

import javax.swing.*;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Automatic gap resizing in group layout");
    GroupLayout layout = new GroupLayout (getContentPane ());
    getContentPane ().setLayout (layout);
    
    // these three labels will be automatically aligned to be placed left, center, right
    JLabel left = new JLabel ("left");
    JLabel center = new JLabel ("center");
    JLabel right = new JLabel ("right");

    JLabel below = new JLabel ("a much, much, much, longer piece of text below");

    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    
    layout.setHorizontalGroup (
      layout.createParallelGroup ()
      .addGroup (layout.createSequentialGroup ()
                 .addComponent (left)
                 .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addComponent (center)
                 .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addComponent (right))
      .addComponent (below));
    
    layout.setVerticalGroup (
      layout.createSequentialGroup ()
      .addGroup (layout.createParallelGroup ()
                 .addComponent (left)
                 .addComponent (center)
                 .addComponent (right))
      .addComponent (below));

    pack ();
    setVisible (true);
  }
}
