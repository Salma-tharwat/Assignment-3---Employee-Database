package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final EmpDBHelper newEmp=new EmpDBHelper(this);
        ImageButton search=(ImageButton)findViewById(R.id.imageButton1);
        final EditText name=(EditText)findViewById(R.id.Name);
        final EditText Title=(EditText)findViewById(R.id.Title);
        final EditText Phone=(EditText)findViewById(R.id.Phone);
        final EditText Email=(EditText)findViewById(R.id.Email);
        final RadioGroup radiodepGroup=(RadioGroup)findViewById(R.id.radio_grp1);
        ImageButton add=(ImageButton)findViewById(R.id.ADD);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent i=new Intent(Main2Activity.this,MainActivity.class);
               startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                RadioButton radiodepButton;
                int selectedId=radiodepGroup.getCheckedRadioButtonId();
                int dep_id;
                radiodepButton=(RadioButton)findViewById(selectedId);

                if(radiodepButton.getText().toString()=="Sales")
                {
                  dep_id=1;
                }
                else if (radiodepButton.getText().toString()=="Accounting")
                {
                    dep_id=2;
                }
                else
                {
                    dep_id=3;
                }
                newEmp.Create_new_Emp(name.getText().toString(),Title.getText().toString(),Phone.getText().toString(),Email.getText().toString(),dep_id);
                Toast.makeText(Main2Activity.this,"Employee Added Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
