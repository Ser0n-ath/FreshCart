package com.example.myapplication;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    int storeID;
    String storeName;
    String storeDescription;
    String storeBannerUrl;

    HashMap<Integer,Product> products;

    public Store(int id, String name, String description, String url){
        this.storeID = id;
        this.storeDescription = description;
        this.storeName = name;
        this.storeBannerUrl = url;
    }

    public int getStoreID(){
        return this.storeID;
    }
    public String getStoreDescription(){
        return this.storeDescription;
    }

    public String getStoreBannerUrl(){
        return this.storeBannerUrl;
    }

    public void setStoreID(int newId){
        this.storeID = newId;
    }
    public void setStoreDescription(String newDesc){
        this.storeDescription = newDesc;
    }

    public void setStoreBannerUrl(String newUrl){
        this.storeBannerUrl = newUrl;
    }


}





