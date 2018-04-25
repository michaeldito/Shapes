import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScaleneDialog extends ShapeDialog
{
  private double angle = 0;
  private double oldAngle = 0;
  private int side2 = 0;
  private int oldSide2 = 0;
  private JTextField side2Text;
  private int side3 = 0;
  private int oldSide3 = 0;
  private JTextField side3Text;
	private JTextField angleText;

  public double getAngle() { return angle; }
  public int getSide2() { return side2; }
  public int getSide3() { return side3; }

	public ScaleneDialog(JFrame frame, boolean modal, int x, int y, int S1, int S2, int S3, double A)
	{
    super(frame, modal, x, y, S1);
    oldSide = S1;
    oldSide2 = S2;
    oldSide3 = S3;
		oldAngle = A;
		setTitle ("Modify Scalene Dialog");
    addText();
    setSize(290, 500);
 		setVisible(true);
	}
	
	private void addText ()
	{
	 	myPanel.add(new JLabel("Enter the side:"));
		sideText = new JTextField(((Integer) oldSide).toString(), 20);
		sideText.addActionListener(this);
    myPanel.add (sideText);
    myPanel.add(new JLabel("Enter the second side:"));
		side2Text = new JTextField(((Integer) oldSide2).toString(), 20);
		side2Text.addActionListener(this);
    myPanel.add (side2Text);
    myPanel.add(new JLabel("Enter the third side:"));
		side3Text = new JTextField(((Integer) oldSide3).toString(), 20);
		side3Text.addActionListener(this);
		myPanel.add (side3Text);
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
        side2 = Integer.parseInt (side2Text.getText());
        side3 = Integer.parseInt (side3Text.getText());
			}
			catch (NumberFormatException ex)
			{
        side = oldSide;
        side2 = oldSide2;
        side3 = oldSide3;
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

