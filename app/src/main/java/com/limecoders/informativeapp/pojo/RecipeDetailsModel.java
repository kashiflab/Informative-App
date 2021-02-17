package com.limecoders.informativeapp.pojo;

public class RecipeDetailsModel {
    private String id;
    private String imageURl;
    private String mainTitle;
    private String title1;
    private String desc1;
    private String title2;
    private String desc2;
    private String title3;
    private String desc3;
    private String title4;
    private String desc4;
    private String title5;
    private String desc5;

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
                ", title3='" + title3 + '\'' +
                ", desc3='" + desc3 + '\'' +
                ", title4='" + title4 + '\'' +
                ", desc4='" + desc4 + '\'' +
                ", title5='" + title5 + '\'' +
                ", desc5='" + desc5 + '\'' +
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

    public String getTitle3() {
        return title3;
    }

    public String getDesc3() {
        return desc3;
    }

    public String getTitle4() {
        return title4;
    }

    public String getDesc4() {
        return desc4;
    }

    public String getTitle5() {
        return title5;
    }

    public String getDesc5() {
        return desc5;
    }

    public RecipeDetailsModel(String id, String imageURl, String mainTitle, String title1, String desc1,
                              String title2, String desc2, String title3, String desc3, String title4,
                              String desc4, String title5, String desc5) {
        this.id = id;
        this.imageURl = imageURl;
        this.mainTitle = mainTitle;
        this.title1 = title1;
        this.desc1 = desc1;
        this.title2 = title2;
        this.desc2 = desc2;
        this.title3 = title3;
        this.desc3 = desc3;
        this.title4 = title4;
        this.desc4 = desc4;
        this.title5 = title5;
        this.desc5 = desc5;
    }
}
