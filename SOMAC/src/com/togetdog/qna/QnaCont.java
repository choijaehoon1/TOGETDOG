package com.togetdog.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.togetdog.qna.QnaVO;
import com.togetdog.qna.QnaVO2;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.StringUtil;
import com.togetdog.community.CommunityVO;
import com.togetdog.community.CommunityVO1;
import com.togetdog.filemng.FileVO;

/**
 * Servlet implementation class BoardCont
 * /SOMAC_0213/board/board.do -> /board/board.do
 */
@WebServlet(description = "게시판", urlPatterns = { "/view/qna.do" })
public class QnaCont extends HttpServlet implements ContHandler{
	private static final long serialVersionUID = 1L;
	/** QnaService객체 */
    private QnaService  service;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaCont() {
        super();
        service = new QnaService();
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
		//1.work_div: 작업구분
		//2.request Encoding: utf-8
		//3.기능별 분해
		    //거래분기:
	    	//do_update:수정
	    	//do_delete:삭제
	    	//do_insert:등록
	    	//do_retrieve:목록조회
	    	//do_selectOne:단건조회		
		
		req.setCharacterEncoding("utf-8");//request의 인코딩: utf-8
		String workDiv = StringUtil.nvl(req.getParameter("work_div"));//null -> ""
		LOG.debug("2====================");
		LOG.debug("2=workDiv="+workDiv);
		LOG.debug("2====================");	
		switch(workDiv) {//거래분기
		case "do_update"://수정
			this.doUpdate(req, res);
		break;
	
		case "do_delete"://삭제
			this.doDel(req, res);
		break;
		
		case "do_selectOne"://단건조회,단건조회 화면으로 이동
			this.doSelectOne(req, res);
		break;
	
		case "move_to_save"://등록화면으로 이동
			doMoveToSave(req, res);
		break;
	
		case "do_insert"://단건 등록
			this.doInsert(req, res);
			break;
			
		case "do_insert2"://단건 등록
			this.doInsert2(req, res);
			break;
			
		case "do_retrieve"://목록조회
			this.doRetrieve(req, res);
			break;
			
		case "do_reply_insert":
			doReplyInsert(req, res);
			break;
			
		case "do_re_update":
			doReplyUpdate(req, res);
			break;
			
		case "do_re_del":
			doReplyDel(req, res);
			break;	
			
		default:

			break;
		}
		
		
	}
	
	public void doReplyDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 LOG.debug("3====================");
		  LOG.debug("3=dodoReplyDel=");
		  LOG.debug("3====================");
		  
		  QnaVO2 inVO = new QnaVO2();
			
			//글번호
			String qna_no =  StringUtil.nvl(req.getParameter("qna_no"));
			//댓글 번호
			String rseq_no =   StringUtil.nvl(req.getParameter("rseq_no"));
			
			int reNum= Integer.parseInt(rseq_no.trim());
			
		  
		  inVO.setReplyNo(qna_no);
		  inVO.setRseqNo(reNum);
		  
