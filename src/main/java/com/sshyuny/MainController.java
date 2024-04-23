package com.sshyuny;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MainController {
    
    /*
     * API 요청으로 들어오면, 요청 데이터의
     * 타입 에러를 BindingResult에서 처리할 수 없다!
     */
    @PostMapping("/api/food")
    public String postFood(@RequestBody Food food, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("잘못된 데이터 요청!");
            return "잘못된 데이터";
        }

        return food.getName();
    }
    
}
