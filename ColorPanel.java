// file: ColorPanel.java
// CS 360 - Fall 2012 - Watts
// Lab 9
// September 2006
// Written by Dr. Watts
// http://watts.cs.sonoma.edu
/*
Panel displaying radio buttons for selecting shape color.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ColorPanel extends JPanel implements ActionListener 
{
	private JRadioButton redRButton = null, orangeRButton = null,
			yellowRButton = null, greenRButton = null,
			blueRButton = null, purpleRButton = null,
			whiteRButton = null, grayRButton = null,
			blackRButton = null;
	private JPanel colorPanel = null;
	private ButtonGroup colorGroup = null;
	private Color oldColor, newColor;
	private static Color orange = new Color (237, 155, 37);
	private static Color purple = new Color (82, 8, 125);
	private static Color gray = new Color (170, 170, 170);
	public Color getColor () { return newColor; }

	public ColorPanel(Color C)
	{
		oldColor = newColor = C;
		setLayout (new GridLayout(2,1));
		//setLayout (new FlowLayout());
		setSize (300,150);
	 	add(new JLabel("Select a color:"));
		colorPanel = new JPanel();
		colorPanel.setSize(300, 100);
		colorPanel.setLayout (new GridLayout (4,3));
		redRButton = new JRadioButton ("Red", C.equals(Color.red));
		redRButton.addActionListener(this);
		redRButton.setForeground (Color.red);
		colorPanel.add (redRButton);
		orangeRButton = new JRadioButton ("Orange", C.equals(orange));
		orangeRButton.addActionListener(this);
		orangeRButton.setForeground (orange);
		colorPanel.add (orangeRButton);
		yellowRButton = new JRadioButton ("Yellow", C == Color.yellow);
		yellowRButton.addActionListener(this);
		yellowRButton.setForeground (Color.yellow);
		colorPanel.add (yellowRButton);
		greenRButton = new JRadioButton ("Green", C == Color.green);
		greenRButton.addActionListener(this);
		greenRButton.setForeground (Color.green);
		colorPanel.add (greenRButton);
		blueRButton = new JRadioButton ("Blue", C == Color.blue);
		blueRButton.addActionListener(this);
		blueRButton.setForeground (Color.blue);
		colorPanel.add (blueRButton);
		purpleRButton = new JRadioButton ("Purple", C == purple);
		purpleRButton.addActionListener(this);
		purpleRButton.setForeground (purple);
		colorPanel.add (purpleRButton);
		whiteRButton = new JRadioButton ("White", C == Color.white);
		whiteRButton.addActionListener(this);
		whiteRButton.setForeground (Color.white);
		colorPanel.add (whiteRButton);
		grayRButton = new JRadioButton ("Gray", C == gray);
		grayRButton.addActionListener(this);
		grayRButton.setForeground (gray);
		colorPanel.add (grayRButton);
		blackRButton = new JRadioButton ("Black", C == Color.black);
		blackRButton.addActionListener(this);
		colorPanel.add (blackRButton);
		add (colorPanel);
		colorGroup = new ButtonGroup ();
		colorGroup.add (redRButton);
		colorGroup.add (orangeRButton);
		colorGroup.add (yellowRButton);
		colorGroup.add (greenRButton);
		colorGroup.add (blueRButton);
		colorGroup.add (purpleRButton);
		colorGroup.add (whiteRButton);
		colorGroup.add (grayRButton);
		colorGroup.add (blackRButton);
	}

	public void actionPerformed(ActionEvent e) 
	{
 		if (redRButton == e.getSource())
			newColor = Color.red;
		else if (orangeRButton == e.getSource())
			newColor = orange;
	 	else if (yellowRButton == e.getSource())
			newColor = Color.yellow;
	 	else if (greenRButton == e.getSource())
			newColor = Color.green;
		else if (blueRButton == e.getSource())
			newColor = Color.blue;
		else if (purpleRButton == e.getSource())
			newColor = purple;
		else if (whiteRButton == e.getSource())
			newColor = Color.white;
		else if (grayRButton == e.getSource())
			newColor = gray;
		else if (blackRButton == e.getSource())
			newColor = Color.black;
       }
} 

