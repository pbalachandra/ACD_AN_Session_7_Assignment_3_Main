package com.acadgild.balu.acd_an_session_7_assignment_3_main;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView    textView_emp_name, textView_emp_name_value, textView_emp_age, textView_emp_age_value,
                textView_emp_photo;
    ImageView   imageView_emp_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);

        textView_emp_name = (TextView) findViewById(R.id.textView_emp_name);
        textView_emp_age = (TextView) findViewById(R.id.textView_emp_age);
        textView_emp_name_value = (TextView) findViewById(R.id.textView_emp_name_value);
        textView_emp_age_value = (TextView) findViewById(R.id.textView_emp_age_value);
        textView_emp_photo = (TextView) findViewById(R.id.textView_emp_photo);
        imageView_emp_photo = (ImageView) findViewById(R.id.imageView_emp_photo);

        dbHelper.clear_table();
        Employee employee = new Employee();
        employee.setName(getResources().getString(R.string.default_emp));
        employee.setAge(Integer.parseInt(getResources().getString(R.string.default_age)));
        employee.setPhoto(BitmapFactory.decodeResource(getResources(),R.drawable.sachin));
        dbHelper.add_employee_details(employee);

        employee = dbHelper.get_employee_details();
        textView_emp_name_value.setText(employee.getName());
        textView_emp_age_value.setText(String.valueOf(employee.getAge()));
        imageView_emp_photo.setImageBitmap(employee.getPhoto());
    }
}
