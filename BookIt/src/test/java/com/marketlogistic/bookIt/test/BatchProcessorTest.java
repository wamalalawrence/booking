package com.marketlogistic.bookIt.test;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DecimalFormat;
import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marketlogistic.bookIt.Application;
import com.marketlogistic.bookIt.model.Booking;
import com.marketlogistic.bookIt.model.Company;
import com.marketlogistic.bookIt.model.CustomAplicationException;
import com.marketlogistic.bookIt.service.impl.BatchProcessor;
import com.marketlogistic.bookIt.util.Utilities;

/**
 * 
 * @author wamalalawrence
 * Test class for BatchProcessor.class
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@SpringApplicationConfiguration(Application.class)
public class BatchProcessorTest {

	private static final String COMPANY_START_HOUR = "09";
	private static final String EMPLOYEE_ID = "EMP002";
	@Autowired
	BatchProcessor batchProcessorService;

	@Value(value = "${test.data}")
	private String testFileLocation;

	/**
	 * Test the method 'process' of BatchProcessor
	 * @throws CustomAplicationException
	 */
	@Test
	public void testProcess() throws CustomAplicationException {

		File dataFile = new File(testFileLocation);
		assertNotNull(dataFile);

		Company companyMeetingBookingData = Utilities.readInBookingData(dataFile);
		assertNotNull(dataFile);

		DecimalFormat timeFormatter = new DecimalFormat(Utilities.DECIMAL_FROMAT_PARTTERN);
		assertEquals(COMPANY_START_HOUR,
				timeFormatter.format(companyMeetingBookingData.getOfficeStartTime().getHourOfDay()));
		// TODO :- add more asserts to verify input

		SortedSet<Booking> bookingCalendar = batchProcessorService.process(companyMeetingBookingData);
		assertNotNull(bookingCalendar);

		// this verifies that sorting works correctly
		assertEquals(EMPLOYEE_ID, bookingCalendar.first().getEmployee().getId());

		// TODO - more asserts
	}

}
