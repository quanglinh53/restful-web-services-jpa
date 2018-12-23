package com.quanglinhit.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/filter")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("val1", "val2", "val3");
    }

    @GetMapping("/filter-list")
    public List<SomeBean> retrieveListOfSomeBeans(){
        return Arrays.asList(new SomeBean("val1", "val2", "val3"), new SomeBean("val4", "val5", "val6"));
    }
}
