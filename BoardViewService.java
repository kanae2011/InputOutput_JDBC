/*
 * 게시판 리스트를 해결하기 위한 객체 
 * BoardDAO객체를 사용해서 DB에서 데이터를 수집해오기
 */
package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.Service;

public class BoardViewService implements Service {

	BoardDAO dao = new BoardDAO();
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		//글 보기 할 때 조회수 1 증가 시키기 -> 글보기와 글수정 할 때 데이터를 가져오는 객체로 사용하는데 글보기에서는 증가, 글 수정은 증가안함으로 해야하지만 자바 프로젝트에서는 둘 다 증가 시키는 것으로 개발 
		dao.increase((long)obj);
		//글보기 vo데이터를 가져와서 넘겨줌 
		return dao.view((Long)obj); 
	}

	
}
