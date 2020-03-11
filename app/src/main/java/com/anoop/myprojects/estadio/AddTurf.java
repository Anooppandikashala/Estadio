package com.anoop.myprojects.estadio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anoop.myprojects.estadio.session_manager.Session;
import com.anoop.myprojects.estadio.ui.tools.ToolsFragment;

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



//    @Override
//    public void navigateToUpdateFragment() {
//        String fragmentTag = ToolsFragment.class.getSimpleName();
//        ToolsFragment fragment = ToolsFragment.newInstance();
//        FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
//        localFragmentTransaction.replace(R.id.nav_host_fragment, fragment, fragmentTag).addToBackStack(fragmentTag).commit();
//
//    }

    public void addTurfs() {

        String Name = name.getText().toString();

        String Address = address.getText().toString();

        String Desc = desc.getText().toString();

        String Phone = phone.getText().toString();

        Session session= new Session(this);

        if(!Name.isEmpty() && !Address.isEmpty() && !Desc.isEmpty() && !Phone.isEmpty())
        {
            databaseHelper = new DatabaseHelper(this);
            System.out.println("Session id : "+session.getId());
            databaseHelper.addTurfs(Name,Address,Desc,Phone,Integer.parseInt(session.getId()));
            Toast.makeText(this,"Turf Added Successfully",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this,"Give valid details",Toast.LENGTH_SHORT).show();

        }



    }


}
