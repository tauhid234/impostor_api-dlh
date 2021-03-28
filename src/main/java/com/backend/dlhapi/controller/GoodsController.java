package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.EntryGoodsModel;
import com.backend.dlhapi.model.GoodsModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.EntryGoodsService;
import com.backend.dlhapi.service.GoodsService;
import com.backend.dlhapi.service.SessionService;
import java.util.Collection;
import java.util.List;
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
    
    @Autowired
    private EntryGoodsService entry_service;
    
    @RequestMapping(path = "insert_goods", method = RequestMethod.POST)
    public ResponseEntity insertOfGoods(@RequestParam("api_key") String api_key, @RequestParam("name") String name, @RequestParam("date") String date, 
                                        @RequestParam("year") String year, @RequestParam("amount") String amount, GoodsModel model){
        
        if(name.equals("")){
            return new MessageResponse().BadRequest();
        }
        
        Optional data = service.ApiKeySet(api_key);
        List<GoodsModel> data2 = svc.foundExistData(name);
        
        if(api_key.equals("")){
            return new MessageResponse().Empty();
        }else if(data.isPresent()){
            if(!data2.isEmpty()){
                return new ResponseEntity(HttpStatus.FOUND);
            }else{
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
    
    @PostMapping("count_goods")
    public ResponseEntity getDataCount(@RequestParam("api_key") String api_key, @RequestParam("year") String year){
        Optional dataApiKey = service.ApiKeySet(api_key);
        
        if(api_key.equals("")){
            return new MessageResponse().Empty();
        }else if(dataApiKey.isPresent()){
            List<GoodsModel> data = svc.CountData(year);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-type", "application/json; charset=utf-8");
            return new ResponseEntity(data,httpHeaders,HttpStatus.OK);
        }
        return new MessageResponse().NotFound();
    }
    
    @PostMapping("goods_data")
    public ResponseEntity getAllGoods(@RequestParam("api_key") String api_key){
        Collection data = svc.getDataGoods();
        Optional data2 = service.ApiKeySet(api_key);
        if(api_key.equals("")){
            return new MessageResponse().Empty();
        }else if(data2.isPresent()){
            if(!data.isEmpty()){
                HttpHeaders header = new HttpHeaders();
                header.add("Content-Type", "application/json; charset=utf-8");
                return new ResponseEntity(svc.getDataGoods(), header, HttpStatus.OK);
            }
        }
        return new MessageResponse().NotFound();
    }
    
    @PostMapping("inventory_in")
    public ResponseEntity inventory_in(@RequestParam("api_key") String api_key, @RequestParam("name") String name, 
                                       @RequestParam("amount_entry") String amount_entry, @RequestParam("information") String information, 
                                       @RequestParam("date") String date, GoodsModel gm, EntryGoodsModel em){
        
        
        int amount = 0;
        int amount2 = 2;
        int total = 0;
        
        List<GoodsModel> data = svc.foundExistData(name);
        Optional apiKey = service.ApiKeySet(api_key);
        
        if(apiKey.isPresent()){
            if(!data.isEmpty()){
                for(int i = 0; i < data.size(); i++){
                    amount = Integer.parseInt(data.get(i).getAmount());
                    total = amount + amount2;


                    GoodsModel update = data.get(i);
                    update.setAmount(String.valueOf(total));
                    svc.updateGoods(update);
                    
                    em.setName(name);
                    em.setAmount_entry(amount_entry);
                    em.setInformation(information);
                    em.setDate(date);
                    
                    entry_service.saveInventoryIn(em);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
        }        
        return new MessageResponse().NotFound();
    }
}
