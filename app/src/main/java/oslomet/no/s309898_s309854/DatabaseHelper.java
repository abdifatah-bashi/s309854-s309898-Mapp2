package oslomet.no.s309898_s309854;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import oslomet.no.s309898_s309854.modeller.Restaurant;
import oslomet.no.s309898_s309854.modeller.Venn;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAVN = "restaurant_bestilling.db";
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

    DatabaseHelper(Context context) {
        super(context, DB_NAVN, null, DB_VERSJON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_restauranter = "CREATE TABLE " + TABLE_RESTAURANT + "(" + ID_RESTAURANT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAVN_RESTAURANT  + " Text, " + ADRESSE_RESTAURANT + " Text, "+
                TELEFONNR_RESTAURANT + " Text, " + TYPE_RESTAURANT + " TEXT);";

        String sql_venner = "CREATE TABLE " + TABLE_VENN + "(" + ID_VENN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FORNAVN  + " Text, " + ETTERNAVN + " Text, "+
                TELEFONNR_VENN + " TEXT);";

        Log.d("SQL RESTAURANTER", sql_restauranter);
        Log.d("SQL VENNER", sql_venner);
        db.execSQL(sql_restauranter);
        db.execSQL(sql_venner);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENN);
        Log.d("DatabaseHelper", "updated");
        onCreate(db);
    }

    public void addRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID_RESTAURANT, restaurant.getID());
        values.put(NAVN_RESTAURANT, restaurant.getNavn());
        values.put(ADRESSE_RESTAURANT, restaurant.getAdresse());
        values.put(TELEFONNR_RESTAURANT, restaurant.getTelefon());
        values.put(TYPE_RESTAURANT, restaurant.getType());
        db.insert(TABLE_RESTAURANT, null, values);
        db.close();
       // getContentResolver().insert(CONTENT_URI,values);

    }

    public void addVenn(Venn venn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID_RESTAURANT, restaurant.getID());
        values.put(FORNAVN, venn.getFornavn());
        values.put(ETTERNAVN, venn.getEtternavn());
        values.put(TELEFONNR_VENN, venn.getTelefon());

        db.insert(TABLE_VENN, null, values);
        db.close();
        // getContentResolver().insert(CONTENT_URI,values);

    }

    public int updateRestaurant(Restaurant restaurant){
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

    public int updateVenn(Venn venn){
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

    public List<Restaurant> hentAlleRestauranter(){
        List<Restaurant> alleRestauranter = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
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

    public List<Venn> hentAlleVenner() {
        List<Venn> alleVenner = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_VENN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Venn venn = new Venn();
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


    public void slettRestaurant(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANT, ID_RESTAURANT + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public Restaurant getRestaurant(int ID){
   //     String query = "SELECT * FROM " + TABLE_RESTAURANT + " WHERE " + ID_RESTAURANT + " = ?" + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANT,
                new String[] { ID_RESTAURANT, NAVN_RESTAURANT, ADRESSE_RESTAURANT, TELEFONNR_RESTAURANT, TYPE_RESTAURANT }, ID_RESTAURANT + " = ?",
                new String[] { String.valueOf(ID) }, null, null, null, null);

       // Cursor cursor = db.rawQuery(query, null);
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

    public Venn getVenn(int ID){

        String query = "SELECT * FROM " + TABLE_VENN + " WHERE Id=" + ID;
        SQLiteDatabase db = this.getWritableDatabase();
       /* Cursor cursor = db.query(TABLE_VENN,
                new String[] { ID_VENN, FORNAVN, ETTERNAVN,TELEFONNR_VENN }, ID_VENN + " = ?",
                new String[] { String.valueOf(ID) }, null, null, null, null);
*/
        Cursor cursor = db.rawQuery(query, null);
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
}
