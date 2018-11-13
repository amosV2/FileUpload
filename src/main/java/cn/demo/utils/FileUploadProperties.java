package cn.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class FileUploadProperties {

	private Map<String,String> properties = new HashMap<>();

	private FileUploadProperties() {}

	private static FileUploadProperties instance = new FileUploadProperties();

	public static FileUploadProperties getInstance(){
		return instance;
	}

	public void setProperties(String propertiesName,String propertiesVal){
		properties.put(propertiesName, propertiesVal);
	}

	public String getProperties(String propertiesName){
		return properties.get(propertiesName);
	}

}
