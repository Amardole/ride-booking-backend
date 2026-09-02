package com.rideApp.RideBookingApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rideApp.RideBookingApp.entity.Vahicle;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.exception.VahicleNotFoundException;
import com.rideApp.RideBookingApp.repo.VahicleRepo;

@Repository
public class VahicleDao {

	@Autowired
    private VahicleRepo vahicleRepo;

    public Vahicle saveVahicle(Vahicle vahicle) {
        return vahicleRepo.save(vahicle);
    }

    public List<Vahicle> getAllVahicles() {
        return vahicleRepo.findAll();
    }

    public Vahicle getVahicleById(int id) {
        return vahicleRepo.findById(id).orElseThrow(() ->new VahicleNotFoundException("Vahicle Not Found With Id " + id));
    }
    
    @Transactional
    public int deleteVahicleById(int id) {
        Vahicle vahicle = vahicleRepo.findById(id).orElseThrow(() ->new VahicleNotFoundException("Vahicle Not Found With Id " + id ) );
        vahicleRepo.delete(vahicle);
        return id;
    }
    
    public List<Vahicle> getVahiclesByType(VahicleType vahicleType){
    	return vahicleRepo.getVahicleByType(vahicleType).orElseThrow(()-> new VahicleNotFoundException("Vahicle not Found With Type of "+vahicleType));
    }
    
    public Vahicle getVahicleByNo(String no) {
    	return vahicleRepo.getVahicleByNo(no).orElseThrow(()-> new VahicleNotFoundException("Vahicle Not Present With Number "+no));
    }
    
    @Transactional
    public int updateVahicleNo(int id, String no) {
        int result = vahicleRepo.updateVahicleNo(id, no);
        if (result == 0) {
            throw new VahicleNotFoundException("Vahicle Not Found With Id " + id);}
        return id;
    }
    
    @Transactional
    public int updateVahicleType(int id, VahicleType type) {
        int result = vahicleRepo.updateVahicleType(id, type);
        if (result == 0) {
            throw new VahicleNotFoundException("Vahicle Not Found With Id " + id);}
        return id;
    }
    
    @Transactional
    public int updateDriver(int id, int driverId) {
        int result = vahicleRepo.updateDriver(id, driverId);
        if (result == 0) {
            throw new VahicleNotFoundException( "Vahicle Not Found With Id " + id);}
        return id;
    }
    
    @Transactional
    public int deleteVahicleByDriverId(int id) {
    	int result = vahicleRepo.deleteVahicleByDriverId(id);
    	if(result == 0) throw new VahicleNotFoundException("No Vahicle With Driver Id "+id);
    	return result;
    }
    
}