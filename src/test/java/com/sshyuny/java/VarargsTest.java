package com.sshyuny.java;

import org.junit.jupiter.api.Test;

/*
 * 매개변수의 가변 인자 선언
 * varargs(short for variable-length arguments)
 */
public class VarargsTest {
    
    /*
     * 가변 인자는 배열을 참조한다는 점을 아래에서 알 수 있다.
     * 
     * 아래 메서드가 호출되면
     * 컴파일러는 배열을 생성하여 전달되는 인자들을 모두 담는다.
     * 그리고 그 배열이 매개변수 values에 전달된다.
     */
    void printWithVarargs(String...values) {
        System.out.println("가변인자 매개변수 길이 = " + values.length);
        for (String s : values) {
            System.out.print(s + '\t');
        }
    }

    void printWithoutVarargs(String[] values) {
        System.out.println("배열 매개변수 길이 = " + values.length);
        for (String s : values) {
            System.out.print(s + '\t');
        }
    }



    @Test
    void 테스트() {
        printWithVarargs("포도", "치즈케이크", "커피");
        System.out.println();

        printWithoutVarargs(new String[] {"포도", "치즈케이크", "커피"});
    }

}
