import java.util.ArrayList;
import java.util.List;

/*
 * FileName: IndexableList.java
 *
 * This class implements an indexable list, which is a list whose
 * elements can be efficiently accessed by their indices in the list.
 * i.e., the first element has index 0, the second has index 1, etc.
 *
 * This class uses two arrays to speed up insertion and deletion.
 * Inserting an element into an indexable list requires making space for the new
 * element by shuffling other elements. If implemented with only one array then typically
 * this is done by moving all elements at the desired index or greater to the right one
 * position in the array. In this class, elements are moved to the left one position if
 * that requires less shuffling. Deletion is similar.
 *
 * IndexableList uses two arrays (leftArray and rightArray) to store the elements.
 * When either array fills up, a bigger array is allocated. The element with index 0
 * can be at an arbitrary point in either list.
 *
 */

public class IndexableList<Element> extends ScannerClass implements IndexableInterface<Element> {

  private final int ERROR_VALUE = -3;     // constant to store the error value which is returned in case of an error

  private final int EXTEND_LENGTH = 1000;     // constant to store the value by which the array will be extended

  protected int numElementLeftArray;     // number of elements in leftArray

  protected int numElementRightArray;    // number of elements in rightArray

  protected Object[] leftArray = new Object[0];     // declaring an object array that stores the elements of leftArray

  protected Object[] rightArray = new Object[0];     // declaring an object array that stores the elements of rightArray

  /*
   * Default constructor
   */
  public IndexableList() {
  }

  /*
   * Parameterized constructor with List data type parameter
   */
  public IndexableList(List<Element> leftList, List<Element> rightList) {
    numElementLeftArray = leftList.size();
    numElementRightArray = rightList.size();
    rightArray = new Object[numElementRightArray];
    leftArray = new Object[numElementLeftArray];

    for (int i = 0; i < numElementLeftArray; i++) {
      leftArray[i] = leftList.get(numElementLeftArray - 1 - i);
    }

    for (int i = 0; i < numElementRightArray; i++) {
      rightArray[i] = rightList.get(i);
    }
  }

  /*
   * Parameterized constructor with Array data type parameter
   */
  public IndexableList(Element[] lArray, Element[] rArray) {
    numElementLeftArray = lArray.length;
    numElementRightArray = rArray.length;
    rightArray = new Object[numElementRightArray];
    leftArray = new Object[numElementLeftArray];

    for (int i = 0; i < numElementLeftArray; i++) {
      leftArray[i] = lArray[numElementLeftArray - 1 - i];
    }

    for (int i = 0; i < numElementRightArray; i++) {
      rightArray[i] = rArray[i];
    }
  }

  /* Appends the specified element to the end of the right list
   * Parameters:
   *     element - element to be appended to this list
   * Returns:
   *     true
   */
  @Override
  public boolean append(Element element) {
    add(size(), element);
    return true;
  }

  /* Appends the specified element to the end of the left list
   * Parameters:
   *     element - element to be appended to this list
   * Returns:
   *     true
   */
  @Override
  public boolean prepend(Element element) {
    add(0, element);
    return true;
  }

