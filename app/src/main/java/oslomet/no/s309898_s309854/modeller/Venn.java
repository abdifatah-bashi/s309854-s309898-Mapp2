package oslomet.no.s309898_s309854.modeller;

public class Venn {
    private String fornavn;
    private String etterNavn;
    private String telefon;


    public Venn(String fornavn, String etterNavn, String telefon) {
        this.fornavn = fornavn;
        this.etterNavn = etterNavn;
        this.telefon = telefon;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtterNavn(String etterNavn) {
        this.etterNavn = etterNavn;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtterNavn() {
        return etterNavn;
    }

    public String getTelefon() {
        return telefon;
    }
}
