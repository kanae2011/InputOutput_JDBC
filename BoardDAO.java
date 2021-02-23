package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.DBinfo;
import com.webjjang.util.sql.DAOSQL;

public class BoardDAO { // data access object -> DB랑 실제로 연락을 하는 클래스

	 //필요한 객체 선언 
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;

	 
	//게시판 리스트 짜기 
	public List<BoardVO> list() throws Exception{ // DB에 있는 모든 게시글의 리스트를 받기 위함 
		
		//실행한 결과를 저장하는 객체 선언
		 List<BoardVO> list = null;
		 
		 //DB의 정보는 DBInfo 객체에 넣어놨음 
		
		 try {
			 //1.드라이버 확인 (static초기화 블록에서 했음)
			 //2.연결객체
			 con=DBinfo.getConnection();
			 //3.쿼리작성 - DAOSQL에서 선언됨
			 //4.실행객체 & 데이터 셋팅
			 pstmt = con.prepareStatement(DAOSQL.BOARD_LIST);
			 rs = pstmt.executeQuery(); //5.실행
			 //6.출력 ->데이터 저장 후 리턴
			 if(rs!=null) { //위에서 null로 지정해놨으니 체크용도 
				 while(rs.next()) {
					 if(list==null) list = new ArrayList<BoardVO>();
					 BoardVO vo = new BoardVO();
					 vo.setNo(rs.getLong("no"));
					 vo.setTitle(rs.getString("title"));
					 vo.setWriter(rs.getString("writer"));
					 vo.setWriteDate(rs.getString("writeDate"));
					 vo.setHit(rs.getLong("hit"));
					 
					 //vo ->list로 저장
					 list.add(vo);
				 }
			 }//end of if
			 
			 return list;
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new Exception("게시판 리스트 데이터 처리 중 오류가 발생했습니다.");	 
       } finally {
				DBinfo.close(con, pstmt,rs);	 
				 
			 } 
	}//end of list()
	
	//게시판 글보기 짜기 
	public BoardVO view(long no) throws Exception{ // 상세보기.DB에 있는 해당넘버의 글 
			
			//실행한 결과를 저장하는 객체 선언
		BoardVO vo= null;
			 
			 //DB의 정보는 DBInfo 객체에 넣어놨음 
			
		try {
			//1.드라이버 확인 (static초기화 블록에서 했음)
			//2.연결객체
			con=DBinfo.getConnection();
			//3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.BOARD_VIEW);
			//4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_VIEW);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery(); //5.실행
			//6.출력 ->데이터 저장 후 리턴
		
			if(rs!=null & rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				
			}//end of if
			
			return vo;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 글보기 데이터 처리 중 오류가 발생했습니다.");	 
		} finally {
			DBinfo.close(con, pstmt,rs);	 
			
		}
		
	}//end of view()
	
	
	//게시판 글보기-조회수 1 증가 시키기  
	public int increase(long no) throws Exception{
		
		//실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1.드라이버 확인 (static초기화 블록에서 했음)
			//2.연결객체
			con=DBinfo.getConnection();
			//3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.BOARD_INCREASE);
			//4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_INCREASE);
			pstmt.setLong(1, no);
			result = pstmt.executeUpdate(); //5.실행-> 결과 int
			//6.출력 ->데이터 저장 후 리턴
			System.out.println("BoardDAO.increase() : 글보기 조회수 1증가 완료");
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 글보기 조회수 1 증가 처리 중 오류가 발생했습니다.");	 
		} finally {
			DBinfo.close(con, pstmt,rs);	 
			
		}
		
	}//end of increase()
	
	//게시판 글쓰기 
	public int write(BoardVO vo) throws Exception{ //INSERT 게시글 추가.그 글의 정보가 필요 
		//실행한 결과를 저장하는 객체 선언
			int result = 0;
			
			try {
				//1.드라이버 확인 (static초기화 블록에서 했음)
				//2.연결객체
				con=DBinfo.getConnection();
				//3.쿼리작성 - DAOSQL에서 선언됨
				System.out.println(DAOSQL.BOARD_WRITE);
				//4.실행객체 & 데이터 셋팅
				pstmt = con.prepareStatement(DAOSQL.BOARD_WRITE);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getWriter());
				pstmt.setLong(4, vo.getNo());
				result = pstmt.executeUpdate(); //5.실행-> 결과 int
				//6.출력 ->데이터 저장 후 리턴
				System.out.println("BoardDAO.write() : 글 쓰기 완료");
				return result;
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new Exception("게시판 글쓰기 처리 중 오류가 발생했습니다.");	 //BoardController에서 예외처리를 위한 처리
			} finally {
				DBinfo.close(con, pstmt,rs);	 
				
			}
				
			}//end of write()
		
		//게시판 수정하기 
		public int update(BoardVO vo) throws Exception{
		//실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1.드라이버 확인 (static초기화 블록에서 했음)
			//2.연결객체
			con=DBinfo.getConnection();
			//3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.BOARD_UPDATE);
			//4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			result = pstmt.executeUpdate(); //5.실행-> 결과 int
			//6.출력 ->데이터 저장 후 리턴
			System.out.println("BoardDAO.update() : 글 수정 완료");
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 수정 처리 중 오류가 발생했습니다.");	 //BoardController에서 예외처리를 위한 처리
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
				System.out.println(DAOSQL.BOARD_DELETE);
				//4.실행객체 & 데이터 셋팅
				pstmt = con.prepareStatement(DAOSQL.BOARD_DELETE);
				pstmt.setLong(1,no);
				result = pstmt.executeUpdate(); //5.실행-> 결과 int
				//6.출력 ->데이터 저장 후 리턴
				System.out.println("BoardDAO.delete() : 글 삭제 완료");
				
				return result;
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new Exception("게시판 글 삭제 처리 중 오류가 발생했습니다.");	 //BoardController에서 예외처리를 위한 처리
			} finally {
				DBinfo.close(con, pstmt,rs);	 
				
			}
			
		}//end of update()
	}
