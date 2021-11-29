import java.util.List;

/*
 * Author: Dhrumil Rakesh Shah
 * Banner ID: B00870600
 * FileName: IndexableInterface.java
 *
 * This interface is implemented by the IndexableList class
 */
public interface IndexableInterface<Element> {

  boolean append(Element element);

  boolean prepend(Element element);

  void add(int index, Element element);

  boolean contains(Object object);

  Element get(int index);

  int indexOf(Object object);

  boolean isEmpty();

  Element remove(int index);

  boolean remove(Object object);

  Element set(int index, Element element);

  int size();

  int errorValue();

  List<Element> subList(int fromIndex, int toIndex);
}
