package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    Button startingBusinessButton, continueBusinessButton ;
    int backButtonCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        startingBusinessButton = (Button) findViewById(R.id.StartingBusinessButton);
        continueBusinessButton = (Button) findViewById(R.id.continueBusinessButton);

        startingBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedSynopsi = getSharedPreferences("synopsi", Context.MODE_PRIVATE);
                sharedSynopsi.edit().clear().commit();

                SharedPreferences sharedBusinessModelHome = getSharedPreferences("businessModel", Context.MODE_PRIVATE);
                sharedBusinessModelHome.edit().clear().commit();

                SharedPreferences sharedProiontaHome = getSharedPreferences("proiontaTabs",Context.MODE_PRIVATE);
                sharedProiontaHome.edit().clear().commit();

                SharedPreferences sharedTeamTabsHome = getSharedPreferences("TeamTabs", Context.MODE_PRIVATE);
                sharedTeamTabsHome.edit().clear().commit();

                SharedPreferences sharedEquipmentTabsHome = getSharedPreferences("equipmentTabs", Context.MODE_PRIVATE);
                sharedEquipmentTabsHome.edit().clear().commit();

                startActivity(new Intent(HomeScreen.this,BusinessCreationScreen.class));
            }
        });

        continueBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,BusinessCreationScreen.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedBusinessModel = getSharedPreferences("businessModel", Context.MODE_PRIVATE);
        SharedPreferences sharedProionta = getSharedPreferences("proiontaTabs",Context.MODE_PRIVATE);
        SharedPreferences sharedTeamTabs = getSharedPreferences("TeamTabs", Context.MODE_PRIVATE);
        SharedPreferences sharedEquipmentTabs = getSharedPreferences("equipmentTabs", Context.MODE_PRIVATE);
        SharedPreferences sharedSynopsi = getSharedPreferences("synopsi", Context.MODE_PRIVATE);

        continueBusinessButton.setEnabled(sharedBusinessModel.getBoolean("businessModelDone", false) ||
                sharedProionta.getBoolean("ProiontaTabsDone",false) ||
                sharedTeamTabs.getBoolean("TeamTabsDone",false) ||
                sharedEquipmentTabs.getBoolean("EquipmentTabsDone",false) ||
                !sharedSynopsi.getString("synopsiText","").isEmpty());

        backButtonCount=0;
    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Πατήστε το πίσω κουμπί άλλη μια φορά για να κλείσει η εφαρμογή.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
