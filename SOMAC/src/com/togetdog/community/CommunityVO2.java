package com.togetdog.community;

import com.togetdog.cmn.DTO;

public class CommunityVO2 extends DTO {
	private String replyNo		;
	private int rseqNo         ;
	private String rcontents    ;
	private String rregId       ;
	private String rregDt       ;
	private String replyYn      ;
	private String totNO        ;
	
	public CommunityVO2() {}

	public CommunityVO2(String replyNo, int rseqNo, String rcontents, String rregId, String rregDt, String replyYn,
			String totNO) {
		super();
		this.replyNo = replyNo;
		this.rseqNo = rseqNo;
		this.rcontents = rcontents;
		this.rregId = rregId;
		this.rregDt = rregDt;
		this.replyYn = replyYn;
		this.totNO = totNO;
	}

	/**
	 * @return the replyNo
	 */
	public String getReplyNo() {
		return replyNo;
	}

	/**
	 * @param replyNo the replyNo to set
	 */
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	/**
	 * @return the rseqNo
	 */
	public int getRseqNo() {
		return rseqNo;
	}

	/**
	 * @param rseqNo the rseqNo to set
	 */
	public void setRseqNo(int rseqNo) {
		this.rseqNo = rseqNo;
	}

	/**
	 * @return the rcontents
	 */
	public String getRcontents() {
		return rcontents;
	}

	/**
	 * @param rcontents the rcontents to set
	 */
	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
	}

	/**
	 * @return the rregId
	 */
	public String getRregId() {
		return rregId;
	}

	/**
	 * @param rregId the rregId to set
	 */
	public void setRregId(String rregId) {
		this.rregId = rregId;
	}

	/**
	 * @return the rregDt
	 */
	public String getRregDt() {
		return rregDt;
	}

	/**
	 * @param rregDt the rregDt to set
	 */
	public void setRregDt(String rregDt) {
		this.rregDt = rregDt;
	}

	/**
	 * @return the replyYn
	 */
	public String getReplyYn() {
		return replyYn;
	}

	/**
	 * @param replyYn the replyYn to set
	 */
	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}

	/**
	 * @return the totNO
	 */
	public String getTotNO() {
		return totNO;
	}

	/**
	 * @param totNO the totNO to set
	 */
	public void setTotNO(String totNO) {
		this.totNO = totNO;
	}

	@Override
	public String toString() {
		return "CommunityVO2 [replyNo=" + replyNo + ", rseqNo=" + rseqNo + ", rcontents=" + rcontents + ", rregId="
				+ rregId + ", rregDt=" + rregDt + ", replyYn=" + replyYn + ", totNO=" + totNO + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
}
