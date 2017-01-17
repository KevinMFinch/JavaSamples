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

public class Enemy
{
	int x ;
	int y;
	int dir;
	double angle;

	public Enemy(int xPos,int yPos,int direction)
	{
		x=xPos;
		y=yPos;
		dir=direction;
	}

	public Enemy(int xPos,int yPos, int direction, double rads)
	{
		x=xPos;
		y=yPos;
		dir=direction;
		angle = rads;
	}

	public double getAngle()
	{
		return angle;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getDir()
	{
		return dir ;
	}

	public void changeX(int input)
	{
		x += input;
	}

	public void changeY(int input)
	{
		y+=input;
	}

	public void updateCoordinates(int xInput, int yInput,double a)
	{
		x = xInput ;
		y = yInput ;
		angle = a;
	}

	public void updateCoordinates(int xInput, int yInput)
	{
		x = xInput ;
		y = yInput ;
	}

	public void setDir(int input)
	{
		dir = input;
	}

	public Ellipse2D.Double collisionBall(int radius)
	{
		return new Ellipse2D.Double(x,y,2*radius,2*radius);
	}

}