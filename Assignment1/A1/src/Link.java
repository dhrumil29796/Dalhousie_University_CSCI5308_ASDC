import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Class representing a link between cities
 */
public class Link implements Comparable<Link> {
    public City city1;
    public City city2;
    public int length;
    /* true if and only if this link is part of the set of shortest paths */
    public boolean used = false;

    /* Construct a Link between c1 and c2 with length len
     * The City alphanumerically smaller is stored as city1 and the other will be city2
     * add the link to both cities
     */
    public Link(City c1, City c2, int len) {
        if (c1.compareTo(c2) < 0) {
            city1 = c1;
            city2 = c2;
        } else {
            city1 = c2;
            city2 = c1;
        }
        length = len;
        c1.addLink(this);
        c2.addLink(this);

        /* Commenting the used value being set to true
         * We cannot automatically assume a new link being created to be a part of the set of shortest paths
         */
        // used = true;
    }

    /* return the length of this link */
    public int getLength() {
        return length;
    }

    /* get the opposite city from c
     * return city1 if c is city2
     * return city2 if c is city1
     * behaviour is unspecified if c is not city1 or city2
     */

    /* Updated the false condition to city1 from city2 in the ternary operation below
     * According to the requirements specified above
     * Also added a if condition to handle the unspecified behavior if passed city is not city1 or city2
     */
    public City getAdj(City c) {
        if (c == city1 || c == city2) {
            return c == city1 ? city2 : city1;
        } else {
            return null;
        }
    }

    /* return true if this link is on a shortest path and false otherwise */
    public boolean isUsed() {

        return used;
    }

    /* set used to u */
    public void setUsed(boolean u) {

        used = u;
    }

    /* return a string representation of the Link
     * e.g. "City1 3 City2"
     * The city names should be in sorted order, e.g. Halifax comes before Toronto
     */

    /* The city names are already in sorted order, as the logic to sort the city names is done in the Link constructor itself.
     */
    public String linkName() {
        return city1.name + this.length + city2.name;
    }

    /* compare this Link to Link l
     * returns 0 if both links have the same city1 and city2
     * return negative int if this.city1 < l.city1 or the city1 are equal and this.city2 < l.city2
     * else return a positive int
     */

    /* Overriding the compareTo() method of the Comparable Interface
     * Implementing the logic for the above missing method
     */
    @Override
    public int compareTo(Link l) {
        if ((this.city1.name.equals(l.city1.name)) && (this.city2.name.equals(l.city2.name))) {
            return 0;
        } else if ((this.city1.name.compareTo(l.city1.name) < 0) || ((this.city1.name.equals(l.city1.name)) && (this.city2.name.compareTo(l.city2.name) < 0)))
            return -1;
        else {
            return 1;
        }
    }
}
