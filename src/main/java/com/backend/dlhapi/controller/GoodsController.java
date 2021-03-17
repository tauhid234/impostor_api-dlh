package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.GoodsModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.GoodsService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/")
public class GoodsController {
    
    @Autowired
    private GoodsService svc;
    
    @RequestMapping(path = "insert_goods", method = RequestMethod.POST)
    public ResponseEntity insertOfGoods(@RequestParam("name") String name, @RequestParam("date") String date, @RequestParam("amount") String amount, GoodsModel model){
        
        if(name.equals("")){
            return new MessageResponse().BadRequest();
        }
        model.setName(name);
        model.setDate(date);
        model.setAmount(amount);
        
        
        svc.insertGoods(model);
        return new MessageResponse().Succes();
    }
    
    @GetMapping("goods_data")
    public ResponseEntity getAllGoods(){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity(svc.getDataGoods(), header, HttpStatus.OK);
    }
}
