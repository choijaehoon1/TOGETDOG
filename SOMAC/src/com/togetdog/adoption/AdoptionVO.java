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

package com.togetdog.adoption;

import com.togetdog.cmn.DTO;

/**
 * @author sist128
 *
 */
public class AdoptionVO extends DTO {

	private String applyNo;
	private String desertionNo;
	private String memberId;
	private String familyCnt;
	private String experienceYn;
	private String applyState;
	private String applyReason;
	private String regDt;
	private String aregDt;

	public AdoptionVO() {
		// TODO Auto-generated constructor stub
	}

	public AdoptionVO(String applyNo, String desertionNo, String memberId, String familyCnt, String experienceYn,
			String applyState, String applyReason, String regDt, String aregDt) {
		super();
		this.applyNo = applyNo;
		this.desertionNo = desertionNo;
		this.memberId = memberId;
		this.familyCnt = familyCnt;
		this.experienceYn = experienceYn;
		this.applyState = applyState;
		this.applyReason = applyReason;
		this.regDt = regDt;
		this.aregDt = aregDt;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
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

	public String getFamilyCnt() {
		return familyCnt;
	}

	public void setFamilyCnt(String familyCnt) {
		this.familyCnt = familyCnt;
	}

	public String getExperienceYn() {
		return experienceYn;
	}

	public void setExperienceYn(String experienceYn) {
		this.experienceYn = experienceYn;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getAregDt() {
		return aregDt;
	}

	public void setAregDt(String aregDt) {
		this.aregDt = aregDt;
	}

	@Override
	public String toString() {
		return "AdoptionVO [applyNo=" + applyNo + ", desertionNo=" + desertionNo + ", memberId=" + memberId
				+ ", familyCnt=" + familyCnt + ", experienceYn=" + experienceYn + ", applyState=" + applyState
				+ ", applyReason=" + applyReason + ", regDt=" + regDt + ", aregDt=" + aregDt + "]";
	}

}
