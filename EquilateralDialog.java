// file: EquilateralDialog.java
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


public class EquilateralDialog extends ShapeDialog
{
	private double oldAngle = 0;
	private double angle = 0;
	private JTextField angleText;

 	public double getAngle() { return angle; }

	public EquilateralDialog(JFrame frame, boolean modal, int x, int y, int R, double A)
	{
 		super(frame, modal, x, y, R);
		oldAngle = A;
		setTitle ("Modify Equilateral Dialog");
		addText();
 		setVisible(true);
	}
	
	private void addText ()
	{
	 	myPanel.add(new JLabel("Enter the side:"));
		sideText = new JTextField(((Integer) oldSide).toString(), 20);
		sideText.addActionListener(this);
		myPanel.add (sideText);
	 	myPanel.add(new JLabel("Enter the angle:"));
		angleText = new JTextField(((Double) oldAngle).toString(), 20);
		angleText.addActionListener(this);
		myPanel.add (angleText); 
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
			try
			{
				angle = Double.parseDouble (angleText.getText());
			}
			catch (NumberFormatException ex)
			{
				angle = oldAngle;
			}
  	}
 		else if(cancelButton == e.getSource()) 
		{
 			answer = false;
			setVisible(false);
		}
  }
} 

