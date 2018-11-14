package cn.demo.entity;

public class FileBean {

	private Integer id;
	// 文件名
	private String fileName;
	// 文件的路径
	private String filePath;
	// 文件的描述
	private String fileDesc;

	public FileBean() {}

	public FileBean(Integer id, String fileName, String filePath, String fileDesc) {
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDesc = fileDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	@Override
	public String toString() {
		return "FileBean{" +
				"id=" + id +
				", fileName='" + fileName + '\'' +
				", filePath='" + filePath + '\'' +
				", fileDesc='" + fileDesc + '\'' +
				'}';
	}
}
