package DeDuplicateJson.DeDuplicateJson;

import org.junit.Test;

import junit.framework.TestCase;

public class DuplicatesRemoverTest extends TestCase {

	DuplicatesRemover testObj = new DuplicatesRemover(); 
	
	@Test
	public void testRemoveDuplicates() {
		DuplicatesRemover testObj = new DuplicatesRemover(); 
		String inputPath = "input\\leads.json";
		String outputPath = "output\\result.json";
		testObj.removeDuplicates(inputPath, outputPath);
		assertEquals(5, testObj.result.size());
	}
	
	@Test
	public void testRemoveDuplicatesWithNullAddressAndName() {
		String inputPath = "input\\leads3.json";
		String outputPath = "output\\result3.json";
		testObj.removeDuplicates(inputPath, outputPath);
		assertEquals(2, testObj.result.size());
	}
	
	@Test
	public void testRemoveDuplicatesWithNullIdOrEmail() {
		String inputPath = "input\\leadsNull.json";
		String outputPath = "output\\resultNull.json";
		testObj.removeDuplicates(inputPath, outputPath);
		assertEquals(0, testObj.result.size());
	}

}
