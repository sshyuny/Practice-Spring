package com.sshyuny.language.enums;

public enum Dog {
    REITRIEVER("big"), JINDO("medium"), BEAGLE("small");

    public String size;

    private Dog(String size) {
        //열거형 값은 해당 자료형의 인스턴스이다!
        System.out.println("생성자 호출!");

        this.size = size;
    }
}
