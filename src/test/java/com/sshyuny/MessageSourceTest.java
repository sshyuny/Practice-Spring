package com.sshyuny;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;
    
    @Test
    void 메시지소스() {
        System.out.println("기본 국제화 값 = " + Locale.getDefault());
        
        String resultKo = ms.getMessage("greeting", null, Locale.KOREA);
        assertThat(resultKo).isEqualTo("안녕!");

        String resultEn = ms.getMessage("greeting", null, Locale.ENGLISH);
        assertThat(resultEn).isEqualTo("Hello!");

        String resultGe = ms.getMessage("greeting", null, Locale.GERMAN);
        assertThat(resultGe).isEqualTo("Hello!");

        String resultDefault = ms.getMessage("greeting", null, null);
        assertThat(resultDefault).isEqualTo("Hello!");
    }

    @Test
    void 없는_메시지소스() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, Locale.KOREA))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void 없는_메시지소스_기본메시지와_함께() {
        String result = ms.getMessage("null", null, "기본 메시지", Locale.KOREA);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void 메시지소스_매개변수와_함께() {
        String result = ms.getMessage("greeting.name", new Object[]{"sshyuny"}, Locale.KOREA);
        assertThat(result).isEqualTo("안녕, sshyuny");
    }


}
