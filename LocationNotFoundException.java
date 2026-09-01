package gps;

/**
 * Thrown when a requested Location ID does not exist in the system.
 * Demonstrates custom exception handling (a required DSA/OOP concept).
 */
public class LocationNotFoundException extends Exception {

    public LocationNotFoundException(String message) {
        super(message);
    }
}
