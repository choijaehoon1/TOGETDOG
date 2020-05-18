package com.togetdog.reply;

import com.togetdog.cmn.DTO;

public class CommonReplyVO extends DTO {

	private String replyNo	;
	private int    rseqNo   ;
	private String contents ;
	private String regId    ;
	private String regDt    ;
	private String replyYn  ;
	
	public CommonReplyVO() {}

	public CommonReplyVO(String replyNo, int rseqNo, String contents, String regId, String regDt, String replyYn) {
		super();
		this.replyNo = replyNo;
		this.rseqNo = rseqNo;
		this.contents = contents;
		this.regId = regId;
		this.regDt = regDt;
		this.replyYn = replyYn;
	}

	public String getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	public int getRseqNo() {
		return rseqNo;
	}

	public void setRseqNo(int rseqNo) {
		this.rseqNo = rseqNo;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getReplyYn() {
		return replyYn;
	}

	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}

	@Override
	public String toString() {
		return "CommonReplyVO [replyNo=" + replyNo + ", rseqNo=" + rseqNo + ", contents=" + contents + ", regId="
				+ regId + ", regDt=" + regDt + ", replyYn=" + replyYn + ", toString()=" + super.toString() + "]";
	}
	
	

	
	
}