		  //TODO
	
		  
		    int flag = this.service.doReplyDel(inVO);
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();


		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = "삭제 되었습니다.";
		  }else {
			  msg ="삭제 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void doReplyUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 LOG.debug("3====================");
		  LOG.debug("3=doReplyUpdate=");
		  LOG.debug("3====================");
		  
		  QnaVO2 inVO = new QnaVO2();
			//내용
			String rcontents = StringUtil.nvl(req.getParameter("rcontents"),"");
			//작성자/수정자
			String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
			//글번호
			String qna_no =  StringUtil.nvl(req.getParameter("qna_no"));
			//댓글 번호
			String rseq_no =   StringUtil.nvl(req.getParameter("rseq_no"));
			
			int reNum= Integer.parseInt(rseq_no.trim());
			
		  inVO.setRcontents(rcontents);
		  inVO.setRregId(regId);
		  inVO.setReplyNo(qna_no);
		  inVO.setRseqNo(reNum);
		  
		  //TODO
	
		  
		    int flag = this.service.doReplyUpdate(inVO);
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();


		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = "수정 되었습니다.";
		  }else {
			  msg ="수정 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
public void doReplyInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		QnaVO2 inVO = new QnaVO2();
		//내용
		String mainreply = StringUtil.nvl(req.getParameter("mainreply"),"");
		//작성자/수정자
		String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
		//글번호
		String qna_no =  StringUtil.nvl(req.getParameter("qna_no"));
		
		inVO.setRcontents(mainreply);
		inVO.setRregId(regId);
		inVO.setReplyNo(qna_no);
		
		int flag = this.service.doReplyInsert(inVO);
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		
		String msg = "";
		Gson gson = new Gson();
		if(flag == 1) {
			msg =  "댓글이 등록 되었습니다.";
		} else {
			msg = "댓글이 등록이 실패.";
		}
		
		String gsonStr= gson.toJson(new MessageVO(String.valueOf(flag), msg));
		LOG.debug("====================");
		LOG.debug("gsonStr=" + gsonStr);
		LOG.debug("====================");
		out.println(gsonStr);
		
		
	}
		
		
	
	
	
	public void doMoveToSave(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/view/qnaboard_save.jsp");
		dispatcher.forward(req, res);
		
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
		
		List<QnaVO> list =(List<QnaVO>) this.service.doRetrieve(inVO);
		
		
		
		for(QnaVO vo :list) {
			LOG.debug("야이씨:"+vo);
		}
		
		int totalCnt = 0;
		//총글수
		if(list.size() == 0 || list == null) {
			totalCnt = 0;
		} else if(list != null || list.size()>0) {
			QnaVO totalVO = list.get(0);
			totalCnt = totalVO.getTotal();
		}
	
		
		
		req.setAttribute("list", list);//목록
		req.setAttribute("paramVO", inVO);//param
		req.setAttribute("totalCnt", totalCnt);//총글수
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/qnaboard.jsp");
		dispatcher.forward(req, res);
	}
	

	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		
		QnaVO inVO = new QnaVO();
		QnaVO2 inVO1 = new QnaVO2();
		
		String qna_no = StringUtil.nvl(req.getParameter("qna_no"));
		
		inVO.setQna_no(qna_no);
		inVO1.setReplyNo(qna_no);
		QnaVO outVO = (QnaVO)service.doSelectOne(inVO);
		req.setAttribute("vo", outVO);
		
		List<QnaVO2> replyList =  (List<QnaVO2>) service.doReplyRetrieve(inVO1);
		
		req.setAttribute("replyList", replyList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/qnaboard_detail.jsp");
		dispatcher.forward(req, res);
		
		
		
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  LOG.debug("3====================");
		  LOG.debug("3=doInsert 들어옴");
		  LOG.debug("3====================");
		  
		  QnaVO  inVO=new QnaVO();//param
		  FileVO fileVO = new FileVO();
		  //1.param read
		  //2.param QnaVO
		  //3.service 메소드 호출
		  //제목
		  String title = StringUtil.nvl(req.getParameter("title"),"");
		  //내용
		  String contents = StringUtil.nvl(req.getParameter("contents"),"");
		  //작성자/수정자
		  String member_id = StringUtil.nvl(req.getParameter("member_id"),"");
		  
		  
	       String fname =  StringUtil.nvl(req.getParameter("fname"),"");
			
			LOG.debug("file name:" + fname);
			
			String[] array = fname.trim().split(",");
			
			for(int i=0;i<array.length;i++) {
				LOG.debug(array[i]);
			}
			
			int dot= array[1].indexOf(".");
			
			String dotPoint=array[1].substring(0,dot);
			
		
		  inVO.setTitle(title);
		  inVO.setMemberId(member_id);//login처리후 session으로 변환 할것
		  inVO.setContents(contents);
		  
		  int flag = this.service.doInsert(inVO);
		  LOG.debug("3====================");
		  LOG.debug("3=flag="+flag);
		  LOG.debug("3====================");
		  
		  
		//String으로 반환 list 그 VO의 gettotno 값을 String으로 저장하고 값을준다.
			QnaVO vo = (QnaVO) this.service.doGetno(inVO);
			String boardNo = vo.getQna_no();
			LOG.debug(boardNo);	// m_84
			
			
			fileVO.setFileNo(boardNo);
			fileVO.setOrgNm(array[0]);
			fileVO.setSaveNm(array[1]);
			fileVO.setImgPath(dotPoint);
			fileVO.setFileSize(Integer.parseInt(array[3]));
			fileVO.setExt(array[4]);
			
			int flag1 = service.doFileInsert(fileVO);
			
		  //응답처리
		  res.setContentType("text/html;charset=utf-8");
		  PrintWriter out=res.getWriter();
//		  if(flag==1) {
//			  out.println("<script>alert('등록성공!');location.href='/SOMAC_0213/board/board_list.jsp'</script>");
//		  }else {
//			  out.println("<script>alert('실패!');history.go(-1);</script>");
//		  }

		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = inVO.getTitle()+"이\n등록 되었습니다.";
		  }else {
			  msg = inVO.getTitle()+"이\n등록 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.print(gsonStr);
			
	}
	
	public void doInsert2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  LOG.debug("3====================");
		  LOG.debug("3=doInsert2 들어옴");
		  LOG.debug("3====================");
		  
		  QnaVO  inVO=new QnaVO();//param
		  FileVO fileVO = new FileVO();
		  //1.param read
		  //2.param QnaVO
		  //3.service 메소드 호출
		  //제목
		  String title = StringUtil.nvl(req.getParameter("title"),"");
		  //내용
		  String contents = StringUtil.nvl(req.getParameter("contents"),"");
		  //작성자/수정자
		  String member_id = StringUtil.nvl(req.getParameter("member_id"),"");
		  
		  
	       String fname =  StringUtil.nvl(req.getParameter("fname"),"");
			
			LOG.debug("file name:" + fname);
			
			String[] array = fname.trim().split(",");
			
			for(int i=0;i<array.length;i++) {
				LOG.debug(array[i]);
			}
			
			int dot= array[1].indexOf(".");
			
			String dotPoint=array[1].substring(0,dot);
			
		
		  inVO.setTitle(title);
		  inVO.setMemberId(member_id);//login처리후 session으로 변환 할것
		  inVO.setContents(contents);
		  
		  int flag = this.service.doInsert2(inVO);
		  LOG.debug("두인서트2 콜");
		  LOG.debug("3====================");
		  LOG.debug("3=doinsert2의 flag="+flag);
		  LOG.debug("3====================");
		  
		  
		//String으로 반환 list 그 VO의 gettotno 값을 String으로 저장하고 값을준다.
			QnaVO vo = (QnaVO) this.service.doGetno(inVO);
			String boardNo = vo.getQna_no();
			LOG.debug(boardNo);	// m_84
			
			
			fileVO.setFileNo(boardNo);
			fileVO.setOrgNm(array[0]);
			fileVO.setSaveNm(array[1]);
			fileVO.setImgPath(dotPoint);
			fileVO.setFileSize(Integer.parseInt(array[3]));
			fileVO.setExt(array[4]);
			
			int flag1 = service.doFileInsert(fileVO);
			  LOG.debug("4====================");
			  LOG.debug("4 doFileinsert의 flag="+flag);
			  LOG.debug("4====================");
		  //응답처리
		  res.setContentType("text/html;charset=utf-8");
		  PrintWriter out=res.getWriter();
