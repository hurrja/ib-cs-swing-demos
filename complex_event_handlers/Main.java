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

package complex_event_handlers;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ------------------------------------------------------------------
// this class implements window focus listening
// ------------------------------------------------------------------
public class Main extends JFrame implements WindowFocusListener
{
  public Main ()
  {
    super ("Complex event demo");
    setSize (400, 300);

    hasFocus = false;
    textLength = 0;

    // ------------------------------------------------------------------
    // register this object to listen to window focus events
    addWindowFocusListener (this); 
    // ------------------------------------------------------------------

    // text field
    textField = new JTextField (10);
    add (textField, BorderLayout.CENTER);
    // ------------------------------------------------------------------
    // adding a listener: anonymous inner class implementing methods
    // of DocumentListener
    textField.getDocument ().addDocumentListener (new DocumentListener ()
      {
        public void insertUpdate (DocumentEvent e)
        {
          updateTextLength ();
        }
        public void removeUpdate (DocumentEvent e)
        {
          updateTextLength ();
        }
        public void changedUpdate (DocumentEvent e)
        {
        }
      });
    // ------------------------------------------------------------------

    // a label for showing whether focus is in or out
    focusLabel = new JLabel ();
    showFocus ();
    add (focusLabel, BorderLayout.PAGE_START);

    // a label for showing length of text
    textLengthLabel = new JLabel ();
    showTextLength ();
    add (textLengthLabel, BorderLayout.PAGE_END);

    setVisible (true);
  }

  // ------------------------------------------------------------------
  // methods needed to implement WindowFocusListener
  public void windowGainedFocus (WindowEvent e)
  {
    hasFocus = true;
    showFocus ();
  }
  
  public void windowLostFocus (WindowEvent e)
  {
    hasFocus = false;
    showFocus ();
  }
  // ------------------------------------------------------------------

  public void updateTextLength ()
  {
    textLength = textField.getText ().length ();
    showTextLength ();
  }
  
  void showTextLength ()
  {
    textLengthLabel.setText (textLength + "");
  }

  void showFocus ()
  {
    focusLabel.setText (hasFocus + "");
  }
  
  private JLabel focusLabel, textLengthLabel;
  private JTextField textField;
  public boolean hasFocus;
  public int textLength;
}
