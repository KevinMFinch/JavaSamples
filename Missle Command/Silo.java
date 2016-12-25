public class Silo
{
	int missles ;
	boolean alive ;
	int xLoc ;

	public Silo(int x)
	{
		missles = 10 ;
		alive = true ;
		xLoc = x ;
	}

	public int getX()
	{
		return xLoc ;
	}

	public int getMissles()
	{
		return missles ;
	}

	public void loseMissle()
	{
		missles-- ;
	}

	public void reload()
	{
		missles = 10;
	}

}