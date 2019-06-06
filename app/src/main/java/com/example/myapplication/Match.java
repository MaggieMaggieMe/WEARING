package com.example.myapplication;

public class Match {
    private String clothesId;
    private String buttomId;
    private String hairId;
    private String shoesId;
    private String accessorieId;

    public Match(String hairId1,String clothesId1,String buttomId1,String shoesId1,String accessorieId1){
        this.hairId = hairId1;
        this.clothesId = clothesId1;
        this.buttomId = buttomId1;
        this.shoesId = shoesId1;
        this.accessorieId = accessorieId1;
    }

    public String toString(){
        String text = hairId +" "+ clothesId +" "+ buttomId +" "+ shoesId +" "+ accessorieId;
        return text;
    }

    public String getHairId(){
        return hairId;
    }

    public String getClothesId(){
        return clothesId;
    }

    public String getButtomId(){
        return buttomId;
    }

    public String getShoesId(){
        return shoesId;
    }

    public String getAccessorieId(){
        return accessorieId;
    }
}
