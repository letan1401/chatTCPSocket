package core;

import java.io.Serializable;

public class MessInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getUserDes() {
		return userDes;
	}

	public void setUserDes(String userDes) {
		this.userDes = userDes;
	}

	public String getMessContent() {
		return MessContent;
	}

	public void setMessContent(String messContent) {
		MessContent = messContent;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	private String userSource;
	private String userDes;
	private String MessContent;
	private FileInfo fileInfo;
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String time;

	public MessInfo(String userSource, String userDes, String MessContent,String time, FileInfo fileInfo) {
		this.userDes = userDes;
		this.MessContent = MessContent;
		this.userSource = userSource;
		this.time = time;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
}
