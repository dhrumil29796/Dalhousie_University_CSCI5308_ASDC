import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Class representing a City
 */
public class City {
    /* lookup table of all cities by name */
    public static HashMap<String, City> cities = new HashMap<>();
    public String name;
    /* adjacent Links */
    public final HashSet<Link> links = new HashSet<>();
    /* shortest path distance */
    public int distance;
    /* shortest path parent */
    public Link parent;

    /* construct a City with name nm
     * add to the HashMap of cities
     */
    public City(String nm) {
        name = nm;
        cities.put(name, this);
    }

    /* add a link to links
     * not checking for exact link being added to as HashSet does not allow duplicates
     */
    public void addLink(Link lnk) {

        links.add(lnk);
    }

    /* compare cities by their names
     * return: negative if c1 is alphanumerically less,
     *  0 if names are the same,
     *  positive if c2 is alphanumerically less
     */
    public int compareTo(City p) {

        return name.compareTo(p.name);
    }

    /* return the name of the city
     * removed the concatenated distance from the return statement
     */
    public String toString() {

        return name;
    }

    /* compare cities by their distance from the start of the rail network
     * return: negative if c1 is closer to 0, 0 if equal distance, positive if c2 is closer to 0
     */

    /* Swapped the c2.distance - c1.distance in return statement
     */
    public int compare(City c1, City c2) {

        return c1.distance - c2.distance;
    }

    /* find a path from this to dest
     * return true if a path is found and false otherwise
     * add the followed Links to routeLinks
     */

    /* Changed the return false to true in the nested if statement, as it denotes that a path has been found
     * Changed the return true to false in the main return statement as we return false if the path is not found
     */
    public boolean getLinksTo(City dest, Set<Link> routeLinks) {
        for (Link l : links) {
            if (l.isUsed() && (l != parent)) {
                City child = l.getAdj(this);
                if ((dest == child) || child.getLinksTo(dest, routeLinks)) {
                    routeLinks.add(l);
                    return true;
                }
            }
        }
        return false;
    }
}
