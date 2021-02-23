/*
 * 게시판 글삭제를 해결하기 위한 객체 
 * 전달받은 글번호에 맞는 데이터를 DB에서 삭제 
 */
package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.Service;

public class BoardDeleteService implements Service {

	BoardDAO dao = new BoardDAO();
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
			
		//글삭제 no데이터를 넘겨주고 DB에서 글삭제 
		return dao.delete((Long)obj);
	}

	
}
