package com.example.sonu.travelguide;

/**
 * Created by shrawankeshari on 12/12/17.
 * Defines the prototype of Place object
 */

public class Place {

    private long id;
    private String placeName;
    private String image;
    private String location;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
