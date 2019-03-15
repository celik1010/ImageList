package com.example.selimelik.imagelist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SaveCommentImage extends AppCompatActivity {

    Uri selectedImageUri;
    byte[] imageDatas;
    ImageView imageView;
    ImageView imageViewCamera;
    EditText commentText;
    EditText placeNameTxt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_comment_image);
        commentText = findViewById(R.id.commmentTxt);
        placeNameTxt = findViewById(R.id.txtPlaceName);
        imageView = findViewById(R.id.imageView);
        imageViewCamera = findViewById(R.id.imageViewCamera);
        placeNameTxt.setText(getIntent().getExtras().getString("placeName"));

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public void uploadImage(View view) {
        final UUID uuid = UUID.randomUUID();
        final String imagename = "selimcelik@yandex.com" + uuid + ".jpg";


        StorageReference storageReference = mStorageRef.child("images/" + imagename);
        if (selectedImageUri == null) {
            UploadTask uploadTask = storageReference.putBytes(imageDatas);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageReference newStorageReference = FirebaseStorage.getInstance().getReference("images/" + imagename);
                    newStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(getApplicationContext(), "URI : " + uri.toString(), Toast.LENGTH_LONG).show();
                            String place_id = uuid.toString();
                            String user_id = "slmcelik@yandex.com";
                            String image_path = uri.toString();
                            Date date = new Date();
                            SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                            String current_time = date_format.format(date);
                            String uuidString = uuid.toString();

                            myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("PLACE_ID").setValue(place_id);
                            myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("USER_ID").setValue(user_id);
                            myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("IMAGE_PATH").setValue(image_path);
                            myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("POSTTIME").setValue(current_time);
                            myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("TIMESTAMP").setValue(-1 * new Date().getTime());

                            Intent intent = new Intent(getApplicationContext(), ImageListActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "URI Başarısız.", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Başarısız..", Toast.LENGTH_LONG).show();
                }
            });


        } else {
            storageReference.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            StorageReference newStorageReference = FirebaseStorage.getInstance().getReference("images/" + imagename);
                            newStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(getApplicationContext(), "URI : " + uri.toString(), Toast.LENGTH_LONG).show();
                                    String place_id = uuid.toString();
                                    String user_id = "slmcelik@yandex.com";
                                    String image_path = uri.toString();
                                    Date date = new Date();
                                    SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                                    String current_time = date_format.format(date);
                                    String uuidString = uuid.toString();

                                    myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("PLACE_ID").setValue(place_id);
                                    myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("USER_ID").setValue(user_id);
                                    myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("IMAGE_PATH").setValue(image_path);
                                    myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("POSTTIME").setValue(current_time);
                                    myRef.child("POSTS").child("IMAGEPATHS").child(uuidString).child("TIMESTAMP").setValue(-1 * new Date().getTime());

                                    Intent intent = new Intent(getApplicationContext(), ImageListActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "URI Başarısız.", Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Başarısız.", Toast.LENGTH_LONG).show();

                }
            });
        }

    }

    public void selectImage(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        }
    }

    public void takePhoto(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 10);
        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 20);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        /*Eğer İzin Varsa GAleri izni*/
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        /*Eğer İzin Varsa Kamera izni*/
        if (requestCode == 10) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 20);
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (requestCode == 20 && resultCode == RESULT_OK && data != null) {


            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageDatas = baos.toByteArray();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
