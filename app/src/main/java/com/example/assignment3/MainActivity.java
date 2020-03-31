package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static String Emp_name_gotten;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton search=(ImageButton)findViewById(R.id.imageButton2);
        final EditText name=(EditText)findViewById(R.id.editText2);
        final EmpDBHelper newEmp=new EmpDBHelper(this);
        ListView Emp_list=(ListView)findViewById(R.id.lst_view);
        final ArrayAdapter<String>EmpAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        Emp_list.setAdapter(EmpAdapter);

        newEmp.Create_new_dep("Sales");
        newEmp.Create_new_dep("Accounting");
        newEmp.Create_new_dep("Marketing");


       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               EmpAdapter.clear();
               Toast.makeText(getApplicationContext(),"HERE", Toast.LENGTH_SHORT).show();
               String Emp_name=name.getText().toString();
              Cursor cursor=newEmp. SearchEmployees(Emp_name);
              Integer num=cursor.getCount();

              while(!cursor.isAfterLast())
               {
                   EmpAdapter.add(cursor.getString(0));
                   cursor.moveToNext();
               }
              //Toast.makeText(getApplicationContext(), num.toString(), Toast.LENGTH_SHORT).show();

           }
       });
   Emp_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id)
       {
           String selectedItem = (String) parent.getItemAtPosition(position);
           Emp_name_gotten=selectedItem;
          // Toast.makeText(getApplicationContext(),selectedItem, Toast.LENGTH_SHORT).show();
           Intent i=new Intent(MainActivity.this,Display_Employees.class);
           startActivity(i);
       }
   });
    }


}
