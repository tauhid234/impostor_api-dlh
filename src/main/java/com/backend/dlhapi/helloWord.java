
package com.backend.dlhapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-word")
public class helloWord {
    
    @GetMapping("/data")
    public String data(){
        return "Hello Dunia Tipu";
    }
}
