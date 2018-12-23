package com.quanglinhit.version2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionController {

    @GetMapping("/persion/v1")
    public PersionV1 persionV1(){
        return new PersionV1("John Baron");
    }

    @GetMapping("/persion/v2")
    public PersionV2 persionV2(){
        return new PersionV2(new Persion("Baron2","John2"));
    }

    // Request Parameter versioning
    @GetMapping(value = "/persion/param", params = "ver=1")
    public PersionV1 paramV1(){
        return new PersionV1("John Baron");
    }

    @GetMapping(value = "/persion/param", params = "ver=2")
    public PersionV2 paramV2(){
        return new PersionV2(new Persion("Baron2","John2"));
    }

    // Also call Content Negotiation or Accept versioning
    @GetMapping(value = "/persion/header", headers = "X-API-VERSION=69")
    public PersionV1 headerV1(){
        return new PersionV1("John Baron");
    }

    @GetMapping(value = "/persion/header", headers = "X-API-VERSION=96")
    public PersionV2 headerV2(){
        return new PersionV2(new Persion("Baron2","John2"));
    }

    // Accept
    @GetMapping(value = "/persion/produces", produces = "application/vnd.company.app-v1+json")
    public PersionV1 produceV1(){
        return new PersionV1("John Baron");
    }

    @GetMapping(value = "/persion/produces", produces = "application/vnd.company.app-v2+json")
    public PersionV2 produceV2(){
        return new PersionV2(new Persion("Baron2","John2"));
    }

}
