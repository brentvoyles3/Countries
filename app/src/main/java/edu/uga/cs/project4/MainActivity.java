package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 pager = findViewById(R.id.viewpager);
        QuizQuestionPagerAdapter avpAdapter = new QuizQuestionPagerAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(avpAdapter);
    } //onCreate

    /*
    //Later, when we need to read from the cvs file:
    InputStream ins = getAssets().open("country_continent.csv");
    CSVReader reader = new CSVReader(new InputStreamReader(ins));
    String[] nextRow;
    while((nextRow = reader.readNext()) != null) {
        for(int i = 0; i < nextRow.length; i++) {
        String nextCell = nextRow[i];
        ...
    } //while
    */

} //MainActivity