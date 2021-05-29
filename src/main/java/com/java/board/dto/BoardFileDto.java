package com.java.board.dto;

public class BoardFileDto {
	
	private int fileNo;
	private String fileName;
	private String filePath;
	private String fileExtension;
	
	public BoardFileDto() {
		super();
	}
	
	public BoardFileDto(int fileNo, String fileName, String filePath, String fileExtension) {
		super();
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileExtension = fileExtension;
	}
	
	@Override
	public String toString() {
		return "MapDto [fileNo=" + fileNo + ", fileName=" + fileName + ", filePath=" + filePath + ", fileExtension="
				+ fileExtension + "]";
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
}