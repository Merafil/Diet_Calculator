package com.merafil;

public class UserData {

    private char gender;
    private String lastName ;
    private String firstName ;
    private int age ;
    private double height ;
    private double weight ;
    private double activity;

    public UserData(char gender, String lastName, String firstName, int age, double height, double weight, double activity) {
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public void printUserData() {
        String sex = null;
        if(gender =='m')    {
            sex = "male";
        }
        else    {
            sex = "female";
        }
        System.out.println("UserData : " + "gender=" + sex + ", lastName=" + lastName +  ", firstName=" + firstName  + ", age=" + age + ", height=" + height + ", weight=" + weight + ", activity=" + activity);
    }

//    not used
    public String toString() {
        String sex = null;
        if(gender =='m')    {
             sex = "male";
        }
        else    {
            sex = "female";
        }
        return "UserData : " + "gender=" + sex + ", lastName=" + lastName +  ", firstName=" + firstName  + ", age=" + age + ", height=" + height + ", weight=" + weight + ", activity=" + activity ;
    }
}
