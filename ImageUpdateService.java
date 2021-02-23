/*
 *  이미지 게시판 리스트를 해결하기 위한 객체 
 * ImageDAO객체를 사용해서 DB에서 데이터를 수집해오기
 */
package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.main.controller.Service;

public class ImageUpdateService implements Service {

	ImageDAO dao = new ImageDAO();
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		
		//글보기 vo데이터를 가져와서 넘겨줌 
		return dao.update((ImageVO)obj);
	}

	
}
