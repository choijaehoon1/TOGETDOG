package com.togetdog.community;

import com.togetdog.cmn.DTO;

public class CommunityVO1 extends DTO {
	private String totNo	   ;
	private String memberId    ;
	private String title       ;
	private String contents    ;
	private String regDt       ;
	private String modDt       ;
	private int 	count      ;
	private String ableYn      ;
	private String saveNm  ;
	private String imgPath ;
	private String ext		;
		
	public CommunityVO1() {}

	public CommunityVO1(String totNo, String memberId, String title, String contents, String regDt, String modDt,
			int count, String ableYn, String saveNm, String imgPath, String ext) {
		super();
		this.totNo = totNo;
		this.memberId = memberId;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
		this.modDt = modDt;
		this.count = count;
		this.ableYn = ableYn;
		this.saveNm = saveNm;
		this.imgPath = imgPath;
		this.ext = ext;
	}

	public String getTotNo() {
		return totNo;
	}

	public void setTotNo(String totNo) {
		this.totNo = totNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAbleYn() {
		return ableYn;
	}

	public void setAbleYn(String ableYn) {
		this.ableYn = ableYn;
	}

	public String getSaveNm() {
		return saveNm;
	}

	public void setSaveNm(String saveNm) {
		this.saveNm = saveNm;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "CommunityVO1 [totNo=" + totNo + ", memberId=" + memberId + ", title=" + title + ", contents=" + contents
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", count=" + count + ", ableYn=" + ableYn + ", saveNm="
				+ saveNm + ", imgPath=" + imgPath + ", ext=" + ext + ", toString()=" + super.toString() + "]";
	}

	
	
}
