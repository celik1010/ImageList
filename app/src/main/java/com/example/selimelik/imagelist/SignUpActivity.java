package com.example.selimelik.imagelist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private Button registerButton;
    private EditText emailText;
    private EditText passwordText;
    private EditText password1Text;
    private EditText usernameText;
    private EditText nameText;
    private EditText websiteText;
    private EditText userinfoText;
    private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerButton = findViewById(R.id.register);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        password1Text = findViewById(R.id.password1);
        usernameText = findViewById(R.id.username);
        nameText = findViewById(R.id.name);
        websiteText = findViewById(R.id.website);
        userinfoText = findViewById(R.id.userinfo);
        genderSpinner = findViewById(R.id.gender);


        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void register(View view) {
        final UUID uuid = UUID.randomUUID();
        // final String imagename = "selimcelik@yandex.com" + uuid + ".jpg";

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String user_id = uuid.toString();
                String email = emailText.getText().toString();
                String username = usernameText.getText().toString();
                String name = nameText.getText().toString();
                String website = websiteText.getText().toString();
                String userInfo = userinfoText.getText().toString();
                int gender = 0;
                //   String image_path = uri.toString();
                String image_path = "";
                Date date = new Date();
                SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                String current_time = date_format.format(date);

                myRef.child("USERS").child(user_id).child("email").setValue(email);
                myRef.child("USERS").child(user_id).child("username").setValue(username);
                myRef.child("USERS").child(user_id).child("name").setValue(name);
                myRef.child("USERS").child(user_id).child("website").setValue(website);
                myRef.child("USERS").child(user_id).child("userInfo").setValue(userInfo);
                myRef.child("USERS").child(user_id).child("photoPath").setValue(image_path);
                myRef.child("USERS").child(user_id).child("gender").setValue(gender);
                myRef.child("USERS").child(user_id).child("registerDate").setValue(current_time);
                myRef.child("USERS").child(user_id).child("timestamp").setValue(-1 * new Date().getTime());
                Intent intent = new Intent(getApplicationContext(), ImageListActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Başarısız.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
