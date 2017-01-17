public class Word implements Comparable
{
    String value ;

    public Word(String word)
    {
        value = word ;
    }

    public String toString()
    {
        return value ;
    }

    public int compareTo(Object obj)
    {
        Word otherWord = (Word)obj ;
        String word1 = value ;
        String word2 = otherWord.value ;

        //To put in ascending order, take out -1*
        return -1*(word1.compareTo(word2)) ;

    }
}
