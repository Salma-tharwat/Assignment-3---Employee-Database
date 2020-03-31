package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Display_Employees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__employees);
        final EmpDBHelper newEmp=new EmpDBHelper(this);
        final TextView name=(TextView) findViewById(R.id.Name_view);
        final TextView Title=(TextView) findViewById(R.id.Title_View);
        final TextView Phone=(TextView) findViewById(R.id.Phone_View);
        final TextView Email=(TextView) findViewById(R.id.Email_View);
        final TextView Dep=(TextView) findViewById(R.id.Dep_view);
        Cursor cursor=newEmp.Get_Employee_ID(MainActivity.Emp_name_gotten);
        //Toast.makeText(getApplicationContext(), MainActivity.Emp_name_gotten, Toast.LENGTH_SHORT).show();
        Cursor cursor_Data=newEmp.Get_Employee_Data(Integer.parseInt(cursor.getString(0)));
        Cursor cursor_Dep_ID=newEmp.Get_Dep_Name(Integer.parseInt(cursor_Data.getString(5)));
        Integer num=cursor.getCount();
        name.setText(cursor_Data.getString(1));
        Title.setText(cursor_Data.getString(2));
        Phone.setText(cursor_Data.getString(3));
        Email.setText(cursor_Data.getString(4));
        Dep.setText(cursor_Dep_ID.getString(0));



    }
}
