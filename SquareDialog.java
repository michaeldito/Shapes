import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SquareDialog extends ShapeDialog
{
  private double angle = 0;
  private double oldAngle = 0;
	private JTextField angleText;

 	public double getAngle() { return angle; }

	public SquareDialog(JFrame frame, boolean modal, int x, int y, int S, double A)
	{
 		super(frame, modal, x, y, S);
		oldAngle = A;
		setTitle ("Modify Square Dialog");
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

