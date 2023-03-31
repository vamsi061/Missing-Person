package com.mperson.missingchild;

public class Police {
    private String email;
    private String name;
    private String id;
    private String num;

    public Police() {}

    public Police(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Police(String email,String name,String id,String num){
        this.email=email;
        this.name=name;
        this.id=id;
        this.num=num;
    }
    public String getEmail() {
        return email;
    }
    public String getName(){return name;}
    public String getId(){return  id;}
    public String getNum(){return num;}

}
