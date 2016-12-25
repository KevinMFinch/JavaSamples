public class Explosion
{
	int x ;
	int y ;
	int radius ;
	boolean contracting;

	public Explosion(int xPos,int yPos, int r)
	{
		x = xPos ;
		y = yPos ;
		radius = r;
		contracting = false ;
	}

	public int getRadius()
	{
		return radius ;
	}

	public int getX()
	{
		return x ;
	}

	public int getY()
	{
		return y;
	}

	public void adjustRadius()
	{
		if(!contracting)
		{
			radius +=1 ;
		}
		else
		{
			radius -=1 ;
		}
	}

	public boolean isContracting()
	{
		return contracting ;
	}

	public void setToContract()
	{
		contracting = true ;
	}




}