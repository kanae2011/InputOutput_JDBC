/*
 * 이미지 게시판 처리 결과를 출력하는 객체 
 * list-print(list),view-print(vo),write/update/delete-print(int result,action)
 * 작성일 : 2021.01.19
 * 작성자 : 최가람
 */
package com.webjjang.print;

import java.util.List;

import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.Output;

public class NoticePrint {

	//1.list결과를 출력하는 메소드 사용법 : NoticePrint.print(list)
	public static void print(List<NoticeVO>list) {
		
		int cnt =70;
		//제목 출력 
		Output.title("공지사항 게시판 리스트");
		//항목이름 출력 / 가운데 출력할 문자열,위아래 라인 출력 문자, 반복횟수 
		Output.printWithLine("글 번호 / 제목  / 공지시작일 / 공지종료일 / 최종수정일 ","-",cnt);
		//데이터출력
		if(list==null) {//예외는 발생되지 않았지만 가져 온 데이터가 없는 경우
			System.out.println("데이터가 없습니다.");
			
		}else {//가져온 데이터가 한 개 이상인 경우 
			for(NoticeVO vo : list) {
				System.out.print(vo.getNo() + "/");
				System.out.print(vo.getTitle() + "/");			
				System.out.print(vo.getStartDate() + "/");
				System.out.print(vo.getEndDate() + "/");			
				System.out.println(vo.getUpdateDate());
				
			}
			
		}
		Output.line("-", cnt);
	}  // 공지사항 게시판 리스트 출력 끝 
	
	//2.view 결과를 출력한느 메소드 
	public static void print(NoticeVO vo) {
		
		int cnt =40;
		//제목 출력 
		Output.title("공지사항 게시판 글보기");
		//데이터출력
		Output.line("-", cnt);
		if(vo==null) {//예외는 발생되지 않았지만 가져 온 데이터가 없는 경우
			System.out.println("해당 데이터가 없습니다.글 번호를 확인하세요.");
			
		}else {//가져온 데이터가 한 개 이상인 경우 	
			System.out.println(" 글번호 :" + vo.getNo());
			System.out.println(" 제   목 :" + vo.getTitle());
			System.out.println(" 내   용 :" + vo.getContent());
			System.out.println(" 공지시작일 :" + vo.getStartDate());
			System.out.println(" 공지종료일 :" + vo.getEndDate());
			System.out.println(" 공지등록일 :" + vo.getWriteDate());
			System.out.println(" 최종수정일 :" + vo.getUpdateDate());
			}	
		Output.line("-", cnt);
	} // 이미지 게시판 리스트 출력 끝 
	
	//3.write,update,delete 결과를 출력하는 메소드
	//msg -동작(글쓰기)
	public static void print(int result,String msg) {
		int cnt =40;
		//제목 출력 
		Output.title(msg);
		//처리한 결과 출력
		if(result > 0)
			Output.printWithLine(msg + " 이(가) 완료되었습니다.","-", 30);
		else
			Output.printWithLine(msg + " 이(가) 완료되지 않았습니다.","-", 30);
			
	}
	}
