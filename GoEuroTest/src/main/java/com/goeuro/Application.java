package com.goeuro;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.goeuro.model.CustomAplicationException;
import com.goeuro.model.Location;
import com.goeuro.service.LoactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wamalalawrence
 * Main application entry for my test project
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application 
{

	private static final String[] PROFILES = { "live", "test" };
	private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	final static Logger logger = LoggerFactory.getLogger(Application.class);

	private static ApplicationContext context;
	private static LoactionService loactionService;
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 * Application entry point, profile command line argument is expected
	 */
	public static void main(String[] args) throws Exception 
	{
		String activeProfile = System.getProperty(SPRING_PROFILES_ACTIVE).toLowerCase();
		validateMandatoryProperties(activeProfile);

		logger.info("Starting application!");
		context = SpringApplication.run(Application.class, args);
		loactionService = context.getBean(LoactionService.class);

		String cityName = "";
		try(Scanner scanIn = new Scanner(System.in))
		{
			while (true) 
			{
				System.out.println("Enter city: ");
				cityName = scanIn.next().trim();
				logger.info("city entered: " + cityName);
				System.out.println("processing..");

				List<Location> locations = null;
				try 
				{
					// goeuro api call
					locations = loactionService.getLocation(cityName);
					if (locations != null && !locations.isEmpty()) 
					{
						//save result to csv file
						loactionService.save(locations, cityName);
						System.out.println("data successfully saved!");
					} else 
					{
						System.out.println("no data available for city " + cityName);
					}
				} catch (CustomAplicationException exception) 
				{
					logger.error(exception.getMessage());
					System.out.println("oops.. this shouldn't be happening; you may try again! \nOtherwise details in log file");
				}
			}
		}

	}

	/**
	 * 
	 * @param activeProfile
	 * validate commandline profile
	 */
	private static void validateMandatoryProperties(String activeProfile) {
		if (activeProfile == null || !(ArrayUtils.contains(PROFILES, activeProfile))) {
			logger.error("Application exiting: System property '" + SPRING_PROFILES_ACTIVE + "' must be set to either 'test' or 'live'");
			System.exit(1);
		}
	}

}
