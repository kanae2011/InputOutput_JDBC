/*
 * 게시판 데이터를 저장하는 객체 
 * 글번호 , 제목, 내용, 아이디, 작성일, 파일명
 * 작성일 : 2021.01.20
 * 작성자 : 최가람 
 */

package com.webjjang.image.vo;

public class ImageVO {

	private long no;
	private String title;
	private String content;
	private String id;
	private String writeDate;
	private String filename;

	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	@Override
	public String toString() {
		return "ImageVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", writeDate="
				+ writeDate + ", filename=" + filename + "]";
	}
	
	
	
	
}
