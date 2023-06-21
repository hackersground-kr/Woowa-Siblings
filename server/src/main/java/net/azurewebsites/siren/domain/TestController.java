package net.azurewebsites.siren.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "성공!!!";
    }

}