  /* Inserts the specified element at the specified
   * position in this list.
   * Parameters:
   *    index - index at which the specified element is
   *            to be inserted
   *    element - element to be inserted
   */
  @Override
  public void add(int index, Element element) {
    int halfSize = ((size() + 1) / 2);
    try {
      if (index < halfSize) {
        if (leftArray.length == numElementLeftArray) {
          extendLeftArray(EXTEND_LENGTH);
        }
        numElementLeftArray++;
        for (int i = 0; i < index; i++) {
          set(i, get(i + 1));
        }
      } else {
        if (rightArray.length == numElementRightArray) {
          extendRightArray(EXTEND_LENGTH);
        }
        numElementRightArray++;
        int size = size();
        for (int i = size() - 1; i > index; i--) {
          set(i, get(i - 1));
        }
      }
      set(index, element);
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  /* Extends the length of the leftArray
   * Parameters:
   *    extend - size by which the array will be extended
   */
  private void extendLeftArray(int extend) {
    Object[] prevArray = leftArray;
    try {
      leftArray = new Object[leftArray.length + extend];
      for (int i = 0; i < numElementLeftArray; i++) {
        leftArray[i] = prevArray[i];
      }
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  /* Extends the length of the rightArray
   * Parameters:
   *    extend - size by which the array will be extended
   */
  private void extendRightArray(int extend) {
    Object[] prevArray = rightArray;
    try {
      rightArray = new Object[prevArray.length + extend];
      for (int i = 0; i < numElementRightArray; i++) {
        rightArray[i] = prevArray[i];
      }
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  /* Checks whether the object is present in the left or right array
   * Parameters:
   *    object -  object that needs to be checked if present in either of the array
   */
  @Override
  public boolean contains(Object object) {
    try {
      for (int i = 0; i < numElementLeftArray; i++) {
        if (leftArray[i].equals(object)) {
          return true;
        }
      }

      for (int i = 0; i < numElementRightArray; i++) {
        if (rightArray[i].equals(object)) {
          return true;
        }
      }
    } catch (NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return false;
  }

  /* Gets the element at the passed index
   * Parameters:
   *    index - index whose element needs to be get
   */
  @Override
  public Element get(int index) {
    try {
      if (index < numElementLeftArray) {
        return (Element) leftArray[numElementLeftArray - 1 - index];
      }
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return (Element) rightArray[index - numElementLeftArray];
  }

  /* Returns the index of the first occurrence of the
   * specified element in this list, or -3 → ERROR_VALUE
   * if this list does not contain the element.
   * Parameters:
   *     object - element to search for
   *  Returns:
   *     the index of the first occurrence of the specified
   *     element in this list, or -3 if this list does not
   *     contain the element
   */
  @Override
  public int indexOf(Object object) {
    try {
      for (int i = numElementLeftArray - 1; i >= 0; i--) {
        if (leftArray[i].equals(object)) {
          return numElementLeftArray - i - 1;
        }
      }

      for (int i = 0; i < numElementRightArray; i++) {
        if (rightArray[i].equals(object)) {
          return numElementLeftArray + i;
        }
      }
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return ERROR_VALUE;
  }

  /* Returns true if this list contains no elements.
   * Returns:
   *     true if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  /* Removes the element at the specified position in
   * this list
   * Parameters:
   *     index - the index of the element to be removed
   * Returns:
   *     the element previously at the specified position
   */
  @Override
  public Element remove(int index) {
    Element removedItem = get(index);
    int halfSize = ((size() + 1) / 2);
    try {
      if (index < halfSize && numElementLeftArray > 0) {
        for (int i = index; i > 0; i--) {
          set(i, get(i - 1));
        }
        set(0, null);
        numElementLeftArray--;
      } else {
        int size = size();
        for (int i = index; i < size - 1; i++) {
          set(i, get(i + 1));
        }
        set(size - 1, null);
        numElementRightArray--;
      }
    } catch (IndexOutOfBoundsException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return removedItem;
  }

  /* Removes the first occurrence of the specified element
   * from this list, if it is present.
   * Parameters:
   *     o - element to be removed from this list, if present
   * Returns:
   *     true if this list contained the specified element
   */
  @Override
  public boolean remove(Object object) {
    int index = indexOf(object);
    if (index > ERROR_VALUE) {
      remove(index);
    }
    return index > ERROR_VALUE;
  }

  /* Replaces the element at the specified position in this
   * list with the specified element.
   * Parameters:
   *     index - index of the element to replace
   *     element - element to be stored at the specified position
   * Returns:
   *     the element previously at the specified position
   */
  @Override
  public Element set(int index, Element element) {
    Element elementNumber = get(index);
    try {
      if (index < numElementLeftArray) {
        leftArray[numElementLeftArray - 1 - index] = element;
      } else {
        rightArray[index - numElementLeftArray] = element;
      }
    } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return elementNumber;
  }

  /* Returns the size of the entire array
   * The length of both the left and right arrays is added
   */
  @Override
  public int size() {
    return numElementLeftArray + numElementRightArray;
  }

  /* Returns the error value -3 → ERROR_VALUE
   * Returns:
   *      -3 → ERROR_VALUE
   */
  @Override
  public int errorValue() {
    return ERROR_VALUE;
  }

  /* Returns a view of the portion of this list between
   * the specified fromIndex, inclusive, and toIndex, exclusive.
   * Parameters:
   *     fromIndex - low endpoint (inclusive) of the subList
   *     toIndex - high endpoint (exclusive) of the subList
   * Returns:
   *     a view of the specified range within this list
   */
  @Override
  public List<Element> subList(int fromIndex, int toIndex) {
    List<Element> list = new ArrayList<>();
    try {
      for (int i = fromIndex; i < toIndex; i++) {
        list.add(get(i));
      }
    } catch (IndexOutOfBoundsException exception) {
      System.out.println("Error: " + exception.getMessage());
    }
    return list;
  }
}
