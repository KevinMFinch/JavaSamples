public class Bowler implements Comparable
{
	String firstName ;
	String lastName ;
	int score ;

	public Bowler(String fN, String lN, int sc)
	{
		firstName = fN ;
		lastName = lN ;
		score = sc ;
	}

	public int compareTo(Object obj)
	{
		Bowler ob = (Bowler)obj ;
		return lastName.compareTo(ob.getLastName()) ;
	}

	public String getLastName()
	{
		return lastName ;
	}

	public String toString()
	{
		return firstName + " "+lastName+" " ;
	}

}