package com.togetdog.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.StringUtil;
import com.togetdog.community.CommunityService;
import com.togetdog.community.CommunityVO;
import com.togetdog.community.CommunityVO1;
import com.togetdog.community.CommunityVO2;
import com.togetdog.desertion.DesertionVO;
import com.togetdog.filemng.FileVO;
import com.togetdog.member.MemberVO;
import com.togetdog.qna.QnaService;
import com.togetdog.qna.QnaVO;
import com.togetdog.qna.QnaVO2;

/**
 * Servlet implementation class ReviewCont
 */
@WebServlet(description = "MyPage", urlPatterns = { "/view/mypage.do" })
public class ReviewCont extends HttpServlet implements ContHandler{
	private static final long serialVersionUID = 1L;
    
	private ReviewService service;
	private CommunityService commService;
	private QnaService qnaService;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCont() {
    	service = new ReviewService();
    	commService = new CommunityService();
    	qnaService = new QnaService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("get---------");
		serviceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("post---------");
		serviceHandler(request, response);
	}

	@Override
	public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String workDiv = StringUtil.nvl(req.getParameter("work_div"));
		LOG.debug("2====================");
		LOG.debug("2작업구분 workDiv=" + workDiv);
        LOG.debug("2====================");
        
        switch(workDiv) {
	        case "do_insert":
	        	this.doInsert(req, res);
	        break;
	        
	        case "move_to_save":
	        	this.doMoveToSave(req, res);
	        break;
	        
	        case "do_retrieve":
	        	this.doRetrieve(req, res);
	        break;
	        
	        case "do_updateMem":
	        	this.doUpdateMem(req, res);
	        break;
	        
	        case "do_selectOneC":
	        	this.doSelectOneC(req, res);
	        break;
	        
	        case "do_selectOneR":
	        	this.doSelectOne(req, res);
	        break;
	        
	        case "do_selectOneQ":
	        	this.doSelectOneQ(req, res);
	        break;
	        
	        case "do_deleteR":
	        	this.doDel(req, res);
	        break;
	        
	        case "do_deleteQ":
	        	this.doDelQ(req, res);
	        break;
	        
	        case "do_deleteC":
	        	this.doDelC(req, res);
	        break;
	        
	        default:
	        	LOG.debug("2작업구분을 확인하세요.workDiv=" + workDiv);
	        break;
        }
		
	}

	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1. 요청으로 들어온 값 꺼내기 (getParameter)
		//2. param을 vo에 set
		//3. service 호출, vo를 param으로 넘겨줌
		//4. req에 속성 set해주기 (req.setAttribute(속성명, 속성값))
		//5. 특정화면으로 데이터 넘기기
		
		
		//service에서 param으로 쓰일 값을 담을 vo객체 생성
		AdoptionVO adoptVO = new AdoptionVO(); 
		CommuVO commVO = new CommuVO();
		JoinVO revVO = new JoinVO();
		QnAVO qnaVO = new QnAVO();
		MemberVO memVO = new MemberVO();
		
		//1. 요청 들어온 값을 변수에 담기
		String pageNum = StringUtil.nvl(req.getParameter("page_num"), "1");
		String pageNumR = StringUtil.nvl(req.getParameter("page_num_r"), "1");
		String pageNumC = StringUtil.nvl(req.getParameter("page_num_c"), "1");
		String pageNumQ = StringUtil.nvl(req.getParameter("page_num_q"), "1");
		String pageSize = StringUtil.nvl(req.getParameter("page_size"), "3");
		String pageSizeA = StringUtil.nvl(req.getParameter("page_size"), "6");//jsp에 page_size 변수 추가
		
		
		
		
		//세션에서 값 받기
	    HttpSession httpSession = req.getSession();
	    MemberVO sseVO = (MemberVO) httpSession.getAttribute("user"); // 세션값 loginID 의 doselectOne
	    String sessionId = sseVO.getMemberId();
	    
	    //세션 아이디;;;;;
	    String memberId = StringUtil.nvl(req.getParameter("member_id"),sessionId);
	    LOG.debug("★★★★★세션 아이디★★★★★: "+memberId);
	      
	      
	      
	     // String memberId = StringUtil.nvl(req.getParameter("member_id"), "");//후에 세션처리!!!
		
		
		//2. 요청받은 데이터가 담긴 변수의 값으로 inVO를 set
		adoptVO.setPageNum(Integer.parseInt(pageNum));
		adoptVO.setPageSize(Integer.parseInt(pageSizeA));
		adoptVO.setMemberId(memberId);
		commVO.setPageNum(Integer.parseInt(pageNumC));
		commVO.setPageSize(Integer.parseInt(pageSize));
		commVO.setMemberId(memberId);
		revVO.setPageNum(Integer.parseInt(pageNumR));
		revVO.setPageSize(Integer.parseInt(pageSize));
		revVO.setMemberId(memberId);
		qnaVO.setPageNum(Integer.parseInt(pageNumQ));
		qnaVO.setPageSize(Integer.parseInt(pageSize));
		qnaVO.setMemberId(memberId);
		//memVO.setPageNum(Integer.parseInt(pageNum));
		memVO.setPageSize(Integer.parseInt(pageSize));
		memVO.setMemberId(memberId);

	     
	    
	    //3. service 호출, vo를 param으로 넘겨줌
	    //회원정보 조회
	    MemberVO memSelect = (MemberVO) this.service.doSelectOneMem(memVO);
	    //입양신청 내역
	    List<AdoptionVO> adoptList = (List<AdoptionVO>)this.service.doRetrieve(adoptVO); 
	    List<AdoptionVO> adoptSList = (List<AdoptionVO>) this.service.doRetrieveS(adoptVO);
	    List<AdoptionVO> adoptRList = (List<AdoptionVO>) this.service.doRetrieveR(adoptVO);
	    List<AdoptionVO> adoptPList = (List<AdoptionVO>) this.service.doRetrieveP(adoptVO);
	    //내가 쓴 글 - 게시판, 후기, qna
	    List<CommuVO> commList = (List<CommuVO>) this.service.doRetrieveCom(commVO);
	    List<JoinVO> revList = (List<JoinVO>) this.service.doRetrieveRev(revVO);
	    List<QnAVO> qnaList = (List<QnAVO>) this.service.doRetrieveQnA(qnaVO);
		
	    //**후기총글수
	    int totalCntRev = 0;
	    if(revList != null && revList.size()>0) {
	    	JoinVO totalVO = revList.get(0);
		    totalCntRev = totalVO.getTotal();
	    }
	    
	    //**게시판총글수
	    int totalCntComm = 0;
	    if(commList != null && commList.size()>0) {
	    	CommuVO totalVO = commList.get(0);
	    	totalCntComm = totalVO.getTotal();
	    }
	    
	    //**QnA총글수
	    int totalCntQnA = 0;
	    if(qnaList != null && qnaList.size()>0) {
	    	QnAVO totalVO = qnaList.get(0);
	    	totalCntQnA = totalVO.getTotal();
	    }
	    
	    //4. req.setAttribute(속성명, 속성값)
	    req.setAttribute("memSelect", memSelect);
	    req.setAttribute("adoptList", adoptList);
	    req.setAttribute("adoptSList", adoptSList);
	    req.setAttribute("adoptRList", adoptRList);
	    req.setAttribute("adoptPList", adoptPList);
	    //req.setAttribute("aimgList", aimgList);
	    req.setAttribute("commList", commList);
	    req.setAttribute("revList", revList);
	    req.setAttribute("qnaList", qnaList);
	    req.setAttribute("memVO", memVO);
	    req.setAttribute("paramVOAdopt", adoptVO);
	    req.setAttribute("paramVOComm", commVO);
	    req.setAttribute("paramVORev", revVO);
	    req.setAttribute("paramVOQnA", qnaVO);
	    req.setAttribute("totalCntRev", totalCntRev);
	    req.setAttribute("totalCntComm", totalCntComm);
	    req.setAttribute("totalCntQnA", totalCntQnA);
	    
	    
	    //5. 특정화면으로 데이터 넘기기
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mypage.jsp");
	    dispatcher.forward(req, res);
	     
		
	}
	

		
	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JoinVO inVO = new JoinVO();
		
		String revNo = StringUtil.nvl(req.getParameter("rev_no"));
		
		inVO.setRevNo(revNo);
		
		JoinVO outVO = (JoinVO) service.doSelectOne(inVO);
		req.setAttribute("vo", outVO);
		
		LOG.debug("cont.service 후기글--------------------------");
		LOG.debug("outVO="+outVO);
		LOG.debug("cont.service 후기글--------------------------");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mypage_review_select.jsp");//후기 디테일페이지로 연결!!
		dispatcher.forward(req, res);
		
	}
	
	public void doSelectOneC(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CommunityVO1 inVO = new CommunityVO1();
		CommunityVO2 inVO1 = new CommunityVO2();
		
		String totNo = StringUtil.nvl(req.getParameter("tot_no"));

		inVO.setTotNo(totNo);
		inVO1.setReplyNo(totNo);
				
		CommunityVO1 outVO =(CommunityVO1) commService.doSelectOne(inVO);
		req.setAttribute("vo", outVO);

		
		List<CommunityVO2> replyList =  (List<CommunityVO2>) commService.doReplyRetrieve(inVO1);		
		req.setAttribute("replyList", replyList);

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_select.jsp");
		dispatcher.forward(req, res);
	}
	
	public void doSelectOneQ(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		QnaVO inVO = new QnaVO();
		QnaVO2 inVO1 = new QnaVO2();
		
		String qnaNo = StringUtil.nvl(req.getParameter("qna_no"));
		
		inVO.setQna_no(qnaNo);
		inVO1.setReplyNo(qnaNo);
		
		QnaVO outVO = (QnaVO)qnaService.doSelectOne(inVO);
		req.setAttribute("vo", outVO);
		
		List<QnaVO2> replyList =  (List<QnaVO2>) qnaService.doReplyRetrieve(inVO1);		
		req.setAttribute("replyList", replyList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/qnaboard_detail.jsp");
		dispatcher.forward(req, res);
	}
	
	public void doMoveToSave(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String applyNo = StringUtil.nvl(req.getParameter("apply_no"));
		req.setAttribute("apply_no", applyNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mypage_review_insert.jsp");
		dispatcher.forward(req, res);
	}
	

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		ReviewVO inVO = new ReviewVO();
		
		String revNo = StringUtil.nvl(req.getParameter("rev_no"));
		
		LOG.debug("revNo★★★★★★★★"+revNo);
		inVO.setRevNo(revNo);
		
		int flag = this.service.doDelete(inVO);
		LOG.debug("flag★★★★★★★★"+flag);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String msg = "";
		Gson gson = new Gson();
		if(flag==1) {
			  msg = "게시물이 삭제되었습니다.";
		} else {
			  msg = "게시물 삭제에 실패했습니다.";
		}
		
		String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag),msg));
		
		out.println(gsonStr);
	}
	
	public void doDelC(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		CommunityVO inVO = new CommunityVO();
		String totNo = StringUtil.nvl(req.getParameter("tot_no"));
		
		LOG.debug("totNo★★★★★★★★"+totNo);
		inVO.setTotNo(totNo);
		
		int flag = this.commService.doDelete(inVO);
		
		Gson gson = new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO msgVO = new MessageVO();
		if(flag>0) {
			msg = "게시물이 삭제되었습니다.";
		} else {
			msg = "게시물 삭제에 실패했습니다.";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		
		LOG.debug("gsonString:" + gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
		
	}
	
	
	public void doDelQ(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		QnaVO inVO =new QnaVO();
		String qna_no = StringUtil.nvl(req.getParameter("qna_no"));
		inVO.setQna_no(qna_no);
		
		LOG.debug("qna_no★★★★★★★★"+qna_no);
		
		int flag = this.qnaService.doDeleteYn(inVO);
		LOG.debug("flag★★★★★★★★"+flag);
		
		Gson gson=new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO  msgVO=new MessageVO();
		
		if(flag>0) {
			msg = "게시물이 삭제되었습니다";
		}else {
			msg = "게시물 삭제에 실패했습니다.";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		LOG.debug("gsonString="+gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	      ReviewVO inVO = new ReviewVO();
	      FileVO fileVO = new FileVO();
	      
	      String title = StringUtil.nvl(req.getParameter("title"),"");
	      String memberId = StringUtil.nvl(req.getParameter("member_id"),"");
	      String contents = StringUtil.nvl(req.getParameter("contents"),"");
	      String applyNo = StringUtil.nvl(req.getParameter("apply_no"),"");
	      String fname =  StringUtil.nvl(req.getParameter("fname"),"");
	      
	      if(fname=="") {
	    	  res.setContentType("text/html;charset=utf-8");
		      PrintWriter out = res.getWriter();
		      
		      String msg = "사진을 첨부해주세요.";
		      Gson gson = new Gson();
		      
		      String gsonStr = gson.toJson(new MessageVO(String.valueOf(0),msg));
		      out.println(gsonStr);
		      
		      return;
	      }
	      
	      LOG.debug("fname:" + fname);
	      LOG.debug("applyNo:" + applyNo);
			
	      String[] array = fname.trim().split(",");
	      
	      for(int i=0;i<array.length;i++) {
	      	LOG.debug("array[i]="+array[i]);
	      }
//	      j_3.jpg
//	      j_32.jpg
//	      D:\03_JSP\daoWorkspace\ZSOMAC\WebContent\img\image_
//	      107074
//	      .jpg
		  
		  int dot= array[1].indexOf(".");//저장파일명에서 .의 위치 찾기
			
		  String dotPoint=array[1].substring(0,dot);
		//저장파일명 시작부터 . 이전까지를 잘라내기 -> .확장자를 뺀 파일명
			
	      inVO.setTitle(title);
	      inVO.setMemberId(memberId);
	      inVO.setContents(contents);
	      inVO.setApplyNo(applyNo);
	      
	      int flag = this.service.doInsert(inVO);
	      LOG.debug("4=============");
	      LOG.debug("4.doInsert.flag="+flag);
	      LOG.debug("4=============");
	      
	      ReviewVO vo = (ReviewVO) this.service.doGetno(inVO);
	      
	      String revNo = vo.getRevNo();
	      
		  fileVO.setFileNo(revNo);
		  fileVO.setOrgNm(array[0]);
		  fileVO.setSaveNm(array[1]);
		  fileVO.setImgPath(dotPoint);
		  fileVO.setFileSize(Integer.parseInt(array[3]));
		  fileVO.setExt(array[4]);
		  
		  int flag1 = this.service.doInsertFile(fileVO);
	      
	      res.setContentType("text/html;charset=utf-8");
	      PrintWriter out = res.getWriter();
	      
	      String msg = "";
	      Gson gson = new Gson();
	      if(flag==1 && flag1==1) {
	    	  flag = 1;
	    	  msg = inVO.getTitle() + "이(가)\n등록되었습니다.";
	      } else {
	    	  msg = inVO.getTitle() + "\n등록 실패했습니다.";
	      }
	      
	      String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag),msg));
	      
	      LOG.debug("5=============");
	      LOG.debug("5.gsonStr="+gsonStr);
	      LOG.debug("5=============");
	      out.println(gsonStr);
	}

	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 회원정보 수정
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doUpdateMem(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1. 요청으로 들어온 값 꺼내기 (getParameter)
		//2. param을 vo에 set
		//3. service 호출, vo를 param으로 넘겨줌
		//4. req에 속성 set해주기 (req.setAttribute(속성명, 속성값))
		//5. 특정화면으로 데이터 넘기기
		
		//service에서 param으로 쓰일 값을 담을 vo객체 생성
		MemberVO memVO = new MemberVO();
		
		//1. 요청 들어온 값을 변수에 담기
		String pw = StringUtil.nvl(req.getParameter("pw"), "");
		String name = StringUtil.nvl(req.getParameter("name"), "");
		String phoneNum = StringUtil.nvl(req.getParameter("phone_num"), "");
		String email = StringUtil.nvl(req.getParameter("email"), "");
		String memberId = StringUtil.nvl(req.getParameter("member_id"), "");
		
		//2. 요청받은 데이터가 담긴 변수의 값으로 inVO를 set
		memVO.setMemberId(memberId);
		memVO.setPassword(pw);
		memVO.setName(name);
		memVO.setPhone(phoneNum);
		memVO.setEmail(email);
		
		 LOG.debug("--------------------------");
	     LOG.debug("cont1.memVO=" + memVO);
	     LOG.debug("--------------------------");
	     
	    //3. service 호출, vo를 param으로 넘겨줌
	    int flag = this.service.doUpdateMem(memVO);
	    
	      LOG.debug("cont3.service flag--------------------------");
	      LOG.debug("flag="+flag);
	      LOG.debug("cont3.service flag--------------------------");
	    
	    res.setContentType("text/html;charset=utf-8");
	    PrintWriter out = res.getWriter();
	    
	    String msg = "";
	    Gson gson = new Gson();
	    if(flag==1) {
	    	msg = "개인정보가 수정되었습니다.";	    	
	    } else {
	    	msg = "개인정보 수정에 실패하였습니다.";
	    }
	    
	    String gsonStr = gson.toJson(new MessageVO(String.valueOf(flag), msg));
	    
	      LOG.debug("cont3.service gsonStr--------------------------");
	      LOG.debug("gsonStr="+gsonStr);
	      LOG.debug("cont3.service gsonStr--------------------------");
	      
	    out.println(gsonStr);
		
	}

}
