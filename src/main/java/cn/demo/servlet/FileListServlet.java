package cn.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.demo.dao.FileUploadDAO;
import cn.demo.dao.impl.FileUploadJdbcDAOImpl;
import cn.demo.entity.FileBean;

public class FileListServlet extends HttpServlet {

	private static FileUploadDAO DAO = new FileUploadJdbcDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<FileBean> files = DAO.getForList();
		req.setAttribute("files",files);
		String forwardUrl = "/app/download.jsp";
		req.getRequestDispatcher(forwardUrl).forward(req,resp);
	}
}
