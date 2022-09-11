package org.nachc.smartonfhirexamples.properties;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

public class AppProperties {
	
	private static final String PROPS_FILE_NAME = "/app-params.properties";
	
	private static final Properties PROPS;
	
	static {
		File file = FileUtil.getFile(PROPS_FILE_NAME);
		PROPS = PropertiesUtil.getAsProperties(file);
	}
	
	public static Properties getProperties() {
		return PROPS;
	}
	
	public static String get(String name) {
		return PROPS.getProperty(name);
	}
	
	public static String getFhirServerUrl() {
		return get("fhirServerUrl");
	}
	
}
