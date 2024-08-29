package com.sshyuny.validation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookReqDto {
    
    private String isbn;
    private Integer price;
    private String title;
    private String subTitle;

    @Override
    public String toString() {
        return "isbn = " + isbn + " & price = " + price;
    }

}
