package com.sshyuny.language.enums;

public class ObjSimilarWithDogEnum {
    
    public static final ObjSimilarWithDogEnum REITRIEVER = new ObjSimilarWithDogEnum("big");
    public static final ObjSimilarWithDogEnum JINDO = new ObjSimilarWithDogEnum("medium");
    public static final ObjSimilarWithDogEnum BEAGLE = new ObjSimilarWithDogEnum("small");

    public String size;

    private ObjSimilarWithDogEnum(String size) {
        System.out.println("생성자 호출!");

        this.size = size;
    }
}
