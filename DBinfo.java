/*
 * 데이터 베이스 처리를 위한 정보를 가지고 있는 객체 
 * 개발환경 : 자바 8,오라클 11g XE 
 * 작성일 : 2021.01.18
 * 작성자 : 최가람
 */
package com.webjjang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBinfo {

	//DB 정보 변수 
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID ="java00";
	private static final String PW ="java00";
	
	private static boolean checkDriver = false; //ture 면 드라이버가 있음 
	
	static { //static 초기화 블록 - 한 번 사용 될 때, 한 번만 실행되는 초기화 블록 - static 변수의 값을 초기화 시킬 때 사용함 
		System.out.println("DBinfo.static{}------------------------------------");
		try { //1.드라이버 확인 -모든 DB처리 프로그램에서 처리를 하지 않아도 된다.
			Class.forName(DRIVER);
			checkDriver =true;
			System.out.println("오라클 DB를 위한 드라이버가 존재합니다.");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클DB를 위한 드라이버가 존재하지 않습니다.\n 이후의 DB처리에 문제가 발생됩니다.");
		}
	}
	
	//DB 연결하는 메소드 - DB를 사용하는 곳에서 모두 써야하므로 메소드로 선언 
	public static final Connection getConnection() throws Exception{
		//연결을 하기위해서 먼저 드라이버 확인 - 없으면 예외 생성하고 던짐 
		if(!checkDriver)throw new Exception("드라이버가 존재하지 않습니다.");
		return DriverManager.getConnection(URL,ID,PW);
	}
	
	//DB 사용객체 닫기 -연결, 실행객체 닫기 - insert,update,delete
	public static final void close(Connection con,PreparedStatement pstmt) throws Exception{
		//예외처리를 안하고 있음- 메소드에 throws Exception 호출한 쪽으로 예외를 던진다 
		if(con!=null) con.close();
		if(pstmt!=null) pstmt.close();
		
	}
	
	//DB 사용객체 닫기 -연결, 실행, 결과객체 닫기 - select
	public static final void close(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
		//예외처리를 안하고 있음- 메소드에 throws Exception 호출한 쪽으로 예외를 던진다 
		close(con,pstmt); //연결,실행 객체 닫기 호출해서 실행하도록 
		if(rs!=null) rs.close();
	}
}
