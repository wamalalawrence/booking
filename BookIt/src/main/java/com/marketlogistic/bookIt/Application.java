package com.marketlogistic.bookIt;

import java.io.File;
import java.util.SortedSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.marketlogistic.bookIt.model.Booking;
import com.marketlogistic.bookIt.model.Company;
import com.marketlogistic.bookIt.model.CustomAplicationException;
import com.marketlogistic.bookIt.service.impl.BatchProcessor;
import com.marketlogistic.bookIt.util.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wamalalawrence 2016-06-17 Main application entry for booking system
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	final static Logger logger = LoggerFactory.getLogger(Application.class);
	private static ApplicationContext context;
	private static BatchProcessor batchProcessorService;

	/**
	 * 
	 * @param args
	 * @throws Exception
	 *             command line argument "profile" is expected, can either be
	 *             "live" or "test"
	 */
	public static void main(String[] args) throws CustomAplicationException {

		// check for proper application start up parameters
		String activeProfile = System.getProperty(Utilities.SPRING_PROFILES_ACTIVE).toLowerCase();
		Utilities.validateMandatoryProperties(activeProfile);

		// start application and do some instantiations
		logger.info("Starting application!");
		context = SpringApplication.run(Application.class, args);
		batchProcessorService = context.getBean(BatchProcessor.class);

		// read in bookings data from file
		File dataFile = new File(batchProcessorService.getDateInputFile());
		Company companyMeetingBookingData = Utilities.readInBookingData(dataFile);

		// process bookings
		SortedSet<Booking> bookingCalendar = batchProcessorService.process(companyMeetingBookingData);

		// print booking calendar to console
		Utilities.printOutBookingCalendar(bookingCalendar);
		
		// shutdown application
		Utilities.exit(Utilities.SYSTEM_EXIT_CODE_NORMAL);

	}

}