//		  if(flag==1) {
//			  out.println("<script>alert('등록성공!');location.href='/SOMAC/view/qnaboard_index.jsp'</script>");
//		  }else {
//			  out.println("<script>alert('실패!');history.go(-1);</script>");
//		  }

		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = inVO.getTitle()+"이\n등록 되었습니다.";
		  }else {
			  msg = inVO.getTitle()+"이\n등록 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.print(gsonStr);
			
	}
	
	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 LOG.debug("3====================");
		  LOG.debug("3=doUpdate=");
		  LOG.debug("3====================");
		  FileVO fileVO = new FileVO();
		  QnaVO  inVO=new QnaVO();//param
		  //1.param read
		  //2.param QnaVO
		  //3.service 메소드 호출
		  //qna_no
		  String qna_no = StringUtil.nvl(req.getParameter("qna_no"),"");
		  
		  //제목
		  String title = StringUtil.nvl(req.getParameter("title"),"");
		  //작성자/수정자
		  String reg_id = StringUtil.nvl(req.getParameter("reg_id"),"");
		  //내용
		  String firstcontents = StringUtil.nvl(req.getParameter("firstcontents"),"");
		  
		  //이미지
		  String fname = StringUtil.nvl(req.getParameter("fname"),"");
		  
		  //수정일
		  String modDt = StringUtil.nvl(req.getParameter("modDt"),"");
		  LOG.debug("file name:" + fname);
			
			String[] array = fname.trim().split(",");
			
			for(int i=0;i<array.length;i++) {
				LOG.debug(array[i]);
			}
			
			int dot= array[1].indexOf(".");
			
			String dotPoint=array[1].substring(0,dot);
		  
		  inVO.setQna_no(qna_no);
		  inVO.setTitle(title);
		  inVO.setMemberId(reg_id);
		  inVO.setContents(firstcontents);
		  inVO.setModDt(modDt);
		  
		  int flag = this.service.doUpdate(inVO);
		  LOG.debug("3====================");
		  LOG.debug("3=flag="+flag);
		  LOG.debug("3====================");
		  
		  
			//String으로 반환 list 그 VO의 gettotno 값을 String으로 저장하고 값을준다.
			
			String boardNo = qna_no;
			LOG.debug(boardNo);	// m_84
			
			
			fileVO.setFileNo(boardNo);
			fileVO.setOrgNm(array[0]);
			fileVO.setSaveNm(array[1]);
			fileVO.setImgPath(dotPoint);
			fileVO.setFileSize(Integer.parseInt(array[3]));
			fileVO.setExt(array[4]);
		  
			service.doFileDel(fileVO);
			
			
			int flag1 = service.doFileInsert(fileVO);
			 LOG.debug("4====================");
			  LOG.debug("4 doFileinsert의 flag="+flag);
			  LOG.debug("4====================");
		  
		  
		  //응답처리
		  res.setContentType("text/html;charset=utf-8");
		  PrintWriter out=res.getWriter();
//		  if(flag==1) {
//			  out.println("<script>alert('등록성공!');location.href='/DaoWEB/board/board_list.jsp'</script>");
//		  }else {
//			  out.println("<script>alert('실패!');history.go(-1);</script>");
//		  }

		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = inVO.getTitle()+"이\n수정 되었습니다.";
		  }else {
			  msg = inVO.getTitle()+"이\n수정 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);

	}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.debug("=======================");
		LOG.debug("=doDel=");
		LOG.debug("=======================");
		
	
		QnaVO inVO =new QnaVO();
		String qna_no = StringUtil.nvl(req.getParameter("qna_no"));
		inVO.setQna_no(qna_no);
		
		int flag = this.service.doDelete(inVO);
		
		Gson gson=new Gson();
		String msg = "";
		String gsonString = "";
		MessageVO  msgVO=new MessageVO();
		
		if(flag>0) {
			msg = "삭제되었습니다";
		}else {
			msg = "삭제실패.";
		}
		
		msgVO.setMsgId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		gsonString = gson.toJson(msgVO);
		LOG.debug("gsonString="+gsonString);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(gsonString);
		
		
	}

}
