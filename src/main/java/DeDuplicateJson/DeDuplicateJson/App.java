package DeDuplicateJson.DeDuplicateJson;

public class App 
{
	
    public static void main( String[] args )
    {
    	DuplicatesRemover duplicatesRemover = new DuplicatesRemover();
		// change the path and test
		String inputFilePath = "input\\leads.json";
		String outputFilePath = "output\\result.json";
    	duplicatesRemover.removeDuplicates(inputFilePath, outputFilePath);
    }
}
