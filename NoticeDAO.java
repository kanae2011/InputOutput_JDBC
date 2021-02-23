package com.webjjang.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.DBinfo;
import com.webjjang.util.sql.DAOSQL;

public class NoticeDAO { // data access object -> DB랑 실제로 연락을 하는 클래스

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 공지사항 리스트 짜기
	public List<NoticeVO> list() throws Exception {

		// 실행한 결과를 저장하는 객체 선언
		List<NoticeVO> list = null;

		// DB의 정보는 DBInfo 객체에 넣어놨음

		try {
			// 1.드라이버 확인 (static초기화 블록에서 했음)
			// 2.연결객체
			con = DBinfo.getConnection();
			// 3.쿼리작성 - DAOSQL에서 선언됨
			// 4.실행객체 & 데이터 셋팅
			System.out.println(DAOSQL.NOTICE_LIST);
			pstmt = con.prepareStatement(DAOSQL.NOTICE_LIST);
			rs = pstmt.executeQuery(); // 5.실행
			// 6.출력 ->데이터 저장 후 리턴
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					//vo.setContent(rs.getString("content"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setUpdateDate(rs.getString("updateDate"));

					// vo ->list로 저장
					list.add(vo);
				}
			} // end of if
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("공지사항 리스트 데이터 처리 중 오류가 발생했습니다.");
		} finally {
			DBinfo.close(con, pstmt, rs);

		}

	}// end of list()

	// 공지사항 글보기 짜기
	public NoticeVO view(long no) throws Exception {

		// 실행한 결과를 저장하는 객체 선언
		NoticeVO vo = null;

		// DB의 정보는 DBInfo 객체에 넣어놨음

		try {
			// 1.드라이버 확인 (static초기화 블록에서 했음)
			// 2.연결객체
			con = DBinfo.getConnection();
			// 3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.NOTICE_VIEW);
			// 4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.NOTICE_VIEW);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery(); // 5.실행
			// 6.출력 ->데이터 저장 후 리턴
			if (rs != null & rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setUpdateDate(rs.getString("updateDate"));

			} // end of if

			return vo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("공지사항 글보기 데이터 처리 중 오류가 발생했습니다.");
		} finally {
			DBinfo.close(con, pstmt, rs);

		}

	}// end of view()

	// 공지사항 글쓰기
	public int write(NoticeVO vo) throws Exception {
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;

		try {
			// 1.드라이버 확인 (static초기화 블록에서 했음)
			// 2.연결객체
			con = DBinfo.getConnection();
			// 3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.NOTICE_WRITE);
			// 4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.NOTICE_WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());

			result = pstmt.executeUpdate(); // 5.실행-> 결과 int
			// 6.출력 ->데이터 저장 후 리턴
			System.out.println("NoticeDAO.write() : 글 쓰기 완료");
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("공지사항 글쓰기 처리 중 오류가 발생했습니다."); // NoticeController에서 예외처리를 위한 처리
		} finally {
			DBinfo.close(con, pstmt, rs);

		}

	}// end of write()

	// 공지사항 수정하기
	public int update(NoticeVO vo) throws Exception {
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;

		try {
			// 1.드라이버 확인 (static초기화 블록에서 했음)
			// 2.연결객체
			con = DBinfo.getConnection();
			// 3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.NOTICE_UPDATE);
			// 4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.NOTICE_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setLong(5, vo.getNo());

			result = pstmt.executeUpdate(); // 5.실행-> 결과 int
			// 6.출력 ->데이터 저장 후 리턴
			System.out.println("NoticeDAO.update() : 글 수정 완료");
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("공지사항 수정 처리 중 오류가 발생했습니다."); // NoticeController에서 예외처리를 위한 처리
		} finally {
			DBinfo.close(con, pstmt, rs);

		}

	}// end of update()

	public int delete(long no) throws Exception {
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;

		try {
			// 1.드라이버 확인 (static초기화 블록에서 했음)
			// 2.연결객체
			con = DBinfo.getConnection();
			// 3.쿼리작성 - DAOSQL에서 선언됨
			System.out.println(DAOSQL.NOTICE_DELETE);
			// 4.실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.NOTICE_DELETE);
			pstmt.setLong(1, no);
			result = pstmt.executeUpdate(); // 5.실행-> 결과 int
			// 6.출력 ->데이터 저장 후 리턴
			System.out.println("NoticeDAO.delete() : 글 삭제 완료");

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("공지사항 글 삭제 처리 중 오류가 발생했습니다."); // NoticeController에서 예외처리를 위한 처리
		} finally {
			DBinfo.close(con, pstmt, rs);

		}

	}// end of delete()
}
