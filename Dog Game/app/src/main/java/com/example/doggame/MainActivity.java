package com.example.doggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, breedIdentifyButton, dogIdentifyButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breedIdentifyButton = (Button) findViewById(R.id.btnIdentifyBreed);
        breedIdentifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityIdentifyBreed();
            }
        });

        dogIdentifyButton = (Button) findViewById(R.id.btnIdentifyDog);
        dogIdentifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityDogActivity();
            }
        });

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySearch();
            }
        });

    }
    public void ActivityIdentifyBreed(){
        Intent intent =new Intent(this,IdentifyBreedActivity.class );
        startActivity(intent);
    }

    public void ActivityDogActivity(){
        Intent intent =new Intent(this,IdentifyDogActivity.class );
        startActivity(intent);
    }

    public void ActivitySearch(){
        Intent intent =new Intent(this,SearchBreedsActivity.class );
        startActivity(intent);
    }


}
