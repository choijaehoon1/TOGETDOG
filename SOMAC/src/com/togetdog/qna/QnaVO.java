
/**
 *<pre>
 * com.hr.board
 * Class Name : QnaVO.java
 * Description : 질문게시판 VO
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-03           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-03 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
 

package com.togetdog.qna;
import com.togetdog.cmn.DTO;

/**
 * @author sist
 *
 */
public class QnaVO extends DTO {

	private String    qna_no    ;/**게시글 번호 */
	private String memberId    ;/**회원아이디 */
	private String title ;/**제목 */
	private String regDt  ;/**작성일 */
	private String modDt   ;/**수정일 */
	private int count  ;/**조회수 */
	private String contents   ;/**내용 */
	private String ableYn; /**게시여부 */
	private String saveNm  ;/**파일저장이름 */
	private String imgPath ;/**파일주소 */
	private String ext		;/**파일확장자 */
	
	
	public QnaVO() {}


	public QnaVO(String qna_no, String memberId, String title, String regDt, String modDt, int count, String contents,
			String ableYn, String saveNm, String imgPath, String ext) {
		super();
		this.qna_no = qna_no;
		this.memberId = memberId;
		this.title = title;
		this.regDt = regDt;
		this.modDt = modDt;
		this.count = count;
		this.contents = contents;
		this.ableYn = ableYn;
		this.saveNm = saveNm;
		this.imgPath = imgPath;
		this.ext = ext;
	}


	public String getQna_no() {
		return qna_no;
	}


	public void setQna_no(String qna_no) {
		this.qna_no = qna_no;
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


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
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
		return "QnaVO [qna_no=" + qna_no + ", memberId=" + memberId + ", title=" + title + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", count=" + count + ", contents=" + contents + ", ableYn=" + ableYn
				+ ", saveNm=" + saveNm + ", imgPath=" + imgPath + ", ext=" + ext + ", toString()=" + super.toString()
				+ "]";
	}

	

	

}


