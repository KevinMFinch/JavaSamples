public class BasketballPlayer
{
	String name ;
	String position ;

	public BasketballPlayer(String n, String pos)
	{
		name = n ;
		position = pos ;
	}

	public String getName()
	{
		return name ;
	}

	public String getPos()
	{
		return position ;
	}

	public String toString()
	{
		return name +" "+position ;
	}

}