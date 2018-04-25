// File: Equilateral.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Equilateral. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public final class Equilateral extends Triangle
{
    /*
     * Default Constructor.
     */
	public Equilateral ()
	{
	}

    /*
     * Copy Constructor.
     *
     * @param E	    Equilateral
     * @return		Equilateral
     */
	public Equilateral (Equilateral E)
	{
        side = E.side;
        centerX = E.centerX;
        centerY = E.centerY;
        color = E.color;
        isSelected = E.isSelected;
        hasVertices = E.hasVertices;
        angle = E.angle;
        for (int i = 0; i < 3; i++)
		{
			vertexX[i] = E.vertexX[i];
            vertexY[i] = E.vertexY[i];
            doubleVertexX[i] = E.doubleVertexX[i];
            doubleVertexY[i] = E.doubleVertexY[i];
        }    
	}
	
    /*
     * Returns the perimeter of the Equilateral.
     *
     * @param 	    none
     * @return		double
     */
	public double perimeter ()
	{
		return 3 * side;
	}

    /*
     * Returns the area of the Equilateral.
     *
     * @param 	    none
     * @return		double
     */
	public double area ()
	{
		return sqrt(3) * side * side / 4;
	}
	
    /*
     * Returns the name of the object.
     *
     * @param 	    none
     * @return		String
     */
	public String getName ()
	{
		return "Equilateral";
    }

    public void setVertices ()
    {
        if (!hasVertices) {
            double height = sqrt(pow(side, 2) - pow((side / 2), 2));

            doubleVertexX[0] = (int) (centerX - (side / 2));
            doubleVertexX[1] = centerX;
            doubleVertexX[2] = (int) (centerX + (side / 2));

            doubleVertexY[0] = (int) (centerY + (height / 3));
            doubleVertexY[1] = (int) (centerY - (height * 2 / 3));
            doubleVertexY[2] = (int) (centerY + (height / 3));
        }
        
        vertexX[0] = (int) (doubleVertexX[0] + .5);
        vertexX[1] = (int) (doubleVertexX[1] + .5);
        vertexX[2] = (int) (doubleVertexX[2] + .5);
        vertexY[0] = (int) (doubleVertexY[0] + .5);
        vertexY[1] = (int) (doubleVertexY[1] + .5);
        vertexY[2] = (int) (doubleVertexY[2] + .5);

        double s = Math.sqrt(Math.pow(vertexX[1] - vertexX[0], 2) + Math.pow(vertexY[1] - vertexY[0], 2));
        if (hasVertices) {
            side = (int) s;
            doubleSide = s;
        }

        hasVertices = false;
        //AffineTransform at = new AffineTransform ();
		//at.rotate (angle, centerX, centerY);
        polygon = new Polygon (vertexX, vertexY, 3);
    }

	public void fromString (String str)
	{
        String [] parts = str.split (" ");
		try
		{
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
			if (parts.length == 4) {
				side = Integer.parseInt(parts[2]);
				color = new Color(Integer.parseInt(parts[3]));
				setVertices ();
			}
			else if (parts.length == 5) {
                side = Integer.parseInt(parts[2]);
                color = new Color(Integer.parseInt(parts[3]));
                angle = Double.parseDouble (parts[4]);
            }
            else {
                color = new Color(Integer.parseInt(parts[2]));
                int numSides = Integer.parseInt(parts[3]);
                for (int i = 0; i < numSides; i++) {
                    doubleVertexX[i] = Double.parseDouble(parts[4 + (2 * i)]);
                    doubleVertexY[i] = Double.parseDouble(parts[5 + (2 * i)]);
                }
                hasVertices = true;
            }
            setVertices();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("File input error - invalid integer");;
        }
        System.out.println(toString());
    }

    public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}

    public void modifyShape (JFrame frame, int x, int y)
	{
		EquilateralDialog equilateralDialog = new EquilateralDialog (frame, true, x, y, side, angle); 
		if (equilateralDialog.getAnswer() == true)
		{
            side = equilateralDialog.getSide ();
            doubleSide = (double) side;
			angle = Math.toRadians(equilateralDialog.getAngle ());
			color = equilateralDialog.getColor ();
            setVertices ();
            rotate(angle);
		}
	}
}
