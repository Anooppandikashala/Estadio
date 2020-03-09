package com.anoop.myprojects.estadio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTurf extends AppCompatActivity {

    EditText name,address,desc,phone;
    Button btn;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turf);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        desc = findViewById(R.id.desc);
        phone = findViewById(R.id.phone);

        btn = findViewById(R.id.addTurf);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTurfs();
            }
        });


    }

    public void addTurfs() {

        String Name = name.getText().toString();

        String Address = address.getText().toString();

        String Desc = desc.getText().toString();

        String Phone = phone.getText().toString();

        if(!Name.isEmpty() && !Address.isEmpty() && !Desc.isEmpty() && !Phone.isEmpty())
        {
            databaseHelper = new DatabaseHelper(this);
            databaseHelper.addTurfs(Name,Address,Desc,Phone);
            Toast.makeText(this,"Turf Added Successfully",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this,"Give valid details",Toast.LENGTH_SHORT).show();

        }



    }


}
