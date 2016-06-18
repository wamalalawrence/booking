package com.marketlogistic.bookIt.service;

import java.util.SortedSet;

import com.marketlogistic.bookIt.model.Booking;
import com.marketlogistic.bookIt.model.Company;
import com.marketlogistic.bookIt.model.CustomAplicationException;

/**
 * 
 * @author wamalalawrence
 * Anyone else interested in using our batch processing functionality could 
 * use this interface
 *
 */
//TODO - we could also expose this functionality as a rest endpoint if required
public interface IBatchProcessor {

	public SortedSet<Booking> process(Company companyBookingData) throws CustomAplicationException;
}
