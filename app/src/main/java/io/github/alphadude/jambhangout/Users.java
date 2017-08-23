package io.github.alphadude.jambhangout;

/**
 * Created by alphadude on 8/23/17.
 */

public class Users {

    private String name;
    private String email;
    private int image;

    public Users(String name, String email,int image){
        this.name = name;
        this.email = email;
        this.image = image;

    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getImage(){
        return image;
    }
}
