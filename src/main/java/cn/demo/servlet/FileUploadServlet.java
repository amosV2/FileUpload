package cn.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.demo.utils.FileUploadProperties;

public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileUploadProperties properties = FileUploadProperties.getInstance();
		String exts = properties.getProperties("exts");
		String fileMaxSize = properties.getProperties("file.max.size");
		String totalFileMaxSize = properties.getProperties("total.file.max.size");
		System.out.println(exts);
		System.out.println(fileMaxSize);
		System.out.println(totalFileMaxSize);

	}
}
