import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *  Main class that runs txt reading/word counting program
 */
public class Main {

    public static void main(String[] args) throws IOException {

        LinkedList<String> buffer = new LinkedList<>();     // linked list to act as buffer/queue
        Scanner input = new Scanner(System.in);             // scanner to input file name
        String fileName;                                    // string to hold name of file



        System.out.print("Enter name of file to read: ");   // prompt user for input
        fileName = input.nextLine();                        // assign input to string fileName

        File f = new File(fileName);                        // new File object
        ProducerThread pt = new ProducerThread(buffer, f);  //ProducerThread object
        pt.start();                                         // start ProducerThread
        ConsumerThread ct = new ConsumerThread(buffer);     // new ConsumerThread object
        ct.start();                                         // start ConsumerThread
    } // end of method main
} // end of class Main
