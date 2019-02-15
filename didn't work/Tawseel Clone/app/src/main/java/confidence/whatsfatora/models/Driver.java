package confidence.whatsfatora.models;

import android.location.Location;
import android.support.annotation.NonNull;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.Exclude;

public class Driver
        implements Comparable<Driver>
{
    public String address;
    public boolean available;
    public String carPlateNumber;
    public String city;
    public Double createdAt;
    @Exclude
    private GeoLocation currentLocation;
    @Exclude
    private float distanceToClient;
    public String district;
    @Exclude
    private Long driverID;
    public String email;
    public boolean hasProfile;
    public String image;
    public boolean loggedIn;
    public String name;
    String phone;
    private Rating rating;
    public Double updatedAt;

    public Driver() {}

    public Driver(Long paramLong, GeoLocation paramGeoLocation)
    {
        this.driverID = paramLong;
        this.currentLocation = paramGeoLocation;
    }

    public void calculateDistance(double paramDouble1, double paramDouble2)
    {
        Location localLocation1 = new Location("");
        localLocation1.setLatitude(this.currentLocation.latitude);
        localLocation1.setLongitude(this.currentLocation.longitude);
        Location localLocation2 = new Location("");
        localLocation2.setLatitude(paramDouble1);
        localLocation2.setLongitude(paramDouble2);
        this.distanceToClient = localLocation2.distanceTo(localLocation1);
    }

    public int compareTo(@NonNull Driver paramDriver)
    {
        return (int)(this.distanceToClient - paramDriver.getDistanceToClient());
    }

    public GeoLocation getCurrentLocation()
    {
        return this.currentLocation;
    }

    public float getDistanceToClient()
    {
        return this.distanceToClient;
    }

    public Long getDriverID()
    {
        return this.driverID;
    }

    public double getRatingAverage()
    {
        if (this.rating != null) {
            return this.rating.getAverage();
        }
        return 0.0D;
    }

    public void setCurrentLocation(GeoLocation paramGeoLocation)
    {
        this.currentLocation = paramGeoLocation;
    }
}