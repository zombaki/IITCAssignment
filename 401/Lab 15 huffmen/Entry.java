public class Entry implements Comparable<Entry> 
{
     int freq;

     String code;

     Entry left,
           right,
           parent;

     public int compareTo (Entry entry) 
     {
         return freq - entry.freq;
     } // compareTo
     
     public int getFreq()
     {
         return freq;
     } // method getFreq
     
     public String getCode()
     {
         return code;
     } // method getCode
     
} // class Entry
