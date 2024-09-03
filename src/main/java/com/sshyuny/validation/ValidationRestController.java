package com.sshyuny.validation;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshyuny.validation.dto.BookReqDto;

@RestController
public class ValidationRestController {
    
    /*
     * json으로 받은 요청은 타입에러 처리 못함!
     * [일반] curl -X POST -H 'Content-Type: application/json' -d '{"isbn": "11223344"}' http://localhost:8080/validation/myself/json
     * [타입에러 발생] curl -X POST -H 'Content-Type: application/json' -d '{"price": "str"}' http://localhost:8080/validation/myself/json
     */
    @PostMapping("/validation/json")
    public String myselfJson(@RequestBody BookReqDto bookReqDto, BindingResult bindingResult) {
        System.out.println("/validation/json 요청 컨트롤러 메서드 들어옴!");
        return "done";
    }

}
