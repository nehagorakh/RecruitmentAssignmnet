package recruitment.assignment.recruitment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("")
    public String sayHello(@Valid @RequestBody String email){
        return "hello";
    }
}
