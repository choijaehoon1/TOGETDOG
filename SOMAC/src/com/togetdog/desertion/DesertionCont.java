package com.togetdog.desertion;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.togetdog.adoption.AdoptionService;
import com.togetdog.adoption.AdoptionVO;
import com.togetdog.api.ApiExplorer;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.StringUtil;
import com.togetdog.code.CodeService;
import com.togetdog.code.CodeVO;
import com.togetdog.member.MemberVO;

/**
 * Servlet implementation class BoardCont /DaoWEB/board/board.do ->
 * /board/board.do
 */
@WebServlet(description = "유기동물정보", urlPatterns = { "/view/info.do" })
public class DesertionCont extends HttpServlet implements ContHandler {
	private static final long serialVersionUID = 1L;
	// static String search_area ;
	/** BoardService객체 */
	ApiExplorer search;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DesertionCont() {
		search = new ApiExplorer();

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

		case "tdo_selectOne":// 단건조회,단건조회 화면으로 이동
			this.tDoSelectOne(req, res);
			break;

		case "ddo_selectOne":// 단건조회,단건조회 화면으로 이동
			this.dDoSelectOne(req, res);
			break;
		case "cdo_selectOne":// 단건조회,단건조회 화면으로 이동
			this.cDoSelectOne(req, res);
			break;
		case "edo_selectOne":// 단건조회,단건조회 화면으로 이동
			this.eDoSelectOne(req, res);
			break;

		case "move_to_save":// 등록화면으로 이동
			doMoveToSave(req, res);
			break;

		case "do_insert":// 단건 등록
			this.doInsert(req, res);
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
		// 총글수

		String search_area = StringUtil.nvl(req.getParameter("search_area"));

		search = new ApiExplorer();

		/*
		 * 축종코드 - 개: 417000 - 고양이 : 422400 - 기타 : 429900
		 */

		List<DesertionVO> tlist = search.areaRetrieve(1, 12, search_area);
		// List<DesertionVO> dlist = search.choiceRetrieve(1, 6, search_kind,
		// search_area);
		// List<DesertionVO> clist = search.choiceRetrieve(1, 6, search_kind,
		// search_area);
		// List<DesertionVO> elist = search.choiceRetrieve(1, 6, search_kind,
		// search_area);

		req.setAttribute("tlist", tlist);// 목록
		// req.setAttribute("dlist", dlist);//목록
		// req.setAttribute("clist", clist);//목록
		// req.setAttribute("elist", elist);//목록

		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_list.jsp");
		dispatcher.forward(req, res);

	}

	/**
	 * 단건조회 원본 백업
	 */
	/*
	 * @Override public void doSelectOne(HttpServletRequest req, HttpServletResponse
	 * res) throws ServletException, IOException {
	 * 
	 * DesertionVO inVO=new DesertionVO(); LOG.debug("*** doSelectOne"); String seq
	 * = StringUtil.nvl(req.getParameter("seq")); LOG.debug("seq="+seq);
	 * LOG.debug("seq:"+seq);
	 * 
	 * String dno= seq; DesertionVO outVO = (DesertionVO) search.areaSelectOne(dno);
	 * req.setAttribute("vo", outVO);
	 * 
	 * LOG.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★vo:"+outVO); RequestDispatcher
	 * dispatcher = req.getRequestDispatcher("/view/desertion_detail.jsp");
	 * dispatcher.forward(req, res);
	 * 
	 * }
	 */

	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		DesertionVO inVO = new DesertionVO();
		LOG.debug("*** doSelectOne");
		String seq = StringUtil.nvl(req.getParameter("seq"));
		LOG.debug("seq=" + seq);
		LOG.debug("seq:" + seq);
		
		// 키값
		String dno = seq;

		// 세션
		HttpSession session = req.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("user");
		String memberId = mvo.getMemberId();

		// 디테일 원본정보
		DesertionVO outVO = (DesertionVO) search.areaSelectOne(dno);
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = dno;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		// 유효검사
		int flag = 0;
		AdoptionService aservice = new AdoptionService();
		List<AdoptionVO> listTest = (List<AdoptionVO>) aservice.doRetrieve(sVO);

