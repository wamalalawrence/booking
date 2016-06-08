package com.goeuro.test.inegration;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goeuro.Application;
import com.goeuro.model.CustomAplicationException;
import com.goeuro.model.Location;
import com.goeuro.service.LoactionService;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@SpringApplicationConfiguration(Application.class)
public class LocationServiceTest {
	
	private static final String CITY_NAME = "berlin";
	
	@Autowired
	LoactionService service; 
	
	@Value(value = "${csv.file.location}")
	private String csvFileLocation;
	
	@Test
	public void testGetLocation() throws CustomAplicationException{
		List<Location> locations = service.getLocation(CITY_NAME);
		assertNotNull(locations);
		assertTrue(locations.size() > 0);
	}
	
	@Test
	public void testSave() throws CustomAplicationException{
		List<Location> locations = service.getLocation(CITY_NAME);
		service.save(locations, CITY_NAME);
		assertTrue(new File(csvFileLocation+CITY_NAME+".csv").exists());
	}

}
