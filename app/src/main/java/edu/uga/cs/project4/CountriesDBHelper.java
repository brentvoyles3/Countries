package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */
public class CountriesDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "CountriesDBHelper";

    private static final String DB_NAME = "countries.db";
    private static final int DB_COUNTRIES = 1;

    // Define all names (strings) for table and column names.
    // This will be useful if we want to change these names later.
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_NAME = "name";
    public static final String COUNTRIES_COLUMN_CONTINENT = "continent";

    // This is a reference to the only instance for the helper.
    private static CountriesDBHelper helperInstance;
    Context context;

    // A Create table SQL statement to create a table for job leads.
    // Note that _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + "("
                    + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRIES_COLUMN_NAME + " TEXT, "
                    + COUNTRIES_COLUMN_CONTINENT + " TEXT"
                    + ")";

    // Note that the constructor is private!
    // So, it can be called only from
    // this class, in the getInstance method.
    private CountriesDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_COUNTRIES );
    }

    // Access method to the single instance of the class.
    // It is synchronized, so that only one thread can executes this method, at a time.
    public static synchronized CountriesDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new CountriesDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_COUNTRIES );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRIES + " created" );

        InputStream in_s = null;
        try {
            in_s = context.getAssets().open( "country_continents.csv" );
            ContentValues values = new ContentValues();
            // read the CSV data
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            while( ( nextRow = reader.readNext() ) != null ) {

                // nextRow[] is an array of values from the line

                // create the next table row for the layout
                for( int i = 0; i < nextRow.length; i++ ) {
                    String[] row = nextRow;
                    values.put( COUNTRIES_COLUMN_NAME, row[i]);
                    values.put( COUNTRIES_COLUMN_CONTINENT, row[i+1]);
                }

                long id = db.insert( CountriesDBHelper.TABLE_COUNTRIES, null, values );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_COUNTRIES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRIES + " upgraded" );
    }
}
