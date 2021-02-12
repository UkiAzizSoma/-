package com.CityHeros.ABCDE_FragsOfMenuBN;

public class NB_FameModle {

    private String name ;
    private String title ;
    private int image;
    private String url;


    public NB_FameModle(String name, String title, int image , String url) {
        this.name = name;
        this.title = title;
        this.image = image;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
