package com.example.maplocation;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private int photo;
    private double latitude;
    private double longitude;
    private double altitude;
    private String pays;
    private String ville;
    private String province;
    private String postalCode;

    public User(int id, String nom, String prenom, int photo, double latitude, double longitude, double altitude, String pays, String ville, String province, String postalCode) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.pays = pays;
        this.ville = ville;
        this.province = province;
        this.postalCode = postalCode;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
