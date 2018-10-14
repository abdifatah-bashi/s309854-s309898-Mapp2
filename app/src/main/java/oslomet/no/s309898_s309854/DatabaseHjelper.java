package oslomet.no.s309898_s309854;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import oslomet.no.s309898_s309854.modeller.Bestilling;
import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class DatabaseHjelper extends SQLiteOpenHelper {

    private static final String DB_NAVN = "restaurant_bestilling1.db";
    private static final int DB_VERSJON = 1;

    /* TABELL Restauranter */
    static String TABLE_RESTAURANT = "restaurant";
    static String ID_RESTAURANT = "_id";
    static String NAVN_RESTAURANT = "navn";
    static String ADRESSE_RESTAURANT = "adresse";
    static String TELEFONNR_RESTAURANT = "telefon";
    static String TYPE_RESTAURANT =  "type";

    /* TABELL VENNER */
    static String TABLE_VENN = "venn";
    static String ID_VENN = "_id";
    static String FORNAVN = "fornavn";
    static String ETTERNAVN = "etternavn";
    static String TELEFONNR_VENN = "telefon";

    /* TABELL BESTILLING */
    static String TABLE_BESTILLING = "bestilling";
    static String ID_BESTILLING = "_id";
    static String DATO = "dato";
    static String KLOKKESLETT = "klokke";
    static String RESTAURANT_ID ="id_res";

    /* TABELL BESTELLING_VENNER */

    static String TABLE_BESTILLING_VENNER = "bestilling_venner";
    static String BESTILLING_ID = "id_best";
    static String VENN_ID= "id_venn";


    public DatabaseHjelper(Context context) {
        super(context, DB_NAVN, null, DB_VERSJON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_restauranter = "CREATE TABLE " + TABLE_RESTAURANT + "(" + ID_RESTAURANT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAVN_RESTAURANT  + " Text, " + ADRESSE_RESTAURANT + " Text, "+
                TELEFONNR_RESTAURANT + " Text, " + TYPE_RESTAURANT + " Text);";

        String sql_venner = "CREATE TABLE " + TABLE_VENN + "(" + ID_VENN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FORNAVN  + " Text, " + ETTERNAVN + " Text, "+
                TELEFONNR_VENN + " TEXT);";

        String sql_bestilling = "CREATE TABLE " + TABLE_BESTILLING + "("
                + ID_BESTILLING + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATO  + " Text, "
                + KLOKKESLETT + " Text, "
                + RESTAURANT_ID +" INTEGER, "
                + " FOREIGN KEY(" + RESTAURANT_ID + ") REFERENCES " + TABLE_RESTAURANT + "(" + ID_RESTAURANT + "));";

        String sql_bestilling_venner = "CREATE TABLE " + TABLE_BESTILLING_VENNER + "("
                + BESTILLING_ID + " INTEGER, "
                + VENN_ID + " INTEGER ,"
                +" value, " + " PRIMARY KEY (" + BESTILLING_ID +","+ VENN_ID
                +"), FOREIGN KEY(" + BESTILLING_ID + ") REFERENCES " + TABLE_BESTILLING + "(" + ID_BESTILLING +
                ") , FOREIGN KEY(" + VENN_ID + ") REFERENCES " + TABLE_VENN + "(" + ID_VENN + "));";

        Log.i("SQL RESTAURANTER", sql_restauranter);
        Log.i("SQL VENNER", sql_venner);
        Log.i("SQL BESTILLING", sql_bestilling);
        db.execSQL(sql_restauranter);
        db.execSQL(sql_venner);
        db.execSQL(sql_bestilling);
        db.execSQL(sql_bestilling_venner);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENN);
        Log.d("DatabaseHjelper", "updated");
        onCreate(db);
    }

    public void leggTilRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID_RESTAURANT, restaurant.getID());
        values.put(NAVN_RESTAURANT, restaurant.getNavn());
        values.put(ADRESSE_RESTAURANT, restaurant.getAdresse());
        values.put(TELEFONNR_RESTAURANT, restaurant.getTelefon());
        values.put(TYPE_RESTAURANT, restaurant.getType());
        db.insert(TABLE_RESTAURANT, null, values);
        db.close(); 
    }

    public void leggTilVenn(Venn venn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FORNAVN, venn.getFornavn());
        values.put(ETTERNAVN, venn.getEtternavn());
        values.put(TELEFONNR_VENN, venn.getTelefon());
        db.insert(TABLE_VENN, null, values);
        db.close();

    }

    public void leggTilBestilling(Bestilling bestilling){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues value_best=new ContentValues();
        value_best.put(DATO,bestilling.getDato());
        value_best.put(KLOKKESLETT,bestilling.getKlokkeslett());
        value_best.put(RESTAURANT_ID,bestilling.getRestaurant_id());
        db.insert(TABLE_BESTILLING,null, value_best); 
    }

    public int hentSisteBestilling(){

        String query="SELECT * FROM " + TABLE_BESTILLING;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Bestilling b= new Bestilling();

        if(cursor.moveToLast()) {
            b = new Bestilling(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            cursor.close();
            db.close();
            return b.getId();
        }
        db.close();
        return -1;
    }

    public Bestilling getBestilling(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_BESTILLING,
                new String[] { ID_BESTILLING, DATO, KLOKKESLETT, RESTAURANT_ID }, ID_BESTILLING + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null){
            if(cursor.moveToFirst()) {
                Bestilling bestilling = new Bestilling(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));

                cursor.close();
                db.close();

                return bestilling;
            }
        }
        db.close();
        return null;
    }

    public void leggTilVennBestilling(Bestilling bestilling, Venn venn){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues value_best_venn=new ContentValues();

            value_best_venn.put(BESTILLING_ID,bestilling.getId());
            value_best_venn.put(VENN_ID,venn.getId());
            db.insert(TABLE_BESTILLING_VENNER,null, value_best_venn);

        db.close();
    }

    public int oppdaterRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAVN_RESTAURANT, restaurant.getNavn());
        values.put(ADRESSE_RESTAURANT, restaurant.getAdresse());
        values.put(TELEFONNR_RESTAURANT, restaurant.getTelefon());
        values.put(TYPE_RESTAURANT, restaurant.getType());
        int changedRestaurant = db.update(TABLE_RESTAURANT, values, ID_RESTAURANT + " = ?",
                new String[] { String.valueOf(restaurant.getID()) });
        db.close();
        return changedRestaurant;
    }

    public int oppdaterVenn(Venn venn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FORNAVN, venn.getFornavn());
        values.put(ETTERNAVN, venn.getEtternavn());
        values.put(TELEFONNR_VENN, venn.getTelefon());

        int changedVenn = db.update(TABLE_VENN, values, ID_VENN + " = ?",
                new String[] { String.valueOf(venn.getId()) });
        db.close();
        return changedVenn;
    }

    public ArrayList<Restaurant> hentAlleRestauranter(){
        ArrayList<Restaurant> alleRestauranter = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setID(cursor.getInt(0));
                restaurant.setNavn(cursor.getString(1));
                restaurant.setAdresse(cursor.getString(2));
                restaurant.setTelefon(cursor.getString(3));
                restaurant.setType(cursor.getString(4));

                alleRestauranter.add(restaurant);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return alleRestauranter;
    }

    public ArrayList<Venn> hentAlleVenner() {
        ArrayList<Venn> alleVenner = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_VENN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Venn venn = new Venn();
                venn.setId(cursor.getInt(0));
                venn.setFornavn(cursor.getString(1));
                venn.setEtternavn(cursor.getString(2));
                venn.setTelefon(cursor.getString(3));

                alleVenner.add(venn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return alleVenner;
    }


    public ArrayList<Venn> hentVenner_Bestilling(Bestilling bestilling) {
        ArrayList<Venn> venner= new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BESTILLING_VENNER + " WHERE " + BESTILLING_ID + " = " + bestilling.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                Venn venn = hentVenn(cursor.getInt(1));
                venner.add(venn);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        return venner;

    }


    public List<Bestilling> hentAlleBestillinger() {
        List<Bestilling> allebestillinger = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BESTILLING;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Bestilling bestilling = new Bestilling();
                bestilling.setId(cursor.getInt(0));
                bestilling.setDato(cursor.getString(1));
                bestilling.setKlokkeslett(cursor.getString(2));
                bestilling.setRestaurant_id(cursor.getInt(3));

                allebestillinger.add(bestilling);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return allebestillinger;
    }

    public int hentVenn(String fornavn, String etternavn){
         int id=0;
        String query = "SELECT * FROM " + TABLE_VENN + " WHERE " + FORNAVN + "=" + fornavn + " AND " + ETTERNAVN + "=" + etternavn;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                Venn venn = new Venn(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                id = venn.getId();

            }
            cursor.close();
            db.close();
        }
        return id;
    }

    public int hentRestaurant(String navn){
        int id=0;
        String query = "SELECT * FROM " + TABLE_RESTAURANT + " WHERE " + NAVN_RESTAURANT + " = '" + navn + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                Venn venn = new Venn(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                id = venn.getId();

            }
            cursor.close();
            db.close();
        }
        return id;
    }

    public void slettRestaurant(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANT, ID_RESTAURANT + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void slettVenn(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VENN, ID_VENN + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public Restaurant hentRestaurant(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANT,
                new String[] { ID_RESTAURANT, NAVN_RESTAURANT, ADRESSE_RESTAURANT, TELEFONNR_RESTAURANT, TYPE_RESTAURANT }, ID_RESTAURANT + " = ?",
                new String[] { String.valueOf(ID) }, null, null, null, null);

        if(cursor != null){
            if(cursor.moveToFirst()) {
                Restaurant restaurant = new Restaurant(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));

                cursor.close();
                db.close();

                return restaurant;
            }
        }
        db.close();
        return null;
    }

    public Venn hentVenn(int ID){

        SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.query(TABLE_VENN,
                new String[] { ID_VENN, FORNAVN, ETTERNAVN, TELEFONNR_VENN }, ID_VENN + " = ?",
                new String[] { String.valueOf(ID) }, null, null, null, null);

        if(cursor != null){
            if(cursor.moveToFirst()) {
                Venn venn = new Venn(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                cursor.close();
                db.close();

                return venn;
            }
        }
        db.close();
        return null;
    }

    public boolean sjekkTidspunkt(String tidspunkt){
        /*
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MELDINGER,
                new String[] { KEY_MELDING_ID, KEY_MELDING, KEY_TIDSPUNKT }, KEY_TIDSPUNKT + " = ?",
                new String[] { tidspunkt }, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                Melding melding = new Melding(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

                cursor.close();
                db.close();
                return true;
            }
        }

        db.close();
        return false;

        */
        return false;
    }
}
