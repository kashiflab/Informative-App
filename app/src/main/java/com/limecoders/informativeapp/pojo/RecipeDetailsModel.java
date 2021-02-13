package com.limecoders.informativeapp.pojo;

public class RecipeDetailsModel {
    private String id;
    private String imageURl;
    private String mainTitle;
    private String title1;
    private String desc1;
    private String title2;
    private String desc2;

    @Override
    public String toString() {
        return "RecipeDetailsModel{" +
                "id='" + id + '\'' +
                ", imageURl='" + imageURl + '\'' +
                ", mainTitle='" + mainTitle + '\'' +
                ", title1='" + title1 + '\'' +
                ", desc1='" + desc1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", desc2='" + desc2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getImageURl() {
        return imageURl;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getTitle1() {
        return title1;
    }

    public String getDesc1() {
        return desc1;
    }

    public String getTitle2() {
        return title2;
    }

    public String getDesc2() {
        return desc2;
    }

    public RecipeDetailsModel(String id, String imageURl, String mainTitle, String title1, String desc1, String title2, String desc2) {
        this.id = id;
        this.imageURl = imageURl;
        this.mainTitle = mainTitle;
        this.title1 = title1;
        this.desc1 = desc1;
        this.title2 = title2;
        this.desc2 = desc2;
    }
}
