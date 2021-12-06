package com.example.practicaso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button(View v) {

        // get properties form the app interface
        EditText minMetersRowedEditText = findViewById(R.id.minMetersRowed);
        EditText maxMetersRowedEditText = findViewById(R.id.maxMetersRowed);
        EditText totalRaceMetersEditText = findViewById(R.id.totalRaceMeters);

        int minMeters = Integer.parseInt(minMetersRowedEditText.getText().toString());
        int maxMeters = Integer.parseInt(maxMetersRowedEditText.getText().toString());
        int totalRaceMeters = Integer.parseInt(totalRaceMetersEditText.getText().toString());
        //Create Race and Canoes
        Race theRace = new Race(totalRaceMeters, minMeters, maxMeters);
        Canoe canoeA = new Canoe("A");
        Canoe canoeB = new Canoe("B");

        //Create Rowers and helmsman
        Rower rower1 = new Rower("RowerA1", theRace, canoeA);
        Rower rower2 = new Rower("RowerA2", theRace, canoeA);
        Rower rower3 = new Rower("RowerA3", theRace, canoeA);
        Helmsman helmsman1 = new Helmsman("HelmsmanA", theRace, canoeA);

        Rower rower4 = new Rower("RowerB1", theRace, canoeB);
        Rower rower5 = new Rower("RowerB2", theRace, canoeB);
        Rower rower6 = new Rower("RowerB3", theRace, canoeB);
        Helmsman helmsman2 = new Helmsman("HelmsmanB", theRace, canoeB);

        //The rowers and helmsman prepare themselves and start
        rower1.start();
        rower2.start();
        rower3.start();

        rower4.start();
        rower5.start();
        rower6.start();

        helmsman1.start();
        helmsman2.start();

        //To see the classification, all the canoes must arrive at the finish line
        try {
            rower1.join();
            rower2.join();
            rower3.join();
            helmsman1.join();

            rower4.join();
            rower5.join();
            rower6.join();
            helmsman2.join();
        } catch (Exception ignored) {
        }

        Log.d("MyTag",
                String.format("End of the race. The winner is Canoe: %s",
                        theRace.getWinner().getName()));
    }

}
