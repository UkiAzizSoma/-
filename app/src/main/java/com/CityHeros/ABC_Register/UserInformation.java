package com.CityHeros.ABC_Register;

public class UserInformation {

   //  private String UID;
     private String Uid ;
     private String pass;
     private String names;
     private String email;
     private String city;
     private String age;

    public UserInformation() {
    }

    public UserInformation(String Uid , String name, String email, String city, String age, String pass) {
        this.Uid = Uid ;
        this.names = name;
        this.email = email;
        this.city = city;
        this.age = age;
        this.pass=pass;
    }

    public String getId() {
        return Uid;
   }

    public void setId(String id) {
        this.Uid = id;
   }

    public String getName() {
        return names;
    }

    public void setName(String name) {
        this.names = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
