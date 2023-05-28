package ma.ensa.bankapp;

import com.google.android.gms.maps.model.Marker;

public class Agence {

    private double latitude;
    private double longitude;
    private String responsable;
    private String adresse;
    private String telephone;
    private String email;
    private Marker marker;

    public Agence(double latitude, double longitude, String responsable, String adresse, String telephone, String email) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.responsable = responsable;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}
