package com.elearning.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {

	@Id
    @GeneratedValue
    private Long id;
	private String fileName;
    private String contentType;
    @Lob
    private byte[] data;
    
    
    
    
	public Image(String fileName, String contentType, byte[] data) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.data = data;
	}
	public Image(Long id, String fileName, String contentType, byte[] data) {
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
		this.data = data;
	}
	public Image() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", fileName=" + fileName + ", contentType=" + contentType + ", data="
				+ Arrays.toString(data) + "]";
	}
    
	
    
}
