package gps;

/**
 * Represents a saved location in the GPS Navigation system.
 */
public class Location {

    private int locationId;
    private String locationName;
    private double latitude;
    private double longitude;
    private double distance; // distance (in km) from the current location
    private boolean favourite;

    public Location(int locationId, String locationName, double latitude,
                     double longitude, double distance) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.favourite = false;
    }

    // ---------- Getters & Setters ----------

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location other = (Location) o;
        return this.locationId == other.locationId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(locationId);
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %-4d | Name: %-15s | Lat: %-9.4f | Lon: %-9.4f | Distance: %-6.2f km | Favourite: %s",
                locationId, locationName, latitude, longitude, distance, favourite ? "Yes" : "No");
    }
}
