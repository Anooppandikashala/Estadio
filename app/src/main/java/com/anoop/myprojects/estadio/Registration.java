package com.anoop.myprojects.estadio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText username,password,name,address,conpassword,phone,email;

    String strusername,strpassword,strname,straddress,strconpassword,strphone,stremail;

    private RadioGroup radioRegType;
    private RadioButton radioReg;

    Button signIn,Reg;

    boolean isowner=false;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.usernameEditText);
        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        address = findViewById(R.id.addresEditText);
        conpassword = findViewById(R.id.conpasswordEditText);
        phone = findViewById(R.id.mobEditText);
        email = findViewById(R.id.emailEditText);

        radioRegType  = findViewById(R.id.radioReg);


        Reg = findViewById(R.id.regiButton);
        signIn = findViewById(R.id.loginButton);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDetails();
                doRegister();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private  void storeDetails()
    {
        strusername= username.getText().toString();
        strpassword = password.getText().toString();
        strname = name.getText().toString();
        straddress = address.getText().toString();
        strconpassword = conpassword.getText().toString();
        strphone= phone.getText().toString();
        stremail  = email.getText().toString();

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

    private void doRegister()
    {
        if( !strusername.isEmpty() &&
            !strpassword.isEmpty() &&
            !strname.isEmpty() &&
            !straddress.isEmpty() &&
            !strconpassword.isEmpty() &&
            !strphone.isEmpty() &&
            !stremail.isEmpty())
        {
            if(checkPassword(strpassword,strconpassword))
            {
                databaseHelper = new DatabaseHelper(this);
                long id = databaseHelper.addUser(strname,straddress,strphone,strusername,strpassword,stremail,isowner);
                if(id > 0)
                {
                    Toast.makeText(this,"Registration Success",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,Login.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this,"Password and Confirm Password must be same",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Give valid details",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPassword(String password1, String password2)
    {
        if(password1.equals(password2))
        {
            return true;
        }
        return  false;
    }


}
