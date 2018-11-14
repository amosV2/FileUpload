package cn.demo.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.demo.dao.FileUploadDAO;
import cn.demo.dao.impl.FileUploadJdbcDAOImpl;
import cn.demo.entity.FileBean;

public class FileDownloadServlet extends HttpServlet {

	private static FileUploadDAO DAO = new FileUploadJdbcDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileId = req.getParameter("id");
		FileBean fileBean = DAO.getFileBean(Integer.parseInt(fileId));
		String filePath = fileBean.getFilePath();
		String fileName = fileBean.getFileName();
		resp.setContentType("application/x-msdownload");
		resp.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
		OutputStream outputStream = resp.getOutputStream();
		InputStream inputStream = new FileInputStream(filePath);
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len=inputStream.read(buf))!=-1){
			outputStream.write(buf,0,len);
		}
		inputStream.close();
	}





}










