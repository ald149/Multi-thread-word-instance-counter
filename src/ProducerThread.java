import java.io.*;
import java.util.LinkedList;

/**
 *  Producer Thread reads txt file into linked list/ buffer, tokenizes file into individual words
 *  pushes tokenizes words onto queue/stack
 *
 */
public class ProducerThread extends Thread {

    private final LinkedList<String> buffer;        // linked list to act as buffer/ queue
    String st;                                      // string holds tokenized words from text file
    private File file = null;                       // initialize new File object
    BufferedReader bRead = null;                    // initialize new Buffered Reader object

    /**
     * contructor
     * @param buffer
     * @param f
     * @throws IOException
     * accepts buffer and file from Main class
     */
    public ProducerThread(LinkedList<String> buffer, File f) throws IOException {
        this.buffer = buffer;
        this.file = f;
    }

    /**
     * run method that reads txt file with buffered reader, tokenizes words and pushes them back into buffer
     */
    public void run() {
       synchronized (buffer) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));  //reads file into buffered reader
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.bRead = br;

            while (true) {                                  // loop to read lines of txt file
                try {
                    if (!((st = bRead.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] tokens = st.split("\\W+");    // tokeninze file txt into words

                for (int i = 0; i < tokens.length; i++) {    // push tokenized words onto buffer
                    buffer.push(tokens[i]);
                }
            }   //  end while loop
            try {
               br.close();                                  // close file stream
           }
           catch (IOException e) {
               e.printStackTrace();
           }
       } // end syrchronized (buffer)
    }   //end run method
}   // end class ProducerThread
