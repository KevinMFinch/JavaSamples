public class Person implements Comparable
{
	String firstName ;
	String lastName ;
	int birthMonth ;
	int dayOfBirth ;
	int yearOfBirth ;

	public Person(String fN, String lN, int month, int day, int year)
	{
		firstName = fN ;
		lastName = lN ;
		birthMonth = month ;
		dayOfBirth = day ;
		yearOfBirth = year ;
	}

	public int compareTo(Object ob)
	{
		Person obj = (Person)ob ;

		if(getYear() != obj.getYear())
		{
			if(getYear() > obj.getYear())
			{
				return 1 ;
			}
			else return -1 ;
		}
		else if(getMonth() != obj.getMonth())
		{
			if(getMonth() > obj.getMonth())
			{
				return 1 ;
			}
			else return -1 ;
		}
		else if(getDay() != obj.getDay())
		{
			if(getDay() > obj.getDay())
			{
				return 1 ;
			}
			else return -1 ;
		}
		else if(getLastName().equals(obj.getLastName()))
		{
			return getLastName().compareTo(obj.getLastName()) ;
		}
		else if(getFirstName().equals(obj.getFirstName()))
		{
			return getFirstName().compareTo(obj.getFirstName()) ;
		}
		else return 0 ;

	}

	public String getFirstName()
	{
		return firstName ;
	}

	public String getLastName()
	{
		return lastName ;
	}

	public int getMonth()
	{
		return birthMonth ;
	}

	public int getDay()
	{
		return dayOfBirth ;
	}

	public int getYear()
	{
		return yearOfBirth ;
	}

}