package test.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataReader {
/*
 * This function was moved to BaseTest
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File("/Users/ibrahim/Ibby/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/test/data/PurchaseOrder.json"),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> xyz = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return xyz;
	}*/
}
