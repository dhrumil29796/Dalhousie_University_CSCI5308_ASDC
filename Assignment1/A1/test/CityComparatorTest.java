import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CityComparatorTest {

    public static final String city1Name = "City1";
    public static final String city2Name = "City2";
    public static final int cityDistanceShort = 1;
    public static final int cityDistanceLong = 2;

    /* 1a. compare returns negative if 0 <= x.distance <= y.distance */
    @Test
    void compare_xSmaller() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        CityComparator cc = new CityComparator();
        c1.distance = cityDistanceShort;
        c2.distance = cityDistanceLong;
        assertTrue(cc.compare(c1, c2) < 0, "Expected negative distance for city1");
    }

    /* 1b. compare returns 0 if 0 <= x.distance == y.distance */
    @Test
    void compare_xEqual() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        CityComparator cc = new CityComparator();
        c1.distance = cityDistanceShort;
        c2.distance = cityDistanceShort;
        assertEquals(0, cc.compare(c1, c2), "Expected zero as distance is equal");
    }

    /* 1c. compare returns positive if 0 <= x.distance <= y.distance */
    @Test
    void compare_xLarger() {
        City c1 = new City(city1Name);
        City c2 = new City(city2Name);
        CityComparator cc = new CityComparator();
        c1.distance = cityDistanceLong;
        c2.distance = cityDistanceShort;
        assertTrue(cc.compare(c1, c2) > 0, "Expected positive distance for city1");
    }

}