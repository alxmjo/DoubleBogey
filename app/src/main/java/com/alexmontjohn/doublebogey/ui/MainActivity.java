package com.alexmontjohn.doublebogey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.alexmontjohn.doublebogey.R;
import com.alexmontjohn.doublebogey.adapters.HoleAdapter;
import com.alexmontjohn.doublebogey.score.ScoreCard;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SCORES = "KEY_SCORES";
    public ScoreCard mScoreCard;
    HoleAdapter adapter;
    ListView lView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        outState.putIntegerArrayList(KEY_SCORES, mScoreCard.scores);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mScoreCard.scores = savedInstanceState.getIntegerArrayList(KEY_SCORES);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Generate holes and scores in scorecard
        mScoreCard = new ScoreCard(18);

        // Initialize custom adapter
        adapter = new HoleAdapter(mScoreCard, this);

        // Handle listview and assign adapter
        lView = (ListView) findViewById(R.id.holes);
        lView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearScores(MenuItem item){
        mScoreCard = new ScoreCard(18);
        adapter = new HoleAdapter(mScoreCard, this);
        lView = (ListView) findViewById(R.id.holes);
        lView.setAdapter(adapter);
    }
}
