package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class facilitates storing and restoring data regarding the countries stored.
 */
public class CountriesData {

    public static final String DEBUG_TAG = "CountriesData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase db;
    private final SQLiteOpenHelper countriesDbHelper;

    private static final String[] allQuizColumns = {
            CountriesDBHelper.QUIZZES_COLUMN_ID,
            CountriesDBHelper.QUIZZES_COLUMN_SCORE,
            CountriesDBHelper.QUIZZES_COLUMN_DATE
    };

    /**
     * Constructor for {@link CountriesData}.
     */
    public CountriesData ( Context context ) {
        this.countriesDbHelper = CountriesDBHelper.getInstance( context );
    }

    /**
     * Opens the database.
     */
    public void open() {
        db = countriesDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "db open" );
    }

    /**
     * Closes the database.
     */
    public void close() {
        if( countriesDbHelper != null ) {
            countriesDbHelper.close();
            Log.d(DEBUG_TAG, "db closed");
        }
    }

    /**
     * Retrieves all of the quiz questions that will be used for the quizzes.
     *
     * @return List<Questions> List containing all of the quiz questions.
     */
    public List<Questions> retrieveAllQuizQuestions() {
        ArrayList<Questions> questions = new ArrayList<>();

        //Cursor cursor = null;
        /*
        try {
            int count = 0;
            AssetManager manager = myContext.getAssets();
            InputStream in_s = manager.open("country_continent.csv");

            Log.d(DEBUG_TAG, "InputStream opened");
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow = new String[4];
            int i = 0;
            //Questions qa = new Questions();
            while ( ( nextRow = reader.readNext() ) != null ) {
                if (i > 0) { //////////////////////////// take out if condition
                    String country = nextRow[0];
                    String continent = nextRow[1];
                    String randomOptionA = nextRow[2];
                    String randomOptionB = nextRow[3];

                    Questions qa = new Questions(country, continent, randomOptionA, randomOptionB);
                    questions.add(qa);
                    count++;
                    Log.d(DEBUG_TAG, count + ": " + country + ", " + continent + ", " + randomOptionA + ", " + randomOptionB);
                }
                i++;

            }
            manager.close();
            Log.d(DEBUG_TAG, "Count of countries add (out of 195): " + count);
            return questions;
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString());
        }
         */

        Questions question1 = new Questions("Afghanistan", "Asia", "", "");
        questions.add(question1);
        Questions question2 = new Questions("Albania", "Europe", "", "");
        questions.add(question2);
        Questions question3 = new Questions("Algeria", "Africa", "", "");
        questions.add(question3);
        Questions question4 = new Questions("Andorra", "Europe", "", "");
        questions.add(question4);
        Questions question5 = new Questions("Angola", "Africa", "", "");
        questions.add(question5);
        Questions question6 = new Questions("Antigua and Barbuda", "North America", "", "");
        questions.add(question6);
        Questions question7 = new Questions("Argentina", "South America", "", "");
        questions.add(question7);
        Questions question8 = new Questions("Armenia", "Europe", "", "");
        questions.add(question8);
        Questions question9 = new Questions("Australia", "Oceania", "", "");
        questions.add(question9);
        Questions question10 = new Questions("Austria", "Europe", "", "");
        questions.add(question10);
        Questions question11 = new Questions("Azerbaijan", "Europe", "", "");
        questions.add(question11);
        Questions question12 = new Questions("Bahrain", "Asia", "", "");
        questions.add(question12);
        Questions question13 = new Questions("Bangladesh", "Asia", "", "");
        questions.add(question13);
        Questions question14 = new Questions("Barbados", "North America", "", "");
        questions.add(question14);
        Questions question15 = new Questions("Belarus", "Europe", "", "");
        questions.add(question15);
        Questions question16 = new Questions("Belgium", "Europe", "", "");
        questions.add(question16);
        Questions question17 = new Questions("Belize", "North America", "", "");
        questions.add(question17);
        Questions question18 = new Questions("Benin", "Africa", "", "");
        questions.add(question18);
        Questions question19 = new Questions("Bhutan", "Asia", "", "");
        questions.add(question19);
        Questions question20 = new Questions("Bolivia", "South America", "", "");
        questions.add(question20);
        Questions question21 = new Questions("Bosnia and Herzegovina", "Europe", "", "");
        questions.add(question21);
        Questions question22 = new Questions("Botswana", "Africa", "", "");
        questions.add(question22);
        Questions question23 = new Questions("Brazil", "South America", "", "");
        questions.add(question23);
        Questions question24 = new Questions("Brunei", "Asia", "", "");
        questions.add(question24);
        Questions question25 = new Questions("Bulgaria", "Europe", "", "");
        questions.add(question25);
        Questions question26 = new Questions("Burkina Faso", "Africa", "", "");
        questions.add(question26);
        Questions question27 = new Questions("Burundi", "Africa", "", "");
        questions.add(question27);
        Questions question28 = new Questions("Cambodia", "Asia", "", "");
        questions.add(question28);
        Questions question29 = new Questions("Cameroon", "Africa", "", "");
        questions.add(question29);
        Questions question30 = new Questions("Canada", "North America", "", "");
        questions.add(question30);
        Questions question31 = new Questions("Cape Verde", "Africa", "", "");
        questions.add(question31);
        Questions question32 = new Questions("Central African Republic", "Africa", "", "");
        questions.add(question32);
        Questions question33 = new Questions("Chad", "Africa", "", "");
        questions.add(question33);
        Questions question34 = new Questions("Chile", "South America", "", "");
        questions.add(question34);
        Questions question35 = new Questions("China", "Asia", "", "");
        questions.add(question35);
        Questions question36 = new Questions("Columbia", "South America", "", "");
        questions.add(question36);
        Questions question37 = new Questions("Comoros", "Africa", "", "");
        questions.add(question37);
        Questions question38 = new Questions("Congo", "Africa", "", "");
        questions.add(question38);
        Questions question39 = new Questions("Costa Rica", "North America", "", "");
        questions.add(question39);
        Questions question40 = new Questions("Croatia", "Europe", "", "");
        questions.add(question40);
        Questions question41 = new Questions("Cuba", "North America", "", "");
        questions.add(question41);
        Questions question42 = new Questions("Cyprus", "Europe", "", "");
        questions.add(question42);
        Questions question43 = new Questions("Czech Republic", "Europe", "", "");
        questions.add(question43);
        Questions question44 = new Questions("Democratic Republic of the Congo", "Africa", "", "");
        questions.add(question44);
        Questions question45 = new Questions("Denmark", "Europe", "", "");
        questions.add(question45);
        Questions question46 = new Questions("Djibouti", "Africa", "", "");
        questions.add(question46);
        Questions question47 = new Questions("Dominica", "North America", "", "");
        questions.add(question47);
        Questions question48 = new Questions("Dominican Republic", "North America", "", "");
        questions.add(question48);
        Questions question49 = new Questions("East Timor", "Asia", "", "");
        questions.add(question49);
        Questions question50 = new Questions("Ecuador", "South America", "", "");
        questions.add(question50);
        Questions question51 = new Questions("Egypt", "Africa", "", "");
        questions.add(question51);
        Questions question52 = new Questions("El Salvador", "North America", "", "");
        questions.add(question52);
        Questions question53 = new Questions("Equatorial Guinea", "Africa", "", "");
        questions.add(question53);
        Questions question54 = new Questions("Eritrea", "Africa", "", "");
        questions.add(question54);
        Questions question55 = new Questions("Estonia", "Europe", "", "");
        questions.add(question55);
        Questions question56 = new Questions("Ethiopia", "Africa", "", "");
        questions.add(question56);
        Questions question57 = new Questions("Fiji", "Oceania", "", "");
        questions.add(question57);
        Questions question58 = new Questions("Finland", "Europe", "", "");
        questions.add(question58);
        Questions question59 = new Questions("France", "Europe", "", "");
        questions.add(question59);
        Questions question60 = new Questions("Gabon", "Africa", "", "");
        questions.add(question60);
        Questions question61 = new Questions("Gambia", "Africa", "", "");
        questions.add(question61);
        Questions question62 = new Questions("Georgia", "Europe", "", "");
        questions.add(question62);
        Questions question63 = new Questions("Germany", "Europe", "", "");
        questions.add(question63);
        Questions question64 = new Questions("Ghana", "Africa", "", "");
        questions.add(question64);
        Questions question65 = new Questions("Greece", "Europe", "", "");
        questions.add(question65);
        Questions question66 = new Questions("Grenada", "North America", "", "");
        questions.add(question66);
        Questions question67 = new Questions("Guatemala", "North America", "", "");
        questions.add(question67);
        Questions question68 = new Questions("Guinea", "Africa", "", "");
        questions.add(question68);
        Questions question69 = new Questions("Guinea-Bissau", "Africa", "", "");
        questions.add(question69);
        Questions question70 = new Questions("Guyana", "South America", "", "");
        questions.add(question70);
        Questions question71 = new Questions("Haiti", "North America", "", "");
        questions.add(question71);
        Questions question72 = new Questions("Honduras", "North America", "", "");
        questions.add(question72);
        Questions question73 = new Questions("Hungary", "Europe", "", "");
        questions.add(question73);
        Questions question74 = new Questions("Iceland", "Europe", "", "");
        questions.add(question74);
        Questions question75 = new Questions("India", "Asia", "", "");
        questions.add(question75);
        Questions question76 = new Questions("Indonesia", "Asia", "", "");
        questions.add(question76);
        Questions question77 = new Questions("Iran", "Asia", "", "");
        questions.add(question77);
        Questions question78 = new Questions("Iraq", "Asia", "", "");
        questions.add(question78);
        Questions question79 = new Questions("Ireland", "Europe", "", "");
        questions.add(question79);
        Questions question80 = new Questions("Israel", "Asia", "", "");
        questions.add(question80);
        Questions question81 = new Questions("Italy", "Europe", "", "");
        questions.add(question81);
        Questions question82 = new Questions("Ivory Coast", "Africa", "", "");
        questions.add(question82);
        Questions question83 = new Questions("Jamaica", "North America", "", "");
        questions.add(question83);
        Questions question84 = new Questions("Japan", "Asia", "", "");
        questions.add(question84);
        Questions question85 = new Questions("Jordan", "Asia", "", "");
        questions.add(question85);
        Questions question86 = new Questions("Kazakhstan", "Asia", "", "");
        questions.add(question86);
        Questions question87 = new Questions("Kenya", "Africa", "", "");
        questions.add(question87);
        Questions question88 = new Questions("Kiribati", "Oceania", "", "");
        questions.add(question88);
        Questions question89 = new Questions("Kosovo", "Europe", "", "");
        questions.add(question89);
        Questions question90 = new Questions("Kuwait", "Asia", "", "");
        questions.add(question90);
        Questions question91 = new Questions("Kyrgyzstan", "Asia", "", "");
        questions.add(question91);
        Questions question92 = new Questions("Laos", "Asia", "", "");
        questions.add(question92);
        Questions question93 = new Questions("Latvia", "Europe", "", "");
        questions.add(question93);
        Questions question94 = new Questions("Lebanon", "Asia", "", "");
        questions.add(question94);
        Questions question95 = new Questions("Lesotho", "Africa", "", "");
        questions.add(question95);
        Questions question96 = new Questions("Liberia", "Africa", "", "");
        questions.add(question96);
        Questions question97 = new Questions("Libya", "Africa", "", "");
        questions.add(question97);
        Questions question98 = new Questions("Liechtenstein", "Europe", "", "");
        questions.add(question98);
        Questions question99 = new Questions("Lithuania", "Europe", "", "");
        questions.add(question99);
        Questions question100 = new Questions("Luxembourg", "Europe", "", "");
        questions.add(question100);
        Questions question101 = new Questions("Macedonia", "Europe", "", "");
        questions.add(question101);
        Questions question102 = new Questions("Madagascar", "Africa", "", "");
        questions.add(question102);
        Questions question103 = new Questions("Malawi", "Africa", "", "");
        questions.add(question103);
        Questions question104 = new Questions("Malaysia", "Asia", "", "");
        questions.add(question104);
        Questions question105 = new Questions("Maldives", "Asia", "", "");
        questions.add(question105);
        Questions question106 = new Questions("Mali", "Africa", "", "");
        questions.add(question106);
        Questions question107 = new Questions("Malta", "Europe", "", "");
        questions.add(question107);
        Questions question108 = new Questions("Marshall Islands", "Oceania", "", "");
        questions.add(question108);
        Questions question109 = new Questions("Mauritania", "Africa", "", "");
        questions.add(question109);
        Questions question110 = new Questions("Mauritius", "Africa", "", "");
        questions.add(question110);
        Questions question111 = new Questions("Mexico", "North America", "", "");
        questions.add(question111);
        Questions question112 = new Questions("Micronesia", "Oceania", "", "");
        questions.add(question112);
        Questions question113 = new Questions("Moldova", "Europe", "", "");
        questions.add(question113);
        Questions question114 = new Questions("Monaco", "Europe", "", "");
        questions.add(question114);
        Questions question115 = new Questions("Mongolia", "Asia", "", "");
        questions.add(question115);
        Questions question116 = new Questions("Montenegro", "Europe", "", "");
        questions.add(question116);
        Questions question117 = new Questions("Morocco", "Africa", "", "");
        questions.add(question117);
        Questions question118 = new Questions("Mozambique", "Africa", "", "");
        questions.add(question118);
        Questions question119 = new Questions("Myanmar", "Asia", "", "");
        questions.add(question119);
        Questions question120 = new Questions("Namibia", "Africa", "", "");
        questions.add(question120);
        Questions question121 = new Questions("Nauru", "Oceania", "", "");
        questions.add(question121);
        Questions question122 = new Questions("Nepal", "Asia", "", "");
        questions.add(question122);
        Questions question123 = new Questions("Netherlands", "Europe", "", "");
        questions.add(question123);
        Questions question124 = new Questions("New Zealand", "Oceania", "", "");
        questions.add(question124);
        Questions question125 = new Questions("Nicaragua", "North America", "", "");
        questions.add(question125);
        Questions question126 = new Questions("Niger", "Africa", "", "");
        questions.add(question126);
        Questions question127 = new Questions("Nigeria", "Africa", "", "");
        questions.add(question127);
        Questions question128 = new Questions("North Korea", "Asia", "", "");
        questions.add(question128);
        Questions question129 = new Questions("Norway", "Europe", "", "");
        questions.add(question129);
        Questions question130 = new Questions("Oman", "Asia", "", "");
        questions.add(question130);
        Questions question131 = new Questions("Pakistan", "Asia", "", "");
        questions.add(question131);
        Questions question132 = new Questions("Palau", "Oceania", "", "");
        questions.add(question132);
        Questions question133 = new Questions("Panama", "North America", "", "");
        questions.add(question133);
        Questions question134 = new Questions("Papua New Guinea", "Oceania", "", "");
        questions.add(question134);
        Questions question135 = new Questions("Paraguay", "South America", "", "");
        questions.add(question135);
        Questions question136 = new Questions("Peru", "South America", "", "");
        questions.add(question136);
        Questions question137 = new Questions("Philippines", "Asia", "", "");
        questions.add(question137);
        Questions question138 = new Questions("Poland", "Europe", "", "");
        questions.add(question138);
        Questions question139 = new Questions("Portugal", "Europe", "", "");
        questions.add(question139);
        Questions question140 = new Questions("Qatar", "Asia", "", "");
        questions.add(question140);
        Questions question141 = new Questions("Romania", "Europe", "", "");
        questions.add(question141);
        Questions question142 = new Questions("Russia", "Asia", "", "");
        questions.add(question142);
        Questions question143 = new Questions("Rwanda", "Africa", "", "");
        questions.add(question143);
        Questions question144 = new Questions("Saint Kitts and Nevis", "North America", "", "");
        questions.add(question144);
        Questions question145 = new Questions("Saint Lucia", "North America", "", "");
        questions.add(question145);
        Questions question146 = new Questions("Saint Vincent and the Grenadines", "North America", "", "");
        questions.add(question146);
        Questions question147 = new Questions("Samoa", "Oceania", "", "");
        questions.add(question147);
        Questions question148 = new Questions("San Marino", "Europe", "", "");
        questions.add(question148);
        Questions question149 = new Questions("Sao Tome and Principe", "Africa", "", "");
        questions.add(question149);
        Questions question150 = new Questions("Saudi Arabia", "Asia", "", "");
        questions.add(question150);
        Questions question151 = new Questions("Senegal", "Africa", "", "");
        questions.add(question151);
        Questions question152 = new Questions("Serbia", "Europe", "", "");
        questions.add(question152);
        Questions question153 = new Questions("Seychelles", "Africa", "", "");
        questions.add(question153);
        Questions question154 = new Questions("Sierra Leone", "Africa", "", "");
        questions.add(question154);
        Questions question155 = new Questions("Singapore", "Asia", "", "");
        questions.add(question155);
        Questions question156 = new Questions("Slovakia", "Europe", "", "");
        questions.add(question156);
        Questions question157 = new Questions("Slovenia", "Europe", "", "");
        questions.add(question157);
        Questions question158 = new Questions("Solomon Islands", "Oceania", "", "");
        questions.add(question158);
        Questions question159 = new Questions("Somalia", "Africa", "", "");
        questions.add(question159);
        Questions question160 = new Questions("South Africa", "Africa", "", "");
        questions.add(question160);
        Questions question161 = new Questions("South Korea", "Asia", "", "");
        questions.add(question161);
        Questions question162 = new Questions("South Sudan", "Africa", "", "");
        questions.add(question162);
        Questions question163 = new Questions("Spain", "Europe", "", "");
        questions.add(question163);
        Questions question164 = new Questions("Sri Lanka", "Asia", "", "");
        questions.add(question164);
        Questions question165 = new Questions("Sudan", "Africa", "", "");
        questions.add(question165);
        Questions question166 = new Questions("Suriname", "South America", "", "");
        questions.add(question166);
        Questions question167 = new Questions("Swaziland", "Africa", "", "");
        questions.add(question167);
        Questions question168 = new Questions("Sweden", "Europe", "", "");
        questions.add(question168);
        Questions question169 = new Questions("Switzerland", "Europe", "", "");
        questions.add(question169);
        Questions question170 = new Questions("Syria", "Asia", "", "");
        questions.add(question170);
        Questions question171 = new Questions("Taiwan", "Asia", "", "");
        questions.add(question171);
        Questions question172 = new Questions("Tajikistan", "Asia", "", "");
        questions.add(question172);
        Questions question173 = new Questions("Tanzania", "Africa", "", "");
        questions.add(question173);
        Questions question174 = new Questions("Thailand", "Asia", "", "");
        questions.add(question174);
        Questions question175 = new Questions("Togo", "Africa", "", "");
        questions.add(question175);
        Questions question176 = new Questions("Tonga", "Oceania", "", "");
        questions.add(question176);
        Questions question177 = new Questions("Trinidad and Tobago", "North America", "", "");
        questions.add(question177);
        Questions question178 = new Questions("Tunisia", "Africa", "", "");
        questions.add(question178);
        Questions question179 = new Questions("Turkey", "Asia", "", "");
        questions.add(question179);
        Questions question180 = new Questions("Turkmenistan", "Asia", "", "");
        questions.add(question180);
        Questions question181 = new Questions("Tuvalu", "Oceania", "", "");
        questions.add(question181);
        Questions question182 = new Questions("Uganda", "Africa", "", "");
        questions.add(question182);
        Questions question183 = new Questions("Ukraine", "Europe", "", "");
        questions.add(question183);
        Questions question184 = new Questions("United Arab Emirates", "Asia", "", "");
        questions.add(question184);
        Questions question185 = new Questions("United Kingdom", "Europe", "", "");
        questions.add(question185);
        Questions question186 = new Questions("United States of America", "North America", "", "");
        questions.add(question186);
        Questions question187 = new Questions("Uruguay", "South America", "", "");
        questions.add(question187);
        Questions question188 = new Questions("Uzbekistan", "Asia", "", "");
        questions.add(question188);
        Questions question189 = new Questions("Vanuatu", "Oceania", "", "");
        questions.add(question189);
        Questions question190 = new Questions("Vatican City", "Europe", "", "");
        questions.add(question190);
        Questions question191 = new Questions("Venezuela", "South America", "", "");
        questions.add(question191);
        Questions question192 = new Questions("Vietnam", "Asia", "", "");
        questions.add(question192);
        Questions question193 = new Questions("Yemen", "Asia", "", "");
        questions.add(question193);
        Questions question194 = new Questions("Zambia", "Africa", "", "");
        questions.add(question194);
        Questions question195 = new Questions("Zimbabwe", "Africa", "", "");
        questions.add(question195);

        // return a list of retrieved questions
        return questions;
    }

    /**
     * Retrieves all of the quiz information of past quizzes, including the score and the date
     * that each quiz was taken.
     *
     * @return List<QuizInfo> List containing all of the results of past (completed) quizzes.
     */
    public List<QuizInfo> retrieveAllQuizzes() {

        ArrayList<QuizInfo> quizzes = new ArrayList<>();
        Cursor cursor = null;
        QuizInfo quiz;

        try {
            cursor = db.query( CountriesDBHelper.TABLE_QUIZZES, allQuizColumns,
                    null, null, null, null, null );

            long id = cursor.getLong( cursor.getColumnIndexOrThrow( CountriesDBHelper.QUIZZES_COLUMN_ID ) );
            if( cursor.getCount() > 1 ) {
                while( cursor.moveToNext() ) {
                    String date = cursor.getString( cursor.getColumnIndexOrThrow( CountriesDBHelper.QUIZZES_COLUMN_DATE ) );
                    int score = Integer.parseInt(cursor.getString( cursor.getColumnIndexOrThrow( CountriesDBHelper.QUIZZES_COLUMN_SCORE ) ));
                    quiz = new QuizInfo(score, date);
                    quiz.setId( id );
                    quizzes.add( quiz );
                    Log.d( DEBUG_TAG, "Retrieved Quiz: " + quiz );
                    id++;
                }
            }
            Log.d( DEBUG_TAG, "Number of Quizzes from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception: " + e );
        }
        finally{
            // close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved quizzes
        return quizzes;
    }

    /**
     * Stores the {@code Question} information into the database. A unique id (primary key) is generated
     * for the Question object, storing it along with its retrospective country and continent, followed by
     * two columns of with the random answer choices given.
     *
     * @param qa A quiz question object.
     * @return Questions The quiz question object.
     */
    public Questions storeQuestions( Questions qa ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the Questions argument.
        // This is how we are providing persistence to a Questions (Java object) instance
        // by storing it as a new row in the database table representing quiz questions.
        ContentValues values = new ContentValues();
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_COUNTRY, qa.getCountry());
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_CONTINENT, qa.getContinent() );
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_OPTION1, qa.getOption1());
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_OPTION2, qa.getOption2() );

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( CountriesDBHelper.TABLE_QUIZZES, null, values );

        // store the id (the primary key) in the Questions instance, as it is now persistent
        qa.setId( id );

        Log.d( DEBUG_TAG, "Stored quiz with id: " + String.valueOf( qa.getId() ) );

        return qa;
    }

    /**
     * Stores the information of {@code QuizInfo} into the database, representing the results of the
     * completed quiz. A unique id (primary key) is  generated for the {@code QuizInfo} object, storing
     * it along with its retrospective score and date of the quiz.
     *
     * @param quiz A quiz object.
     * @return QuizInfo The quiz results.
     */
    public QuizInfo storeQuiz( QuizInfo quiz ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the QuizInfo argument.
        // This is how we are providing persistence to a QuizInfo (Java object) instance
        // by storing it as a new row in the database table representing quiz results.
        ContentValues values = new ContentValues();
        values.put( CountriesDBHelper.QUIZZES_COLUMN_SCORE, quiz.getScore());
        values.put( CountriesDBHelper.QUIZZES_COLUMN_DATE, quiz.getDate() );

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( CountriesDBHelper.TABLE_QUIZZES, null, values );

        // store the id (the primary key) in the QuizInfo instance, as it is now persistent
        quiz.setId( id );

        Log.d( DEBUG_TAG, "Store new quiz with id: " + String.valueOf( quiz.getId() ) );

        return quiz;
    }

}
