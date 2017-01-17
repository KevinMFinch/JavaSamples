import java.util.* ;
import java.io.* ;

public class BowlerDriver
{
	BufferedReader input ;
	TreeMap<Integer,PriorityQueue<Bowler>> tMap = new TreeMap<Integer,PriorityQueue<Bowler>>() ;

	public BowlerDriver()
	{
		try
		{

			input = new BufferedReader(new FileReader(new File("BowlingData.txt")));
			String text = "";
			while ((text = input.readLine()) != null)
			{
				String parts[] = text.split(" ") ;
				if (tMap.containsKey(Integer.parseInt(parts[2])))
				{
					PriorityQueue<Bowler> pastValues = tMap.get(Integer.parseInt(parts[2]));
					pastValues.add(new Bowler(parts[0],parts[1],Integer.parseInt(parts[2])));
					tMap.put(Integer.parseInt(parts[2]), pastValues);
				}
				else
				{
					PriorityQueue<Bowler> values = new PriorityQueue<Bowler>();
					values.add(new Bowler(parts[0],parts[1],Integer.parseInt(parts[2])));
					tMap.put(Integer.parseInt(parts[2]), values);
				}
			}
		} catch (IOException io)
		{

        }

        Iterator it = tMap.values().iterator() ;
        while(it.hasNext())
        {
			PriorityQueue<Bowler> pq = (PriorityQueue<Bowler>) it.next() ;
			while(pq.peek() != null)
			{
				System.out.println(pq.remove()) ;
			}
			System.out.println() ;
		}

	}

	public static void main(String args[])
	{
		BowlerDriver app = new BowlerDriver() ;
	}
}