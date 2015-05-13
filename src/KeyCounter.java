import java.io.*;
import java.util.*;

public class KeyCounter{
    private static String line;                //String for reading a line from the buffered reader
    private static String keyName;             //String for the key name; used with iterator
    private static BufferedReader inFile;      //object for BufferedReader
    private static Map finalMap;               //HashMap for holding final values
    private static int keyCount;               //int for keeping track of count from ArrayList
    private static int mapCount;               //int for keeping track of count from HashMap
    private static int finalCount;             //int to hold the final count of keys
    private static ArrayList<String> key_pair; //String ArrayList to hold key-pairs derived from line object from buffered reader
    private static Set<String> keySet;         //set to hold all of the keys in the HashMap. This is more for printing than logic really.
    private static Iterator<String> keyIter;   //Iterator to help print the final map

    public KeyCounter(String fileName){
        try {
            //Creating a BufferedReader object to read in input file
            inFile = new BufferedReader(new FileReader(fileName));
            //Initiating key_pair
            key_pair = new ArrayList<>();
            //formatting the input from file
            key_pair = formatBufferedReader(key_pair,inFile);
            inFile.close();
            //Instantiating the new HashMap to hold strings
            finalMap = new HashMap<String,String>();
            //Loading the keys into the HashMap and initiating with 0's
            for(int i=0;i<key_pair.size();i+=2){ //offset by 2 to account for key then value
                finalMap.put(key_pair.get(i),0);
            }//End for
            //Each key receives their total count here.
            finalMap = getTotalCounts(key_pair,finalMap);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: Check FilePath");
        } catch (IOException e) {
            System.out.println("Cannot not readLine() or infile.close()");
            e.printStackTrace();
        }//End try-catch
    }//End Default Constructor
    //@@Formatting the input from file
    private ArrayList formatBufferedReader(ArrayList emptyArList, BufferedReader bufRead){
        try {
            //Sentinel value
            line = bufRead.readLine();
            while (line != null) {
                //Check to see if line has anything. If not, move it along
                if (!line.isEmpty()) {
                    //Trim any whitespace and convert everything to lowercase
                    line = line.trim();
                    //Car Model
                    emptyArList.add(line.split(",")[0]);
                    //Counting cars
                    emptyArList.add(line.split(",")[1]);
                    //Reinitialize Sentinel value
                    line = inFile.readLine();
                } else {
                    line = inFile.readLine();
                }//End if
            }//End while
        } catch (IOException e) {
            System.out.println("Cannot not readLine() or infile.close()");
            e.printStackTrace();
        }//End try-catch
        return emptyArList;
    }//End private method
    //@@Adding up totals for each key
    private Map getTotalCounts(ArrayList<String> keyPairs, Map finalMapping){
        //This is where the magic happens: each key receives their total count here.
        for(int i=0;i<keyPairs.size();i+=2){
            //First, get the current value for a key in the map
            mapCount= (int) finalMapping.get(keyPairs.get(i));
            //Then, get the value for said key from ArrayList
            keyCount = Integer.parseInt(keyPairs.get(i+1)); //The ArrayList keyCount
            //Next, Add the map's count for a given key and the ArrayList value
            finalCount = mapCount + keyCount;
            //Finally, look for a given key in the map and replace it's value with the final count
            finalMapping.put(keyPairs.get(i),finalCount);
        }
        return finalMapping;
    }//End private method
    //@@Method to nicely print everything
    public void printTotals(){
        //Putting the  keys in a set to make it easier to iterate over
        keySet = finalMap.keySet();
        //Creating the iterator for keySet
        keyIter = keySet.iterator();
        //Iterating and printing
        while(keyIter.hasNext()){
            keyName = keyIter.next();
            System.out.println("The total for "+keyName+" is: "+finalMap.get(keyName));
        }//End while
    }//End public method

}//End class KeyCounter
