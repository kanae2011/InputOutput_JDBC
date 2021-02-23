
package com.webjjang.util;
/**
 * <h3>Output class JAVA console 출력에 관련된 객체 </h3>
 * <p>
 * <h4>line()</h4>
 * 특별한 문자의 반복으로 한 줄을 출력하는 메소드 <br/>
 * line(반복문자열,박복횟수)
 * 
 * <h4>greeting()</h4>
 * 프로그램의 시작과끝에 작성하는 인사 
 * greeting(인삿말 문자열 )-위아래  '*'라인 포함 
 * 
 * <h4>Position()</h4>
 * 프로그램의 실행 위치를 나타내는 메소드 -위아래  '-'라인 포함 </br>
 * position(위치정보 main>board)
 * 
 * <h4>menu()</h4>
 * 메뉴 출력,여러개 출력을 위해 여러개의 문자열을 전달
 * menu(메뉴 문자열,...,메뉴문자열)
 * <h4>exePos</h4>
 * 실행하는 클래스와 메소드 정보를 출력 -'***'문자열'***'<br/>
 * exePos(실행정보의 문자열)
 * 
 * <h4>title</h4>
 * 제목을 출력 -위아래 '*'라인 포함
 * 
 * <h4>printWithLine</h4>
 * 위아래 문자열 라인을 포함 가운데 문자열  
 * printWithLine(입력한 문자열,라인문자열,반복횟수)
 * </p>
 * @작성자 : 최가람
 * @작성일 : 2021.01.20
 */
public class Output { //개발 단계에서 구조 파악을 위해 임시적으로 쓰는 클래스 

	//str문자열을 받아서 cnt만큼 반복해서 출력
	public static void line(String str, int cnt) {
		for(int i = 1; i <=cnt ; i++)
			System.out.print(str);
		//줄바꿈
		System.out.println();
	}
	
	//환영인사,작별인사 
	public static void greeting(String str) {
		line("*",30);
		System.out.println(str);
		line("*",30);
	}
	
	//위치 정보 출력
	public static void position(String p) {
		printWithLine("position :"+ p, "-", 30);
	}
	
	public static void menu(String ...strs) {
		line("-",30);
		for(String str : strs)
			System.out.println(str);
		line("-",30);
	}
	
	//실행위치 확인을 위한 출력
	public static void exePos(String str) {
		System.out.println("***"+ str +"***");
	}
	
	//제목을 출력
	public static void title(String str) {
		printWithLine(str, "*", 20);
		
	}
	//위아래 반복되는 문자와 반복 횟수를 받아서 입력받은 문자열 가운데 출력하는 메소드 
	public static void printWithLine(String str,String loopChar,int cnt) {
		line(loopChar,cnt);
		System.out.println(str);
		line(loopChar,cnt);
	}
	
	
}
