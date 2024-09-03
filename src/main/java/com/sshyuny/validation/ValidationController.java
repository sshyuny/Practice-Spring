package com.sshyuny.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshyuny.validation.dto.BookReqDto;

@Controller
public class ValidationController {

    @GetMapping("/validation/myself")
    public String myselfGet() {
        return "validation-success";
    }
    
    /*
     * FieldError 첫 번째 생성자 사용 버전!
     * 
     * [일반] curl -X POST -d "isbn=1122334455&price=12000" http://localhost:8080/validation/myself
     * [타입에러 컨트롤러 진입 가능!] curl -X POST -d "isbn=1122334455&price=twothousand" http://localhost:8080/validation/myself
     * [isbn, pricd 에러] curl -X POST -d "isbn=11223&pri" http://localhost:8080/validation/myself
     * [글로벌에러] curl -X POST -d "isbn=1122334455&price=12000&title=abcdefghijklmnopqrstuvwx&subTitle=abcdefghijklmnopqrstuvwx" http://localhost:8080/validation/myself
     */
    @PostMapping("/validation/myself")
    public String myself(@ModelAttribute BookReqDto bookReqDto, BindingResult bindingResult) {
        System.out.println(bookReqDto);

        String isbn = bookReqDto.getIsbn();
        Integer price = bookReqDto.getPrice();
        String title = bookReqDto.getTitle();
        String subTitle = bookReqDto.getSubTitle();

        if (isbn == null || isbn.isBlank() || isbn.length() < 10 || isbn.length() > 13) {
            bindingResult.addError(new FieldError("bookReqDto", "isbn", "ISBN 자릿수를 확인해주세요."));
        }

        if (price == null || price <= 1000 || price >= 10000000) {
            bindingResult.addError(new FieldError("bookReqDto", "price", "가격은 1000원 이상 10000000원 이하여야 합니다."));
        }

        if (title != null && subTitle != null) {
            if (title.length() + subTitle.length() > 24) {
                bindingResult.addError(new ObjectError("bookReqDto", "제목과 부제목의 글자 수 합이 24자를 넘어서는 안됩니다."));
            }
        }

        if (bindingResult.hasErrors()) {
            System.out.println("검증 실패!");
            return "validation-hasError";
        }
        
        System.out.println("검증 통과!");
        return "validation-success";
    }

    /*
     * json으로 받은 요청은 타입에러 처리 못함!
     * [일반] curl -X POST -H 'Content-Type: application/json' -d '{"isbn": "11223344"}' http://localhost:8080/validation/myself/json
     * [타입에러 발생] curl -X POST -H 'Content-Type: application/json' -d '{"price": "str"}' http://localhost:8080/validation/myself/json
     */
    @ResponseBody
    @PostMapping("/validation/myself/json")
    public String myselfJson(@RequestBody BookReqDto bookReqDto, BindingResult bindingResult) {
        System.out.println("/validation/myself/json 요청 컨트롤러 메서드 들어옴!");
        return "done";
    }

    /*
     * FieldError 두 번째 생성자 사용 버전!
     */
    @PostMapping("/validation/myself/with-codes")
    public String myselfWithCodes(@ModelAttribute BookReqDto bookReqDto, BindingResult bindingResult) {

        System.out.println(bookReqDto);

        String isbn = bookReqDto.getIsbn();
        Integer price = bookReqDto.getPrice();
        String title = bookReqDto.getTitle();
        String subTitle = bookReqDto.getSubTitle();

        if (isbn == null || isbn.isBlank() || isbn.length() < 10 || isbn.length() > 13) {
            bindingResult.addError(new FieldError("bookReqDto", "isbn", isbn, false, new String[] {"length.bookReqDto.isbn"}, null, null));
        }

        if (price == null || price <= 1000 || price >= 10000000) {
            bindingResult.addError(new FieldError("bookReqDto", "price", price, false, new String[] {"range.bookReqDto.price"}, new Object[] {1000, 1000000}, null));
        }

        if (title != null && subTitle != null) {
            int totalTitleLength = title.length() + subTitle.length();
            if (totalTitleLength > 24) {
                bindingResult.addError(new ObjectError("bookReqDto", new String[] {"totalTitleMax"}, new Object[] {24, totalTitleLength}, null));
            }
        }

        if (bindingResult.hasErrors()) {
            System.out.println("검증 실패!");
            return "validation-hasError";
        }
        
        System.out.println("검증 통과!");
        return "validation-success";
    }
}
