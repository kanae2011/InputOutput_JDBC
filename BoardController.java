/*
 * 모듈 컨트롤러 -  메인 메뉴를 입력하고 어떤 객체를 처리할지를 선택해서 처리해주는 객체(Controller) 
 * 예외처리를 함 
 * 작성일 : 2021.01.18
 * 작성자 : 최가람 
 */
package com.webjjang.board.controller;

import java.util.List;

//import com.webjjang.board.service.BoardDeleteService;
//import com.webjjang.board.service.BoardListService;
//import com.webjjang.board.service.BoardUpdateService;
//import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.*; // service 패키지 안에 있는 모든 클래스를 다 import 함 : * - 모두다 
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.print.BoardPrint;
import com.webjjang.util.Input;
import com.webjjang.util.Output;

public class BoardController implements Controller {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		// TODO Auto-generated method stub

		//게시판 처리를 위한 무한 루프
		while(true) { 
			try {
			//위치정보출력
			Output.position("main > board");
			//메뉴출력,입력
			Output.menu("1.게시판 리스트 2.게시판 글보기 3.게시판 글쓰기 ","4.게시판 글수정 5.게시판 글삭제 0.이전메뉴",
					"글보기,글수정,글 삭제는 리스트에서 글 번호 확인요망");
			String menu = Input.getString("메뉴 입력");
			//메뉴처리
			switch(menu) {
			case "1" :
				//1.DB에서 데이터 가져오기
				List<BoardVO> list =(List <BoardVO>)ExeService.execute(new BoardListService(),null);
				//2.데이터 출력하기-데이터 가져오기에서 실패(예외발생)하면 출력하러 가지 않음 -catch로 이동 
				BoardPrint.print(list);
				break;
			case "2" :
				//BoardVO 
				//0.가져 올 글 번호 받기 -> 있는 글 번호 확인을 위해 리스트 실행 (수동)
				long no = Input.getLong("보여줄 글 번호 ");
				//1.DB에서 데이터 가져오기 
				BoardVO vo =(BoardVO)ExeService.execute(new BoardViewService(),no);
				//2.데이터 출력하기 
				BoardPrint.print(vo);
				break;
			case "3" :
				//1.데이터를 수집해서 vo객체에 넣음 
				BoardVO invo = new BoardVO();
				invo.setTitle(Input.getString("제목"));
				invo.setContent(Input.getString("내용"));
				invo.setWriter(Input.getString("작성자"));
				//2.BoardWriteService에 전달 
				int writeresult = (Integer)ExeService.execute(new BoardWriteService(),invo);
				//3.결과에 대한 출력 
				BoardPrint.print(writeresult,"게시판 글쓰기");
				break;
			case "4" :
				//1.수정 할 글번호를 받음				
				//2.수정 할 데이터를 DB에서 가져와서 - BoardViewService
				//가져온 데이터를 2번 이상 사용 할 때는 반드시 변수에 저장해서 사용할 것 
				BoardVO updateVO =((BoardVO)ExeService.execute(new BoardViewService(),Input.getLong("수정할 글 번호 ")));
				//3.리스트 출력 -글보기 출력-> 수정처리 while안에서 출력 
				//4.수정 처리를 함 
				updateBoardVO(updateVO); //위의 메소드 안에서 vo객체를 변경 하면 밖에서도 변경됨 (참조형 변수의 특징!)
				System.out.println("BoardController.execute() 수정 완료 후 수정 메소드 밖에서의 vo " + updateVO);
				//5.수정 내용을 DB에 저장  -BoardUpdateService
				int updateResult = (Integer)ExeService.execute(new BoardUpdateService(), updateVO);
				//6.수정 결과 출력 -print(result,msg)
				BoardPrint.print(updateResult,"게시판 글수정");
				break;
			case "5" :
				//삭제 할 글 번호를 받는다
				//DB에서 삭체처리 -BoardDeleteService
				int deleteResult =(Integer)ExeService.execute(new BoardDeleteService(),Input.getLong("삭제 할 글 번호 "));
				BoardPrint.print(deleteResult,"게시판 글삭제");
				break;
			case "0" :
				//이전메뉴 
				return;
			default:
				break;
			} // end of switch
			}catch(Exception e) {
				//e.printStackTrace(); //개발자 코드 
				Output.printWithLine(e.getMessage(), "-", 30); //사용자 코드 
			}
			
		}//end of while(true)
	}//end of execute()

	//수정을 위한 객체의 데이터 메소드 / 수정 후의 내용은 DB에 저장한 것이 아님 
	private void updateBoardVO(BoardVO vo) throws Exception {
		Output.title("게시글 수정을 위한 데이터 수정 진행");
		while(true) {
			BoardPrint.print(vo);
			Output.menu("1.제목 2.내용 3.작성자 ","0.수정완료 (DB적용) -1.수정 취소 ");
			String menu = Input.getString("항목 선택");
			switch(menu) {
			case "1":
				vo.setTitle(Input.getString("제목"));
				break;
			case "2":
				vo.setContent(Input.getString("내용"));
				break;
			case "3":
				vo.setWriter(Input.getString("작성자"));
				break;
			case "0":
				return;
			case "-1":
				throw new Exception("게시판 수정이 취소되었습니다.");
			default:
				Output.printWithLine("잘못된 항목 선택", "-", 30);
				break;
			}
			
		}
	}
}
