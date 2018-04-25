
/*
Dialog box for selecting a shape and its color and providing 
a name for the shape
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeDialog extends JDialog implements ActionListener
{
 	protected JPanel myPanel = null;
  protected JButton OKButton = null, cancelButton = null;
  protected JTextField sideText;
	protected ColorPanel colorPanel = null;
	protected JPanel buttonPanel = null;    
	protected Color currentColor = Color.red;
  protected Shape newShape = null;
  protected int side = 0;
  protected int oldSide = 0;
  protected boolean answer = false;

 	public boolean getAnswer() { return answer; }
 	public Color getColor() { return currentColor; }
 	public int getSide() { return side; }

	public ShapeDialog(JFrame frame, boolean modal, int x, int y, int S)
	{
		super(frame, modal);
		oldSide = S;
 		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout (new GridLayout(7,1));
		myPanel.setLayout (new FlowLayout());
		colorPanel = new ColorPanel (Color.red);
		myPanel.add (colorPanel);
		addText ();
		addButtons ();
		setLocation (x, y);
		setSize (290,450);
 		setVisible(false);
	}

	private void addText ()
  {
	}

	private void addButtons ()
  {
		buttonPanel = new JPanel();
		OKButton = new JButton("    OK    ");
		OKButton.addActionListener(this);
		buttonPanel.add(OKButton); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton); 
		myPanel.add(buttonPanel); 
	}

	public void actionPerformed(ActionEvent e) 
	{
  }
} 

