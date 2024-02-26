package com.example.questioner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference rf;


    EditText userNameEditText;
    EditText passwordEditText;
    MyDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new MyDbHelper(this);
        userNameEditText = findViewById(R.id.registerUsername);
        passwordEditText = findViewById(R.id.registerPassword);
        findViewById(R.id.registerSignUpBtn).setOnClickListener(view -> registerUser(userNameEditText.getText().toString().trim(),passwordEditText.getText().toString().trim()));

        findViewById(R.id.loginCreateAccountLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                RegisterActivity.this.finish();
            }
        });

    }

    public void registerUser(String username,String password){
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill both the fields!", Toast.LENGTH_SHORT).show();
        }else if(username.equalsIgnoreCase("admin")){
            Toast.makeText(this, "Please choose another username!", Toast.LENGTH_SHORT).show();
        }else {
            long result = dbHelper.createUserAccount(username, password);
            if(result == -1){
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("QUESTIONER",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER",username);

                editor.apply();

                //editor.putString(username, password);
                //editor.commit();

                db = FirebaseDatabase.getInstance();
                rf = db.getReference("users");
                helper h = new helper(username,password);
                rf.child(username).setValue(h);

                startActivity(new Intent(RegisterActivity.this,TopicActivity.class));
                RegisterActivity.this.finish();
            }
        }
    }
}