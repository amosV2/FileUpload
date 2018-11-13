package cn.demo.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.demo.utils.FileUploadProperties;

public class FileUploadServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		InputStream in = getClass().getClassLoader().getResourceAsStream("/upload.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
			for(Map.Entry<Object,Object> entry:properties.entrySet()){
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				FileUploadProperties.getInstance().setProperties(key,value);
			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}
