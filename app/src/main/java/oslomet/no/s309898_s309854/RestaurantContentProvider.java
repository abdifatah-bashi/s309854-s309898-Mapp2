package oslomet.no.s309898_s309854;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RestaurantContentProvider extends ContentProvider {

    public static final String PROVIDER = "oslomet.no.s309898_s309854";
    public static final Uri CONTENT_URI_RESTAURANT = Uri.parse("content://" + PROVIDER + "/restaurant");

    private static final int RESTAURANT = 1;
    private static final int MRESTAURANT = 2;

    DatabaseHjelper dbHjelper;
    SQLiteDatabase db;


    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "restaurant", MRESTAURANT);
        uriMatcher.addURI(PROVIDER, "restaurant/#", RESTAURANT);
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
            case RESTAURANT:
                cursor = db.query(dbHjelper.TABLE_RESTAURANT, projection, dbHjelper.ID_RESTAURANT + " = " + uri.getPathSegments().get(1),
                        selectionArgs, null, null, sortOrder);
                return cursor;
            case MRESTAURANT:
                cursor = db.query(dbHjelper.TABLE_RESTAURANT, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            default:
                throw new IllegalArgumentException("Invalid URI " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MRESTAURANT:
                return "vnd.android.cursor.dir/vnd.example.restaurant";
            case RESTAURANT:
                return "vnd.android.cursor.item/vnd.example.restaurant";
            default:
                throw new IllegalArgumentException("Invalid URI " + uri);
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


