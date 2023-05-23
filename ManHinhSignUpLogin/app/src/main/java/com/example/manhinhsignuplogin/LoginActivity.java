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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersRef = db.collection("users");
    Button btnLogin;
    TextView txtSignUpClick;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.UsernameFill);
        txtPassword = findViewById(R.id.PasswordFill);
        btnLogin = findViewById(R.id.LoginButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = null;
                try {
                    password = txtPassword.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                }
                else {
                    String inputpassword = password;
                    usersRef.whereEqualTo("username", username)
                            .get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (!querySnapshot.isEmpty()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String databasepassword = document.getString("password");
                                            try {
                                                String databasepassword_giaima = MaHoa.decrypt(databasepassword);
                                                if (inputpassword.equals(databasepassword_giaima)) {
                                                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                    intent.putExtra("username", username);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    //Em viết cái này để nếu nhập sai password thì tự động điền lại password đúng để login nhanh hơn
                                                    txtPassword.setText(databasepassword_giaima);
                                                    Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (Exception e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            });
                }
            }
        });

        txtSignUpClick = findViewById(R.id.SignUpTextClick);
        txtSignUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}