package com.recorders.pet_diary.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public Map<String, Object> helloGet(@RequestParam(defaultValue = "User",required = false) String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", name+" Hello, World!");
        return response;
    }

    @PostMapping("/hello")
    @ResponseBody
    public Map<String, Object> helloPost(@RequestBody Name name) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", name.getName()+" Hello, World!");
        return response;
    }
}
@Getter
@NoArgsConstructor
class Name{
    private String name;
}

