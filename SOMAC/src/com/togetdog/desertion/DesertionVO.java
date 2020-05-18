package com.togetdog.desertion;

import com.togetdog.cmn.DTO;

public class DesertionVO extends DTO {
	private String desertionNo;
	private String processState;
	private String fileName;
	private String happenDt;
	private String happenPlace;
	private String kindCd;
	private String colorCd;
	private String age;
	private String weight;
	private String noticeNo;
	private String noticeSdt;
	private String noticeEdt;
	private String popFile;
	private String sexCd;
	private String neuterYn;
	private String specialMark;
	private String careNm;
	private String careTel;
	private String careAddr;
	private String orgNm;
	private String chargeNm;
	private String officeTel;

	public DesertionVO() {

	}

	public DesertionVO(String desertionNo, String processState, String fileName, String happenDt, String happenPlace,
			String kindCd, String colorCd, String age, String weight, String noticeNo, String noticeSdt,
			String noticeEdt, String popFile, String sexCd, String neuterYn, String specialMark, String careNm,
			String careTel, String careAddr, String orgNm, String chargeNm, String officeTel) {
		super();
		this.desertionNo = desertionNo;
		this.processState = processState;
		this.fileName = fileName;
		this.happenDt = happenDt;
		this.happenPlace = happenPlace;
		this.kindCd = kindCd;
		this.colorCd = colorCd;
		this.age = age;
		this.weight = weight;
		this.noticeNo = noticeNo;
		this.noticeSdt = noticeSdt;
		this.noticeEdt = noticeEdt;
		this.popFile = popFile;
		this.sexCd = sexCd;
		this.neuterYn = neuterYn;
		this.specialMark = specialMark;
		this.careNm = careNm;
		this.careTel = careTel;
		this.careAddr = careAddr;
		this.orgNm = orgNm;
		this.chargeNm = chargeNm;
		this.officeTel = officeTel;
	}

	public String getDesertionNo() {
		return desertionNo;
	}

	public void setDesertionNo(String desertionNo) {
		this.desertionNo = desertionNo;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHappenDt() {
		return happenDt;
	}

	public void setHappenDt(String happenDt) {
		this.happenDt = happenDt;
	}

	public String getHappenPlace() {
		return happenPlace;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}

	public String getKindCd() {
		return kindCd;
	}

	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}

	public String getColorCd() {
		return colorCd;
	}

	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSdt() {
		return noticeSdt;
	}

	public void setNoticeSdt(String noticeSdt) {
		this.noticeSdt = noticeSdt;
	}

	public String getNoticeEdt() {
		return noticeEdt;
	}

	public void setNoticeEdt(String noticeEdt) {
		this.noticeEdt = noticeEdt;
	}

	public String getPopFile() {
		return popFile;
	}

	public void setPopFile(String popFile) {
		this.popFile = popFile;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getNeuterYn() {
		return neuterYn;
	}

	public void setNeuterYn(String neuterYn) {
		this.neuterYn = neuterYn;
	}

	public String getSpecialMark() {
		return specialMark;
	}

	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	public String getCareNm() {
		return careNm;
	}

	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}

	public String getCareTel() {
		return careTel;
	}

	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}

	public String getCareAddr() {
		return careAddr;
	}

	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getChargeNm() {
		return chargeNm;
	}

	public void setChargeNm(String chargeNm) {
		this.chargeNm = chargeNm;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Override
	public String toString() {
		return "DesertionVO [desertionNo=" + desertionNo + ", processState=" + processState + ", fileName=" + fileName
				+ ", happenDt=" + happenDt + ", happenPlace=" + happenPlace + ", kindCd=" + kindCd + ", colorCd="
				+ colorCd + ", age=" + age + ", weight=" + weight + ", noticeNo=" + noticeNo + ", noticeSdt="
				+ noticeSdt + ", noticeEdt=" + noticeEdt + ", popFile=" + popFile + ", sexCd=" + sexCd + ", neuterYn="
				+ neuterYn + ", specialMark=" + specialMark + ", careNm=" + careNm + ", careTel=" + careTel
				+ ", careAddr=" + careAddr + ", orgNm=" + orgNm + ", chargeNm=" + chargeNm + ", officeTel=" + officeTel
				+ "]";
	}

}