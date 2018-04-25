// File: Background.java
// Author: Dr. Watts
// Contents: This file contains the implementation of a small
// GUI application that uses the Shape class hierarchy.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.*;

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener
{
	private JButton saveButton;
	private JButton loadShapes;
	private boolean mouseIsInFrame = true;
	private JFrame outsideFrame;
	private int currentX, currentY;
	private ArrayList <Shape> S = new ArrayList <Shape> ();
	private Shape selectedShape = null;
	private boolean shiftIsDown, altIsDown;
	private ShapeIO shapeIO = new ShapeIO ();
	public Background ()
	{
		repaint();
	}
	public Background (JFrame frame, String [] files)
	{
		outsideFrame = frame;
		shiftIsDown = false;
		altIsDown = false;
		saveButton = new JButton ("Save Shapes");
		add (saveButton);
		saveButton.addActionListener (this);
		saveButton.setFocusable(false);
		loadShapes = new JButton ("Load Shapes");
		add (loadShapes);
		loadShapes.addActionListener (this);
		loadShapes.setFocusable(false);
		setBackground (Color.BLACK);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		for (int i = 0; i < files.length; i++)
			shapeIO.readShapes (files[i], S);
		repaint();
	}
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		System.out.println("Key pressed:" + keyCode);
		if (keyCode == KeyEvent.VK_SHIFT)
			shiftIsDown = true;
		if (keyCode == KeyEvent.VK_ALT)
			altIsDown = true;
		if (selectedShape != null) {
			if (keyCode == KeyEvent.VK_DOWN)
				selectedShape.move(0, 1);
			if (keyCode == KeyEvent.VK_UP)
				selectedShape.move(0, -1);
			if (keyCode == KeyEvent.VK_LEFT)
				selectedShape.move(-1, 0);
			if (keyCode == KeyEvent.VK_RIGHT)
				selectedShape.move(1, 0);
			if (keyCode == KeyEvent.VK_EQUALS)
				selectedShape.resize(1);
			if (keyCode == KeyEvent.VK_MINUS)
				selectedShape.resize(-1);
			if (keyCode == KeyEvent.VK_R)
				selectedShape.rotate(0.087);
			if (keyCode == KeyEvent.VK_L)
				selectedShape.rotate(-0.087);
			repaint();
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SHIFT)
			shiftIsDown = false;
		if (keyCode == KeyEvent.VK_ALT)
			altIsDown = false;
	}
	public void mouseDragged(MouseEvent e)
	{
		if ((mouseIsInFrame && selectedShape != null) && shiftIsDown)
		{
			if (e.getX() > currentX)
				selectedShape.resize(1);
			else if(e.getX() < currentX)
				selectedShape.resize(-1);
			else if(e.getY() < currentY)
				selectedShape.resize(1);
			else if(e.getY() > currentY)
				selectedShape.resize(-1);
		}
		if ((mouseIsInFrame && selectedShape != null) && altIsDown)
		{
			if (e.getX() > currentX)
				selectedShape.rotate(0.087);
			else if(e.getX() < currentX)
				selectedShape.rotate(-0.087);
			else if(e.getY() < currentY)
				selectedShape.rotate(0.087);
			else if(e.getY() > currentY)
				selectedShape.rotate(-0.087);
		}
		if ((mouseIsInFrame && selectedShape != null) && (! shiftIsDown && ! altIsDown))
		{
			selectedShape.move (e.getX() - currentX, e.getY() - currentY);
		}
		currentX = e.getX();
		currentY = e.getY();
		repaint();
	}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < S.size(); i++)
		{
			S.get(i).paintComponent (g2);
		}
	}
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == saveButton)
		{
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Shape files", "sio");
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setFileFilter(filter);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				String filename = fileChooser.getSelectedFile().getName();
				int period = filename.lastIndexOf('.');
				String extension = new String();
				if (period > 0)
					extension = filename.substring(period);
				if (!extension.equalsIgnoreCase(".sio"))
					filename += ".sio";
				shapeIO.writeShapes (filename, S);
			}
		}
		if (e.getSource() == loadShapes)
		{
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Shape files", "sio");
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setFileFilter(filter);
			if (fileChooser.showOpenDialog(outsideFrame) == JFileChooser.APPROVE_OPTION) 
			{
				String filename = fileChooser.getSelectedFile().getName();
				shapeIO.readShapes(filename, S);
			}
			repaint();
		}
	}

	public void mousePressed (MouseEvent e)
	{
		mouseIsInFrame = true;
		currentX = e.getX();
		currentY = e.getY();
		selectedShape = null;
		if (e.getButton() == e.BUTTON1) // Left mouse button
		{
			for (int i = S.size()-1; i >= 0; i--) {
				if (S.get(i).isIn(currentX, currentY)) {
					selectedShape = S.get(i);
					S.get(i).isSelected = true;
				}
				else {
					S.get(i).isSelected = false;
				}
			}
		}
		else if (e.getButton() == e.BUTTON3) // Right mouse button
		{
			for (int i = S.size()-1; selectedShape == null && i >= 0; i--)
				if (S.get(i).isIn(currentX, currentY))
					selectedShape = S.get(i);
				if (selectedShape != null)
						selectedShape.modifyShape (outsideFrame, e.getX(), e.getY());
				else
				{	
					SelectShapeDialog selectShapeDialog = new SelectShapeDialog(outsideFrame, true, e.getX(), e.getY());
					if (selectShapeDialog.getAnswer() == true)
					{
						Shape newShape = selectShapeDialog.getNewShape();
						S.add(newShape);
						newShape.setCenterX(e.getX());
						newShape.setCenterY(e.getY());
						newShape.modifyShape (outsideFrame, e.getX(), e.getY());
					}
				}
			repaint();
		}
	}
	public void mouseReleased (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) 
	{
		mouseIsInFrame = true;
	}
	public void mouseExited (MouseEvent e)
	{
		mouseIsInFrame = false;
		selectedShape = null;
	}
	public void mouseClicked (MouseEvent e) {}
}
