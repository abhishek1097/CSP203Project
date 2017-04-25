package menu;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.fazooz.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

public ImageButton button;
    private ImageView imageView;
    private Button camera,gallery;
    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE_REQUEST = 2;
    final int PIC_CROP = 3;
    //captured picture uri
    private Uri picUri;
    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_address, container, false);


        button = (ImageButton) view.findViewById(R.id.camera_btn);
        imageView = (ImageView) view.findViewById(R.id.yug);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.dialog_action,null);
                mbuilder.setView(mview);
                      mbuilder  .setTitle("Select profile photo");
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
                Button mLogin = (Button)mview.findViewById(R.id.camera);
                Button mLogin2 = (Button)mview.findViewById(R.id.gallery);
                mLogin.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (v.getId()) {
                                    case R.id.camera:
                                        // code
                                        try {

                                            //use standard intent to capture an image
                                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
                                            File imageFile = new File(imageFilePath);
                                            picUri = Uri.fromFile(imageFile); // convert path to Uri
                                            takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT, picUri );
                                            startActivityForResult(takePictureIntent, CAMERA_CAPTURE);
                                        }
                                        catch(ActivityNotFoundException anfe){
                                            //display an error message
                                            String errorMessage = "Whoops - your device doesn't support capturing images!";

                                        }
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                );


                mLogin2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (v.getId()) {
                                    case R.id.gallery:
                                        // code
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        // Start the Intent
                                        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                );



            }
        });


        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
//user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE){
                Uri uri = picUri;
                Log.d("picUri", uri.toString());
                performCrop();
            }
            else if(requestCode == PICK_IMAGE_REQUEST){
                picUri= data.getData();
                performCrop();
            }
            //user is returning from cropping the image
            else if(requestCode == PIC_CROP){
//get the returned data
                Bundle extras = data.getExtras();
//get the cropped bitmap
                Bitmap thePic = (Bitmap) extras.get("data");
                //display the returned cropped image
                imageView.setImageBitmap(thePic);

            }
        }
    }

    private void performCrop() {


        try {
//call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
//set crop properties
            cropIntent.putExtra("crop", "true");
//indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
//indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
//retrieve data on return
            cropIntent.putExtra("return-data", true);
//start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";

        }

    }


}
