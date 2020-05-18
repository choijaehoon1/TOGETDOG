/**
 *<pre>
 * com.JW.board
 * Class Name : MemberVO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-11 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.member;

import com.togetdog.cmn.DTO;

/**
 * @author SIST
 *
 */
public class MemberVO extends DTO {
	private String memberId;
	private String password ;
	private String name     ;
	private String phone  ;
	private String email    ;
	private String author    ;
	private String regId     ;
	private String regDt   ;
	private String modId   ;
	private String modDt   ;
	
	public MemberVO() {}

	public MemberVO(String memberId, String password, String name, String phone, String email, String author,
			String regId, String regDt, String modId, String modDt) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.author = author;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
		return "MemberVO [memberId=" + memberId + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", author=" + author + ", regId=" + regId + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
	
}
