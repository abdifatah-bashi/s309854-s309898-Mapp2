package oslomet.no.s309898_s309854.modeller;

import java.util.List;


public class Bestilling {
    private int Id;
    private String dato;
    private String klokkeslett;
    private int restaurant_id;

    public Bestilling(){}
    public Bestilling(int id,String dato, String klokkeslett, int restaurant_id) {
        this.Id=id;
        this.dato = dato;
        this.klokkeslett = klokkeslett;
        this.restaurant_id = restaurant_id;

    }

    public Bestilling(String dato, String klokkeslett, int restaurant_id) {

        this.dato = dato;
        this.klokkeslett = klokkeslett;
        this.restaurant_id = restaurant_id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setRestaurant(int rest_id) {
        this.restaurant_id = rest_id;
    }

   public void setKlokkeslett(String klokkeslett) {this.klokkeslett = klokkeslett;}

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getId() {
        return Id;
    }

    public String getDato() {
        return dato;
    }

    public String getKlokkeslett() {
        return klokkeslett;
    }

    public int getRestaurant_id() { return restaurant_id; }

    public void setRestaurant_id(int restaurant_id) { this.restaurant_id = restaurant_id; }
}