		for (int i = 0; i < listTest.size(); i++) {
			LOG.debug("아푸니카:" + listTest.get(i));
		}

		if (listTest.isEmpty()) {
			flag = 2;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S") && listTest.get(i).getMemberId().equals(memberId)) {
					flag = 1;
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 2;
				} else {
					flag = 2;
				}
			}
		}
		
		
		String hiddenFlag ="";
		if (flag == 1) {
			hiddenFlag="block";

		} else if (flag == 2) {
			hiddenFlag="none";
			
		} else {
			hiddenFlag="none";
		}
		
		req.setAttribute("hiddenFlag", hiddenFlag);
		req.setAttribute("vo", outVO);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_detail.jsp");
		dispatcher.forward(req, res);

	}

	public void dDoSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LOG.debug("*** ddoSelectOne");
		String dseq = StringUtil.nvl(req.getParameter("dseq"));
		LOG.debug("dseq※※※※※※※※※※※※※※※※※※=" + dseq);


		
		// 세션
		HttpSession session = req.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("user");
		String memberId = mvo.getMemberId();

		// 디테일 원본정보
		DesertionVO outVO = (DesertionVO) search.dogSelectOne(dseq);
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = dseq;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		// 유효검사
		int flag = 0;
		AdoptionService aservice = new AdoptionService();
		List<AdoptionVO> listTest = (List<AdoptionVO>) aservice.doRetrieve(sVO);

		for (int i = 0; i < listTest.size(); i++) {
			LOG.debug("아푸니카:" + listTest.get(i));
		}

		if (listTest.isEmpty()) {
			flag = 2;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S") && listTest.get(i).getMemberId().equals(memberId)) {
					flag = 1;
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 2;
				} else {
					flag = 2;
				}
			}
		}
		
		
		String hiddenFlag ="";
		if (flag == 1) {
			hiddenFlag="block";

		} else if (flag == 2) {
			hiddenFlag="none";
			
		} else {
			hiddenFlag="none";
		}
		
		req.setAttribute("hiddenFlag", hiddenFlag);
		req.setAttribute("dvo", outVO);
		req.setAttribute("dseq", dseq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_ddetail.jsp");
		dispatcher.forward(req, res);

	}

	public void tDoSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LOG.debug("*** tdoSelectOne");
		String tseq = StringUtil.nvl(req.getParameter("tseq"));
		LOG.debug("tseq=" + tseq);

		// 세션
				HttpSession session = req.getSession();
				MemberVO mvo = (MemberVO) session.getAttribute("user");
				String memberId = mvo.getMemberId();

				// 디테일 원본정보
				DesertionVO outVO = (DesertionVO) search.totalSelectOne(tseq);
				SearchVO sVO = new SearchVO();
				String searchDiv = "30";
				String searchWord = tseq;

				sVO.setSearchDiv(searchDiv);
				sVO.setSearchWord(searchWord);
				sVO.setPageNum(1);
				sVO.setPageSize(10);

				// 유효검사
				int flag = 0;
				AdoptionService aservice = new AdoptionService();
				List<AdoptionVO> listTest = (List<AdoptionVO>) aservice.doRetrieve(sVO);

				for (int i = 0; i < listTest.size(); i++) {
					LOG.debug("아푸니카:" + listTest.get(i));
				}

				if (listTest.isEmpty()) {
					flag = 2;

				} else {

					for (int i = 0; i < listTest.size(); i++) {
						if (listTest.get(i).getApplyState().equals("S") && listTest.get(i).getMemberId().equals(memberId)) {
							flag = 1;
						} else if (listTest.get(i).getApplyState().equals("P")) {
							flag = 2;
						} else if (listTest.get(i).getApplyState().equals("R")) {
							flag = 2;
						} else {
							flag = 2;
						}
					}
				}
				
				
				String hiddenFlag ="";
				if (flag == 1) {
					hiddenFlag="block";
				} else if (flag == 2) {
					hiddenFlag="none";
				} else {
					hiddenFlag="none";
				}
				
				req.setAttribute("hiddenFlag", hiddenFlag);
				req.setAttribute("tvo", outVO);
				req.setAttribute("tseq", tseq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_tdetail.jsp");
		dispatcher.forward(req, res);

	}

	public void cDoSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LOG.debug("*** cdoSelectOne");
		String cseq = StringUtil.nvl(req.getParameter("cseq"));

		// 세션
		HttpSession session = req.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("user");
		String memberId = mvo.getMemberId();

		// 디테일 원본정보
		DesertionVO outVO = (DesertionVO) search.catSelectOne(cseq);
		SearchVO sVO = new SearchVO();
		String searchDiv = "30";
		String searchWord = cseq;

		sVO.setSearchDiv(searchDiv);
		sVO.setSearchWord(searchWord);
		sVO.setPageNum(1);
		sVO.setPageSize(10);

		// 유효검사
		int flag = 0;
		AdoptionService aservice = new AdoptionService();
		List<AdoptionVO> listTest = (List<AdoptionVO>) aservice.doRetrieve(sVO);

		for (int i = 0; i < listTest.size(); i++) {
			LOG.debug("아푸니카:" + listTest.get(i));
		}

		if (listTest.isEmpty()) {
			flag = 2;

		} else {

			for (int i = 0; i < listTest.size(); i++) {
				if (listTest.get(i).getApplyState().equals("S") && listTest.get(i).getMemberId().equals(memberId)) {
					flag = 1;
				} else if (listTest.get(i).getApplyState().equals("P")) {
					flag = 2;
				} else if (listTest.get(i).getApplyState().equals("R")) {
					flag = 2;
				} else {
					flag = 2;
				}
			}
		}
		
		
		String hiddenFlag ="";
		if (flag == 1) {
			hiddenFlag="block";
		} else if (flag == 2) {
			hiddenFlag="none";
		} else {
			hiddenFlag="none";
		}
		
		req.setAttribute("hiddenFlag", hiddenFlag);
		req.setAttribute("cvo", outVO);
		req.setAttribute("cseq", cseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_cdetail.jsp");
		dispatcher.forward(req, res);

	}

	public void eDoSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LOG.debug("***edoSelectOne");
		String eseq = StringUtil.nvl(req.getParameter("eseq"));
		LOG.debug("■■■■■■■■■■■■■■■■■eseq■■■■■■■■■■■■■■■■:" + eseq);

		// 세션
				HttpSession session = req.getSession();
				MemberVO mvo = (MemberVO) session.getAttribute("user");
				String memberId = mvo.getMemberId();

				// 디테일 원본정보
				DesertionVO outVO = (DesertionVO) search.etcSelectOne(eseq);
				SearchVO sVO = new SearchVO();
				String searchDiv = "30";
				String searchWord = eseq;

				sVO.setSearchDiv(searchDiv);
				sVO.setSearchWord(searchWord);
				sVO.setPageNum(1);
				sVO.setPageSize(10);

				// 유효검사
				int flag = 0;
				AdoptionService aservice = new AdoptionService();
				List<AdoptionVO> listTest = (List<AdoptionVO>) aservice.doRetrieve(sVO);

				for (int i = 0; i < listTest.size(); i++) {
					LOG.debug("아푸니카:" + listTest.get(i));
				}

				if (listTest.isEmpty()) {
					flag = 2;

				} else {

					for (int i = 0; i < listTest.size(); i++) {
						if (listTest.get(i).getApplyState().equals("S") && listTest.get(i).getMemberId().equals(memberId)) {
							flag = 1;
						} else if (listTest.get(i).getApplyState().equals("P")) {
							flag = 2;
						} else if (listTest.get(i).getApplyState().equals("R")) {
							flag = 2;
						} else {
							flag = 2;
						}
					}
				}
				
				
				String hiddenFlag ="";
				if (flag == 1) {
					hiddenFlag="block";
				} else if (flag == 2) {
					hiddenFlag="none";
				} else {
					hiddenFlag="none";
				}
				
				req.setAttribute("hiddenFlag", hiddenFlag);
				req.setAttribute("evo", outVO);
				req.setAttribute("eseq", eseq);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/desertion_edetail.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
