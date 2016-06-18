package com.marketlogistic.bookIt.service.impl;

import java.util.Iterator;
import java.util.SortedSet;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marketlogistic.bookIt.model.Booking;
import com.marketlogistic.bookIt.model.Company;
import com.marketlogistic.bookIt.model.CustomAplicationException;
import com.marketlogistic.bookIt.service.IBatchProcessor;

/**
 * 
 * @author wamalalawrence
 * 2016-06-17
 * This is the batch processing service managed by spring as a singleton!
 */

@Service
public class BatchProcessor implements IBatchProcessor{
	
	final static Logger logger = LoggerFactory.getLogger(BatchProcessor.class);
	
	/**
	 * application property exposed by spring from properties file
	 */
	@Value(value = "${data.input.file.location}")
	private String dateInputFile;

	@Override
	public SortedSet<Booking> process(Company companyBookingData) throws CustomAplicationException {
		Company validatedBookingData =  validateBookingsforOfficeHours(companyBookingData);
		return validatedBookingData.getBookings();
	}
	
	/**
	 * 
	 * @param companyBookingData
	 * @return companyBookingData
	 * 	 Refines bookings, deletes bookings that don't fall within company working hours.
	 *   Removes bookings from collection using iterator to avoid the usual ConcurrentModificationException
	 */
	private Company validateBookingsforOfficeHours(Company companyBookingData){
		Iterator<Booking> iterator = companyBookingData.getBookings().iterator();
		while(iterator.hasNext()){
			Booking booking = iterator.next();
			if(!isBookingWithInOfficeHours(companyBookingData.getOfficeStartTime(), companyBookingData.getOfficeEndTime(), booking)){
				iterator.remove();
				logger.warn("The following booking was removed from batch because it does not fall within company working hours: [" + booking + "]");
			}
		}
		return companyBookingData;
	}

	/**
	 * helper function to Validate that No part of the requested booking falls outside office hours
	 * @param company
	 * @param booking
	 * @return boolean
	 */
	private boolean isBookingWithInOfficeHours(DateTime officeStartDateTime, DateTime officeEndDateTime, Booking booking) 
	{
		if(booking.getStartTime() == null || booking.getDuration() == null){
			return false;
		}
		
		// compute get time ranges - before and after
		int officeStartTime = officeStartDateTime.getHourOfDay() + officeStartDateTime.getMinuteOfHour();
		int officeEndTime = officeEndDateTime.getHourOfDay() + officeEndDateTime.getMinuteOfHour();
		
		int bookingStartTime = booking.getStartTime().getHourOfDay() + booking.getStartTime().getMinuteOfHour();
		
		int bookingEndTime = booking.getStartTime().getHourOfDay() + booking.getDuration();
//		System.out.println("## "+ bookingEndTime);
		
		boolean isBefore = (bookingStartTime >= officeStartTime) ? true : false;
		boolean isAfter = (bookingEndTime <= officeEndTime) ? true : false;
		
		return isBefore && isAfter;
	}
	
	/**
	 * Method to expose input file location 
	 * @return dateInputFile location
	 */
	public String getDateInputFile() {
		return dateInputFile;
	}

}
