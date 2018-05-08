package com.lm.ta.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lm.ta.config.WebInit;
/**
 * @author dmonaco
 *
 */
public class TestCase_003 extends WebInit{

   private static final Logger logger = LoggerFactory.getLogger(TestCase_003.class);
   
   @Before
   public void enterBefore(){
	   logger.info("In @Before TC_003");
   }
   
   @Test
   public void sayHello() {
	   logger.info("Messaggio: Hello Gradle TC_003");
   }
   
   @After
   public void enterAfter(){
	   logger.info("In @After TC_003");
   }
}