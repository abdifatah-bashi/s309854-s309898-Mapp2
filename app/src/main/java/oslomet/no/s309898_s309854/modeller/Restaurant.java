package oslomet.no.s309898_s309854.modeller;

public class Restaurant {

    private String navn;
    private String adresse;
    private String telefon;
    private String type;

    public Restaurant(String navn, String adresse, String telefon, String type) {
        this.navn = navn;
        this.adresse = adresse;
        this.telefon = telefon;
        this.type = type;
    }

    public Restaurant(String navn) {
        this.navn = navn;
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
