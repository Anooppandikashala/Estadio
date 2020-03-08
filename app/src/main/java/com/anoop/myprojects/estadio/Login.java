package com.anoop.myprojects.estadio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.anoop.myprojects.estadio.DataModels.UserModel;

public class Login extends AppCompatActivity {

    EditText username,password;
    String strusername,strpassword;

    private RadioGroup radioRegType;
    private RadioButton radioReg;

    Button signIn,Reg;

    boolean isowner=false;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userEditText);

        password = findViewById(R.id.passwordEditText);

        radioRegType  = findViewById(R.id.radioReg);


        Reg = findViewById(R.id.regButton);
        signIn = findViewById(R.id.loginButton);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDetails();
            }
        });


    }

    private  void storeDetails()
    {
        strusername= username.getText().toString();
        strpassword = password.getText().toString();

        isowner = isOwner();
    }

    private boolean isOwner()
    {
        radioReg = findViewById(radioRegType.getCheckedRadioButtonId());

        String btnName = radioReg.getText().toString().toLowerCase();

        if(!btnName.isEmpty() && btnName.equals("player"))
        {
            return false;
        }

        return true;
    }

    private void doSignIn()
    {
        if( !strusername.isEmpty() &&
            !strpassword.isEmpty())
        {
            int id = databaseHelper.isValidUser(strusername,strpassword,isowner);

            if(id != -1)
            {
                Intent intent = new Intent(Login.this,MainActivity.class);
                intent.putExtra("userId",id);
                startActivity(intent);
                finish();
            }
        }
        else
        {
            Toast.makeText(this,"Give valid details",Toast.LENGTH_SHORT).show();
        }
    }
}
