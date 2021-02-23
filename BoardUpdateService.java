/*
 * 게시판 리스트를 해결하기 위한 객체 
 * BoardDAO객체를 사용해서 DB에서 데이터를 수집해오기
 */
package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Service;

public class BoardUpdateService implements Service {

	BoardDAO dao = new BoardDAO();
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		
		//글보기 vo데이터를 가져와서 넘겨줌 
		return dao.update((BoardVO)obj);
	}

	
}
