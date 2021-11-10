package panel_grid;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;

public class Main extends JFrame
{
  public Main ()
  {
    super ("Grid of panels");

    // topmost panel has grid layout; 7 days per week, max 5 weeks
    Container pane = getContentPane ();
    final int NUM_WEEKS = 5, NUM_WEEKDAYS = 7;
    pane.setLayout (new GridLayout (NUM_WEEKS, NUM_WEEKDAYS));
    final int FIRST_WEEK_DAY_START = 3, LAST_WEEK_DAY_END = 1;
    
    for (int w = 0; w < NUM_WEEKS; w++)
    {
      for (int d = 0; d < NUM_WEEKDAYS; d++)
      {
        // each day is a panel of its own
        JPanel dayPanel = new JPanel ();
        dayPanel.setBorder (BorderFactory.createLineBorder (Color.RED));
        String dayText;
        if ((w == 0 && d < FIRST_WEEK_DAY_START) || (w == NUM_WEEKS - 1 && d > LAST_WEEK_DAY_END))
          dayText = "";
        else
          dayText = "w " + w + " d " + d;
        dayPanel.add (new JLabel (dayText));
        pane.add (dayPanel);
      }
    }
    
    pack ();
    setVisible (true);
  }
}
