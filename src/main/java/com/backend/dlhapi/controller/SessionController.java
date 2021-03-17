package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.ApiKeyModel;
import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.ApiKeyService;
import com.backend.dlhapi.service.SessionService;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.util.calendar.Gregorian;

@RestController
@RequestMapping("api/")
public class SessionController {
    @Autowired
    private SessionService srv;
    
    @Autowired
    private ApiKeyService skey;
    
    private String body, responseError;
    private MongoOperations operation;
    
    private MongoTemplate mongotemplate;
    
     @PostMapping("admin")
    public ResponseEntity getAll(@RequestParam(value = "api_key") String api_key){
        Optional<SessionModel> data = srv.ApiKeySet(api_key);
        if(data.isPresent()){
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity(srv.getData(),header, HttpStatus.OK);
        }
        return new MessageResponse().NotFound();
    }
    
    @RequestMapping(path = "session_name", method= RequestMethod.POST)
    public ResponseEntity getSessionName(@RequestParam(value = "name") String name, @RequestParam(value = "api_key") String api_key, HttpServletResponse response){
        Optional<SessionModel> data = srv.getSessionName(name);
        Optional data2 = srv.ApiKeySet(api_key);
        if(data2.isPresent()){
            
            if(data.isPresent()){
                HttpHeaders header = new HttpHeaders();
                header.add("Content-Type", "application/json; charset=utf-8");
                return new ResponseEntity(srv.getSessionName(name),header,HttpStatus.OK);
            }
        }
        return new MessageResponse().NotFound();
    }
    
    
    @RequestMapping(path = "session_login", method = RequestMethod.POST)
    public ResponseEntity <Optional<SessionModel>> getDataLogin( @RequestParam(value = "name", required = true) String nama, @RequestParam(value = "password", required = true) String password, SessionModel sm){
        
        List<SessionModel> data = srv.getSession(password, nama);
        GregorianCalendar gregorian = new GregorianCalendar();
        Random random = new Random();
        int rand1 = random.nextInt(1000000000);
        String encode = String.valueOf(gregorian.get(Calendar.HOUR_OF_DAY)+""+gregorian.get(Calendar.MINUTE)+""+gregorian.get(Calendar.MILLISECOND)+""+rand1);
        String enc = Base64.getEncoder().encodeToString(encode.getBytes());
        
        if(!data.isEmpty()){
            
            System.out.println("ENCODE WORKS "+enc);
            return setApiKeyLogin(nama, enc, sm);
//            return getDataKey(key);
        }
          return new MessageResponse().NotFound();
    }
    
    
    public ResponseEntity getDataKey(String api_key){
        Optional<SessionModel> data = srv.ApiKeySet(api_key);
        
        if(data.isPresent()){
          return new MessageResponse().Succes();
        }else if(data.equals("")){
          return new MessageResponse().BadRequest();            
        }
          return new MessageResponse().NotFound();
    }
    
    @RequestMapping(path = "insert", method = RequestMethod.POST)
    public ResponseEntity insertAdmin(@RequestParam(value = "name") String nama, 
            @RequestParam(value = "password") String pass, 
            @RequestParam(value = "api_key") String api_key,
            @RequestParam(value = "jabatan") String jabatan,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "no_hp") String no_hp,
            @RequestParam(value = "alamat") String alamat, SessionModel sc){
        
        Optional data = srv.ApiKeySet(api_key);
        if(data.isPresent()){
            
            sc.setName(nama);
            sc.setPassword(pass);
            sc.setJabatan(jabatan);
            sc.setApikey("");
            sc.setEmail(email);
            sc.setNo_hp(no_hp);
            sc.setAlamat(alamat);

            srv.insert(sc);
            return new MessageResponse().Succes();
        }
        return new MessageResponse().BadRequest();
    }
    
//    test
    @PutMapping(value = "update")    
    public ResponseEntity updateAdmin(@RequestParam(value = "id") String id, 
                                      @RequestParam(value = "api_key") String api_key,
                                      @RequestParam(value = "name") String nama,  
                                      @RequestParam("jabatan") String jabatan,
                                      @RequestParam("email") String email,
                                      @RequestParam("no_hp") String nohp,
                                      @RequestParam("alamat") String alamat,SessionModel sc){
        
        Optional data = srv.getId(id);
        Optional data2 = srv.ApiKeySet(api_key);
        
        if(data.isPresent()){
            
            if(data2.isPresent()){
            
            SessionModel scc = (SessionModel) data.get();
            scc.setName(sc.getName());
            scc.setJabatan(jabatan);
            scc.setEmail(email);
            scc.setNo_hp(nohp);
            scc.setAlamat(alamat);
            srv.update(scc);
                
            return new MessageResponse().Succes();
            
            }else{
                return new MessageResponse().NotFound();
            }
            
        }else if(data == null){
            return new MessageResponse().NotFound();
        }
            return new MessageResponse().BadRequest();
    }
    
    public ResponseEntity setApiKeyLogin(String name, String api_key, SessionModel sc){
        Optional data = srv.getSessionName(name);
        if(data.isPresent()){
            SessionModel sm = (SessionModel) data.get();
            sm.setApikey(api_key);
            srv.update(sm);
            return new MessageResponse().Succes();
        }
        return new MessageResponse().BadRequest();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity <Optional<SessionModel>> deleteAdmin(@RequestParam("id") String id, @RequestParam("api_key") String api_key){
        Optional data = srv.ApiKeySet(api_key);
        if(data.isPresent()){
            srv.delete(id);
            return new MessageResponse().Succes();
        }
            return new MessageResponse().NotFound();
    }
}
