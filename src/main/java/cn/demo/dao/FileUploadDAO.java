package cn.demo.dao;

import java.util.List;

import cn.demo.entity.FileBean;

public interface FileUploadDAO {

	void addFileBean(FileBean c);

	void updateFileBean(FileBean c);

	void deleteFileBean(Integer id);

	FileBean getFileBean(Integer id);

	List<FileBean> getForList();

//	List<FileBean> getForListWithCondition(FileBeanQueryReq req);

	Long getCount();

	Long getCountWithName(String name);
}
