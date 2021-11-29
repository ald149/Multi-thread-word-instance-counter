import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *  class Consumer Thread sorts linked list into hashmap, sorts words and counts occurrences of each
 * @ Andy Darby ald149
 */

public class ConsumerThread extends Thread {
    Map<String, Integer> myMap = new HashMap<String, Integer>();    // create new hashmap to store words
    private final LinkedList<String> buffer;                        // linked list to act as buffer
    private String[] words;                                         // string to hold words from buffer
    private String mostFrequent = null;                             // string to hold name of frequent word

    /**
     * constructor
     * @param buffer
     */
    public ConsumerThread(LinkedList<String> buffer) {
        this.buffer = buffer;
    }

    /**
     *  run method to move tokenized words from buffer to a string
     *  creates hashmap, calculates frequency of each word and displays results
     *
     */
    public void run() {
        synchronized (buffer) {

            for (int i = 0; i < buffer.size(); i++) {               // move words from buffer to string array
                words = buffer.toArray(new String[buffer.size()]);
            }
            createMap(myMap, words);                                // call function to create hashmap
            frequency(words);                               //call method to calculate frequency of words
            displayMap(myMap);                              // call display function
        } // end of synchronized (buffer)
    }//end of method run

    /**
     * frequency method calculates frequency of each word in the buffer - > array
     * @param words
     */
    private void frequency(String[] words) {

        int tally = 0;                                  // int to act as counter
        Set<Map.Entry<String, Integer>> entrySet = myMap.entrySet();

        for (Map.Entry<String, Integer> entry : entrySet)
        {
            if(entry.getValue() > tally)
            {
                mostFrequent = entry.getKey();
                tally = entry.getValue();
            }
        }
        System.out.println("Most frequent word is: " + mostFrequent);
        System.out.println("It occurs " + tally + " times." );
    }// end of method frequency


    /**
     * createMap method copies tokenized words from array and adds them to newly formed hashmap for sorting
     * counting
     * @param map
     * @param words
     */
    private void createMap(Map<String, Integer> map, String[] words) {
        for (String token : words){
            String word = token.toLowerCase();                  // convert all words to lowercase

            if (myMap.containsKey(word)){
                int count = map.get(word);
                map.put(word, count + 1);
            }
            else
                map.put(word, 1);
        }
    }   //end of method createMap

    /**
     * displayMap method that displays words from the txt and the frequency of each
     * @param map
     */
    private void displayMap(Map<String, Integer> map) {
        Set< String > keys = map.keySet();
        TreeSet< String > sortedKeys = new TreeSet< String>(keys);
        System.out.println("\nKey \t\t Value");

        for (String key: sortedKeys) {
            System.out.printf("%-10s%10s\n", key, map.get(key));
        }
    }//end of method displayMap
}// Ends class ConsumerThread
