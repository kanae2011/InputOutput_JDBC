/*
 * 게시판 데이터를 저장하는 객체 
 * 글번호 , 제목, 내용, 작성자, 작성일, 조회수
 * 작성일 : 2021.01.18
 * 작성자 : 최가람 
 */

package com.webjjang.board.vo;

public class BoardVO {

	private long no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private long hit;
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + ", hit=" + hit + "]";
	}
	
	
}
