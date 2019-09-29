package DeDuplicateJson.DeDuplicateJson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessJson {
	
	public Leads readJsonFromFile(ObjectMapper mapper, String filePath) throws JsonParseException, JsonMappingException, IOException {
		Leads leads = mapper.readValue(new File(filePath), Leads.class);	
		return leads;
	}
	
	public void writeToOutput(ObjectMapper mapper, String filePath, Leads result) throws JsonGenerationException, JsonMappingException, IOException {
		File resultFile = new File(filePath);
		resultFile.createNewFile(); // if file already exists will do nothing 
		mapper.writerWithDefaultPrettyPrinter().writeValue(resultFile, result);
	}

}
