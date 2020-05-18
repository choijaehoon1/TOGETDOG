
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
 

package com.togetdog.filemng;
import com.togetdog.cmn.DTO;

/**
 * @author sist
 *
 */
public class FileVO extends DTO {

	private String fileNo;		/**파일번호 */
	private String orgNm;		/**원본파일명 */
	private String saveNm;		/**파일명 */
	private String imgPath;	/**이미지경로 */
	private int	   fileSize;	/**파일사이즈 */
	private String ext;			/**확장자 */
	private String regId; 		/**회원 아이디*/
	private String regDt;		/**등록일시 */

	
	public FileVO() {}


	public FileVO(String fileNo, String orgNm, String saveNm, String imgPath, int fileSize, String ext, String regId,
			String regDt) {
		super();
		this.fileNo = fileNo;
		this.orgNm = orgNm;
		this.saveNm = saveNm;
		this.imgPath = imgPath;
		this.fileSize = fileSize;
		this.ext = ext;
		this.regId = regId;
		this.regDt = regDt;
	}


	public String getFileNo() {
		return fileNo;
	}


	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public String getOrgNm() {
		return orgNm;
	}


	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
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


	public int getFileSize() {
		return fileSize;
	}


	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
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


	@Override
	public String toString() {
		return "FileVO [fileNo=" + fileNo + ", orgNm=" + orgNm + ", saveNm=" + saveNm + ", imgPath=" + imgPath
				+ ", fileSize=" + fileSize + ", ext=" + ext + ", regId=" + regId + ", regDt=" + regDt + "]";
	}





	
}


