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

public class EskivEnemy
{
	int x ;
	int y;
	int dir;

	public EskivEnemy(int xPos,int yPos,int direction)
	{
		x=xPos;
		y=yPos;
		dir=direction;
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

	public void setDir(int input)
	{
		dir = input;
	}

}