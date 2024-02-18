package com.example.blog_spring_mvc.model;

public class Admin {


    private String adminMail;

    private String password;

    private static volatile Admin admin = null;

    public Admin(String adminMail, String password) {
        this.adminMail = adminMail;
        this.password = password;
    }


    public static Admin getAdmin(){
        if (admin == null){
            synchronized (Admin.class){
                if (admin == null){
                    admin = new Admin("admin@email.com","1234aA");
                }
            }
        }
        return admin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminMail='" + adminMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
