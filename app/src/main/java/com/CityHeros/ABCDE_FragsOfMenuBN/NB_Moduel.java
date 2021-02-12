package com.CityHeros.ABCDE_FragsOfMenuBN;

public class NB_Moduel {

    private String name  ,anyUri ;





    public NB_Moduel() {
        //need
    }

    public NB_Moduel(String name, String anyUri ) {
        if (name.trim().equals("")){
            name ="no name";

        }

        this.name = name;

        this.anyUri = anyUri;

    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnyUri() {
        return anyUri;
    }

    public void setAnyUri(String anyUri) {
        this.anyUri = anyUri;
    }
}
