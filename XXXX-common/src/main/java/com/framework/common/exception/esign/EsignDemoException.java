package com.framework.common.exception.esign;

/**
 * description 自定义全局异常
 * @author 澄泓
 * datetime 2019年7月1日上午10:43:24
 */
public class EsignDemoException extends Exception {

	private static final long serialVersionUID = -1L;
	private Exception e;

	public EsignDemoException(String msg) {
		super(msg);
	}

	public EsignDemoException(String msg, Throwable cause) {
		super(msg,cause);
	}

	public EsignDemoException(){

	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}




}
