package gps;

import java.util.List;
import java.util.Scanner;

/**
 * GPS Navigation Management System
 * Console-based menu driver (Core Java + Collections + basic DSA).
 */
public class GPSMain {

    private static final Scanner sc = new Scanner(System.in);
    private static final GPSService service = new GPSService();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=====================================");
        System.out.println(" GPS NAVIGATION MANAGEMENT SYSTEM");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addLocation();
                        break;
                    case 2:
                        viewAllLocations();
                        break;
                    case 3:
                        updateLocation();
                        break;
                    case 4:
                        deleteLocation();
                        break;
                    case 5:
                        searchLocation();
                        break;
                    case 6:
                        sortLocations();
                        break;
                    case 7:
                        findNearestLocation();
                        break;
                    case 8:
                        manageFavourites();
                        break;
                    case 9:
                        viewRecentSearches();
                        break;
                    case 10:
                        System.out.println("Exiting... Thank you for using GPS Navigation System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1-10.");
                }
            } catch (LocationNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--------- MAIN MENU ---------");
        System.out.println("1. Add Location");
        System.out.println("2. View All Locations");
        System.out.println("3. Update Location");
        System.out.println("4. Delete Location");
        System.out.println("5. Search Location");
        System.out.println("6. Sort Locations");
        System.out.println("7. Find Nearest Location");
        System.out.println("8. Favourite Locations");
        System.out.println("9. Recent Searches");
        System.out.println("10. Exit");
        System.out.println("------------------------------");
    }

    // ===================== MODULE HANDLERS =====================

    private static void addLocation() {
        int id = readInt("Enter Location ID: ");
        System.out.print("Enter Location Name: ");
        String name = sc.nextLine();
        double lat = readDouble("Enter Latitude: ");
        double lon = readDouble("Enter Longitude: ");
        double dist = readDouble("Enter Distance from Current Location (km): ");

        service.addLocation(new Location(id, name, lat, lon, dist));
        System.out.println("Location added successfully!");
    }

    private static void viewAllLocations() {
        List<Location> all = service.viewAllLocations();
        if (all.isEmpty()) {
            System.out.println("No locations saved yet.");
            return;
        }
        System.out.println("\nSaved Locations:");
        for (Location loc : all) {
            System.out.println(loc);
        }
    }

    private static void updateLocation() throws LocationNotFoundException {
        int id = readInt("Enter Location ID to update: ");
        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        double lat = readDouble("Enter new Latitude: ");
        double lon = readDouble("Enter new Longitude: ");
        double dist = readDouble("Enter new Distance: ");

        service.updateLocation(id, name, lat, lon, dist);
        System.out.println("Location updated successfully!");
    }

    private static void deleteLocation() throws LocationNotFoundException {
        int id = readInt("Enter Location ID to delete: ");
        service.deleteLocation(id);
        System.out.println("Location deleted successfully!");
    }

    private static void searchLocation() {
        System.out.println("Search by: 1. ID (Linear)  2. ID (Binary)  3. ID (HashMap)  4. Name");
        int opt = readInt("Choose option: ");
        int id;
        Location loc;

        switch (opt) {
            case 1:
                id = readInt("Enter Location ID: ");
                loc = service.searchByIdLinear(id);
                System.out.println(loc != null ? loc : "Location not found.");
                break;
            case 2:
                id = readInt("Enter Location ID: ");
                loc = service.searchByIdBinary(id);
                System.out.println(loc != null ? loc : "Location not found.");
                break;
            case 3:
                id = readInt("Enter Location ID: ");
                loc = service.searchByIdHashMap(id);
                System.out.println(loc != null ? loc : "Location not found.");
                break;
            case 4:
                System.out.print("Enter Location Name (or part of it): ");
                String name = sc.nextLine();
                List<Location> results = service.searchByName(name);
                if (results.isEmpty()) {
                    System.out.println("No matching locations found.");
                } else {
                    for (Location l : results) {
                        System.out.println(l);
                    }
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void sortLocations() {
        if (service.isEmpty()) {
            System.out.println("No locations to sort.");
            return;
        }
        System.out.println("Sort by: 1. Distance (Bubble Sort)  2. Name (Selection Sort)  3. ID (Collections.sort)");
        int opt = readInt("Choose option: ");

        switch (opt) {
            case 1:
                service.sortByDistanceBubble();
                System.out.println("Sorted by distance.");
                break;
            case 2:
                service.sortByNameSelection();
                System.out.println("Sorted by name.");
                break;
            case 3:
                service.sortByIdCollections();
                System.out.println("Sorted by ID.");
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        viewAllLocations();
    }

    private static void findNearestLocation() {
        // currentDistance is read for display purposes as per the workflow described
        readDouble("Enter your current distance reference (km): ");
        Location nearest = service.findNearestLocation();
        if (nearest == null) {
            System.out.println("No locations saved yet.");
        } else {
            System.out.println("Nearest Location -> " + nearest);
        }
    }

    private static void manageFavourites() throws LocationNotFoundException {
        System.out.println("1. Add Favourite  2. Remove Favourite  3. View Favourites");
        int opt = readInt("Choose option: ");
        int id;

        switch (opt) {
            case 1:
                id = readInt("Enter Location ID: ");
                service.addFavourite(id);
                System.out.println("Added to favourites.");
                break;
            case 2:
                id = readInt("Enter Location ID: ");
                service.removeFavourite(id);
                System.out.println("Removed from favourites.");
                break;
            case 3:
                List<Location> favs = service.viewFavourites();
                if (favs.isEmpty()) {
                    System.out.println("No favourite locations yet.");
                } else {
                    for (Location f : favs) {
                        System.out.println(f);
                    }
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void viewRecentSearches() {
        List<Location> recent = service.viewRecentSearches();
        if (recent.isEmpty()) {
            System.out.println("No recent searches yet.");
        } else {
            System.out.println("Last " + recent.size() + " searched location(s):");
            for (Location r : recent) {
                System.out.println(r);
            }
        }
    }

    // ===================== INPUT HELPERS (exception handling) =====================

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
