import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Author: Dhrumil Rakesh Shah
 * Banner ID: B00870600
 * FileName: Tests.java
 *
 * This class is the Test class that implements the unit test cases
 */
class Tests {

  @Test
  @DisplayName("Test Case for the default constructor of IndexableList class")
  void testDefaultConstructor() {
    IndexableList<Integer> indexableList = new IndexableList<>();
    Assertions.assertTrue(indexableList.isEmpty(), "Error initializing the default IndexableList constructor");
  }

  @Test
  @DisplayName("Test Case for the parametrized constructor of IndexableList class for List")
  void parameterizedConstructorList() {
    List<Integer> lList = new ArrayList<>();
    List<Integer> rList = new ArrayList<>();
    lList.add(10);
    rList.add(20);
    IndexableList<Integer> indexableList = new IndexableList<>(lList, rList);
    Assertions.assertTrue(indexableList.contains(10) && indexableList.contains(20), "Error initializing the parameterized constructor of IndexableList for List");
  }

  @Test
  @DisplayName("Test Case for the parametrized constructor of IndexableList class for Array")
  void parameterizedConstructorArray() {
    Integer[] lArray = {10};
    Integer[] rArray = {20};
    IndexableList<Integer> indexableList = new IndexableList<>(lArray, rArray);
    Assertions.assertTrue(indexableList.contains(10) && indexableList.contains(20), "Error initializing the parameterized constructor of IndexableList for Array");
  }

  @Test
  @DisplayName("Test Case for the append() method IndexableList class")
  void append() {
    Integer[] lList = {10};
    Integer[] rList = {20};
    IndexableList<Integer> indexableList = new IndexableList<>(lList, rList);
    indexableList.append(30);
    int index = indexableList.size() - 1;
    Assertions.assertEquals(30, (int) indexableList.get(index), "Unable to append");
  }

  @Test
  @DisplayName("Test Case for the prepend() method IndexableList class")
  void prepend() {
    Integer[] lList = {10};
    Integer[] rList = {20};
    IndexableList<Integer> indexableList = new IndexableList<>(lList, rList);
    indexableList.prepend(30);
    Assertions.assertEquals(30, (int) indexableList.get(0), "Unable to prepend");
  }

  @Test
  @DisplayName("Test Case for the add() method IndexableList class")
  void add() {
    Integer[] lList = {10};
    Integer[] rList = {20};
    IndexableList<Integer> indexableList = new IndexableList<>(lList, rList);
    indexableList.add(1, 30);
    Assertions.assertEquals(30, (int) indexableList.get(1), "Unable to add the element");
  }

  @Test
  @DisplayName("Test Case for the size() method")
  void size() {
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    left.add(1);
    left.add(2);
    left.add(3);
    right.add(4);
    right.add(5);
    right.add(6);
    IndexableList<Integer> indexableList = new IndexableList<>(left, right);
    Assertions.assertEquals(6, indexableList.size());
  }

  @Test
  @DisplayName("Test Case for the remove() method")
  void remove() {
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    left.add(1);
    right.add(4);
    IndexableList<Integer> indexableList = new IndexableList<>(left, right);
    Assertions.assertEquals(1, indexableList.remove(0));

  }

  @Test
  @DisplayName("Test Case for the toScanner() method")
  void toScanner() {
    String expectedString = new Scanner("10 20 30 ").toString();
    IndexableList<Integer> indexableList = new IndexableList<>();
    indexableList.append(10);
    indexableList.append(20);
    indexableList.append(30);
    Scanner scanner = indexableList.toScanner();
    String actualString = scanner.toString();
    Assertions.assertEquals(expectedString, actualString, "Unable to return the Scanner string");
  }
}
