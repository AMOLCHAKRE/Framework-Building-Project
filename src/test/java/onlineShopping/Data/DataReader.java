package onlineShopping.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getJasonDataToMap (String filepath) throws IOException
	{
		//Read jason to String
		
		String jasondata = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// Convert String to HashMap - Jakson databind - dependency
		
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap <String, String>> data = mapper.readValue(jasondata, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
		
		//it will provide list of hashmap 
	}

}
