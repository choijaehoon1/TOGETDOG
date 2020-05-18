/**
 *<pre>
 * com.hr.cmn
 * Class Name : MessageVO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-25           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-25 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package com.togetdog.cmn;

/**
 * @author sist
 * msgId
 * msgContents
 */
public class MessageVO extends DTO {
	/** 메시지 ID */
	private String msgId;
	/** 메시지 내용*/
	private String msgContents;
	
	public MessageVO() {}

	public MessageVO(String msgId, String msgContents) {
		super();
		this.msgId = msgId;
		this.msgContents = msgContents;
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the msgContents
	 */
	public String getMsgContents() {
		return msgContents;
	}

	/**
	 * @param msgContents the msgContents to set
	 */
	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "MessageVO [msgId=" + msgId + ", msgContents=" + msgContents + ", toString()=" + super.toString() + "]";
	}

	 
}
