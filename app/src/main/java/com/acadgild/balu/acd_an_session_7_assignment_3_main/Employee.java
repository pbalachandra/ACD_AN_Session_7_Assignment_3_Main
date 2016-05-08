package com.acadgild.balu.acd_an_session_7_assignment_3_main;

import android.graphics.Bitmap;

/**
 * Created by BALU on 4/21/2016.
 */
public class Employee
{
    private String name;
    private int age;
    private Bitmap photo;

    public Employee() {
    }

    public Employee(String name, int age, Bitmap photo)
    {
        this.name = name;
        this.age = age;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
