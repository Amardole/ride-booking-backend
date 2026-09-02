package com.rideApp.RideBookingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideApp.RideBookingApp.dao.VahicleDao;
import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.entity.Vahicle;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.exception.DriverNotFoundException;

@Service
public class VahicleService {

	@Autowired
    private VahicleDao vahicleDao;
	
	@Autowired
	private DriverService ds;


    public Vahicle saveVahicle(Vahicle vahicle, int id) {
    	Driver d = ds.getDriverById(id);
    	vahicle.setDriver(d);
        return vahicleDao.saveVahicle(vahicle);
    }

    public List<Vahicle> getAllVahicles() {
        return vahicleDao.getAllVahicles();
    }

    public Vahicle getVahicleById(int id) {
        return vahicleDao.getVahicleById(id);
    }

    public int deleteVahicleById(int id) {
        return vahicleDao.deleteVahicleById(id);
    }
    
    public List<Vahicle> getVahicleByType(VahicleType vahicleType){
    	return vahicleDao.getVahiclesByType(vahicleType);
    }
    
    public Vahicle getVahicleByNo(String no) {
    	return vahicleDao.getVahicleByNo(no);
    }
    public int updateVahicleNo(int id, String no) {
        return vahicleDao.updateVahicleNo(id, no);
    }
    public int updateVahicleType(int id, VahicleType type) {
        return vahicleDao.updateVahicleType(id, type);
    }
    
    public int updateDriver(int id, int driverId) {
        ds.getDriverById(driverId);
        return vahicleDao.updateDriver(id, driverId);
    }
    
    public int deleteVahicleByDriverId(int id) {
    	Driver d = ds.getDriverById(id);
    	if(d.getId() != 0) {
    		return vahicleDao.deleteVahicleByDriverId(id);
    	}
    	else throw new DriverNotFoundException("No Driver Present with id "+id);
    }
}
