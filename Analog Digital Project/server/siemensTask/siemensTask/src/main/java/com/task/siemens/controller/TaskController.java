package com.task.siemens.controller;

import com.task.siemens.model.AnalogData;
import com.task.siemens.model.DigitalData;
import com.task.siemens.service.AnalogDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class TaskController {

    @Autowired
    AnalogDigitalService adService;

    @PutMapping("/analog/{id}")
    public ResponseEntity<?> updateAnalog(@PathVariable int id,@RequestBody AnalogData d) {
        return adService.updateAnalog(id,d);
    }

    @GetMapping("/analog/{id}")
    public float getAnalog(@PathVariable int id){
        return adService.getAnalog(id);
    }

    @PutMapping("/digital/{id}")
    public ResponseEntity<String> updateDigital(@PathVariable int id,@RequestBody DigitalData d) {
        return adService.updateDigital(id,d);
    }

    @GetMapping("/digital/{id}")
    public boolean getDigital(@PathVariable int id){
        return adService.getDigital(id);
    }
}