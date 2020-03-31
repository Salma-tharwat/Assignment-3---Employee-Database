package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;

public class EmpDBHelper extends SQLiteOpenHelper {
    private static String databaseName="EmpDatabase";
    SQLiteDatabase EmpDatabase;
    public EmpDBHelper(Context context)
    {
        super(context,databaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Department (DeptID integer primary key autoincrement,Name text);");
        db.execSQL("create table Employee (EmpID integer primary key autoincrement,Name text not null, Title text not null , Phone text not null,Email text not null,deptID integer,FOREIGN KEY(deptID)REFERENCES Department (DeptID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Department");
        db.execSQL("drop table if exists Employee");
        onCreate(db);
    }
    public void Create_new_Emp(String name,String Title,String Phone,String Email,Integer Department)
    {
        ContentValues row=new ContentValues();
        row.put("Name",name);
        row.put("Title",Title);
        row.put("Phone",Phone);
        row.put("Email",Email);
        row.put("deptID",Department);
        EmpDatabase=getWritableDatabase();
        EmpDatabase.insert("Employee",null,row);
        EmpDatabase.close();
    }
    public void Create_new_dep(String name)
    {
        ContentValues row=new ContentValues();
        row.put("Name",name);
        EmpDatabase=getWritableDatabase();
        EmpDatabase.insert("Department",null,row);
        EmpDatabase.close();
    }
    public Cursor SearchEmployees(String name)
    {
        EmpDatabase=getReadableDatabase();
        Cursor resultSet = EmpDatabase.rawQuery("Select Name from Employee Where Name Like \'%"+name+"%\'",null);
        resultSet.moveToFirst();
        return resultSet;
    }
    public Cursor Get_Employee_Data(Integer ID )
    {
        EmpDatabase=getReadableDatabase();
        Cursor resultSet = EmpDatabase.rawQuery("Select * from Employee Where EmpID Like \'%"+ID+"%\'",null);
        resultSet.moveToFirst();
        return resultSet;
    }
    public Cursor Get_Employee_ID(String name)
    {
        EmpDatabase=getReadableDatabase();
        Cursor resultSet = EmpDatabase.rawQuery("Select EmpID from Employee Where Name Like \'%"+name+"%\'",null);
        resultSet.moveToFirst();
        return resultSet;
    }
    public Cursor Get_Dep_Name(Integer ID)
    {
        EmpDatabase=getReadableDatabase();
        Cursor resultSet = EmpDatabase.rawQuery("Select Name from Department Where DeptID  Like \'%"+ID+"%\'",null);
        resultSet.moveToFirst();
        return resultSet;
    }
    public void clearDatabase() {

        String clearDBQuery = "DELETE FROM Department";
        String clearDBQuery1 = "DELETE FROM Employee";
        EmpDatabase.execSQL(clearDBQuery);
        EmpDatabase.execSQL(clearDBQuery1);
    }


}
