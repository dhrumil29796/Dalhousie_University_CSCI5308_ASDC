import java.util.List;

/*
 * FileName: OrderedIndexableList.java
 *
 * This class extends the IndexableList class and implements the addElement() method
 * that adds the element to the list.
 *
 * This class calls the add() method of the IndexableList class.
 */
public class OrderedIndexableList<Element extends Comparable> extends IndexableList<Element> implements OrderedIndexableInterface<Element> {

  private int leftNumElements;     // number of elements in leftArray

  private int rightNumElements;     // number of elements in rightArray

  private Object[] leftArray = new Object[0];     // declaring an object array that stores the elements of leftArray

  private Object[] rightArray = new Object[0];     // declaring an object array that stores the elements of rightArray

  /*
   * Default constructor
   */
  public OrderedIndexableList() {
  }

  /*
   * Parameterized constructor with List data type parameter
   */
  public OrderedIndexableList(List<Element> list) {
    for (Element element : list) {
      addElement(element);
    }
  }

  /*
   * Parameterized constructor with Array data type parameter
   */
  public OrderedIndexableList(Element[] array) {
    for (Element element : array) {
      addElement(element);
    }
  }

  /* Inserts the specified element into the correct position
   * in this list.
   * Parameters:
   *     e - element to be appended to this list
   * Returns:
   *     true
   * TBT
   */
  @Override
  public boolean addElement(Element element) {
    int i;
    try {
      for (i = 0; i < size(); i++) {
        if (element.compareTo(get(i)) > 0) {
          break;
        }
      }
      add(i, element);
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return true;
  }
}
