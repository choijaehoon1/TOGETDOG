package com.togetdog.review;

import com.togetdog.cmn.DTO;

public class AdoptImgVO extends DTO {

	private String desertionNo; //유기번호
	private String memberId   ; //회원 아이디
	private String popFile    ; //유기동물 사진 파일
	
	AdoptImgVO() {}

	public AdoptImgVO(String desertionNo, String memberId, String popFile) {
		super();
		this.desertionNo = desertionNo;
		this.memberId = memberId;
		this.popFile = popFile;
	}

	public String getDesertionNo() {
		return desertionNo;
	}

	public void setDesertionNo(String desertionNo) {
		this.desertionNo = desertionNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPopFile() {
		return popFile;
	}

	public void setPopFile(String popFile) {
		this.popFile = popFile;
	}

	@Override
	public String toString() {
		return "AdoptImgVO [desertionNo=" + desertionNo + ", memberId=" + memberId + ", popFile=" + popFile
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
