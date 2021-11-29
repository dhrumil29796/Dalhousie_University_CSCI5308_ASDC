import java.util.Scanner;

/*
 * Author: Dhrumil Rakesh Shah
 * Banner ID: B00870600
 * FileName: ScannerClass.java
 *
 * This class implements the ScannerInterface and a toScanner() method
 * that returns the Scanner object with a string containing all the elements in the indexableList
 *
 * This class calls the get() method of the IndexableList class.
 */
public class ScannerClass implements ScannerInterface {

  /* Returns a Scanner containing the elements represented
   * by their toString() method.
   */
  @Override
  public Scanner toScanner() {
    IndexableList<Integer> indexableList = new IndexableList<>();
    String string = "";
    int sizeOfLeftAndRightArray = indexableList.size();
    for (int i = 0; i < sizeOfLeftAndRightArray; i++) {
      string += indexableList.get(i).toString() + " ";
    }
    return new Scanner(string);
  }
}
