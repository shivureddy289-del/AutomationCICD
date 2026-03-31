package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {

		//read json to string
		String JsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\purchaseOrder.json"),
				"UTF-8");
		
		//String to HashMap
		
		ObjectMapper objmapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = objmapper.readValue(
	            JsonContent, 
	            new TypeReference<List<HashMap<String, String>>>() {}
	        );
		return data;
	}
}
