package com.example.springbootdemo.controller;

import com.example.springbootdemo.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getRequest(id);
    }

    private Map<String, String> getRequest(@PathVariable String id) {
        return requests.stream()
                .filter(m -> m.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> request) {
        request.put("id", String.valueOf(requests.size() + 1));
        requests.add(request);
        return request;
    }

    @PutMapping("{id}")
    public  Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> request) {
        Map<String, String> updRequest = getRequest(id);
        updRequest.putAll(request);
        updRequest.put("id", id);
        return updRequest;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> request = getRequest(id);
        requests.remove(request);
    }

}
