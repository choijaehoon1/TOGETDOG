package com.togetdog.qna;

import com.togetdog.cmn.DTO;

public class QnaVO2 extends DTO {
	private String replyNo		;
	private int rseqNo         ;
	private String rcontents    ;
	private String rregId       ;
	private String rregDt       ;
	private String replyYn      ;

	
	public QnaVO2() {}


	public QnaVO2(String replyNo, int rseqNo, String rcontents, String rregId, String rregDt, String replyYn) {
		super();
		this.replyNo = replyNo;
		this.rseqNo = rseqNo;
		this.rcontents = rcontents;
		this.rregId = rregId;
		this.rregDt = rregDt;
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


	public String getRcontents() {
		return rcontents;
	}


	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
	}


	public String getRregId() {
		return rregId;
	}


	public void setRregId(String rregId) {
		this.rregId = rregId;
	}


	public String getRregDt() {
		return rregDt;
	}


	public void setRregDt(String rregDt) {
		this.rregDt = rregDt;
	}


	public String getReplyYn() {
		return replyYn;
	}


	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}


	@Override
	public String toString() {
		return "QnaVO2 [replyNo=" + replyNo + ", rseqNo=" + rseqNo + ", rcontents=" + rcontents + ", rregId=" + rregId
				+ ", rregDt=" + rregDt + ", replyYn=" + replyYn + ", toString()=" + super.toString() + "]";
	}

	
	
	
}
