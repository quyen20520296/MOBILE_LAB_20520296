package com.example.manhinhsignuplogin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btnSignUp;
    EditText txtFullname, txtPhone, txtUsername, txtPassword;
    TextView txtLoginClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtFullname = findViewById(R.id.FullnameFill);
        txtPhone = findViewById(R.id.PhoneFill);
        txtUsername = findViewById(R.id.UsernameFill);
        txtPassword = findViewById(R.id.PasswordFill);
        btnSignUp = findViewById(R.id.SignUpButton);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = txtFullname.getText().toString();
                String phone = txtPhone.getText().toString();
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (fullname.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty())
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                if (!(username.matches("[a-zA-Z]+"))) {
                    Toast.makeText(SignUpActivity.this, "Username contains only letters", Toast.LENGTH_SHORT).show();
                } else if (!(username.length() >= 6)) {
                    Toast.makeText(SignUpActivity.this, "Username must be 6 characters or more", Toast.LENGTH_SHORT).show();
                } else if (!(password.length() >= 6)) {
                    Toast.makeText(SignUpActivity.this, "Password must be 6 characters or more", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("fullname", fullname);
                    user.put("username", username);
                    user.put("phone", phone);
                    try {
                        user.put("password", MaHoa.encrypt(password));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(SignUpActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });

        txtLoginClick = findViewById(R.id.LoginTextClick);
        txtLoginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}