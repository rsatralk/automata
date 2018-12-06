package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import beforeafters.BeforeAfterTest;

public class Wikipedia extends BeforeAfterTest {
	
	@Test
	public void testTwo()
	{
		System.out.println(" In wikipedia ");
		assertTrue(true);
	}

}
