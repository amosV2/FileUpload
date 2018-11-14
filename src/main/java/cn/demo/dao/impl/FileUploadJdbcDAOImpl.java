package cn.demo.dao.impl;

import java.util.List;

import cn.demo.dao.FileUploadDAO;
import cn.demo.dao.DAO;
import cn.demo.entity.FileBean;

public class FileUploadJdbcDAOImpl extends DAO<FileBean> implements FileUploadDAO {

	@Override
	public void addFileBean(FileBean f) {
		String sql = "insert into file(file_name,file_path,file_desc) values (?,?,?)";
		update(sql,f.getFileName(),f.getFilePath(),f.getFileDesc());
	}

	@Override
	public void updateFileBean(FileBean c) {

	}

	@Override
	public void deleteFileBean(Integer id) {

	}

	@Override
	public FileBean getFileBean(Integer id) {
		String sql = "select id, file_name as fileName,file_path as filePath,file_desc as fileDesc from file " +
				"where id=?";
		return getOne(sql,id);
	}

	@Override
	public List<FileBean> getForList() {
		String sql = "select id, file_name as fileName,file_path as filePath,file_desc as fileDesc from file";
		return getForList(sql);
	}

	@Override
	public Long getCount() {
		return null;
	}

	@Override
	public Long getCountWithName(String name) {
		return null;
	}
}
