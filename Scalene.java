import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Scalene extends Triangle
{
    private double side2, side3;

   /*
     * Default Constructor.
     */
    public Scalene ()
    {
        side2 = 0;
        side3 = 0;
    }

    /*
     * Copy Constructor.
     *
     * @param S	    Scalene
     * @return		Scalene
     */
    public Scalene (Scalene S)
    {
        side = S.side;
        side2 = S.side2;
        side3 = S.side3;
        centerX = S.centerX;
        centerY = S.centerY;
        color = S.color;
        isSelected = S.isSelected;
        hasVertices = S.hasVertices;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] = S.vertexX[i];
            vertexY[i] = S.vertexY[i];
            doubleVertexX[i] = S.doubleVertexX[i];
            doubleVertexY[i] = S.doubleVertexY[i];
		}        
    }

   /*
     * Returns the area of the Scalene.
     *
     * @param 	    none
     * @return		double
     */
    public double area () 
    {
        double s = (side + side2 + side3) / 2;
        return Math.sqrt (s * (s - side) * (s - side2) * (s - side3));
    }

    /*
     * Returns the perimeter of the Scalene.
     *
     * @param 	    none
     * @return		double
     */
    public double perimeter ()
    {
        return side + side2 + side3;
    }

    /*
     * Returns the name of the object.
     *
     * @param 	    none
     * @return		String
     */
    public String getName ()
    {
        return "Scalene";
    }

    public void fromString (String str)
    {
        String [] parts = str.split (" ");
        try {
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
            if (parts.length == 6) {
                side = Integer.parseInt(parts[2]);
                side2 = Integer.parseInt(parts[3]);
                side3 = Integer.parseInt(parts[4]);
                color = new Color(Integer.parseInt(parts[5]));
            }
            else if (parts.length == 7) {
                side = Integer.parseInt(parts[2]);
                side2 = Double.parseDouble(parts[3]);
                side3 = Double.parseDouble(parts[4]);
                color = new Color(Integer.parseInt(parts[5]));
                angle = Double.parseDouble (parts[6]);
            }
            else {
                color = new Color(Integer.parseInt(parts[2]));
                angle = Double.parseDouble(parts[3]);
                for (int i = 0; i < 3; i++) {
                    doubleVertexX[i] = Double.parseDouble(parts[4 + (2 * i)]);
                    doubleVertexY[i] = Double.parseDouble(parts[5 + (2 * i)]);
                }
                hasVertices = true;
            }
            setVertices();
        }
        catch (NumberFormatException e) {
			//System.out.println ("Numeric input error");
        }
        System.out.println("scalene read");
        System.out.println(toString());
    }

    public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += side2 + " ";
		string += side3 + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}

    public void setVertices ()
    {
        if (! hasVertices) {
            double alpha = acos( ( (side * side) + (side2 * side2) - (side3 * side3) ) / (2 * side * side2) );
            
            double x = side * cos(alpha);
            double y = side * sin(alpha);

            doubleVertexX[0] = (int) (centerX - (side2 / 2 - x));
            doubleVertexX[1] = (int) (centerX - (side2 / 2));
            doubleVertexX[2] = (int) (centerX + (side2 / 2));
            doubleVertexY[0] = (int) (centerY - y / 2);
            doubleVertexY[1] = (int) (centerY + y / 2);
            doubleVertexY[2] = (int) (centerY + y / 2);
        }
        for (int i = 0; i < 3; i++) {
            vertexX[i] = (int) (doubleVertexX[i] + .5);
            vertexY[i] = (int) (doubleVertexY[i] + .5);
        }
        if (angle != 0)
            rotate(angle);
        hasVertices = false;
        polygon = new Polygon (vertexX, vertexY, 3);
    }

    public void modifyShape (JFrame frame, int x, int y)
	{
		ScaleneDialog scaleneDialog = new ScaleneDialog (frame, true, x, y, side, (int) side2, (int) side3, angle); 
		if (scaleneDialog.getAnswer() == true)
		{
            side = scaleneDialog.getSide ();
            doubleSide = (double) side;
            side2 = scaleneDialog.getSide2();
            side3 = scaleneDialog.getSide3();
			angle = Math.toRadians(scaleneDialog.getAngle ());
			color = scaleneDialog.getColor ();
            setVertices ();
            rotate(angle);
		}
	}
}