package com.example.questioner;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {
    public SharedPreferences usrAcc;
    EditText Newpass;
    EditText usr;
    MyDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        dbHelper = new MyDbHelper(this);

        usr = (EditText)findViewById(R.id.usrinp);
        Newpass= (EditText)findViewById(R.id.editpass);
        Button btnChang = (Button) findViewById(R.id.btnChange);

        btnChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usr.getText().toString();
                String p =Newpass.getText().toString();
                int rr =dbHelper.changepass(username,p);


                if(rr==-1){
                    Toast.makeText(getApplicationContext(), "The User is Not Exists", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                Toast.makeText(getBaseContext(),
                        "Password has Changed", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(ChangePass.this,LoginActivity.class));
                ChangePass.this.finish();}


            }
        });

    }
}
