package com.limecoders.informativeapp.pojo;

public class MainRecipeModel {
    private String id;
    private String name;
    private String imageUrl;
    private String subCategoriesId;

    @Override
    public String toString() {
        return "MainRecipeModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", subCategoriesId='" + subCategoriesId + '\'' +
                '}';
    }

    public MainRecipeModel(String id, String name, String imageUrl, String subCategoriesId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.subCategoriesId = subCategoriesId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubCategoriesId() {
        return subCategoriesId;
    }
}
