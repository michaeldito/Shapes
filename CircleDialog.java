// file: CircleDialog.java
// CS 360 - Fall 2006 - Watts
// Project 1
// September 2006
// Written by Dr. Watts
// http://www.cs.sonoma.edu/~tiawatts 
/*
Dialog box for selecting a shape and its color and providing 
a name for the shape
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CircleDialog extends ShapeDialog
{
	public CircleDialog(JFrame frame, boolean modal, int x, int y, int R)
	{
 		super(frame, modal, x, y, R);
		setTitle ("Modify Circle Dialog");
		addCircleText();
 		setVisible(true);
	}
	
	private void addCircleText ()
	{
	 	myPanel.add(new JLabel("Enter the radius:"));
		sideText = new JTextField(((Integer) oldSide).toString(), 20);
		sideText.addActionListener(this);
		myPanel.add (sideText);
	}

	public void actionPerformed(ActionEvent e) 
	{
 		if(OKButton == e.getSource()) 
		{
			answer = true;
			setVisible(false);
			getContentPane().remove(myPanel);
			currentColor = colorPanel.getColor ();
			try
			{
				side = Integer.parseInt (sideText.getText());
			}
			catch (NumberFormatException ex)
			{
				side = oldSide;
			}
    }
 		else if(cancelButton == e.getSource()) 
		{
 			answer = false;
			setVisible(false);
		}
  }
} 

