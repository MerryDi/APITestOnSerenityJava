package com.umarkets.models;
import static io.qala.datagen.RandomValue.length;


public class UserModel {


    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setToken(String token) {
        Token = token;
     }

    String Email;
    String Password;
    public String Token;


    public UserModel() {
        Email = length(6).alphanumeric() + "@" + length(3).english()+ "." + length(3).english();
        Password = length(10).english();
        Token = "";
    }

    public static String hash(String password) {
        String passHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password).toUpperCase();
        return passHash;
    }



}
