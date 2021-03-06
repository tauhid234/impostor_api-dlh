package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.ApiKeyModel;
import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.ApiKeyService;
import com.backend.dlhapi.service.SessionService;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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

@RestController
@RequestMapping("api/")
public class SessionController {
    @Autowired
    private SessionService srv;
    
    @Autowired
    private ApiKeyService skey;
    
    private String body, responseError;
    private MongoOperations operation;
    
     @GetMapping("admin")
    public Collection <SessionModel> getAll(){
        return srv.getData();
//            if(data.isEmpty()){
//                return (Collection<session_service>) new Message().NotFound();
//            }
//            return (Collection<session_service>) new Message().Succes();
    }
    
    @RequestMapping(path = "session_name", method= RequestMethod.POST)
    public Optional<SessionModel> getSessionName(@RequestParam(value = "name") String name, HttpServletResponse response){
        Optional<SessionModel> data = srv.getSession(name);
        if(data.isPresent()){
            return data;
        }
        return new MessageResponse().Unknown(data,response);
    }
    
    
    @RequestMapping(path = "session_login", method = RequestMethod.POST)
    public ResponseEntity <Optional<SessionModel>> getDataLogin( @RequestParam(value = "name", required = true) String nama, @RequestParam(value = "api_key", required = true) String key, @RequestParam(value = "password", required = true) String password){
        
        Optional<SessionModel> data = srv.getSession(nama);
        if(data.isPresent()){
          return getPassword(password, key);
        }
          return new MessageResponse().NotFound();
    }
    
    public ResponseEntity getPassword(String pass, String key){
        Optional <SessionModel> data = srv.getPassword(pass);
        if(data.isPresent()){
            return getDataKey(key);
        }
        return new MessageResponse().NotFound();
    }
    
    public ResponseEntity getDataKey(String api_key){
        Optional<ApiKeyModel> data = skey.getKey(api_key);
        
        if(data.isPresent()){
          return new MessageResponse().Succes();
        }else if(data.equals("")){
          return new MessageResponse().BadRequest();            
        }
          return new MessageResponse().NotFound();
    }
    
    @PostMapping("/insert")
    public ResponseEntity insertAdmin(@RequestParam("name") String nama, @RequestParam("password") String pass, SessionModel sc){
        
        sc.setName(nama);
        sc.setPassword(pass);
        
        if(nama.equals("") || pass.equals("")){
            return new MessageResponse().BadRequest();
        }else if(nama == null || pass == null){
            return new MessageResponse().NotFound();
        }
        srv.insert(sc);
        return new MessageResponse().Succes();
    }
    
//    test
    @PutMapping(value = "/update")    
    public ResponseEntity updateAdmin(@RequestParam(value = "name") String nama,  @RequestParam("password") String pass, SessionModel sc){
        Optional data = srv.getSession(nama);
        if(data.isPresent()){
            SessionModel scc = (SessionModel) data.get();
            scc.setName(sc.getName());
            scc.setPassword(sc.getPassword());
            srv.update(scc);
            return new ResponseEntity(HttpStatus.OK);
        }else if(data == null){
            return new MessageResponse().NotFound();
        }
            return new MessageResponse().BadRequest();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity <Optional<SessionModel>> deleteAdmin(@RequestParam("name") String name){
        Optional data = srv.delete(name);
        if(data == null){
            return new MessageResponse().NotFound();
        }
            return new MessageResponse().Succes();
    }
}
