package com.example.doggame;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchBreedsActivity extends AppCompatActivity {

    private TextView insertText;
    private Integer id = 1; //the id for the image assing to id
    private boolean showImages = true; //inisialize show true
    private ImageView breedView;

    public ArrayList<String> breedListArray = new ArrayList<>(Arrays.asList("affenpinscher","bloodhound","clumber","dingo","groenendael","keeshond","lhasa","malamute","newfoundland","otterhound"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breeds);

        insertText = findViewById(R.id.insertBar);
        breedView = findViewById(R.id.dogImg);

    }

    public void OnClickBegin(View view) {

        String text = insertText.getText().toString();
        if (!breedListArray.contains(text)){
            Toast.makeText(getBaseContext(), "ERROR, Details entered is not available in the system", Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(), "Please Enter The Breed", Toast.LENGTH_SHORT).show();
        } else {

            showImages = true;

            String imageName = text + "_" + id; //get the image name if text is a breed
            breedView.setImageDrawable(getResources().getDrawable(getBreedNo(imageName, "drawable", getApplicationContext())));
            id=id+1;
            showImage(5, breedView, text); //
        }
    }

    //refered from https://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android
    public void showImage( int seconds, final ImageView image, final String breedName){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String imageName = breedName + "_" + id;
                if (showImages==true) {
                    image.setImageDrawable(getResources().getDrawable(getBreedNo(imageName, "drawable", getApplicationContext()))); //get the image from the drawable folder
                    image.animate();
                    if (id > 10) { // there is only 10 image in for breed
                        id = 1; //the Id value is set 1 after all it should be because i breed has 10 images
                    } else {
                        id=id+1;
                    }
                    showImage(5, image, breedName); // image is show after  every 5 seconds
                }

            }
        }, seconds * 1000);
    }

    //https://stackoverflow.com/questions/41479017/how-to-get-id-of-images-in-drawable
    //check if the name of the image matches with the images in drawable
    protected final static int getBreedNo(final String imageName, final String imageType, final Context context) {
        final int imagedog = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (imagedog == 0) {
            throw new IllegalArgumentException("Image is not found with a Breed Name" + imageName);

        } else {
            return imagedog;
        }

    }

    public void onClickEnd (View view){
        showImages = false;
        insertText.setText(""); //set text blank

        breedView.setImageResource(android.R.color.transparent); // image view get trasparent

    }
}
