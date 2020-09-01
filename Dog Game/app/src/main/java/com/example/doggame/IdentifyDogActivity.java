package com.example.doggame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyDogActivity extends AppCompatActivity {
    private String dogImg1;
    private String dogImg2;
    private String dogImg3;
    private TextView breedNameToGuess, messageCorrectIncorrect;
    private ImageView firstImg, secondImg, thirdImg;
    private String pickedBreedImgName;
    private Integer select;

    private ArrayList<String> selectedRandomBreeds;

    private int numberOfDogsForBreed = 10; // number of breeds
    //inisialize the breeds to a array list
    public ArrayList<String> breedListArray = new ArrayList<>(Arrays.asList("affenpinscher","bloodhound","clumber","dingo","groenendael","keeshond","lhasa","malamute","newfoundland","otterhound"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        breedNameToGuess = findViewById(R.id.textView_Breed); //the TextView view is taken from the layout.xml and assign it to the breedNameToGuess
        messageCorrectIncorrect = findViewById(R.id.textView_correct_answer); //the TextView view is taken from the layout.xml and assign it to the messageCorrectIncorrect
        firstImg = findViewById(R.id.dog_image1); //the 1st ImageView is taken from the layout.xml and assign it to the firstImg
        secondImg = findViewById(R.id.dog_image2); ///the 2nd ImageView view is taken from the layout.xml and assign it to the secondImg
        thirdImg = findViewById(R.id.dog_image3); //the 3rd ImageView view is taken from the layout.xml and assign it to the thirdImg

        begin();
    }

    public void begin(){
        select =  0;

        selectedRandomBreeds = getBreedsRandom(); //assigning the 3 unique breeds to a array list
        pickedBreedImgName = selectedRandomBreeds.get(getRandomNumber(0,2)); //select a breed (1 out of 3) from the array list and for this breed we are finding image

        dogImg1 = selectedRandomBreeds.get(0) + "_" + getRandomNumber(1,numberOfDogsForBreed); // the first element (breed) from the array list and a random image( a random id ) from the breed is taken
        firstImg.setImageDrawable( getResources().getDrawable(getBreedNo(dogImg1, "drawable", getApplicationContext()))); // image is taken from the drawable file

        dogImg2 = selectedRandomBreeds.get(1) + "_" +getRandomNumber(1,numberOfDogsForBreed);  // the second element (breed) from the array list and a random image( a random id ) from the breed is taken
        secondImg.setImageDrawable( getResources().getDrawable(getBreedNo(dogImg2, "drawable", getApplicationContext()))); // image is taken from the drawable file

        dogImg3 = selectedRandomBreeds.get(2) + "_" + getRandomNumber(1,numberOfDogsForBreed); // the third element (breed) from the array list and a random image( a random id ) from the breed is taken
        thirdImg.setImageDrawable( getResources().getDrawable(getBreedNo(dogImg3, "drawable", getApplicationContext()))); // image is taken from the drawable file

        breedNameToGuess.setText(pickedBreedImgName.toUpperCase()); //displaying the selected breed in a text view
    }

    public ArrayList<String> getBreedsRandom(){

        ArrayList<String> uniqueBreedsList = new ArrayList<>(); // getting 3 breeds from breedListArray and checking is it available in the array list and then add them to the array list
        while(uniqueBreedsList.size()!=3){ //only take 3 Unique breeds
            String pickedBreed = getABreedRandom(); // take a breed
            if(uniqueBreedsList.indexOf(pickedBreed) == -1){ // check if the breed exist in the  list
                uniqueBreedsList.add(pickedBreed);      // add the unique breed to the array list
            }
        }
        return uniqueBreedsList; //the list return 3 unique breeds
    }

    //https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
    // get a random integer value from a range
    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    //https://stackoverflow.com/questions/41479017/how-to-get-id-of-images-in-drawable
    //check if the name of the image matches with the images in drawable
    protected final static int getBreedNo(final String imageName, final String imageType, final Context context) {
        final int imagedog = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (imagedog == 0) {
            throw new IllegalArgumentException("Image is not found with a Name " + imageName);

        } else {
            return imagedog;
        }

    }

    public String getABreedRandom(){ //get a random breed
        return breedListArray.get(getRandomNumber(0,(breedListArray.size()-1))); //get a random breed from breedListArray
    }

    // method for the first image
    public void OnClickFirstImage(View view){
        String currentAnswer;           // hold the result (is the seleted item is true or false)
        String Image1Name = dogImg1.split("_")[0]; // get the breed name for the image by seperating the Number of the image name
        select = select +1;     //increase the count when the user select the image
        if(select== 1) {    //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBreedImgName.equals(Image1Name)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT!";    // if above condition true correct is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase());       // display the status


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase());  // display the status
                firstImg.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBreed(); // find the correct image and background colour is change to green
             }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show(); //after the image is selected the only option is to select next
        }
    }

    // method for the Second image
    public void OnClickSecondImage(View view){
        String currentAnswer;  // hold the result (is the seleted item is true or false)
        String Image2Name = dogImg2.split("_")[0]; // get the breed name for the image by seperating the Number of the image name
        select = select +1;  //increase the count when the user select the image
        if(select== 1) { //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBreedImgName.equals(Image2Name)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT !"; // if above condition true correct is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase()); // display the status


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase()); // display the status
                secondImg.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBreed(); // find the correct image and background colour is change to green

            }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show();  //after the image is selected the only option is to select next
        }

    }


    public void OnClickThirdImage(View view){
        String currentAnswer; // hold the result (is the seleted item is true or false)
        select = select +1; //increase the count when the user select the image
        String Image3Name = dogImg3.split("_")[0]; // get the breed name for the image by seperating the Number of the image name

        if(select== 1) { //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBreedImgName.equals(Image3Name)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT !"; // if above condition true correct is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase()); // display the status


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                messageCorrectIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                messageCorrectIncorrect.setText(currentAnswer.toUpperCase()); // display the status
                thirdImg.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBreed(); // find the correct image and background colour is change to green


            }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show(); //after the image is selected the only option is to select next
        }

    }

    public void colourCorrectBreed(){
        String firstBreedName = dogImg1.split("_")[0]; //split the name from the image name
        String secondBreedName = dogImg2.split("_")[0];
        String thirdBreedName = dogImg3.split("_")[0];

        if (pickedBreedImgName.equals(firstBreedName)){ // if the image name is found equal to breed name the image background is coloured with green
            firstImg.setBackgroundColor(Color.parseColor("#008000"));
        }

        if (pickedBreedImgName.equals(secondBreedName)){
            secondImg.setBackgroundColor(Color.parseColor("#008000"));
        }

        if(pickedBreedImgName.equals(thirdBreedName)){
            thirdImg.setBackgroundColor(Color.parseColor("#008000"));
        }
    }

    public void OnClickNextButton(View view){ //method for the Next is selected
        if (select != 0) {  // select is already counted if the above image is selected
            begin(); //now advance to a next screen
            messageCorrectIncorrect.setText(""); //initialize the status black in the next screen

            firstImg.setBackgroundColor(Color.parseColor("#FFD700")); //initial the background color of the images
            secondImg.setBackgroundColor(Color.parseColor("#FFD700"));
            thirdImg.setBackgroundColor(Color.parseColor("#FFD700"));
        }else {
            Toast.makeText(this, "Click A Dog Photo", Toast.LENGTH_SHORT).show();
        }
    }


}
