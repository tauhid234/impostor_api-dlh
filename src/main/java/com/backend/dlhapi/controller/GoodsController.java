package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.GoodsModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.GoodsService;
import com.backend.dlhapi.service.SessionService;
import java.util.Collection;
import java.util.Optional;
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
    
    @Autowired
    private SessionService service;
    
    @RequestMapping(path = "insert_goods", method = RequestMethod.POST)
    public ResponseEntity insertOfGoods(@RequestParam("api_key") String api_key, @RequestParam("name") String name, @RequestParam("date") String date, 
                                        @RequestParam("amount") String amount, @RequestParam("year") String year, GoodsModel model){
        
        if(name.equals("")){
            return new MessageResponse().BadRequest();
        }
        
        Optional data = service.ApiKeySet(api_key);
        Collection data2 = svc.getDataGoods();
        if(data.isPresent()){
            if(!data2.isEmpty()){
                model.setName(name);
                model.setDate(date);
                model.setYear(year);
                model.setAmount(amount);
                
                svc.insertGoods(model);
                return new MessageResponse().Succes();
            }
        }
        return new MessageResponse().NotFound();
    }
    
    @PostMapping("goods_data")
    public ResponseEntity getAllGoods(@RequestParam("api_key") String api_key){
        Collection data = svc.getDataGoods();
        Optional data2 = service.ApiKeySet(api_key);
        if(data2.isPresent()){
            if(!data.isEmpty()){
                HttpHeaders header = new HttpHeaders();
                header.add("Content-Type", "application/json; charset=utf-8");
                return new ResponseEntity(svc.getDataGoods(), header, HttpStatus.OK);
            }
        }
        return new MessageResponse().NotFound();
    }
}
