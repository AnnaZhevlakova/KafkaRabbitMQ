package com.example.CreditService.controller;

import com.example.CreditService.model.CreditRequest;
import com.example.CreditService.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/credit"))
public class CreditController {
   private final CreditService service;

   public CreditController(CreditService service){
       this.service = service;
   }

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody CreditRequest request) {
       var id = service.submit(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatus(@PathVariable long id) {
        var status = service.getStatus(id);
        if(status.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(status);
    }
}
