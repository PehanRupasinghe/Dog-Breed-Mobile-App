package com.example.doggame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyBreedActivity extends AppCompatActivity {

    private String selectedBreed;
    private String dogImageName;
    private Button labelChangeBan;
    private TextView messageBreedName;
    private TextView messageCorrectIncorrect;
    private Spinner breedDropDown;
    private ImageView dogImage;

    //list of breeds
    public ArrayList<String> breedListArray = new ArrayList<>(Arrays.asList("affenpinscher","bloodhound","clumber","dingo","groenendael","keeshond","lhasa","malamute","newfoundland","otterhound"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        dogImage = findViewById(R.id.dogImage); //Image is assign to DogImage
        breedDropDown = findViewById(R.id.spinner); //Spinner is assign to breedDropDown
        messageCorrectIncorrect = findViewById(R.id.answer_textView); //TextView is assign to messageCorrectIncorrect To show Correct or Wrong
        messageBreedName = findViewById(R.id.correct_breedName_textView); //TextView is assign to messageBreedName To show Correct Name
        labelChangeBan = findViewById(R.id.press_button); //Button is assign to labelChangeBan

        begin();
    }

    public void begin(){
        dogImageName = getABreedRandom(); //get random breed name from the array list
        String imageName = dogImageName + "_" + getRandomNumber(1, 10);  //get image name with random image name and random Id

        dogImage.setImageDrawable( getResources().getDrawable(getBreedID(imageName, "drawable", getApplicationContext()))); // image is taken from the drawable file


        breedDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // get the selected name from the drop down
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBreed = breedDropDown.getItemAtPosition(position).toString(); //get the drop down value
                Toast.makeText(IdentifyBreedActivity.this,"Selected "+selectedBreed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdownArrayBreeds, android.R.layout.simple_spinner_item); // arrayAdapter with default spinner layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //layout is set to get list of drop down
        breedDropDown.setAdapter(adapter);


        labelChangeBan.setText("Submit"); //initially but is set to submit
    }

    public String getABreedRandom(){  //get a random breed
        return breedListArray.get(getRandomNumber(0,(breedListArray.size()-1)));
    }


    public static int getRandomNumber(int min, int max) { // get a random integer value from a range
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    //https://stackoverflow.com/questions/41479017/how-to-get-id-of-images-in-drawable
    //check if the name of the image matches with the images in drawable
    protected final static int getBreedID(final String imageName, final String imageType, final Context context) {
        final int breedID = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (breedID == 0) {
            throw new IllegalArgumentException("There is no dog with name " + imageName);
        }else{
            return breedID;
        }
    }


    public void changeBtn(View view) {
        String currentResult;


        if (labelChangeBan.getText().equals("Submit")){
            labelChangeBan.setText("Next"); //change the lable to text

            if (selectedBreed.equals(dogImageName) ){
                currentResult = "CORRECT!";
                messageCorrectIncorrect.setTextColor(Color.parseColor("#008000")); // to make the text colour green
            }else {
                currentResult = "WRONG!";
                messageCorrectIncorrect.setTextColor(Color.parseColor("#FF0000")); // to make the text colour red
            }

            messageCorrectIncorrect.setText(currentResult); //display the status
            messageBreedName.setText(dogImageName.toUpperCase()); //display the nem of breed

        }else {
            begin(); //go to the next screen
            messageCorrectIncorrect.setText(""); //show nothing because it a start of a new screen
            messageBreedName.setText("");
        }
    }
}
