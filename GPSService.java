package gps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Business logic layer for the GPS Navigation Management System.
 *
 * DSA concepts used:
 *  - ArrayList  -> master list of all locations
 *  - HashMap    -> O(1) lookup by Location ID
 *  - LinkedList -> fixed-size "recent searches" history
 *  - Linear Search, Binary Search
 *  - Bubble Sort, Selection Sort, Collections.sort()
 */
public class GPSService {

    private final ArrayList<Location> locations = new ArrayList<>();
    private final HashMap<Integer, Location> locationMap = new HashMap<>();
    private final LinkedList<Location> recentSearches = new LinkedList<>();
    private static final int MAX_RECENT = 5;

    // ===================== CRUD =====================

    /** Create */
    public void addLocation(Location loc) throws IllegalArgumentException {
        if (locationMap.containsKey(loc.getLocationId())) {
            throw new IllegalArgumentException(
                    "Location ID " + loc.getLocationId() + " already exists.");
        }
        locations.add(loc);
        locationMap.put(loc.getLocationId(), loc);
    }

    /** Read */
    public List<Location> viewAllLocations() {
        // Return a read-only view so callers can't mutate internal state directly.
        return Collections.unmodifiableList(locations);
    }

    /** Update */
    public void updateLocation(int id, String name, double lat, double lon, double distance)
            throws LocationNotFoundException {
        Location loc = locationMap.get(id);
        if (loc == null) {
            throw new LocationNotFoundException("No location found with ID " + id);
        }
        loc.setLocationName(name);
        loc.setLatitude(lat);
        loc.setLongitude(lon);
        loc.setDistance(distance);
    }

    /** Delete */
    public void deleteLocation(int id) throws LocationNotFoundException {
        Location loc = locationMap.remove(id);
        if (loc == null) {
            throw new LocationNotFoundException("No location found with ID " + id);
        }
        locations.remove(loc);
        recentSearches.remove(loc);
    }

    // ===================== SEARCH =====================

    /** O(1) direct lookup via HashMap (fastest way to search by ID). */
    public Location searchByIdHashMap(int id) {
        Location loc = locationMap.get(id);
        if (loc != null) addToRecentSearches(loc);
        return loc;
    }

    /** Linear Search by ID - O(n), works on unsorted data. */
    public Location searchByIdLinear(int id) {
        for (Location loc : locations) {
            if (loc.getLocationId() == id) {
                addToRecentSearches(loc);
                return loc;
            }
        }
        return null;
    }

    /**
     * Binary Search by ID - O(log n). Requires the list to be sorted by ID first,
     * so this method sorts a temporary copy before searching.
     */
    public Location searchByIdBinary(int id) {
        List<Location> sorted = new ArrayList<>(locations);
        sorted.sort(Comparator.comparingInt(Location::getLocationId));

        int low = 0, high = sorted.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midId = sorted.get(mid).getLocationId();
            if (midId == id) {
                addToRecentSearches(sorted.get(mid));
                return sorted.get(mid);
            } else if (midId < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    /** Linear Search by Name (case-insensitive, partial match). */
    public List<Location> searchByName(String name) {
        List<Location> matches = new ArrayList<>();
        for (Location loc : locations) {
            if (loc.getLocationName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(loc);
                addToRecentSearches(loc);
            }
        }
        return matches;
    }

    // ===================== SORTING =====================

    /** Bubble Sort by distance (ascending). Easy to explain in interviews. */
    public void sortByDistanceBubble() {
        int n = locations.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (locations.get(j).getDistance() > locations.get(j + 1).getDistance()) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /** Selection Sort by name (alphabetical, ascending). */
    public void sortByNameSelection() {
        int n = locations.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (locations.get(j).getLocationName()
                        .compareToIgnoreCase(locations.get(minIndex).getLocationName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) swap(i, minIndex);
        }
    }

    /** Collections.sort() by ID - built-in Java sort (Timsort), shown as the "optional" approach. */
    public void sortByIdCollections() {
        Collections.sort(locations, Comparator.comparingInt(Location::getLocationId));
    }

    private void swap(int i, int j) {
        Location temp = locations.get(i);
        locations.set(i, locations.get(j));
        locations.set(j, temp);
    }

    // ===================== NEAREST LOCATION =====================

    /**
     * Finds the saved location with the smallest "distance from current location" value.
     * currentDistance is accepted for display purposes / future extension (e.g. filtering).
     */
    public Location findNearestLocation() {
        if (locations.isEmpty()) return null;
        Location nearest = locations.get(0);
        for (Location loc : locations) {
            if (loc.getDistance() < nearest.getDistance()) {
                nearest = loc;
            }
        }
        return nearest;
    }

    // ===================== FAVOURITES =====================

    public void addFavourite(int id) throws LocationNotFoundException {
        Location loc = locationMap.get(id);
        if (loc == null) throw new LocationNotFoundException("No location found with ID " + id);
        loc.setFavourite(true);
    }

    public void removeFavourite(int id) throws LocationNotFoundException {
        Location loc = locationMap.get(id);
        if (loc == null) throw new LocationNotFoundException("No location found with ID " + id);
        loc.setFavourite(false);
    }

    public List<Location> viewFavourites() {
        List<Location> favs = new ArrayList<>();
        for (Location loc : locations) {
            if (loc.isFavourite()) favs.add(loc);
        }
        return favs;
    }

    // ===================== RECENT SEARCHES =====================

    private void addToRecentSearches(Location loc) {
        recentSearches.remove(loc);       // avoid duplicate entries
        recentSearches.addFirst(loc);     // most recent first
        if (recentSearches.size() > MAX_RECENT) {
            recentSearches.removeLast();  // keep only last 5
        }
    }

    public List<Location> viewRecentSearches() {
        // Read-only view - keeps the LinkedList itself as the sole mutator.
        return Collections.unmodifiableList(recentSearches);
    }

    // ===================== HELPERS =====================

    public boolean isEmpty() {
        return locations.isEmpty();
    }

    public boolean exists(int id) {
        return locationMap.containsKey(id);
    }
}
