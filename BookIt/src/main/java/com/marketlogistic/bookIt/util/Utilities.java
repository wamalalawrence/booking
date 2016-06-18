package com.marketlogistic.bookIt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.SortedSet;

import org.apache.commons.lang.ArrayUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marketlogistic.bookIt.model.Booking;
import com.marketlogistic.bookIt.model.Company;
import com.marketlogistic.bookIt.model.CustomAplicationException;
import com.marketlogistic.bookIt.model.Employee;

/**
 * 
 * @author wamalalawrence 
 * utility functions and constants live here,
 */
public class Utilities {

	/**
	 * some application constants
	 */
	public static final String[] PROFILES = { "live", "test" };
	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	public static final String OFFICE_HOUR_TIME_FORMAT = "HHmm";
	public static final String REQUEST_SUBMISSION_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MEETING_START_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String BOOKED_DATE_FORMAT = "yyyy-MM-dd";
	public static final String OFFICE_HOURS_DELIMITER = " ";
	public static final String BOOKING_DATA_DELIMITER = "#";
	public static final String DECIMAL_FROMAT_PARTTERN = "00";
	public static final String NEW_LINE = "\n";
	public static final int SYSTEM_EXIT_CODE_WITH_ERROR = 1;
	public static final int SYSTEM_EXIT_CODE_NORMAL = 0;

	final static Logger logger = LoggerFactory.getLogger(Utilities.class);
	
	/**
	 * 
	 * @param dataFile
	 * @return marketLogisticBookingData
	 * @throws CustomAplicationException
	 * This method reads data from file and populates it into the company object
	 */
	public static Company readInBookingData(File dataFile) throws CustomAplicationException {

		Company marketLogisticBookingData = null;
		String[] officeHours = null;
		DateTimeFormatter dateTimeFormatter = null;

		Utilities.print("Processing bookings from input file: " + dataFile.getAbsolutePath());
		try (Scanner scanIn = new Scanner(dataFile)) {

			marketLogisticBookingData = new Company();

			// read in first line (office hours) using a space delimiter
			officeHours = scanIn.nextLine().split(Utilities.OFFICE_HOURS_DELIMITER);

			// populate company object with office hours
			dateTimeFormatter = DateTimeFormat.forPattern(Utilities.OFFICE_HOUR_TIME_FORMAT);
			marketLogisticBookingData.setOfficeStartTime(dateTimeFormatter.parseDateTime(officeHours[0]));
			marketLogisticBookingData.setOfficeEndTime(dateTimeFormatter.parseDateTime(officeHours[1]));

			Booking booking = null;
			Employee employee = null;

			// read in booking data from file
			while (scanIn.hasNextLine()) {
				booking = new Booking();
				dateTimeFormatter = DateTimeFormat.forPattern(Utilities.REQUEST_SUBMISSION_DATE_TIME_FORMAT);
				String[] bookingData = scanIn.nextLine().split(Utilities.BOOKING_DATA_DELIMITER);
				booking.setSubmissionTime(dateTimeFormatter.parseDateTime(bookingData[0]));

				employee = new Employee(bookingData[1], "");
				marketLogisticBookingData.addEmployee(employee);
				booking.setEmployee(employee);

				dateTimeFormatter = DateTimeFormat.forPattern(Utilities.MEETING_START_DATE_TIME_FORMAT);
				booking.setStartTime(dateTimeFormatter.parseDateTime(bookingData[2]));

				booking.setDuration(Integer.valueOf(bookingData[3]));
				marketLogisticBookingData.addBooking(booking);
			}
		} catch (FileNotFoundException aplicationException) {
			Utilities.print("problem occured while processing data, details in log file!");
			throw new CustomAplicationException(aplicationException.getMessage());
		}
		return marketLogisticBookingData;
	}

	/**
	 * 
	 * @param bookingCalendar
	 * re-arranges out data as expected
	 */
	public static void printOutBookingCalendar(SortedSet<Booking> bookingCalendar) {

		StringBuffer bookingResults = new StringBuffer();
		DateTimeFormatter formatter = null;
		DecimalFormat timeFormatter = null;

		if (!bookingCalendar.isEmpty()) {
			formatter = DateTimeFormat.forPattern(Utilities.BOOKED_DATE_FORMAT);

			// format Hour and Minute output to 2 digits
			timeFormatter = new DecimalFormat(DECIMAL_FROMAT_PARTTERN);
			bookingResults.append("=============Booking calendar=============" + NEW_LINE);
			for (Booking booking : bookingCalendar) {
				bookingResults.append(formatter.print(booking.getStartTime()));
				bookingResults.append(" ");
				bookingResults.append(timeFormatter.format(booking.getStartTime().getHourOfDay()));
				bookingResults.append(":");
				bookingResults.append(timeFormatter.format(booking.getStartTime().getMinuteOfHour()));
				bookingResults.append(" ");
				bookingResults.append(
						timeFormatter.format(booking.getStartTime().getHourOfDay() + booking.getDuration()));
				bookingResults.append(":");
				bookingResults.append(timeFormatter.format(booking.getStartTime().getMinuteOfHour()));
				bookingResults.append(" ");
				bookingResults.append(booking.getEmployee().getId());
				bookingResults.append(NEW_LINE);
			}
		} else {
			bookingResults.append("no bookings!");
		}
		print(bookingResults.toString());

	}
	
	/**
	 * 
	 * @param data
	 * prints any string to console
	 */
	public static void print(String data){
		System.out.println(data);
	}
	
	/**
	 * 
	 * @param activeProfile
	 *            validate commandline profile
	 */
	public static void validateMandatoryProperties(String activeProfile) {
		if (activeProfile == null || !(ArrayUtils.contains(PROFILES, activeProfile))) {
			logger.error("Application exiting: System property '" + SPRING_PROFILES_ACTIVE
					+ "' must be set to either 'test' or 'live'");
			exit(Utilities.SYSTEM_EXIT_CODE_WITH_ERROR);
		}
	}
	
	public static void exit(int exitCode){
		if(exitCode == Utilities.SYSTEM_EXIT_CODE_WITH_ERROR){
			logger.error("System will exit due to errors");
		}
		System.exit(exitCode);
	}

	// TODO:- more utility functions
}
