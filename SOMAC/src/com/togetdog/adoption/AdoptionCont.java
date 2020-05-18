package com.togetdog.adoption;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.togetdog.api.ApiExplorer;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.StringUtil;
import com.togetdog.desertion.DesertionService;
import com.togetdog.desertion.DesertionVO;

/**
 * Servlet implementation class BoardCont /DaoWEB/board/board.do ->
 * /board/board.do
 */
@WebServlet(description = "유기동물정보", urlPatterns = { "/view/adop.do" })
public class AdoptionCont extends HttpServlet implements ContHandler {
	private static final long serialVersionUID = 1L;
	/** BoardService객체 */
	private AdoptionService service;
	ApiExplorer search;
	DesertionService dservice;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdoptionCont() {
		service = new AdoptionService();
		search = new ApiExplorer();
		dservice = new DesertionService();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		LOG.debug("doGet------");
		serviceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("1. doPost------");
		serviceHandler(request, response);
	}

	@Override
	public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1.work_div: 작업구분
		// 2.request Encoding: utf-8
		// 3.기능별 분해
		// 거래분기:
		// do_update:수정
		// do_delete:삭제
		// do_insert:등록
		// do_retrieve:목록조회
		// do_selectOne:단건조회

		req.setCharacterEncoding("utf-8");// request의 인코딩: utf-8
		String workDiv = StringUtil.nvl(req.getParameter("work_div"));// null -> ""
		LOG.debug("2====================");
		LOG.debug("2=workDiv=" + workDiv);
		LOG.debug("2====================");
		switch (workDiv) {// 거래분기
		case "do_update":// 수정
			this.doUpdate(req, res);
			break;

		case "do_delete":// 삭제
			this.doDel(req, res);
			break;
		case "do_selectOne":// 단건조회,단건조회 화면으로 이동
			this.doSelectOne(req, res);
			break;

		case "move_to_save":// 등록화면으로 이동
			doMoveToSave(req, res);
			break;

		case "do_insert":// 단건 등록
			this.doInsert(req, res);
			break;

		case "tdo_insert":// 단건 등록
			this.tdoInsert(req, res);
			break;

		case "ddo_insert":// 단건 등록
			this.ddoInsert(req, res);
			break;

		case "cdo_insert":// 단건 등록
			this.cdoInsert(req, res);
			break;

		case "edo_insert":// 단건 등록
			this.edoInsert(req, res);
			break;

		case "do_retrieve":// 목록조회
			this.doRetrieve(req, res);
			break;
		default:
			LOG.debug("====================");
			LOG.debug("=작업구분을 확인 하세요.workDiv=" + workDiv);
			LOG.debug("====================");
			break;
		}
	}

	/**
	 * 
	 * Method Name:doMoveToSave 작성일: 2020. 2. 26. 작성자: sist 설명: 등록화면으로 이동
	 * 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doMoveToSave(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/*
		 * RequestDispatcher dispatcher =
		 * req.getRequestDispatcher("/view/desertion_list.jsp"); dispatcher.forward(req,
		 * res);
		 */

	}

	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	/**
	 * 단건조회
	 */
	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
	
	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doInsert=");
		LOG.debug("3====================");
		AdoptionVO inVO = new AdoptionVO();// param
		// 1.param read
		// 2.param BoardVO
		// 3.service 메소드 호출
		// 제목

		String desertionNo = StringUtil.nvl(req.getParameter("desertion_no"), "");
		// 세션데이터 받아옴
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		String familyCnt = StringUtil.nvl(req.getParameter("family_cnt"), "");
		String experienceYn = StringUtil.nvl(req.getParameter("experience_yn"), "");
		// String applyState = StringUtil.nvl(req.getParameter("apply_state"),"");
		String applyReason = StringUtil.nvl(req.getParameter("apply_reason"), "");
		String regDt = StringUtil.nvl(req.getParameter("reg_dt"), "");
		String aregDt = StringUtil.nvl(req.getParameter("areg_dt"), "");

		// 입력받은 분양신청 데이터를 inVO에 담는다.
		inVO.setDesertionNo(desertionNo);
		inVO.setMemberId(memberId);
		inVO.setFamilyCnt(familyCnt);
		inVO.setExperienceYn(experienceYn);
		inVO.setApplyState("P");
		inVO.setApplyReason(applyReason);
		LOG.debug("invo" + inVO);

		//
		ApiExplorer search = new ApiExplorer();
		DesertionVO outVO = new DesertionVO();
		DesertionService dservice = new DesertionService(); // 검색 결과 VO
		outVO = search.areaSelectOne(desertionNo);

		int dflag = dservice.doInsert(outVO);

		if (dflag == 1) {
			LOG.debug("dflag=" + dflag);
			LOG.debug("ouvvzxcvzxcv" + outVO);

		}

		// 현재 분양 중 및 완료인 상태를 확인하여 분양 신청이 가능하도록 진행.
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = desertionNo;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		int flag = 0;
		List<AdoptionVO> listTest = (List<AdoptionVO>) this.service.doRetrieve(sVO);
		String applyNo = null;
		for (int i = 0; i < listTest.size(); i++) {

			LOG.debug("아푸니카:" + listTest.get(i));

		}

		if (listTest.isEmpty()) {
			flag = 1;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S")) {
					flag = 4;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 3;
					applyNo = listTest.get(i).getApplyNo();
					LOG.debug(applyNo);
				} else {
					flag = 1;
					LOG.debug("나머지경우" + listTest.get(i));
				}
			}
		}

		if (flag == 1) {
			flag = this.service.doInsert(inVO);
		} else if (flag == 3) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yy/mm/dd");
			Date time = new Date();
			String time1 = format1.format(time);

			AdoptionVO upVO = new AdoptionVO();
			upVO.setApplyNo(applyNo);
			upVO.setApplyState("P");
			upVO.setMemberId(memberId);
			upVO.setFamilyCnt(familyCnt);
			upVO.setExperienceYn(experienceYn);
			upVO.setApplyReason(applyReason);
			upVO.setRegDt(time1);
			LOG.debug("이걸루 수정"+upVO);
			flag = this.service.adopDoUpdate(upVO);

		} else {

			LOG.debug("읍읍읍읍으ㅡㅇ브읍읍으븡브읍다");
		}

		// 응답처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		String msg = "";
		Gson gson = new Gson();
		if (1 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (2 == flag) {
			msg = "이미 분양이 진행 중입니다.";
		} else if (3 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (4 == flag) {
			msg = "이미 분양이 완료되었습니다.";
		} else {
			msg = "분양신청이 등록 실패되었습니다.";
		}

		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);

	}

	public void tdoInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doInsert=");
		LOG.debug("3====================");
		AdoptionVO inVO = new AdoptionVO();// param
		// 1.param read
		// 2.param BoardVO
		// 3.service 메소드 호출
		// 제목

		String desertionNo = StringUtil.nvl(req.getParameter("desertion_no"), "");
		// 세션데이터 받아옴
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		String familyCnt = StringUtil.nvl(req.getParameter("family_cnt"), "");
		String experienceYn = StringUtil.nvl(req.getParameter("experience_yn"), "");
		// String applyState = StringUtil.nvl(req.getParameter("apply_state"),"");
		String applyReason = StringUtil.nvl(req.getParameter("apply_reason"), "");
		String regDt = StringUtil.nvl(req.getParameter("reg_dt"), "");
		String aregDt = StringUtil.nvl(req.getParameter("areg_dt"), "");

		// 입력받은 분양신청 데이터를 inVO에 담는다.
		inVO.setDesertionNo(desertionNo);
		inVO.setMemberId(memberId);
		inVO.setFamilyCnt(familyCnt);
		inVO.setExperienceYn(experienceYn);
		inVO.setApplyState("P");
		inVO.setApplyReason(applyReason);
		LOG.debug("invo" + inVO);

		//
		ApiExplorer search = new ApiExplorer();
		DesertionVO outVO = new DesertionVO();
		DesertionService dservice = new DesertionService(); // 검색 결과 VO
		outVO = search.totalSelectOne(desertionNo);

		int dflag = dservice.doInsert(outVO);

		if (dflag == 1) {
			LOG.debug("dflag=" + dflag);
			LOG.debug("ouvvzxcvzxcv" + outVO);

		}

		// 현재 분양 중 및 완료인 상태를 확인하여 분양 신청이 가능하도록 진행.
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = desertionNo;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		int flag = 0;
		List<AdoptionVO> listTest = (List<AdoptionVO>) this.service.doRetrieve(sVO);
		String applyNo = null;
		for (int i = 0; i < listTest.size(); i++) {

			LOG.debug("아푸니카:" + listTest.get(i));

		}

		if (listTest.isEmpty()) {
			flag = 1;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S")) {
					flag = 4;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 3;
					applyNo = listTest.get(i).getApplyNo();
					LOG.debug(applyNo);
				} else {
					flag = 1;
					LOG.debug("나머지경우" + listTest.get(i));
				}
			}
		}

		if (flag == 1) {
			flag = this.service.doInsert(inVO);
		} else if (flag == 3) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yy/mm/dd");
			Date time = new Date();
			String time1 = format1.format(time);

			AdoptionVO upVO = new AdoptionVO();
			upVO.setApplyNo(applyNo);
			upVO.setApplyState("P");
			upVO.setMemberId(memberId);
			upVO.setFamilyCnt(familyCnt);
			upVO.setExperienceYn(experienceYn);
			upVO.setApplyReason(applyReason);
			upVO.setRegDt(time1);
			LOG.debug("이걸루 수정"+upVO);
			flag = this.service.adopDoUpdate(upVO);

		} else {

			LOG.debug("읍읍읍읍으ㅡㅇ브읍읍으븡브읍다");
		}

		// 응답처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		String msg = "";
		Gson gson = new Gson();
		if (1 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (2 == flag) {
			msg = "이미 분양이 진행 중입니다.";
		} else if (3 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (4 == flag) {
			msg = "이미 분양이 완료되었습니다.";
		} else {
			msg = "분양신청이 등록 실패되었습니다.";
		}

		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);

	}

	public void ddoInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doInsert=");
		LOG.debug("3====================");
		AdoptionVO inVO = new AdoptionVO();// param
		// 1.param read
		// 2.param BoardVO
		// 3.service 메소드 호출
		// 제목

		String desertionNo = StringUtil.nvl(req.getParameter("desertion_no"), "");
		// 세션데이터 받아옴
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		String familyCnt = StringUtil.nvl(req.getParameter("family_cnt"), "");
		String experienceYn = StringUtil.nvl(req.getParameter("experience_yn"), "");
		// String applyState = StringUtil.nvl(req.getParameter("apply_state"),"");
		String applyReason = StringUtil.nvl(req.getParameter("apply_reason"), "");
		String regDt = StringUtil.nvl(req.getParameter("reg_dt"), "");
		String aregDt = StringUtil.nvl(req.getParameter("areg_dt"), "");

		// 입력받은 분양신청 데이터를 inVO에 담는다.
		inVO.setDesertionNo(desertionNo);
		inVO.setMemberId(memberId);
		inVO.setFamilyCnt(familyCnt);
		inVO.setExperienceYn(experienceYn);
		inVO.setApplyState("P");
		inVO.setApplyReason(applyReason);
		LOG.debug("invo" + inVO);

		//
		ApiExplorer search = new ApiExplorer();
		DesertionVO outVO = new DesertionVO();
		DesertionService dservice = new DesertionService(); // 검색 결과 VO
		outVO = search.dogSelectOne(desertionNo);

		int dflag = dservice.doInsert(outVO);

		if (dflag == 1) {
			LOG.debug("dflag=" + dflag);
			LOG.debug("ouvvzxcvzxcv" + outVO);

		}

		// 현재 분양 중 및 완료인 상태를 확인하여 분양 신청이 가능하도록 진행.
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = desertionNo;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);
		
		int flag = 0;
		List<AdoptionVO> listTest = (List<AdoptionVO>) this.service.doRetrieve(sVO);
		String applyNo = null;
		for (int i = 0; i < listTest.size(); i++) {

			LOG.debug("아푸니카:" + listTest.get(i));

		}

		if (listTest.isEmpty()) {
			flag = 1;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S")) {
					flag = 4;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 3;
					applyNo = listTest.get(i).getApplyNo();
					LOG.debug(applyNo);
				} else {
					flag = 1;
					LOG.debug("나머지경우" + listTest.get(i));
				}
			}
		}

		if (flag == 1) {
			flag = this.service.doInsert(inVO);
		} else if (flag == 3) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yy/mm/dd");
			Date time = new Date();
			String time1 = format1.format(time);

			AdoptionVO upVO = new AdoptionVO();
			upVO.setApplyNo(applyNo);
			upVO.setApplyState("P");
			upVO.setMemberId(memberId);
			upVO.setFamilyCnt(familyCnt);
			upVO.setExperienceYn(experienceYn);
			upVO.setApplyReason(applyReason);
			upVO.setRegDt(time1);
			LOG.debug("이걸루 수정"+upVO);
			flag = this.service.adopDoUpdate(upVO);

		} else {

			LOG.debug("읍읍읍읍으ㅡㅇ브읍읍으븡브읍다");
		}

		// 응답처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		String msg = "";
		Gson gson = new Gson();
		if (1 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (2 == flag) {
			msg = "이미 분양이 진행 중입니다.";
		} else if (3 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (4 == flag) {
			msg = "이미 분양이 완료되었습니다.";
		} else {
			msg = "분양신청이 등록 실패되었습니다.";
		}

		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);

	}

	public void cdoInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doInsert=");
		LOG.debug("3====================");
		AdoptionVO inVO = new AdoptionVO();// param
		// 1.param read
		// 2.param BoardVO
		// 3.service 메소드 호출
		// 제목

		String desertionNo = StringUtil.nvl(req.getParameter("desertion_no"), "");
		// 세션데이터 받아옴
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		String familyCnt = StringUtil.nvl(req.getParameter("family_cnt"), "");
		String experienceYn = StringUtil.nvl(req.getParameter("experience_yn"), "");
		// String applyState = StringUtil.nvl(req.getParameter("apply_state"),"");
		String applyReason = StringUtil.nvl(req.getParameter("apply_reason"), "");
		String regDt = StringUtil.nvl(req.getParameter("reg_dt"), "");
		String aregDt = StringUtil.nvl(req.getParameter("areg_dt"), "");

		// 입력받은 분양신청 데이터를 inVO에 담는다.
		inVO.setDesertionNo(desertionNo);
		inVO.setMemberId(memberId);
		inVO.setFamilyCnt(familyCnt);
		inVO.setExperienceYn(experienceYn);
		inVO.setApplyState("P");
		inVO.setApplyReason(applyReason);
		LOG.debug("invo" + inVO);

		//
		ApiExplorer search = new ApiExplorer();
		DesertionVO outVO = new DesertionVO();
		DesertionService dservice = new DesertionService(); // 검색 결과 VO
		outVO = search.catSelectOne(desertionNo);

		int dflag = dservice.doInsert(outVO);

		if (dflag == 1) {
			LOG.debug("dflag=" + dflag);
			LOG.debug("ouvvzxcvzxcv" + outVO);

		}

		// 현재 분양 중 및 완료인 상태를 확인하여 분양 신청이 가능하도록 진행.
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = desertionNo;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		int flag = 0;
		List<AdoptionVO> listTest = (List<AdoptionVO>) this.service.doRetrieve(sVO);
		String applyNo = null;
		for (int i = 0; i < listTest.size(); i++) {

			LOG.debug("아푸니카:" + listTest.get(i));

		}

		if (listTest.isEmpty()) {
			flag = 1;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S")) {
					flag = 4;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 3;
					applyNo = listTest.get(i).getApplyNo();
					LOG.debug(applyNo);
				} else {
					flag = 1;
					LOG.debug("나머지경우" + listTest.get(i));
				}
			}
		}

		if (flag == 1) {
			flag = this.service.doInsert(inVO);
		} else if (flag == 3) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yy/mm/dd");
			Date time = new Date();
			String time1 = format1.format(time);

			AdoptionVO upVO = new AdoptionVO();
			upVO.setApplyNo(applyNo);
			upVO.setApplyState("P");
			upVO.setMemberId(memberId);
			upVO.setFamilyCnt(familyCnt);
			upVO.setExperienceYn(experienceYn);
			upVO.setApplyReason(applyReason);
			upVO.setRegDt(time1);
			LOG.debug("이걸루 수정"+upVO);
			flag = this.service.adopDoUpdate(upVO);

		} else {

			LOG.debug("읍읍읍읍으ㅡㅇ브읍읍으븡브읍다");
		}

		// 응답처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		String msg = "";
		Gson gson = new Gson();
		if (1 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (2 == flag) {
			msg = "이미 분양이 진행 중입니다.";
		} else if (3 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (4 == flag) {
			msg = "이미 분양이 완료되었습니다.";
		} else {
			msg = "분양신청이 등록 실패되었습니다.";
		}

		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);

	}

	public void edoInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doInsert=");
		LOG.debug("3====================");
		AdoptionVO inVO = new AdoptionVO();// param
		// 1.param read
		// 2.param BoardVO
		// 3.service 메소드 호출
		// 제목

		String desertionNo = StringUtil.nvl(req.getParameter("desertion_no"), "");
		// 세션데이터 받아옴
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		String familyCnt = StringUtil.nvl(req.getParameter("family_cnt"), "");
		String experienceYn = StringUtil.nvl(req.getParameter("experience_yn"), "");
		// String applyState = StringUtil.nvl(req.getParameter("apply_state"),"");
		String applyReason = StringUtil.nvl(req.getParameter("apply_reason"), "");
		String regDt = StringUtil.nvl(req.getParameter("reg_dt"), "");
		String aregDt = StringUtil.nvl(req.getParameter("areg_dt"), "");

		// 입력받은 분양신청 데이터를 inVO에 담는다.
		inVO.setDesertionNo(desertionNo);
		inVO.setMemberId(memberId);
		inVO.setFamilyCnt(familyCnt);
		inVO.setExperienceYn(experienceYn);
		inVO.setApplyState("P");
		inVO.setApplyReason(applyReason);
		LOG.debug("invo" + inVO);

		//
		ApiExplorer search = new ApiExplorer();
		DesertionVO outVO = new DesertionVO();
		DesertionService dservice = new DesertionService(); // 검색 결과 VO
		outVO = search.etcSelectOne(desertionNo);

		int dflag = dservice.doInsert(outVO);

		if (dflag == 1) {
			LOG.debug("dflag=" + dflag);
			LOG.debug("ouvvzxcvzxcv" + outVO);

		}

		// 현재 분양 중 및 완료인 상태를 확인하여 분양 신청이 가능하도록 진행.
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = desertionNo;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		int flag = 0;
		List<AdoptionVO> listTest = (List<AdoptionVO>) this.service.doRetrieve(sVO);
		String applyNo = null;
		for (int i = 0; i < listTest.size(); i++) {

			LOG.debug("아푸니카:" + listTest.get(i));

		}

		if (listTest.isEmpty()) {
			flag = 1;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S")) {
					flag = 4;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
					LOG.debug(listTest.get(i));
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 3;
					applyNo = listTest.get(i).getApplyNo();
					LOG.debug(applyNo);
				} else {
					flag = 1;
					LOG.debug("나머지경우" + listTest.get(i));
				}
			}
		}

		if (flag == 1) {
			flag = this.service.doInsert(inVO);
		} else if (flag == 3) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yy/mm/dd");
			Date time = new Date();
			String time1 = format1.format(time);

			AdoptionVO upVO = new AdoptionVO();
			upVO.setApplyNo(applyNo);
			upVO.setApplyState("P");
			upVO.setMemberId(memberId);
			upVO.setFamilyCnt(familyCnt);
			upVO.setExperienceYn(experienceYn);
			upVO.setApplyReason(applyReason);
			upVO.setRegDt(time1);
			LOG.debug("이걸루 수정"+upVO);
			flag = this.service.adopDoUpdate(upVO);

		} else {

			LOG.debug("읍읍읍읍으ㅡㅇ브읍읍으븡브읍다");
		}

		// 응답처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		String msg = "";
		Gson gson = new Gson();
		if (1 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (2 == flag) {
			msg = "이미 분양이 진행 중입니다.";
		} else if (3 == flag) {
			msg = "분양신청이 등록이 완료되었습니다.";
		} else if (4 == flag) {
			msg = "이미 분양이 완료되었습니다.";
		} else {
			msg = "분양신청이 등록 실패되었습니다.";
		}

		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);

		
	}

	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3====================");
		LOG.debug("3=doUpdate=");
		LOG.debug("3====================");

	}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("=======================");
		LOG.debug("=doDel=");
		LOG.debug("=======================");

	}

}
