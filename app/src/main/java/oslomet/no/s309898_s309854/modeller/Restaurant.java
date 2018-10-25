package oslomet.no.s309898_s309854.modeller;

public class Restaurant {
    int ID;
    String navn;
    String adresse;
    String telefon;
    String type;

    public Restaurant() {
    }

    public Restaurant(int ID, String navn, String adresse, String telefon, String type) {
        this.ID = ID;
        this.navn = navn;
        this.adresse = adresse;
        this.telefon = telefon;
        this.type = type;
    }

    public Restaurant(String navn, String adresse, String telefon, String type) {
        this.navn = navn;
        this.adresse = adresse;
        this.telefon = telefon;
        this.type = type;
    }

    public Restaurant(String navn) {
        this.navn = navn;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getType() {
        return type;
    }
}
