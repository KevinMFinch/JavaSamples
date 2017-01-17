import java.io.* ;
import java.util.* ;

public class SetDriver
{
	BufferedReader input ;
	HashSet<Person> hSet = new HashSet<Person>() ;
	TreeSet<Person> tSet = new TreeSet<Person>() ;

	public SetDriver()
	{
		try
		{

			input = new BufferedReader(new FileReader(new File("PersonList.txt")));
			String text = "";
			while ((text = input.readLine()) != null)
			{
				String parts[] = text.split(" ");
				String firstName = parts[0] ;
				String lastName = parts[1] ;
				String[] dateParts = parts[2].split("/") ;
				int month = Integer.parseInt(dateParts[0]) ;
				int day = Integer.parseInt(dateParts[1]) ;
				int year = Integer.parseInt(dateParts[2]) ;
				Person person = new Person(firstName,lastName,month,day,year) ;
				hSet.add(person) ;
				tSet.add(person) ;

			}
		} catch (IOException io)
		{

        }
        //hSet iteration
        Iterator it = hSet.iterator() ;
        System.out.println("Iterate through the HashSet:") ;
        while(it.hasNext())
        {
			Person guy = (Person)it.next() ;
			System.out.println(guy.getFirstName()+","+guy.getLastName()+" Birth Year:"+guy.getYear()+" Birth Month:"+guy.getMonth()+" Birth Day:"+guy.getDay()+" HashValue:"+guy.hashCode()) ;
			System.out.println();
		}
		System.out.println();

		it = tSet.iterator() ;
		System.out.println("Iterate through the TreeSet:") ;
		while(it.hasNext())
		{
			Person guy = (Person)it.next() ;
			System.out.println(guy.getFirstName()+","+guy.getLastName()+" Birth Year:"+guy.getYear()+" Birth Month:"+guy.getMonth()+" Birth Day:"+guy.getDay()+" HashValue:"+guy.hashCode()) ;
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String args[])
	{
		SetDriver app = new SetDriver() ;
	}

}