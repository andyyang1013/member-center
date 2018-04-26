package com.hhly.member.client.exception;


import lombok.Getter;

/**
* 系统业务异常类
* @author jiasx
* @create 2018/2/5 17:19
**/
public class MemberException extends RuntimeException {

	@Getter
	private String replyMsg;
	
	public MemberException(String replyMsg){
		this.replyMsg = replyMsg;
	}

	public MemberException(String replyMsg, Throwable e){
		super(replyMsg,e);
		this.replyMsg = replyMsg;
	}

	@Override
	public String getMessage() {
		return replyMsg;
	}
}