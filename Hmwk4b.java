// File: Hmwk4a.java
// Author: Dr. Watts
// Contents: This file contains the implementation of a small
// GUI application that uses the Shape class hierarchy.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Hmwk4b
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Homework 4B");
		Background background = new Background (args);
		frame.getContentPane().add (background);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize (600,600);
		frame.setVisible (true);
		frame.setResizable (false);
		frame.setLocation (200, 200);
		frame.addKeyListener (background);
	}

}
