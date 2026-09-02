package com.rideApp.RideBookingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rideApp.RideBookingApp.dao.VahicleDao;
import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.Vahicle;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.service.VahicleService;

@RestController
@RequestMapping("/vahicle")
public class VahicleController {

	@Autowired
    private VahicleService vahicleService;

    @PostMapping("/saveVahicle/DriverId/{id}")
    public ResponseEntity<ResponceStructure<Vahicle>> saveVahicle(@PathVariable int id, @RequestBody Vahicle vahicle) {
        Vahicle savedVahicle = vahicleService.saveVahicle(vahicle,id);
        ResponceStructure<Vahicle> response = new ResponceStructure<>();
        response.setData(savedVahicle);
        response.setMessage("Vahicle Saved Successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponceStructure<List<Vahicle>>> getAllVahicles() {
    	List<Vahicle> vahicles = vahicleService.getAllVahicles();
        ResponceStructure<List<Vahicle>> response = new ResponceStructure<>();
        response.setData(vahicles);
        response.setMessage("Vahicles Fetched Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponceStructure<Vahicle>> getVahicleById(@PathVariable int id) {
        Vahicle vahicle = vahicleService.getVahicleById(id);
        ResponceStructure<Vahicle> response = new ResponceStructure<>();
        response.setData(vahicle);
        response.setMessage("Vahicle Found Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponceStructure<Integer>> deleteVahicleById(@PathVariable int id) {
        int deletedId = vahicleService.deleteVahicleById(id);
        ResponceStructure<Integer> response = new ResponceStructure<>();
        response.setData(deletedId);
        response.setMessage("Vahicle Deleted Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getVahicleByType")
    public ResponseEntity<ResponceStructure<List<Vahicle>>> getVahicleByType(@RequestParam VahicleType vahicleType){
    	ResponceStructure<List<Vahicle>> responce = new ResponceStructure<>();
    	responce.setMessage(vahicleType + " Vahicles");
    	responce.setStatusCode(HttpStatus.OK.value());
    	responce.setData(vahicleService.getVahicleByType(vahicleType));
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @GetMapping("/getVahicleByNo/{no}")
    public ResponseEntity<ResponceStructure<Vahicle>> getVahicleByNo(@PathVariable String no){
    	ResponceStructure<Vahicle> responce = new ResponceStructure<>();
    	responce.setData(vahicleService.getVahicleByNo(no));
    	responce.setStatusCode(HttpStatus.FOUND.value());
    	responce.setMessage("Vahicle Found");
    	return ResponseEntity.status(HttpStatus.FOUND).body(responce);
    }
    
    @PutMapping("/updateNo/{id}")
    public ResponseEntity<ResponceStructure<Integer>> updateVahicleNo(@PathVariable int id,@RequestParam String no) {
        int updatedId = vahicleService.updateVahicleNo(id, no);
        ResponceStructure<Integer> response = new ResponceStructure<>();
        response.setData(updatedId);
        response.setMessage("Vahicle Number Updated Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/updateType/{id}")
    public ResponseEntity<ResponceStructure<Integer>> updateVahicleType(@PathVariable int id,@RequestParam VahicleType type) {
        int updatedId = vahicleService.updateVahicleType(id, type);
        ResponceStructure<Integer> response = new ResponceStructure<>();
        response.setData(updatedId);
        response.setMessage("Vahicle Type Updated Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/updateDriver/{id}")
    public ResponseEntity<ResponceStructure<Integer>> updateDriver( @PathVariable int id,@RequestParam int driverId) {
        int updatedId = vahicleService.updateDriver(id, driverId);
        ResponceStructure<Integer> response = new ResponceStructure<>();
        response.setData(updatedId);
        response.setMessage("Driver Updated Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/deleteVahicleByDriver/{id}")
    public ResponseEntity<ResponceStructure<Integer>> deleteVahicleByDriverId( @PathVariable int id) {
        int updatedId = vahicleService.deleteVahicleByDriverId(id);
        ResponceStructure<Integer> response = new ResponceStructure<>();
        response.setData(updatedId);
        response.setMessage("Vahicle Deleted Successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
    
}