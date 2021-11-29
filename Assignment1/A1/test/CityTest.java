import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    public static final String city1Name = "City1";
    public static final String city2Name = "City2";
    public static final String city3Name = "City3";
    public static final int cityDistance = 3;

    /* addLink adds the link to each linked city's HashSet for city1 & city2*/
    @Test
    void addLink_Test1() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        Link l = new Link(c1, c2, cityDistance);
        c1.addLink(l);
        c2.addLink(l);
        assertTrue(c1.links.contains(l), "City1 does not contain link in its HashSet");
        assertTrue(c2.links.contains(l), "City2 does not contain link in its HashSet");
    }

    /* addLink adds the link to each linked city's HashSet for city1 & city2 and city1 & city3*/
    @Test
    void addLink_Test2() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        City c3 = new City(city3Name);
        Link l1 = new Link(c1, c3, cityDistance);
        Link l2 = new Link(c1, c2, cityDistance);
        c1.addLink(l1);
        c3.addLink(l1);
        c1.addLink(l2);
        c2.addLink(l2);
        assertTrue(c1.links.contains(l1), "City1 does not contain link in its HashSet");
        assertTrue(c3.links.contains(l1), "City3 does not contain link in its HashSet");
        assertTrue(c1.links.contains(l2), "City1 does not contain link in its HashSet");
        assertTrue(c2.links.contains(l2), "City2 does not contain link in its HashSet");
    }

    /* adding an already added link to the HashSet */
    @Test
    void addLink_Test3() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        Link l = new Link(c1, c2, cityDistance);
        c1.addLink(l);
        c2.addLink(l);
        assertTrue(c1.links.contains(l), "City1 does not contain link in its HashSet");
        assertTrue(c2.links.contains(l), "City2 does not contain link in its HashSet");
        // Adding the already added link
        c1.addLink(l);
        c2.addLink(l);
        assertTrue(c1.links.contains(l), "City1 link already added");
        assertTrue(c2.links.contains(l), "City2 link already added");
    }

    /* compareTo() returns 0 if both cities have same name */
    @Test
    void compareTo_zero1() {
        City c1 = new City(city1Name);
        City c2 = new City(city1Name);
        assertEquals(0, c1.compareTo(c2), "Both cities have same name");
    }

    /* compareTo() returns 0 if same city is compared with itself */
    @Test
    void compareTo_zero2() {
        City c1 = new City(city1Name);
        assertEquals(0, c1.compareTo(c1), "Both cities are the same");
    }

    /* compareTo() returns negative if city1 name < city2 name */
    @Test
    void compareTo_negative1() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        City c3 = new City(city3Name);
        assertTrue(c1.compareTo(c2) < 0, "Returns negative if city1 name < city2 name");
        assertTrue(c1.compareTo(c3) < 0, "Returns negative if city1 name < city2 name");
    }

    /* compareTo() returns positive if city1 name > city2 name */
    @Test
    void compareTo_positive1() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        City c3 = new City(city3Name);
        assertTrue(c2.compareTo(c1) > 0, "Returns positive if city1 name > city2 name");
        assertTrue(c3.compareTo(c1) > 0, "Returns positive if city1 name > city2 name");
    }

    /* toString() returns the name of the current city  */
    @Test
    void toString_Test() {
        City c1 = new City(city1Name);
        assertEquals(city1Name, c1.toString(), "Wrong city name returned");
    }

    /* compare() returns zero for the same distance */
    @Test
    void compare_zero() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        c1.distance = 1;
        c2.distance = 1;
        assertEquals(0, c1.compare(c1, c2), "Expected zero result as distance is same");
    }

    /* compare() returns negative result as city1 distance is shorter */
    @Test
    void compare_positive() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        c1.distance = 1;
        c2.distance = 2;
        assertTrue(c1.compare(c1, c2) < 0, "Expected negative result as city1 distance is shorter");
    }

    /* compare() returns positive as city1 distance is longer */
    @Test
    void compare_negative() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        c1.distance = 2;
        c2.distance = 1;
        assertTrue(c1.compare(c1, c2) > 0, "Expected positive result as city1 distance is longer");
    }

    /* getLinksTo() returns true if path of links is found from start city to end city */
    @Test
    void getLinksTo_Test1() {
        City c1 = new City("Delhi");
        City c2 = new City("Mumbai");
        City c3 = new City("Telangana");
        City c4 = new City("Hyderabad");
        Link l1 = new Link(c1, c2, 5);
        Link l2 = new Link(c2, c3, 5);
        Link l3 = new Link(c3, c4, 5);
        l1.setUsed(true);
        l2.setUsed(true);
        l3.setUsed(true);
        c1.parent = null;
        c2.parent = l1;
        c3.parent = l2;
        c4.parent = l3;
        Set<Link> set = new HashSet<>();
        assertTrue(c1.getLinksTo(c4, set), "Path of links not found");
    }

    /* getLinksTo() returns false if path of links is not found from start city to end city */
    @Test
    void getLinksTo_Test2() {
        City c1 = new City("Delhi");
        City c2 = new City("Mumbai");
        City c3 = new City("Telangana");
        City c4 = new City("Hyderabad");
        Link l1 = new Link(c1, c2, 5);
        Link l3 = new Link(c3, c4, 5);
        l1.setUsed(true);
        l3.setUsed(true);
        c1.parent = null;
        c2.parent = l1;
        c4.parent = l3;
        Set<Link> set = new HashSet<>();
        assertFalse(c1.getLinksTo(c4, set), "Path of links not found");
    }

    /* getLinksTo() returns false as only one link is missing to the destination city */
    @Test
    void getLinksTo_Test3() {
        City c1 = new City("Delhi");
        City c2 = new City("Mumbai");
        City c3 = new City("Telangana");
        City c4 = new City("Hyderabad");
        Link l1 = new Link(c1, c2, 5);
        Link l3 = new Link(c3, c4, 5);
        l1.setUsed(true);
        l3.setUsed(true);
        c1.parent = null;
        c2.parent = l1;
        c3.parent = l3;
        Set<Link> set = new HashSet<>();
        assertFalse(c1.getLinksTo(c4, set), "Path of links not found");
    }

}
