// file: ShapeDialog.java
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

public class SelectShapeDialog extends JDialog implements ActionListener 
{
 	private JPanel myPanel = null;
 	private JButton OKButton = null, cancelButton = null;
	private SelectShapePanel selectShapePanel = null;
	private ColorPanel colorPanel = null;
	private JPanel buttonPanel = null;    
	private Color currentColor = Color.red;
	private Shape newShape = null;
 	private boolean answer = false;
	public Shape getNewShape () {return newShape; }
 	public boolean getAnswer() { return answer; }

	public SelectShapeDialog(JFrame frame, boolean modal, int x, int y)
	{
 		super(frame, modal);
 		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout (new GridLayout(3,1));
		myPanel.setLayout (new FlowLayout());
		selectShapePanel = new SelectShapePanel ();
		myPanel.add (selectShapePanel);
		addTextAndButtons ();
		setTitle ("New Shape Dialog");
		setLocation (x, y);
		setSize (300,250);
 		setVisible(true);
	}

	private void addTextAndButtons ()
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
		if(OKButton == e.getSource()) 
		{
			Shape.ShapeType currentShape = selectShapePanel.getNewShape ();
			switch (currentShape)
			{
				case CIRCLE:
					newShape = new Circle (); break;
				case SQUARE:
					newShape = new Square (); break;
				case RECTANGLE:
					newShape = new Rectangle (); break;
				case EQUILATERAL:
					newShape = new Equilateral (); break;
				case RIGHT:
					newShape = new Right (); break;
				case SCALENE:
					newShape = new Scalene (); break;
				default:
					newShape = new Circle (); break;
			}
			answer = true;
			setVisible(false);
			getContentPane().remove(myPanel);
    }
 		else if(cancelButton == e.getSource()) 
		{
 			answer = false;
			setVisible(false);
		}
  }

	public class SelectShapePanel extends JPanel implements ActionListener 
	{
		private JCheckBox circleCBox = null, equilateralCBox = null, 
			squareCBox = null, scaleneCBox = null,
			rightCBox = null, rectangleCBox = null;
		private JPanel selectShapePanel = null;
		private ButtonGroup shapeGroup = null;
		private Shape.ShapeType currentShape = Shape.ShapeType.CIRCLE;
		public Shape.ShapeType getNewShape () { return currentShape; }

		public SelectShapePanel ()
		{
			setLayout (new GridLayout (2,1));
			add(new JLabel("Select a shape:"));
			selectShapePanel = new JPanel();
			selectShapePanel.setLayout (new GridLayout (2,3));
			circleCBox = new JCheckBox ("Circle",true);
			circleCBox.addActionListener(this);
			selectShapePanel.add (circleCBox);
			squareCBox = new JCheckBox ("Square",false);
			squareCBox.addActionListener(this);
			selectShapePanel.add (squareCBox);
			rectangleCBox = new JCheckBox ("Rectangle",false);
			rectangleCBox.addActionListener(this);
			selectShapePanel.add (rectangleCBox);
			equilateralCBox = new JCheckBox ("Equilateral",false);
			equilateralCBox.addActionListener(this);
			selectShapePanel.add (equilateralCBox);
			rightCBox = new JCheckBox ("Right",false);
			rightCBox.addActionListener(this);
			selectShapePanel.add (rightCBox);
			scaleneCBox = new JCheckBox ("Scalene",false);
			scaleneCBox.addActionListener(this);
			selectShapePanel.add (scaleneCBox);
			add (selectShapePanel);
			shapeGroup = new ButtonGroup ();
			shapeGroup.add (circleCBox);
			shapeGroup.add (squareCBox);
			shapeGroup.add (equilateralCBox);
			shapeGroup.add (rectangleCBox);
			shapeGroup.add (rightCBox);
			shapeGroup.add (scaleneCBox);
		}

		public void actionPerformed(ActionEvent e) 
		{
			if (circleCBox == e.getSource())
				currentShape = Shape.ShapeType.CIRCLE;
			else if (squareCBox == e.getSource())
				currentShape = Shape.ShapeType.SQUARE;
			else if (rectangleCBox == e.getSource())
				currentShape = Shape.ShapeType.RECTANGLE;
			else if (equilateralCBox == e.getSource())
				currentShape = Shape.ShapeType.EQUILATERAL;
			else if (equilateralCBox == e.getSource())
				currentShape = Shape.ShapeType.EQUILATERAL;
			else if (rightCBox == e.getSource())
				currentShape = Shape.ShapeType.RIGHT;
			else if (scaleneCBox == e.getSource())
				currentShape = Shape.ShapeType.SCALENE;
		}
	}
} 

