package oslomet.no.s309898_s309854.modeller;

public class Venn {
    private int Id;
    private String fornavn;
    private String etternavn;
    private String telefon;


    public Venn() {
    }

    public Venn(int ID, String forNavn, String etternavn, String telefon) {
        this.Id = ID;
        this.fornavn = forNavn;
        this.etternavn = etternavn;
        this.telefon = telefon;
    }

    public Venn(String fornavn, String etternavn, String telefon) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefon = telefon;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getId() {
        return Id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getTelefon() {
        return telefon;
    }
}
