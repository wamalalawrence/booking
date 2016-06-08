package com.lulimi.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lulimi.LulimiApplication;
import com.lulimi.model.PhrasesDictionary;
import com.lulimi.service.TranslationService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LulimiApplication.class)
@WebIntegrationTest
public class TranslationServiceTest {

	private final String COLLECTION_NAME = "englishluganda"; 
	private final String KEY = "I want"; 
	private final String TEST_KEY = "testing"; 
	private PhrasesDictionary dictionary;
	
	@Autowired
	private TranslationService service;
	
	@After
	public void tearDown(){
		// because there is no transactions support in mongodb, i need to find away to roll-back test data
		if(dictionary != null){
			service.delete(COLLECTION_NAME, dictionary);
		}
	}
	
	@Test
	public void testFind(){
		PhrasesDictionary dictionary = service.find(COLLECTION_NAME, KEY);
		assertNotNull(dictionary);
		assertSame(1, dictionary.getValue().size());
	}
	
	@Test
	public void testFindWithFullTextSearch(){
		List<PhrasesDictionary> dictionaries = service.findWithFullTextSearch(COLLECTION_NAME, KEY);
		assertNotNull(dictionaries);
		assertSame(5, dictionaries.size());
	}
	
	@Test
	public void testSaveByCollectionName(){
		dictionary = new PhrasesDictionary();
		dictionary.setKey(TEST_KEY);
		List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("2");
		dictionary.setValue(values);
		service.saveByCollectionName(COLLECTION_NAME, dictionary);
		
		PhrasesDictionary dictionary = service.find(COLLECTION_NAME, TEST_KEY);
		
		assertNotNull(dictionary);
		assertSame(2, dictionary.getValue().size());
	}
	
	
}
