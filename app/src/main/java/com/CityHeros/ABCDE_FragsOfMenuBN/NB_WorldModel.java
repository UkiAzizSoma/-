package com.CityHeros.ABCDE_FragsOfMenuBN;



public class NB_WorldModel {



    private  String id;
    private String name;
    private String mImage;

    public NB_WorldModel() {
    }

    public NB_WorldModel(String id,String name, String mImage) {
        if (name.trim().equals("")){
            name = "لا يوجد اسم";
        }
        this.name = name;
        this.mImage = mImage;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
