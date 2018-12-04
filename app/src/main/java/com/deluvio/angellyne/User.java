package com.deluvio.angellyne;

public class User {
    private String fullname, age, gender;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String fullname, String age, String gender) {
        this.fullname = fullname;
        this.age = age;
        this.gender = gender;
    }

    public User() {

    }
}