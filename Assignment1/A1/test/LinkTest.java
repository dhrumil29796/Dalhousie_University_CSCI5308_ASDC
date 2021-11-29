import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class LinkTest {

    public static final String city1Name = "City1";
    public static final String city2Name = "City2";
    public static final String city3Name = "City3";
    public static final int cityDistance = 3;

    /* getLength() returns the length of a link */
    @Test
    void getLength() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        Link l = new Link(c1, c2, cityDistance);
        assertEquals(3, l.getLength(), "Error. The length returned is wrong");
    }

    /* getAdj() returns city1 if c is city2 */
    @Test
    void getAdj_city1() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        Link l = new Link(c1, c2, cityDistance);
        assertSame(l.getAdj(c2), c1, "Wrong adjacent city returned");
    }

    /* getAdj() returns city2 if c is city1 */
    @Test
    void getAdj_city2() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        Link l = new Link(c1, c2, cityDistance);
        assertSame(l.getAdj(c1), c2, "Wrong adjacent city returned");
    }

    /* for unspecified behaviour if passed city is not city1 or city2 */
    @Test
    void getAdj_city3() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        City c3 = new City(city3Name);
        Link l = new Link(c1, c2, cityDistance);
        assertNull(l.getAdj(c3), "Returns NULL if any other city is passed other than city1 & city2");
    }

    /* for unspecified behaviour if passed city is null */
    @Test
    void getAdj_city4() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        City c3 = new City(city3Name);
        Link l = new Link(c1, c2, cityDistance);
        assertNull(l.getAdj(null), "Returns NULL if null is passed");
    }

    /* isUsed() returns true if the link is on a shortest path */
    @Test
    void isUsed_true() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new Link(city1, city2, cityDistance);
        link.setUsed(true);
        assertTrue(link.isUsed(), "true was not returned on the path");
    }

    /* isUsed() returns false if the link is not  on a shortest path */
    @Test
    void isUsed_false() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new Link(city1, city2, cityDistance);
        link.setUsed(false);
        assertFalse(link.isUsed(), "false was not returned on the path");
    }


    /* setUsed() set to true */
    @Test
    void setUsed_true() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new Link(city1, city2, cityDistance);
        link.setUsed(true);
        assertTrue(link.used, "user was not set to true");
    }

    /* setUsed() set to true */
    @Test
    void setUsed_false() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new Link(city1, city2, cityDistance);
        link.setUsed(false);
        assertFalse(link.used, "user was not set to false");
    }

    /* */
    @Test
    void linkName_test() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link l = new Link(city1, city2, cityDistance);
        assertEquals(l.linkName(), city1Name + cityDistance + city2Name, "Wrong String format returned");
    }

    /* compareTo() returns 0 if both links have the same city1.name and city2.name */
    @Test
    void compareTo_zero1() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link1 = new Link(city1, city2, cityDistance);
        Link link2 = new Link(city1, city2, cityDistance);
        assertEquals(0, link1.compareTo(link2), "Both links have same city1.name and city2.name");
    }

    /* compareTo() returns 0 if both links have the combination of city1.name = city2.name and city2.name = city1.name */
    @Test
    void compare_zero2() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link1 = new Link(city1, city2, cityDistance);
        Link link2 = new Link(city2, city1, cityDistance);
        assertEquals(0, link1.compareTo(link2), "Both links have combination of city1.name = city2.name and city2.name = city1.name");
    }

    /* compareTo() returns negative if link1.city1.name < link2.city1.name */
    @Test
    void compareTo_negative1() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        City city3 = new City(city3Name);
        Link link1 = new Link(city1, city2, cityDistance);
        Link link2 = new Link(city2, city3, cityDistance);
        assertTrue(link1.compareTo(link2) < 0, "Returns negative if link1.city1.name < link2.city1.name");
    }

    /* compareTo() returns negative if link1.city1.name = link2.city1.name */
    @Test
    void compareTo_negative2() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        City city3 = new City(city3Name);
        Link link1 = new Link(city1, city2, cityDistance);
        Link link2 = new Link(city1, city3, cityDistance);
        assertTrue(link1.compareTo(link2) < 0, "Returns negative if link1.city1.name = link2.city1.name");
    }

    /* compareTo() returns negative if link1.city2.name < link2.city2.name */
    @Test
    void compareTo_negative3() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        City city3 = new City(city3Name);
        Link link1 = new Link(city1, city2, cityDistance);
        Link link2 = new Link(city2, city3, cityDistance);
        assertTrue(link1.compareTo(link2) < 0, "Returns negative if link1.city2.name < link2.city2.name");
    }

    /* Combining all positive tests in one method
     * compareTo() returns positive if:
     * link1.city1.name > link2.city1.name
     * link1.city2.name > link2.city2.name
     * Above both condition combined
     */
    @Test
    void compareTo_positive() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        City city3 = new City(city3Name);
        Link link1 = new Link(city3, city2, cityDistance);
        Link link2 = new Link(city1, city2, cityDistance);
        Link link3 = new Link(city1, city3, cityDistance);
        Link link4 = new Link(city1, city2, cityDistance);
        Link link5 = new Link(city2, city1, cityDistance);
        assertTrue(link1.compareTo(link2) > 0, "Returns positive if link1.city1.name > link2.city1.name ");
        assertTrue(link3.compareTo(link4) > 0, "Returns positive if link1.city2.name > link2.city2.name");
        assertTrue(link1.compareTo(link5) > 0, "Returns positive if link1.city2.name > link2.city2.name and link1.city1.name > link2.city1.name");
    }

}