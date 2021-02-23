/*
 * 처리를 해결하기 위한 객체 
 * 리턴타입이나 매개변수가 특정한 것 없이 여러타입을 사용하게 되면 Object 사용 
 * 예외처리는 Controller에서 처리하므로 여기서는 던진다 
 * 작성일 :2021.01.18
 * 작성자 :최가람 
 */
package com.webjjang.main.controller;

public interface Service {

	public Object service(Object obj) throws Exception;
	
}
