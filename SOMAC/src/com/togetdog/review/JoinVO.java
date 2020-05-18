package com.togetdog.review;

import com.togetdog.cmn.DTO;

public class JoinVO extends DTO {

	private String revNo    ;/** 게시글번호 */         
	private String applyNo  ;/** 입양신청코드 */        
	private String memberId ;/** 회원아이디 */         
	private String title    ;/** 제목 */            
	private String contents ;/** 내용 */            
	private String familyDt ;/** 가족이된날 */         
	private String regDt    ;/** 작성일 */           
	private String modDt    ;/** 수정일 */           
	private int count       ;/** 조회수 */           
	private String ableYn   ;/** 게시글공개여부 */
	private String imgPath  ;/**이미지경로 */
	private String ext      ;/**확장자 */
	
	public JoinVO() {}

	public JoinVO(String revNo, String applyNo, String memberId, String title, String contents, String familyDt,
			String regDt, String modDt, int count, String ableYn, String imgPath, String ext) {
		super();
		this.revNo = revNo;
		this.applyNo = applyNo;
		this.memberId = memberId;
		this.title = title;
		this.contents = contents;
		this.familyDt = familyDt;
		this.regDt = regDt;
		this.modDt = modDt;
		this.count = count;
		this.ableYn = ableYn;
		this.imgPath = imgPath;
		this.ext = ext;
	}

	public String getRevNo() {
		return revNo;
	}

	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
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

	public String getFamilyDt() {
		return familyDt;
	}

	public void setFamilyDt(String familyDt) {
		this.familyDt = familyDt;
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
		return "JoinVO [revNo=" + revNo + ", applyNo=" + applyNo + ", memberId=" + memberId + ", title=" + title
				+ ", contents=" + contents + ", familyDt=" + familyDt + ", regDt=" + regDt + ", modDt=" + modDt
				+ ", count=" + count + ", ableYn=" + ableYn + ", imgPath=" + imgPath + ", ext=" + ext + ", toString()="
				+ super.toString() + "]";
	}


	
	
	
}
