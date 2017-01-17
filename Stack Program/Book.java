public class Book implements Comparable
{
	String authorFirst ;
	String authorLast;
	String title;
	int rank;
	Double ratingScore ;
	int numRatings ;
	int score;
	int numVoters;

	public Book(String aF, String aL, String t, int ra, Double rS, int nR,int s, int nV)
	{
		authorFirst = aF;
		authorLast = aL;
		title = t;
		rank = ra;
		ratingScore = rS ;
		numRatings = nR;
		score = s;
		numVoters = nV;
	}

	public String getTitle()
	{
		return title;
	}

	public String getLastName()
	{
		return authorLast;
	}

	public int compareTo(Object ob)
	{
		Book otherBook=(Book)ob;
		return this.getLastName().compareTo(otherBook.getLastName());
	}

}