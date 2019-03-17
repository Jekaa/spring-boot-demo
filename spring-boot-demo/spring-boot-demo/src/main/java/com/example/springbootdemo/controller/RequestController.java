package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("request")
public class RequestController {
    private List<Map<String, String>> requests = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("value", "request1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("value", "request2");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("value", "request3");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return requests;
    }
}
