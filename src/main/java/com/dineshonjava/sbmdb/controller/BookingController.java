/**
 * 
 */
package com.dineshonjava.sbmdb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dineshonjava.sbmdb.models.Booking;
import com.dineshonjava.sbmdb.models.BookingRepository;

/**
 * @author Dinesh.Rajput
 *
 */
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, Object> create(@Valid @RequestBody Booking booking) {
		System.out.println("booking:"+booking.getPsngrName());
		booking.setTravelDate(new Date());
		booking.set_id(ObjectId.get());
		booking = bookingRepository.save(booking);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking created successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	
	@RequestMapping(value ="/read",method = RequestMethod.GET)
	public Map<String, Object> read(@RequestParam String bookingId) {
		Booking booking = bookingRepository.findOne(bookingId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking found successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Map<String, Object> update(@RequestParam String bookingId, @Valid @RequestBody Booking vbooking) {
		Booking booking = bookingRepository.findOne(bookingId);
		booking.setPsngrName(vbooking.getPsngrName());
		booking = bookingRepository.save(booking);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking updated successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam String bookingId) {
		bookingRepository.delete(bookingId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking deleted successfully");
		dataMap.put("status", "1");
	    return dataMap;
	}
	
	
	@RequestMapping(value ="/read-all",method = RequestMethod.GET)
	public Map<String, Object> readAll() {
		List<Booking> bookings = bookingRepository.findAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Bookings found successfully");
		dataMap.put("totalBooking", bookings.size());
		dataMap.put("status", "1");
		dataMap.put("bookings", bookings);
	    return dataMap;
	}
}
