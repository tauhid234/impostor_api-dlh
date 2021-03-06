package com.backend.dlhapi.controller;

import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.SessionService;
import com.backend.dlhapi.service.outGoodsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class OutGoodsController {
    
    @Autowired
    outGoodsService oGoodsService;
    
    @Autowired
    private SessionService session;
    
    @PostMapping("data_out_goods")
    public ResponseEntity getAlldata(@RequestParam(value = "api_key") String api_key){
        Optional dataSession = session.ApiKeySet(api_key);
        if(dataSession.isPresent()){
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity(oGoodsService.getAllInventoryIn(), header, HttpStatus.OK);
        }
        return new MessageResponse().NotFound();
    }
}
