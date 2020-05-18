package com.togetdog.community;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.StringUtil;
import com.togetdog.filemng.FileVO;
import com.togetdog.member.MemberVO;
import com.togetdog.reply.CommonReplyVO;
import com.togetdog.review.JoinVO;

/**
 * Servlet implementation class CommunityCont
 */
@WebServlet(description = "커뮤니티 게시판", urlPatterns = { "/view/community.do" })
public class CommunityCont extends HttpServlet implements ContHandler {
	private static final long serialVersionUID = 1L;
       
	private CommunityService communityService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityCont() {
        super();
        communityService = new CommunityService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceHandler(request, response);
	}

	@Override
	public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //request의 인코딩: utf-8
		String workDiv = StringUtil.nvl(req.getParameter("work_div"));//request에 null들어오면 ->""
		
		LOG.debug("2=============");
		LOG.debug("2workDiv=" + workDiv);
		LOG.debug("2=============");
		switch(workDiv) {	
		
			case "do_reply_update": //단건등록
				this.doReplyUpdate(req, res);
			break;
			
			case "do_reply_delete": //단건등록
				this.doReplyDelete(req, res);
			break;
		
			case "do_update": //단건등록
				this.doUpdate(req, res);
			break;
		
			case "do_delete": 
				this.doDel(req, res);
			break;
		
			case "do_insert": 
				this.doInsert(req, res);
			break;
			
			case "do_retrieve":
				this.doRetrieve(req, res);
				break;
			
			case "do_retrieve_m":
				doMRetrieve(req, res);
				break;
			
			case "do_retrieve_j":
				doJRetrieve(req, res);
				break;
			
			case "do_retrieve_r":
				doRRetrieve(req, res);
				break;
				
			case "move_to_save":
				doMoveToSave(req,res);
				break;
			case "do_selectOne":
				this.doSelectOne(req, res);
				break;
				
			case "do_selectOne_r":
				this.doRSelectOne(req, res);
				break;	
				
			case "do_reply_insert":
				doReplyInsert(req, res);
				break;
			default:
				break;
		}
	}
	
	
	public void doReplyUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("======================");
		LOG.debug("doReplyDelete");
		LOG.debug("======================");
		
		//1.vo 변수 선언
		//2.param read
		//3.vo에 param set
		//4.service호출
		//5.Gson message
		//6.forward
		
		CommunityVO2 inVO = new CommunityVO2();
		String seq = StringUtil.nvl(req.getParameter("seq"));
		int rseq = Integer.parseInt(StringUtil.nvl(req.getParameter("rseq")));
		String rcontents = StringUtil.nvl(req.getParameter("rcontents"));
		
		HttpSession httpSession = req.getSession();
		MemberVO mvo= (MemberVO)httpSession.getAttribute("user");
	   	String id = mvo.getMemberId();
		
		inVO.setReplyNo(seq);
		inVO.setRseqNo(rseq);
		inVO.setRcontents(rcontents);
		inVO.setRregId(id);
		int flag = this.communityService.doReplyupdate(inVO);
		
		Gson gson = new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO msgVO = new MessageVO();
		if(flag>0) {
			msg = "수정되었습니다.";
		} else {
			msg = "수정 실패";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		
		LOG.debug("gsonString:" + gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
	}
	
	public void doReplyDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("======================");
		LOG.debug("doReplyDelete");
		LOG.debug("======================");
		
		//1.vo 변수 선언
		//2.param read
		//3.vo에 param set
		//4.service호출
		//5.Gson message
		//6.forward
		
		CommunityVO2 inVO = new CommunityVO2();
		String seq = StringUtil.nvl(req.getParameter("seq"));
		int rseq = Integer.parseInt(StringUtil.nvl(req.getParameter("rseq")));
		
		inVO.setReplyNo(seq);
		inVO.setRseqNo(rseq);
		
		int flag = this.communityService.doReplyDelete(inVO);
		
		Gson gson = new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO msgVO = new MessageVO();
		if(flag>0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제 실패";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		
		LOG.debug("gsonString:" + gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
	}

	public void doRRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		SearchVO inVO = new SearchVO();
		
		String searchDiv = StringUtil.nvl(req.getParameter("search_div"));
		String searchWord = StringUtil.nvl(req.getParameter("search_word"));
		String pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setPageNum(Integer.parseInt(pageNum));
		
		List<JoinVO> list =(List<JoinVO>) this.communityService.doRRetrieve(inVO);
		
		List<JoinVO> recentList =(List<JoinVO>) this.communityService.doRecentRRetrieve(inVO);
		
		
		for(JoinVO vo :list) {
			LOG.debug(vo);
		}
		
		for(JoinVO vo :recentList) {
			LOG.debug(vo);
		}
		int totalCnt = 0;
		
		if(list.size() == 0 || list == null) {
			totalCnt = 0;
		} else if(list != null || list.size()>0) {
			JoinVO totalVO = list.get(0);
			totalCnt = totalVO.getTotal();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("paramVO", inVO);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("recentList", recentList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_r.jsp");
		dispatcher.forward(req, res);
		
	}		
	
	
	public void doJRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		SearchVO inVO = new SearchVO();
		
		String searchDiv = StringUtil.nvl(req.getParameter("search_div"));
		String searchWord = StringUtil.nvl(req.getParameter("search_word"));
		String pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setPageNum(Integer.parseInt(pageNum));
		
		List<CommunityVO1> list =(List<CommunityVO1>) this.communityService.doJRetrieve(inVO);
		
		List<CommunityVO1> recentList =(List<CommunityVO1>) this.communityService.doRecentJRetrieve(inVO);
		
		
		for(CommunityVO1 vo :list) {
			LOG.debug(vo);
		}
		
		int totalCnt = 0;
		
		
		if(list.size() == 0 || list == null) {
			totalCnt = 0;
		} else if(list != null || list.size()>0) {
			CommunityVO1 totalVO = list.get(0);
			totalCnt = totalVO.getTotal();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("paramVO", inVO);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("recentList", recentList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_j.jsp");
		dispatcher.forward(req, res);
		
	}	
	
	public void doMRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		SearchVO inVO = new SearchVO();
		
		String searchDiv = StringUtil.nvl(req.getParameter("search_div"));
		String searchWord = StringUtil.nvl(req.getParameter("search_word"));
		String pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setPageNum(Integer.parseInt(pageNum));
		
		List<CommunityVO1> list =(List<CommunityVO1>) this.communityService.doMRetrieve(inVO);
		
		List<CommunityVO1> recentList =(List<CommunityVO1>) this.communityService.doRecentMRetrieve(inVO);
		
		
		for(CommunityVO1 vo :list) {
			LOG.debug(vo);
		}
		
		int totalCnt = 0;
		
		if(list.size() == 0 || list == null) {
			totalCnt = 0;
		} else if(list != null || list.size()>0) {
			CommunityVO1 totalVO = list.get(0);
			totalCnt = totalVO.getTotal();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("paramVO", inVO);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("recentList", recentList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_m.jsp");
		dispatcher.forward(req, res);
		
	}	
	
	public void doReplyInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		CommunityVO2 inVO = new CommunityVO2();
		//제목
		String contents = StringUtil.nvl(req.getParameter("contents"),"");
		//작성자/수정자
		String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
		//내용
		String seq =  StringUtil.nvl(req.getParameter("seq"));
		
		HttpSession httpSession = req.getSession();
		MemberVO mvo= (MemberVO)httpSession.getAttribute("user");
	   	String id = mvo.getMemberId();
	   	/*
	   	if(id.equals(regId)) {
		   	 res.setContentType("text/html;charset=utf-8");
	         PrintWriter out = res.getWriter();
	         
	         String msg = "원래 댓글을 수정해주세요";
	         Gson gson = new Gson();
	         
	         String gsonStr = gson.toJson(new MessageVO(String.valueOf(0),msg));
	         out.println(gsonStr);
	         
	         return;

	   	}*/
		
		
		inVO.setRcontents(contents);
		inVO.setRregId(regId);
		inVO.setReplyNo(seq);
		
		int flag = this.communityService.doReplyInsert(inVO);
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
//		if(flag==1) {
//			out.println("<script>alert('등록성공!');location.href='/DaoWEB/board/board_list.jsp'</script>");
//		} else {
//			out.println("<script>alert('실패!');history.go(-1);</script>");
//		}
		
		String msg = "";
		Gson gson = new Gson();
		if(flag == 1) {
			msg = inVO.getRcontents() + "이\n등록 되었습니다.";
		} else {
			msg = inVO.getRcontents() + "이\n등록 실패.";
		}
		
		String gsonStr= gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);
		
		
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CommunityVO inVO = new CommunityVO();
		FileVO fileVO = new FileVO();
		//제목
		String title = StringUtil.nvl(req.getParameter("title"),"");
		//작성자/수정자
		String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
		//내용
		String contents =  StringUtil.nvl(req.getParameter("contents"),"");
		
		String op =StringUtil.nvl(req.getParameter("op"),"");
		
		String fname =  StringUtil.nvl(req.getParameter("fname"),"");
		LOG.debug("fname:" + fname);
		
		if(fname=="") {
            res.setContentType("text/html;charset=utf-8");
            PrintWriter out = res.getWriter();
            
            String msg = "사진을 첨부해주세요.";
            Gson gson = new Gson();
            
            String gsonStr = gson.toJson(new MessageVO(String.valueOf(0),msg));
            out.println(gsonStr);
            
            return;
         }
		
		
		String[] array = fname.trim().split(",");
		
		for(int i=0;i<array.length;i++) {
			LOG.debug(array[i]);
		}
//		j_3.jpg
//		j_32.jpg
//		D:\03_JSP\daoWorkspace\ZSOMAC\WebContent\img\image_
//		107074
//		.jpg
		
		int dot= array[1].indexOf(".");
		
		String dotPoint=array[1].substring(0,dot);
		
		inVO.setTotNo(op);
		inVO.setTitle(title);
		inVO.setMemberId(regId);
		inVO.setContents(contents);
		
		int flag = this.communityService.doInsert(inVO);//r_54
		
		//String으로 반환 list 그 VO의 gettotno 값을 String으로 저장하고 값을준다.
		CommunityVO vo = (CommunityVO) this.communityService.doGetno(inVO);
		
		//LOG.debug("vodd:" + vo);
		String boardNo = vo.getTotNo();
		LOG.debug(boardNo);	// r_54
		
		fileVO.setFileNo(boardNo);
		fileVO.setOrgNm(array[0]);
		fileVO.setSaveNm(array[1]);
		fileVO.setImgPath(dotPoint);
		fileVO.setRegId(regId);
		fileVO.setFileSize(Integer.parseInt(array[3]));
		fileVO.setExt(array[4]);
		
		int flag1 = communityService.doFileInsert(fileVO);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
//		if(flag==1) {
//			out.println("<script>alert('등록성공!');location.href='/DaoWEB/board/board_list.jsp'</script>");
//		} else {
//			out.println("<script>alert('실패!');history.go(-1);</script>");
//		}
		
		String msg = "";
		Gson gson = new Gson();
		if(flag == 1 && flag1 ==1) {
			msg = inVO.getTitle() + "이\n등록 되었습니다.";
		} else {
			msg = inVO.getTitle() + "\n등록 실패.";
		}
		
		LOG.debug("등록 seq"+inVO.getTotNo());
		
		String gsonStr= gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);
	}

	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("======================");
		LOG.debug("doUpdate");
		LOG.debug("======================");
		
		//1.vo 변수 선언
		//2.param read
		//3.vo에 param set
		//4.service호출
		//5.Gson message
		//6.forward
		
		CommunityVO inVO = new CommunityVO();
		FileVO fileVO = new FileVO();
		
		String seq = StringUtil.nvl(req.getParameter("seq"));
		String detail_title = StringUtil.nvl(req.getParameter("detail_title"));
		String detail_contents = StringUtil.nvl(req.getParameter("detail_contents"));
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
		
		HttpSession httpSession = req.getSession();
		MemberVO mvo= (MemberVO)httpSession.getAttribute("user");
	   	String id = mvo.getMemberId();
		
	
		
		Gson gson = new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO msgVO = new MessageVO();
		
		String[] array = fname.trim().split(",");
		
		
		int dot= array[1].indexOf(".");
		String dotPoint=array[1].substring(0,dot);
		
		fileVO.setFileNo(seq);
		fileVO.setOrgNm(array[0]);
		fileVO.setSaveNm(array[1]);
		fileVO.setImgPath(dotPoint);
		fileVO.setFileSize(Integer.parseInt(array[3]));
		fileVO.setExt(array[4]);
		fileVO.setRegId(id);
		
		int flag1 = communityService.doFileUpdate(fileVO);
		
		
		
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		
		
		//param -> inVO에 새팅
		inVO.setMemberId(id);
		inVO.setTitle(detail_title);
		inVO.setContents(detail_contents);
		inVO.setTotNo(seq);
		
		
		LOG.debug("param==============");
		LOG.debug("inVO:" + inVO.toString());
		LOG.debug("param===========");
		
		int flag = this.communityService.doUpdate(inVO);
		
		if(flag==1 && flag1==1) {
			msg = seq + "수정되었습니다.";
		} else {
			msg = seq + "이 수정 실패";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		
		LOG.debug("gsonString:" + gsonString);
		
		
		out.print(gsonString);
		
		
	}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("======================");
		LOG.debug("doDel");
		LOG.debug("======================");
		
		//1.vo 변수 선언
		//2.param read
		//3.vo에 param set
		//4.service호출
		//5.Gson message
		//6.forward
		
		CommunityVO inVO = new CommunityVO();
		String seq = StringUtil.nvl(req.getParameter("seq"));
		inVO.setTotNo(seq);
		
		int flag = this.communityService.doDelete(inVO);
		
		Gson gson = new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO msgVO = new MessageVO();
		if(flag>0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제 실패";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		
		LOG.debug("gsonString:" + gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
	}

	
	public void doRSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JoinVO inVO = new JoinVO();
		CommunityVO2 inVO1 = new CommunityVO2();
		
		String seq = StringUtil.nvl(req.getParameter("seq"));
		
		inVO.setRevNo(seq);
		inVO1.setReplyNo(seq);
		JoinVO outVO =(JoinVO) communityService.doRSelectOne(inVO);
		req.setAttribute("vo", outVO);
		
		List<CommunityVO2> replyList =  (List<CommunityVO2>) communityService.doRReplyRetrieve(inVO1);
		
		req.setAttribute("replyList", replyList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_rselect.jsp");
		dispatcher.forward(req, res);
	}

	
	
	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CommunityVO1 inVO = new CommunityVO1();
		CommunityVO2 inVO1 = new CommunityVO2();
		CommonReplyVO replyinVO = new CommonReplyVO();
		
		String seq = StringUtil.nvl(req.getParameter("seq"));
		String check = "0";
		String recheck ="0";
		String existcheck ="0";
		HttpSession httpSession = req.getSession();
		MemberVO mvo= (MemberVO)httpSession.getAttribute("user");
	   	String id = mvo.getMemberId();
		
		
		inVO.setTotNo(seq);
		inVO1.setReplyNo(seq);
		
		
		CommunityVO1 outVO =(CommunityVO1) communityService.doSelectOne(inVO);
		LOG.debug(outVO);
		
		
		String modId =outVO.getMemberId();//게시물 작성자 아이디
		req.setAttribute("vo", outVO);
		List<CommunityVO2> replyList =  (List<CommunityVO2>) communityService.doReplyRetrieve(inVO1);
		req.setAttribute("replyList", replyList);
		
		
		replyinVO.setRegId(id);
		replyinVO.setReplyNo(seq);
		
		CommonReplyVO reoutVO = (CommonReplyVO) communityService.doreplySelectOne(replyinVO);
		LOG.debug("ssssss"+reoutVO);
		
		LOG.debug("ssssss"+replyList);
		
		
		
		if(reoutVO ==null && replyList.isEmpty() && replyList.size() ==0) {	//아예 댓글이 없는경우
			//LOG.debug("댓글이 없다");
			if(id.equals(modId) || id.equals("ADMIN") ) {
				check = "1";
			} else {
				check = "0";
			}
			
			req.setAttribute("check", check);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_select.jsp");
			dispatcher.forward(req, res);
		} else if( reoutVO ==null && replyList.size()>0) {					//자기가 쓴 댓글은 없지만 다른 사람 댓글 있는경우
			if(id.equals(modId) || id.equals("ADMIN")) {
				check = "1";
			} else {
				check = "0";
			}
			
			recheck = "0";
			existcheck ="1";
			req.setAttribute("check", check);
			req.setAttribute("existcheck", existcheck);
			req.setAttribute("recheck", recheck);
			List<CommunityVO2> replyList1 =  (List<CommunityVO2>) communityService.doReplyRetrieve(inVO1);
			req.setAttribute("replyList", replyList1);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_select.jsp");
			dispatcher.forward(req, res);
		}
		
		else if(!(id.equals(modId) || id.equals("ADMIN"))){
			//LOG.debug("댓글이 존재");
			//LOG.debug("게시물아이디랑 세션아이디 다르다");
			check ="0";
			existcheck ="1";		//댓글들
			if(id.equals(reoutVO.getRegId())) {		//reoutVO는 댓글을 selectOne한 작성자 아이디
				recheck ="1";
			} else{
				recheck ="0";
			}
			req.setAttribute("check", check);
			req.setAttribute("existcheck", existcheck);
			req.setAttribute("recheck", recheck);
			req.setAttribute("replyList", replyList);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_select.jsp");
			dispatcher.forward(req, res);		
		} 
		
		else if((id.equals(modId)) || id.equals("ADMIN")){
			//LOG.debug("댓글이 존재");
			//LOG.debug("게시물아이디랑 세션아이디 같다");
			check = "1";
			existcheck ="1";	
			if(id.equals(reoutVO.getRegId())) {
				recheck ="1";
			} else{
				recheck ="0";
			}
			req.setAttribute("check", check);
			req.setAttribute("existcheck", existcheck);
			req.setAttribute("recheck", recheck);
			req.setAttribute("replyList", replyList);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_select.jsp");
			dispatcher.forward(req, res);	
		}
				
			
	}
	

	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		SearchVO inVO = new SearchVO();
		
		String searchDiv = StringUtil.nvl(req.getParameter("search_div"));
		String searchWord = StringUtil.nvl(req.getParameter("search_word"));
		String pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setPageNum(Integer.parseInt(pageNum));
		
		List<CommunityVO1> list =(List<CommunityVO1>) this.communityService.doRetrieve(inVO);
		
		List<CommunityVO1> recentList =(List<CommunityVO1>) this.communityService.doRecentRetrieve(inVO);
		
		
		for(CommunityVO1 vo :list) {
			LOG.debug(vo);
		}
		
		int totalCnt = 0;
		
		if(list.size() == 0 || list == null) {
			totalCnt = 0;
		} else if(list != null || list.size()>0) {
			CommunityVO1 totalVO = list.get(0);
			totalCnt = totalVO.getTotal();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("paramVO", inVO);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("recentList", recentList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community.jsp");
		dispatcher.forward(req, res);
		
	}
	
	public void doMoveToSave(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/community_insert.jsp");
		dispatcher.forward(req, res);
	}

	
}
