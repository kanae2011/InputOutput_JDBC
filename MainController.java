/*
 * 메인 메뉴를 입력하고 어떤 객체를 처리할지를 선택해서 처리해주는 객체(Controller) 
 */
package com.webjjang.main.controller;

import com.webjjang.board.controller.BoardController;
import com.webjjang.image.controller.ImageController;
import com.webjjang.notice.controller.NoticeController;
import com.webjjang.util.Input;
import com.webjjang.util.Output;

public class MainController {

	static {
		System.out.println("MainController 실행시작"); 
		// 프로젝트가 시작과 동시에 처리해야할 내용을 여기에 넣는다 
		//DB 드라이버 확인,모든 DB처리 프로그램에서 처리를 하지 않아도 된다.
		try {
			Class.forName("com.webjjang.util.DBinfo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//프로그램 시작하면서 환영인사 
		Output.greeting("Hello!");
		
		//메인처리 무한루프
		while(true) {
			//위치,메뉴 출력
			Output.position("main");
			Output.menu("1.공지사항 2.이미지 게시판", "3.질문답변 4.게시판 5.메세지", "6.로그인 0.종료");
			//메뉴입력,처리
			String menu = Input.getString("메뉴 입력");
			switch(menu) {
			case"1" :
				System.out.println("1.공지사항 처리");
				execute(new NoticeController());
				break;
			case"2" :
				System.out.println("2.이미지 게시판 처리");
				execute(new ImageController()); //생성->호출 
				break;
			case"3" :
				break;
			case"4" :
				System.out.println("4.게시판 처리 ");
				execute(new BoardController());
				break;
			case"5" :
				break;
			case"6" :
				break;
			case"0" :
				Output.greeting("Thank you.Have a nice day!");
				System.exit(0);
				break;
			default: 
				Output.printWithLine("잘못된 메뉴를 선택했습니다.","-", 30);
			
				break;
				
			}
		}//end of while(true)
	}//end of main 
	
	//moduleController 실행하는 메소드 -인터페이스/공통처리.프록시  
	private static void execute(Controller controller) { 
		Output.exePos(controller.getClass().getSimpleName()+".execute()");
		controller.execute();
	}
	
}
