import java.awt.geom.Line2D ;

public class ExtendingLine
{
	int startX ;
	int startY ;
	int currentX ;
	int currentY ;
	int endX ;
	int endY ;
	int yInt ;
	double slope ;
	boolean active = false ;

	public ExtendingLine(int x,int y, int xx, int yy, int b, double m)
	{
		startX = x ;
		startY = y ;
		currentX = x ;
		currentY = y ;
		endX = xx ;
		endY = yy ;
		yInt = b ;
		slope = m ;
	}

	public int getCurrentX()
	{
		return currentX ;
	}

	public int getCurrentY()
	{
		return currentY ;
	}

	public int getStartX()
	{
		return startX ;
	}

	public int getStartY()
	{
		return startY ;
	}

	public int getEndX()
	{
		return endX ;
	}

	public int getEndY()
	{
		return endY ;
	}

	public int getYInt()
	{
		return yInt ;
	}

	public double getSlope()
	{
		return slope ;
	}

	public void updateLine()
	{
			currentY-- ;
			currentX = (int)((currentY - yInt + 0.0) / slope) ;
	}

	public void updateLine(int x)
	{
		currentY++ ;
		currentX = (int)((currentY - yInt + 0.0) / slope) ;
	}

	public void setActive()
	{
		active = true ;
	}

	public boolean isActive()
	{
		return active ;
	}

}