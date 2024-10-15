package com.sshyuny.java;

import org.junit.jupiter.api.Test;

import com.sshyuny.language.enums.Dog;
import com.sshyuny.language.enums.ObjSimilarWithDogEnum;

public class EnumTest {
    
    @Test
    void 기본_enum() {
        String jindoSize = Dog.JINDO.size;
        System.out.println("jindoSize = " + jindoSize);
    }

    @Test
    void enum과_비슷한_클래스() {
        String jindoSize = ObjSimilarWithDogEnum.JINDO.size;
        System.out.println("jindoSize = " + jindoSize);
    }



}
