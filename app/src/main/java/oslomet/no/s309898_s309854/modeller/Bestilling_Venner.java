package oslomet.no.s309898_s309854.modeller;

public class Bestilling_Venner {

    private int B_Id;
    private int V_Id;

    public Bestilling_Venner(){}

    Bestilling_Venner(int b_id, int v_id){
        this.B_Id=b_id;
        this.V_Id=v_id;
    }


    public int getB_Id() {
        return B_Id;
    }

    public void setB_Id(int b_Id) {
        B_Id = b_Id;
    }

    public int getV_Id() {
        return V_Id;
    }

    public void setV_Id(int v_Id) {
        V_Id = v_Id;
    }
}
