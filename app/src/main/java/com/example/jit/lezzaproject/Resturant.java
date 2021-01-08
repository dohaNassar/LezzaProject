package com.example.jit.lezzaproject;

public class Resturant {
    private String restaurant;
    private String descrip;
    private String photo;
    private double latitude;
    private double longitude;
    private boolean isNotified;



    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public boolean isEqual(Resturant resturant){
        if(resturant.getRestaurant().equals(this.restaurant) &&
                resturant.getLatitude() == this.latitude &&
                resturant.getLongitude() == this.longitude &&
                resturant.getDescrip().equals(this.descrip) &&
                resturant.getPhoto().equals(this.photo)){
                return true;
        }
        return false;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }
}
