package cn.demo.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.demo.dao.FileUploadDAO;
import cn.demo.dao.impl.FileUploadJdbcDAOImpl;
import cn.demo.entity.FileBean;
import cn.demo.utils.FileUploadProperties;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	private static String FILE_PATH = "E:\\upload\\files\\";

	private static FileUploadDAO DAO = new FileUploadJdbcDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		FileUploadProperties properties = FileUploadProperties.getInstance();
		String exts = properties.getProperties("exts");
		String fileMaxSize = properties.getProperties("file.max.size");
		String totalFileMaxSize = properties.getProperties("total.file.max.size");


		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*500);
		factory.setRepository(new File("E:\\upload\\temp"));
		List<FileItem> items = null;
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		try {
			items = fileUpload.parseRequest(req);
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		Map<String,FileItem> pathFileItemMap = new HashMap<>();
		List<FileBean> beans = getBeans(items,pathFileItemMap);
		System.out.println(beans);
		upload(pathFileItemMap);
		saveBean(beans);
		String redirectUrl = "/upload/app/success.jsp";
		resp.sendRedirect(redirectUrl);
	}

	private void saveBean(List<FileBean> beans){
		for(FileBean bean:beans){
			DAO.addFileBean(bean);
		}
	}

	private void checkExtFileName(){

	}

	private void upload(Map<String,FileItem> pathFileItemMap) throws IOException {
		InputStream inputStream = null;
		for(Map.Entry<String,FileItem> entry:pathFileItemMap.entrySet()){
			String path = entry.getKey();
			FileItem pathFileItem = entry.getValue();
			inputStream = pathFileItem.getInputStream();
			writeFile(path,inputStream);
		}
		if (inputStream != null) {
			inputStream.close();
		}
	}

	private void writeFile(String path,InputStream inputStream) throws IOException {
		OutputStream outputStream = new FileOutputStream(path);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer);
		}
		outputStream.close();
	}

	private List<FileBean> getBeans(List<FileItem> items,Map<String,FileItem> pathFileItemMap) throws UnsupportedEncodingException {
		Map<String,FileItem> fieldMap = new HashMap<>();
		Map<String,FileItem> fileMap = new HashMap<>();
		List<FileBean> beans = new ArrayList<>();
		for(FileItem item:items){
			String name = item.getFieldName();
			String val = item.getString();
			if(item.isFormField()){//表单值
				fieldMap.put(name.substring(name.length()-1),item);
			}else {//文件
				fileMap.put(name.substring(name.length()-1),item);
			}
		}
		for(Map.Entry<String,FileItem> entry:fieldMap.entrySet()){
			String fieldMapKey = entry.getKey();
			FileItem fieldItem = entry.getValue();
			FileItem fileItem = fileMap.get(fieldMapKey);
			FileBean bean = new FileBean();
			bean.setFileDesc(fieldItem.getString("UTF-8"));
			bean.setFileName(fileItem.getName());
			String filePath = getFilePath(fileItem.getName());
			bean.setFilePath(filePath);
			pathFileItemMap.put(filePath,fileItem);
			beans.add(bean);
		}
		return beans;
	}

	private String getFilePath(String fileName){
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String filePath = FILE_PATH + System.currentTimeMillis() + "_" + fileName ;
		return filePath;
	}


}









