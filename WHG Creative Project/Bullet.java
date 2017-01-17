import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Bullet
{
	String direction;
	int x ;
	int y;
	double slope;
	int yInt ;

	public Bullet(int xPos,int yPos,double sl, int b,String dir)
	{
		direction=dir;
		x=xPos;
		y=yPos;
		slope=sl;
		yInt = b;
	}

	public String getDir()
	{
		return direction;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public double getSlope()
	{
		return slope ;
	}

	public int getYInt()
	{
		return yInt;
	}

	public void updateCoordinates(int xInput, int yInput)
	{
		x = xInput ;
		y = yInput ;
	}

	public Rectangle hitBox()
	{
		return new Rectangle((int) x, (int) y, 5, 5);
	}

}