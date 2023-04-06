package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.os.AsyncTask;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app. The class will generate the tables for the quiz and populate them with necessary information.
 * One table is populated by opening and retrieving information from Assets/country_continents.csv, which
 * fills the tables with the countries and continents, as well as two empty columns that will be filled with
 * random answer choices for the quiz once th quiz is started. The other table holds information about
 * past quizzes regarding the score and date of the quiz.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */
public class CountriesDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "CountriesDBHelper";

    private static final String DB_NAME = "countries.db";
    private static final int DB_VERSION = 1;

    // Define all names (strings) for table and column names.
    // This will be useful if we want to change these names later.
    public static final String TABLE_COUNTRIES = "cities";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_COUNTRY = "country";
    public static final String COUNTRIES_COLUMN_CONTINENT = "continent";
    public static final String COUNTRIES_COLUMN_OPTION1 = "option1";
    public static final String COUNTRIES_COLUMN_OPTION2 = "option2";
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_SCORE = "score";
    public static final String QUIZZES_COLUMN_DATE = "date";

    // This is a reference to the only instance for the helper.
    private static CountriesDBHelper helperInstance;

    private static Context context;

    // A Create table SQL statement to create a table for countries and their
    // matching continents.
    // Note that _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + "("
                    + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRIES_COLUMN_COUNTRY + " TEXT, "
                    + COUNTRIES_COLUMN_CONTINENT + " TEXT, "
                    + COUNTRIES_COLUMN_OPTION1 + " TEXT, "
                    + COUNTRIES_COLUMN_OPTION2 + " TEXT"
                    + ")";

    // A Create table SQL statement to create a table for past quiz results.
    // Note that _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + "("
                    + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZZES_COLUMN_SCORE + " TEXT, "
                    + QUIZZES_COLUMN_DATE + " TEXT"
                    + ")";

    /**
     * Constructor for {@link CountriesDBHelper}.
     * @param context Context of the application.
     */
    private CountriesDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    /**
     * Access method to the single instance of the class. It is synchronized, so that
     * only one thread can executes this method, at a time.
     *
     * @param context Context of the application.
     * @return CountriesDBHelper
     */
    public static synchronized CountriesDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new CountriesDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    /**
     * Creates the tables in the database to store information on our Countries data
     * and past quiz results.
     *
     * @param db The database.
     */
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( "drop table if exists " + TABLE_COUNTRIES   );
        db.execSQL( "drop table if exists " + TABLE_QUIZZES );
        db.execSQL( CREATE_COUNTRIES );
        db.execSQL( CREATE_QUIZZES );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRIES + " created" );

        try {
            int count = 0;
            AssetManager manager = context.getAssets();
            InputStream in_s = manager.open("country_continents.csv");
            Log.d(DEBUG_TAG, "InputStream opened");
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            while ( ( nextRow = reader.readNext() ) != null ) {
                ContentValues values = new ContentValues();
                values.put( CountriesDBHelper.COUNTRIES_COLUMN_COUNTRY, nextRow[0]);
                values.put( CountriesDBHelper.COUNTRIES_COLUMN_CONTINENT, nextRow[1]);
                values.put( CountriesDBHelper.COUNTRIES_COLUMN_OPTION1, nextRow[2]);
                values.put( CountriesDBHelper.COUNTRIES_COLUMN_OPTION2, nextRow[3]);
                long id = db.insert( CountriesDBHelper.TABLE_COUNTRIES, null, values );
                count++;
                Log.d(DEBUG_TAG, "Inserted Id: " + id);
                Log.d(DEBUG_TAG, "Next row: " + nextRow);
            }
            manager.close();
            Log.d(DEBUG_TAG, "Count of countries add (out of 195): " + count);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString());
        }

    }

    /**
     * Used to upgrade the database if its version (DB_VERSION) has changed. This will be
     * done automatically by Android if the version will be bumped up, as we modify the
     * database schema.
     *
     * @param db The database.
     * @param oldVersion The old version of the database.
     * @param newVersion The new version of the database.
     */
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_COUNTRIES );
        db.execSQL( "drop table if exists " + TABLE_QUIZZES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRIES + " upgraded" );
    }

}
