/**
 *<pre>
 * com.hr.cmn
 * Class Name : SearchVO.java
 * Description : 검색VO
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-05           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-05 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
 
package com.togetdog.cmn;

/**
 * @author sist
 *
 */
public class SearchVO extends DTO {
	private String searchDiv; //검색구분
	private String searchWord;//검색어
	
	public SearchVO() {}
	
	public SearchVO(String searchDiv, String searchWord) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "SearchVO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	
}

















