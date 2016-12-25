public class City
{
	int x ;
	int y ;
	boolean alive ;

	public City(int xLoc,int yLoc)
	{
		x = xLoc ;
		y = yLoc ;
		alive = true ;
	}

	public int getX()
	{
		return x ;
	}

	public int getY()
	{
		return y ;
	}

	public boolean getAlive()
	{
		return alive ;
	}

	public void setDead()
	{
		alive = false ;
	}
}