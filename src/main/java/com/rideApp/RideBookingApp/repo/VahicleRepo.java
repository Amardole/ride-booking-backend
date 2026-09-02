package com.rideApp.RideBookingApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rideApp.RideBookingApp.entity.Vahicle;
import com.rideApp.RideBookingApp.enums.DriverStatus;
import com.rideApp.RideBookingApp.enums.VahicleType;

public interface VahicleRepo extends JpaRepository<Vahicle, Integer> {

	@Query("SELECT v FROM Vahicle v WHERE v.vahicletype = :vahicletype")
	Optional<List<Vahicle>> getVahicleByType(@Param("vahicletype") VahicleType vahicletype);
	
	@Query("SELECT v FROM Vahicle v WHERE v.vahicle_no = :vahicle_no")
	Optional<Vahicle> getVahicleByNo(@Param("vahicle_no") String vahicle_no);
	
	@Modifying
	@Query("UPDATE Vahicle v SET v.vahicle_no = :no WHERE v.id = :id")
	int updateVahicleNo(@Param("id") int id, @Param("no") String no);


	@Modifying
	@Query("UPDATE Vahicle v SET v.vahicletype = :type WHERE v.id = :id")
	int updateVahicleType(@Param("id") int id, @Param("type") VahicleType type);


	@Modifying
	@Query("UPDATE Vahicle v SET v.driver.id = :driverId WHERE v.id = :id")
	int updateDriver(@Param("id") int id, @Param("driverId") int driverId);
	
	@Modifying
	@Query("DELETE FROM Vahicle v WHERE v.driver.id = :driverId")
	int deleteVahicleByDriverId(@Param("driverId") int driverId);
	
	
	@Modifying
	@Query(value = """
	    UPDATE vahicle
	    SET driver_id = NULL
	    WHERE driver_id IN (
	        SELECT id FROM driver
	        WHERE driver_status = :status
	    )
	    """, nativeQuery = true)
	int removeDriverFromVahicles(@Param("status") String status);
}
