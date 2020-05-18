/**
 *<pre>
 * com.togetdog
 * Class Name : CommunityVO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-10           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-10 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.community;

import com.togetdog.cmn.DTO;

/**
 * @author sist128
 *
 */
public class CommunityVO extends DTO {
	private String totNo	   ;
	private String memberId    ;
	private String title       ;
	private String contents    ;
	private String regDt       ;
	private String modDt       ;
	private int 	count      ;
	private String ableYn      ;
	
	public CommunityVO() {}

	public CommunityVO(String totNo, String memberId, String title, String contents, String regDt, String modDt,
			int count, String ableYn) {
		super();
		this.totNo = totNo;
		this.memberId = memberId;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
		this.modDt = modDt;
		this.count = count;
		this.ableYn = ableYn;
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

	@Override
	public String toString() {
		return "CommunityVO [totNo=" + totNo + ", memberId=" + memberId + ", title=" + title + ", contents=" + contents
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", count=" + count + ", ableYn=" + ableYn + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
