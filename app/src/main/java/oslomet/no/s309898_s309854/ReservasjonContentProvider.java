package oslomet.no.s309898_s309854;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReservasjonContentProvider extends ContentProvider {

    public static final String PROVIDER = "oslomet.no.s309898_s309854";
    public static final Uri CONTENT_URI_BESTILLING = Uri.parse("content://" + PROVIDER + "/bestilling");
    public static final Uri CONTENT_URI_RESTAURANT = Uri.parse("content://" + PROVIDER + "/restaurant");
    public static final Uri CONTENT_URI_VENN = Uri.parse("content://" + PROVIDER + "/venn");
    public static final Uri CONTENT_URI_BESTILLING_VENNER = Uri.parse("content://" + PROVIDER + "/bestillingvenner");



    private static final int BESTILLING = 1;
    private static final int MBESTILLING = 2;
    private static final int RESTAURANT = 3;
    private static final int MRESTAURANT = 4;
    private static final int VENN = 5;
    private static final int MVENN = 6;
    private static final int BESTILLING_VENNER = 7;
    private static final int MBESTILLING_VENNER = 8;

    DatabaseHjelper dbHjelper;
    SQLiteDatabase db;


    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "bestilling", MBESTILLING);
        uriMatcher.addURI(PROVIDER, "bestilling/#", BESTILLING);
        uriMatcher.addURI(PROVIDER, "restaurant", MRESTAURANT);
        uriMatcher.addURI(PROVIDER, "restaurant/#", RESTAURANT);
        uriMatcher.addURI(PROVIDER, "venn", MVENN);
        uriMatcher.addURI(PROVIDER, "venn/#", VENN);
        uriMatcher.addURI(PROVIDER, "bestillingvenner", MBESTILLING_VENNER);
        uriMatcher.addURI(PROVIDER, "bestillingvenner/#", BESTILLING_VENNER);


    }


    @Override
    public boolean onCreate() {
        dbHjelper = new DatabaseHjelper(getContext());
        db = dbHjelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case BESTILLING:
                cursor = db.query(dbHjelper.TABLE_BESTILLING, projection, dbHjelper.ID_BESTILLING + " = " + uri.getPathSegments().get(1),
                        selectionArgs, null, null, sortOrder);
                return cursor;
            case MBESTILLING:
                cursor = db.query(dbHjelper.TABLE_BESTILLING, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case RESTAURANT:
                cursor = db.query(dbHjelper.TABLE_RESTAURANT, projection, dbHjelper.ID_RESTAURANT + " = " + uri.getPathSegments().get(1),
                        selectionArgs, null, null, sortOrder);
                return cursor;
            case MRESTAURANT:
                cursor = db.query(dbHjelper.TABLE_RESTAURANT, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case VENN:
                cursor = db.query(dbHjelper.TABLE_VENN, projection, dbHjelper.ID_VENN + " = " + uri.getPathSegments().get(1),
                        selectionArgs, null, null, sortOrder);
                return cursor;
            case MVENN:
                cursor = db.query(dbHjelper.TABLE_VENN, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case BESTILLING_VENNER:
                cursor = db.query(dbHjelper.TABLE_BESTILLING_VENNER, projection, dbHjelper.BESTILLING_ID + " = " + uri.getPathSegments().get(1),
                        selectionArgs, null, null, sortOrder);
                return cursor;
            case MBESTILLING_VENNER:
                cursor = db.query(dbHjelper.TABLE_BESTILLING_VENNER, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            default:
                throw new IllegalArgumentException("Invalid URI " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case MBESTILLING: return "vnd.android.cursor.dir/vnd.example.bestilling";
            case BESTILLING: return "vnd.android.cursor.item/vnd.example.bestilling";
            case MRESTAURANT: return "vnd.android.cursor.dir/vnd.example.restaurant";
            case RESTAURANT: return "vnd.android.cursor.item/vnd.example.restaurant";
            case MVENN: return "vnd.android.cursor.dir/vnd.example.venn";
            case VENN: return "vnd.android.cursor.item/vnd.example.venn";

            default: throw new IllegalArgumentException("Invalid URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

