
public class Passenger implements Comparable
{
    String firstName ;
    String lastName ;
    String city ;
    String departTime ;
    int timeCalc ;
    int CURRENT_TIME = 903;

    public Passenger(String first,String last,String dest,String leaving,int time)
    {
        firstName = first ;
        lastName = last ;
        city = dest ;
        departTime = leaving ;
        timeCalc = time ;
    }

    public String getLastName()
    {
        return lastName ;
    }

    public String getFirstName()
    {
        return firstName ;
    }

    public String flightCity()
    {
        return city ;
    }

    public String etdCalc()
    {
        int etd = timeCalc - CURRENT_TIME ;
        String str1 = Integer.toString(etd%100) ;
        String str2 = Integer.toString(etd/100) ;
        return str2+" hours and "+str1+" minutes until flight departure." ;
    }

    public int etdHour()
    {
        int etd = timeCalc - CURRENT_TIME ;
        if(Integer.toString(etd/100).equals("0"))
        {
            return 0 ;
        }
        else
        {
            return 1 ;
        }


    }

    public String toString()
    {
        return firstName+" "+lastName+"-"+city+"-"+departTime+"-"+etdCalc() ;
    }

    public int compareTo(Object obj)
    {
        Passenger otherPs = (Passenger)obj ;
        Integer time1 = timeCalc ;
        Integer time2 = otherPs.timeCalc ;
        return time1.compareTo(time2) ;
    }
}
