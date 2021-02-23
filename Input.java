package com.webjjang.util;

import java.util.Scanner;

/**
 * <h3>데이터를 입력하는 객체 생성</h3>
 * <p>
 * <h4>getString()</h4>
 * 문자열 scanner.nextLine()에 의해서 사용자가 입력하는 메소드
 * 
 * <h4>getString(String msg)</h4>
 * 문자열을 입력하기 전에 msg 출력 후 사용자가 입력 getString()(호출)하는 메소드
 * 
 * <h4>getInt</h4>
 * 문자열을 받아서  int로 바꿔서 리턴하는 메소드.정확한 int 가 들어오지 않으면 반복처리 
 * <h4>getInt(String msg)</h4>
 * 문자열을 받아서 getString()(호출) int로 바꿔서 리턴하는 메소드.int 데이터가 들어오지 않으면 반복처리 
 * 
 * <h4>getLong</h4>
 * 문자열을 받아서  Long으로 바꿔서 리턴하는 메소드.Long데이터가 들어오지 않으면 반복처리
 * <h4>getLong(String msg)</h4>
 * 문자열을 받아서 getString()(호출) Long으로 바꿔서 리턴하는 메소드.Long데이터가 들어오지 않으면 반복처리
 * 
 * </p>
 * @작성자 : 최가람
 * @작성일 : 2021.01.20
 * 
 */

public class Input {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static String getString() { //문자열만 받아내는 기본 메소드 
		return SCANNER.nextLine();
	}
	
	public static String getString(String msg) { //메세지를 출력, 문자열 받기  
		System.out.print(msg + "-> ");
		return getString();
	}
	
	//int data 받는 메소도 
	public static int getInt() {
		while(true) {
			try {
				System.out.print("int 숫자입력 ->");
				return Integer.parseInt(getString());
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("int 숫자만 입력가능합니다.");
			}
		}
	}
	//int data를 받는 메소드 - 입력 메세지 출력을 먼저 하는 메소드 
	public static int getInt(String msg) {
		System.out.println(msg);
		return getInt();
	}
	
	//long data 받는 메소도 
	public static long getLong() {
		while(true) {
			try {
				System.out.print("long 숫자입력 ->");
				return Integer.parseInt(getString());
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("long 숫자만 입력가능합니다.");
			}
		}
	}
	//long data를 받는 메소드 - 입력 메세지 출력을 먼저 하는 메소드 
	public static long getLong(String msg) {
		System.out.println(msg);
		return getLong();
	}
}
