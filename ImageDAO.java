package com.webjjang.image.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.DBinfo;
import com.webjjang.util.sql.DAOSQL;

public class ImageDAO { // data access object -> DB랑 실제로 연락을 하는 클래스

	 //필요한 객체 선언 
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 

	//이미지 게시판 리스트 짜기 
	public List<ImageVO>list() throws Exception{
		
		//실행한 결과를 저장하는 객체 선언
		 List<ImageVO>list = null;
		 
		 //DB의 정보는 DBInfo 객체에 넣어놨음 
		
		 try {
			 //1.드라이버 확인 (static초기화 블록에서 했음)
			 //2.연결객체
			 con=DBinfo.getConnection();
			 //3.쿼리작성 - DAOSQL에서 선언됨
			 //4.실행객체 & 데이터 셋팅
			 pstmt = con.prepareStatement(DAOSQL.IMAGE_LIST);
			 rs = pstmt.executeQuery(); //5.실행
			 //6.출력 ->데이터 저장 후 리턴
			 if(rs!=null) {
				 while(rs.next()) {
					 if(list==null) list = new ArrayList<ImageVO>();
					 ImageVO vo = new ImageVO();
					 vo.setNo(rs.getLong("no"));
					 vo.setTitle(rs.getString("title"));
					 vo.setId(rs.getString("id"));
					 vo.setWriteDate(rs.getString("writeDate"));
					 vo.setFilename(rs.getString("filename"));
					 
					 //vo ->list로 저장
					 list.add(vo);
				 }
			 }//end of if
			 return list;
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new Exception("이미지 게시판 리스트 데이터 처리 중 오류가 발생했습니다.");	 
       } finally {
				DBinfo.close(con, pstmt,rs);	 
				 
			 }
		 
	}//end of list()
	
	//이미지 게시판 글보기 짜기 
	public ImageVO view(long no) throws Exception{
			
			//실행한 결과를 저장하는 객체 선언
		ImageVO vo= null;
			 
			 //DB의 정보는 DBInfo 객체에 넣어놨음 
			
		try {
			//1.드라이버 확인 (static초기화 블록에서 했음)
			//2.연결객체
			con=DBinfo.getConnection();
			//3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.IMAGE_VIEW);
			//4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.IMAGE_VIEW);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery(); //5.실행
			//6.출력 ->데이터 저장 후 리턴
			if(rs!=null & rs.next()) {
				vo = new ImageVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setFilename(rs.getString("filename"));
				
			}//end of if
			
			return vo;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("이미지 게시판 글보기 데이터 처리 중 오류가 발생했습니다.");	 
		} finally {
			DBinfo.close(con, pstmt,rs);	 
			
		}
		
	}//end of view()
	
	
	//이미지 게시판 글쓰기 
	public int write(ImageVO vo) throws Exception{
		//실행한 결과를 저장하는 객체 선언
			int result = 0;
			
			try {
				//1.드라이버 확인 (static초기화 블록에서 했음)
				//2.연결객체
				con=DBinfo.getConnection();
				//3.쿼리작성 - DAOSQL에서 선언됨
				System.out.println(DAOSQL.IMAGE_WRITE);
				//4.실행객체 & 데이터 셋팅
				pstmt = con.prepareStatement(DAOSQL.IMAGE_WRITE);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getId());
				pstmt.setString(4, vo.getFilename());
				
				result = pstmt.executeUpdate(); //5.실행-> 결과 int
				//6.출력 ->데이터 저장 후 리턴
				System.out.println("ImageDAO.write() : 글 쓰기 완료");
				return result;
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new Exception("이미지 게시판 글쓰기 처리 중 오류가 발생했습니다.");	 //ImageController에서 예외처리를 위한 처리
			} finally {
				DBinfo.close(con, pstmt,rs);	 
				
			}
				
			}//end of write()
		
		//이미지 게시판 수정하기 
		public int update(ImageVO vo) throws Exception{
		//실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1.드라이버 확인 (static초기화 블록에서 했음)
			//2.연결객체
			con=DBinfo.getConnection();
			//3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.IMAGE_UPDATE);
			//4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.IMAGE_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			result = pstmt.executeUpdate(); //5.실행-> 결과 int
			//6.출력 ->데이터 저장 후 리턴
			System.out.println("ImageDAO.update() : 글 수정 완료");
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("이미지 게시판 수정 처리 중 오류가 발생했습니다.");	 //ImageController에서 예외처리를 위한 처리
		} finally {
			DBinfo.close(con, pstmt,rs);	 
			
		}
		
	}//end of update()
		
		public int delete(long no) throws Exception{
			//실행한 결과를 저장하는 객체 선언
			int result = 0;
			
			try {
				//1.드라이버 확인 (static초기화 블록에서 했음)
				//2.연결객체
				con=DBinfo.getConnection();
				//3.쿼리작성 - DAOSQL에서 선언됨
				System.out.println(DAOSQL.IMAGE_DELETE);
				//4.실행객체 & 데이터 셋팅
				pstmt = con.prepareStatement(DAOSQL.IMAGE_DELETE);
				pstmt.setLong(1,no);
				result = pstmt.executeUpdate(); //5.실행-> 결과 int
				//6.출력 ->데이터 저장 후 리턴
				System.out.println("ImageDAO.delete() : 글 삭제 완료");
				
				return result;
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new Exception("이미지 게시판 글 삭제 처리 중 오류가 발생했습니다.");	 //ImageController에서 예외처리를 위한 처리
			} finally {
				DBinfo.close(con, pstmt,rs);	 
				
			}
			
		}//end of delete()
	}
