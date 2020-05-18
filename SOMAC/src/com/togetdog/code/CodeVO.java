/**
 *<pre>
 * com.hr.code
 * Class Name : CodeVO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-27           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-27 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.code;

import com.togetdog.cmn.DTO;

/**
 * @author sist
 *
 */
public class CodeVO extends DTO {
	private String	mstId	; /**마스터코드*/
	private String	dtlId	; /**상세코드*/
	private String	mstNm	; /**마스터코드명*/
	private String	dtlNm	; /**상세코드명*/
	private String	pMsgId	; /**상위 마스터코드*/
	private int		seq		; /**순서*/
	private String	useYn	; /**사용여부*/
	private String	regId	; /**등록자아이디*/
	private String	regDt	; /**등록일*/
	private String	modId	; /**아이디ID*/
	private String	modDt	; /**수정일*/
	
	public CodeVO() {}
	
	public String getMstId() {
		return mstId;
	}
	public void setMstId(String mstId) {
		this.mstId = mstId;
	}
	public String getDtlId() {
		return dtlId;
	}
	public void setDtlId(String dtlId) {
		this.dtlId = dtlId;
	}
	public String getMstNm() {
		return mstNm;
	}
	public void setMstNm(String mstNm) {
		this.mstNm = mstNm;
	}
	public String getDtlNm() {
		return dtlNm;
	}
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}
	public String getpMsgId() {
		return pMsgId;
	}
	public void setpMsgId(String pMsgId) {
		this.pMsgId = pMsgId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "CodeVO [mstId=" + mstId + ", dtlId=" + dtlId + ", mstNm=" + mstNm + ", dtlNm=" + dtlNm + ", pMsgId="
				+ pMsgId + ", seq=" + seq + ", useYn=" + useYn + ", regId=" + regId + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}

	
	
	
}
