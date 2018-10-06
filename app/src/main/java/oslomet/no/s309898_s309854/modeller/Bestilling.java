package oslomet.no.s309898_s309854.modeller;

import java.util.List;

public class Bestilling {
    private int Id;
    private Restaurant restaurant;
    private List<Venn> venner;
    private String dato;
    private String klokkeslett;




    public Bestilling(Restaurant restaurant, List<Venn> venner, String dato, String klokkeslett) {
        this.restaurant = restaurant;
        this.venner = venner;
        this.dato = dato;
        this.klokkeslett = klokkeslett;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setVenner(List<Venn> venner) {
        this.venner = venner;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setKlokkeslett(String klokkeslett) {
        this.klokkeslett = klokkeslett;
    }

    public int getId() {
        return Id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Venn> getVenner() {
        return venner;
    }

    public String getDato() {
        return dato;
    }

    public String getKlokkeslett() {
        return klokkeslett;
    }
}
