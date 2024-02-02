/**
 * This program takes a text file, creates an index (by line numbers)
 *  for all the words in the file and writes the index
 *  into the output file.  The program takes input and output file names
 *  from the command-line args or prompts the user for the file names.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IndexMaker
{
  public static void main(String[] args) throws IOException
  {
    Scanner keyboard = new Scanner(System.in);
    String fileName;

    // Open input file:

    if (args.length > 0)
      fileName = args[0];
    else
    {
      System.out.print("\nEnter input file name: ");
      fileName = keyboard.nextLine().trim();
    }

    BufferedReader inputFile =
                 new BufferedReader(new FileReader(fileName), 1024);

    // Create output file:

    if (args.length > 1)
      fileName = args[1];
    else
    {
      System.out.print("\nEnter output file name: ");
      fileName = keyboard.nextLine().trim();
    }

    PrintWriter outputFile =
                 new PrintWriter(new FileWriter(fileName));

    // Create index:

    DocumentIndex index = new DocumentIndex();
    ArrayList<IndexEntry> indexList = index.getTest(); //create variable to store the "test" from document index

    String line;
    int lineNum = 0;
    while ((line = inputFile.readLine()) != null)
    {
      lineNum++;
      index.addAllWords(line, lineNum);
    }
    
    //Sorting list with Comparators: 
    
//    Collections.sort(indexList, new ComparatorAlphabetically(false));
//    Collections.sort(indexList, new ComparatorAlphabetically(true));
    Collections.sort(indexList, new ComparatorFrequency());
    
    // Save index:

    for (IndexEntry entry : indexList)
      outputFile.println(entry);

    // Finish:

    inputFile.close();
    outputFile.close();

    keyboard.close();
    System.out.println("Done.");
  }
}
