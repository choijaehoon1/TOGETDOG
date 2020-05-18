package com.togetdog.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.togetdog.member.MemberService;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.ContHandler;
import com.togetdog.cmn.StringUtil;
import com.togetdog.member.MemberVO;


@WebServlet(description = "회원가입", urlPatterns = {"/view/member.do"})
public class MemberCont extends HttpServlet implements ContHandler{
	   private static final long serialVersionUID = 1L;
	 
		 /** MemberService 객체*/
		   private MemberService memberService; 
		       
		    /**
		     * @see HttpServlet#HttpServlet()
		     */
		    public MemberCont() {
		      
		    	memberService = new MemberService();
		    }

		    
		    
		    /**
		     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		     */
		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       // TODO Auto-generated method stub
		       //response.getWriter().append("Served at: ").append(request.getContextPath());
		       LOG.debug("doGet------");
		       serviceHandler(request, response);
		    }

		    /**
		     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		     */
		    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       LOG.debug("1. doPost------");
		       serviceHandler(request, response);
		    }
		 
		    
		    
		@Override
		public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			 //1.work_div: 작업구분
		      //2.request Encoding: utf-8
		      //3.기능별 분해
		      
		      //거래분기
		      //do_update:수정
		      //do_delete:삭제
		      //do_insert:등록
		      //do_retrieve:목록조회
		      //do_selectOne:단건조회
		      
		      req.setCharacterEncoding("utf-8"); //request의 인코딩: utf-8
		      String workDiv = StringUtil.nvl(req.getParameter("work_div"));//request에 null들어오면 ->""
		      LOG.debug("2=============");
		      LOG.debug("2workDiv=" + workDiv);
		      LOG.debug("2=============");
		      switch(workDiv) {   //거래분기
		         case "do_insert": //단건등록
		               this.doInsert(req, res);
		            break;
		         case "do_login":
		              this.doLogin(req, res);
		           break;
		         case "do_selectOneId":
		              this.doSelectOneId(req, res);
		           break;
		         case "do_selectOnePw":
		              this.doSelectOnePw(req, res);
		           break;
		         case "do_delete":
		              this.doDel(req, res);
		           break;
		         case "do_idcheck":
		              this.doidcheck(req, res);
		           break;
		         case "do_kaInsert":
		              this.dokaInsert(req, res);
		           break;
		         case "do_kdlogin":
		              this.dokalogin(req, res);
		           break;
		         case "do_logout":
		              this.doLogout(req, res);
		           break;
		         default:
		               LOG.debug("2=============");
		               LOG.debug("2작업구분을 확인하세요.workDiv=" + workDiv);
		               LOG.debug("2=============");
		            break;
		      }
		      
		      
		   }
		
		/**
		 * 
		*Method Name:doidcheck
		*작성일: 2020. 3. 12.
		*작성자: sist
		*설명: 로그인 처리
		*@param req
		*@param res
		*@throws ServletException
		*@throws IOException
		 */
	    public void dokalogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	    	//1.param read
	        //2.param MemberVO
	        //3.service 메소드 호출
	        MemberVO inVO=new MemberVO();
	        MemberVO outVO=new MemberVO();
	        
	       String memberId = StringUtil.nvl(req.getParameter("member_id"));
	       inVO.setMemberId(memberId);
	       
	       LOG.debug("======================");
	       LOG.debug("-inVO-"+inVO);
	       LOG.debug("======================");
	       MessageVO checkmsg = (MessageVO)this.memberService.dokalogin(inVO);
	       HttpSession httpSession =  req.getSession();
	       if(checkmsg.getMsgId().equals("1")) {
	    	   outVO = (MemberVO) memberService.doSelectOne(inVO);
	    	   httpSession.setAttribute("user", outVO);
	       }else {
	    	   //session삭제 : logout
	    	   httpSession.invalidate();
	       }
	       
	       //checkmsg -> JSON
	       Gson gson=new Gson();
	       res.setContentType("text/html;charset=UTF-8");
	       PrintWriter out = res.getWriter();
	       String gsonStr = "";
	       gsonStr = gson.toJson(checkmsg);
	       LOG.debug("gsonStr"+gsonStr);
	       out.print(gsonStr);
	       
	       
	    }
		
		public void dokaInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			  LOG.debug("3=============");
		      LOG.debug("3dokaInsert.workDiv=");
		      LOG.debug("3=============");
		      
		      MemberVO inVO = new MemberVO();
		      MemberVO  outVO=new MemberVO();
		      //1.param read
		      //2.param BoardVO
		      //3.service 메소드 호출
		      
		  
		      //제목
		      String memberId = StringUtil.nvl(req.getParameter("member_id"),"");
		      //작성자/수정자
		      
		      System.out.println("memberId:"+memberId);
		      
		      String password = StringUtil.nvl(req.getParameter("password"),"");
		      
		      String name = StringUtil.nvl(req.getParameter("name"),"");
		      
		      String phone = StringUtil.nvl(req.getParameter("phone"),"");
		      
		      String email = StringUtil.nvl(req.getParameter("email"),"");
		     
		      String author = "";
		      //TODO
		      //로그인 처리후 session으로 변환
		      inVO.setMemberId(memberId);
		      inVO.setPassword(password);
		      inVO.setName(name);
		      inVO.setPhone(phone);
		      inVO.setEmail(email);
		      inVO.setAuthor(author);
		      inVO.setRegId(memberId);
		      inVO.setModId(memberId);
		      
		      
		      int flag = this.memberService.dokaInsert(inVO);
		      LOG.debug("3=============");
		      LOG.debug("3flagv=" +flag);
		      LOG.debug("3=============");
		      
		      MessageVO checkmsg = (MessageVO)this.memberService.loginCheck(inVO);
		       HttpSession httpSession =  req.getSession();
		       if(checkmsg.getMsgId().equals("1")) {
		    	   outVO = (MemberVO) memberService.doSelectOne(inVO);
		    	   httpSession.setAttribute("user", outVO);
		       }else {
		    	   //session삭제 : logout
		    	   httpSession.invalidate();
		      
		      
		      //응답처리
		      res.setContentType("text/html;charset=utf-8");
		      PrintWriter out = res.getWriter();
		     // if(flag==1) {
		     //    out.println("<script>alert('등록성공!');location.href='/SOMAC/view/login.jsp'</script>");
		     // } else {
	        //   out.println("<script>alert('실패!');history.go(-1);</script>");
		    //  }
		      
		      
		      
		      String msg = "";
		      Gson gson = new Gson();
		      if(flag == 1) {
		         msg = inVO.getMemberId() + "\n등록 되었습니다.";
		      } else {
		         msg = inVO.getMemberId()+ " \n아이디가 사용중입니다.";
		      }
		      
		      String gsonStr= gson.toJson(new MessageVO(String.valueOf(flag), msg));
		      LOG.debug("====================");
		      LOG.debug("gsonStr=" + gsonStr);
		      LOG.debug("====================");
		      out.println(gsonStr);
		      
		      //RequestDispatcher dispatcher = req.getRequestDispatcher("/view/login.jsp");
		      //dispatcher.forward(req, res);
		       }
		}
		
		
		/**
		 * 
		*Method Name:doidcheck
		*작성일: 2020. 3. 12.
		*작성자: sist
		*설명: 로그인 처리
		*@param req
		*@param res
		*@throws ServletException
		*@throws IOException
		 */
	    public void doidcheck(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	        //1.param read
	        //2.param MemberVO
	        //3.service 메소드 호출
	        MemberVO inVO=new MemberVO();
	        MemberVO  outVO=new MemberVO();
	       String memberId = StringUtil.nvl(req.getParameter("member_id"));
	       inVO.setMemberId(memberId);
	       
	       MessageVO checkMsg = (MessageVO)this.memberService.doidcheck(inVO);
	        if(checkMsg.getMsgId().equals("1")) {
	        	outVO = (MemberVO) memberService.doSelectOne(inVO);
	        	HttpSession  httpSession  =req.getSession();
	        	httpSession.setAttribute("user", outVO);
	        }
	        
	        //checkMsg -> JSON
	        Gson gson=new Gson();
	        res.setContentType("text/html;charset=UTF-8");
	        PrintWriter  out = res.getWriter();
	        String gsonStr = "";
	        gsonStr = gson.toJson(checkMsg);
	        LOG.debug("gsonStr:"+gsonStr);
	        out.print(gsonStr);
	    }
	    
		
		
		/**
		 * 
		*Method Name:doLogin
		*작성일: 2020. 3. 12.
		*작성자: sist
		*설명: 로그인 처리
		*@param req
		*@param res
		*@throws ServletException
		*@throws IOException
		 */
	    public void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	        //1.param read
	        //2.param MemberVO
	        //3.service 메소드 호출
	        MemberVO inVO=new MemberVO();
	        MemberVO outVO=new MemberVO();
	        
	       String memberId = StringUtil.nvl(req.getParameter("member_id"));
	       String password = StringUtil.nvl(req.getParameter("password"));
	       inVO.setMemberId(memberId);
	       inVO.setPassword(password);
	       
	       LOG.debug("======================");
	       LOG.debug("-inVO-"+inVO);
	       LOG.debug("======================");
	       MessageVO checkmsg = (MessageVO)this.memberService.loginCheck(inVO);
	       HttpSession httpSession =  req.getSession();
	       if(checkmsg.getMsgId().equals("1")) {
	    	   outVO = (MemberVO) memberService.doSelectOne(inVO);
	    	   httpSession.setAttribute("user", outVO);
	       }else {
	    	   //session삭제 : logout
	    	   httpSession.invalidate();
	       }
	       
	       //checkmsg -> JSON
	       Gson gson=new Gson();
	       res.setContentType("text/html;charset=UTF-8");
	       PrintWriter out = res.getWriter();
	       String gsonStr = "";
	       gsonStr = gson.toJson(checkmsg);
	       LOG.debug("gsonStr"+gsonStr);
	       out.print(gsonStr);
	       
	       
	    }
		
	    /**
	     * 
	     *Method Name:doLogout
	     *작성일: 2020. 3. 13.
	     *작성자: sist
	     *설명:
	     *@param req
	     *@param res
	     *@throws ServletException
	     *@throws IOException
	     */
	    public void doLogout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	    	//1.param read
			//2.param MemberVO
			//3.service 메소드 호출
	        
	        HttpSession  httpSession  =req.getSession();
	        if(null !=httpSession) {
	        	httpSession.removeAttribute("user");
	        	httpSession.invalidate();
	        	LOG.debug("httpSession:"+httpSession);
	        }
	        String path = "/view/login.jsp";
	        RequestDispatcher dequestDispatcher=req.getRequestDispatcher(path);
	        dequestDispatcher.forward(req, res);
	        
	    }
		
		
		@Override
		public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			  LOG.debug("3=============");
		      LOG.debug("3doInsert.workDiv=");
		      LOG.debug("3=============");
		      
		      MemberVO inVO = new MemberVO();
		      //1.param read
		      //2.param BoardVO
		      //3.service 메소드 호출
		      
		  
		      //제목
		      String memberId = StringUtil.nvl(req.getParameter("member_id"),"");
		      //작성자/수정자
		      
		      System.out.println("memberId:"+memberId);
		      
		      String password = StringUtil.nvl(req.getParameter("password"),"");
		      
		      String name = StringUtil.nvl(req.getParameter("name"),"");
		      
		      String phone = StringUtil.nvl(req.getParameter("phone"),"");
		      
		      String email = StringUtil.nvl(req.getParameter("email"),"");
		     
		      String author = StringUtil.nvl(req.getParameter("author"),"");

		      String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
		      
		      String modId = StringUtil.nvl(req.getParameter("mod_id"),"");
		      //TODO
		      //로그인 처리후 session으로 변환
		      inVO.setMemberId(memberId);
		      inVO.setPassword(password);
		      inVO.setName(name);
		      inVO.setPhone(phone);
		      inVO.setEmail(email);
		      inVO.setAuthor(author);
		      inVO.setRegId(memberId);
		      inVO.setModId(memberId);
		      
		      
		      int flag = this.memberService.doInsert(inVO);
		      LOG.debug("3=============");
		      LOG.debug("3flagv=" +flag);
		      LOG.debug("3=============");
		    
		      
		      
		      
		      //응답처리
		      res.setContentType("text/html;charset=utf-8");
		      PrintWriter out = res.getWriter();
		     // if(flag==1) {
		     //    out.println("<script>alert('등록성공!');location.href='/SOMAC/view/login.jsp'</script>");
		     // } else {
	        //   out.println("<script>alert('실패!');history.go(-1);</script>");
		    //  }
		      
		      String msg = "";
		      Gson gson = new Gson();
		      if(flag == 1) {
		         msg = inVO.getMemberId() + "\n등록 되었습니다.";
		      } else {
		         msg = inVO.getMemberId()+ " \n아이디가 사용중입니다.";
		      }
		      
		      String gsonStr= gson.toJson(new MessageVO(String.valueOf(flag), msg));
		      LOG.debug("====================");
		      LOG.debug("gsonStr=" + gsonStr);
		      LOG.debug("====================");
		      out.println(gsonStr);
		      
		      //RequestDispatcher dispatcher = req.getRequestDispatcher("/view/login.jsp");
		      //dispatcher.forward(req, res);
		      
		}
	
		
		public void doSelectOneId(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			  //1.param
		      //2.param to set vo
		      //3.service call
		      //4.request set
		      //5.forward
		      
		      MemberVO inVO = new MemberVO();
		      
		      LOG.debug("doSelectOne");
		      String name = StringUtil.nvl(req.getParameter("name"));
		      String phone = StringUtil.nvl(req.getParameter("phone"));
		      LOG.debug("name=" + name);
		      LOG.debug("phone=" + phone);
		     
		      inVO.setName(name);
		      inVO.setPhone(phone);
		      
		      MemberVO outVO = (MemberVO)memberService.doSelectOneId(inVO);
		      PrintWriter out = res.getWriter();
		      out.print (outVO.getMemberId()); //request에 데이터 담기
		      
		      Gson gson=new Gson();
		       res.setContentType("text/html;charset=UTF-8");
		       String gsonStr = "";
		       gsonStr = gson.toJson(outVO);
		       LOG.debug("gsonStr"+gsonStr);
		       //out.print(gsonStr);
		      
		}
		
		public void doSelectOnePw(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			  //1.param
		      //2.param to set vo
		      //3.service call
		      //4.request set
		      //5.forward
		      
		      MemberVO inVO = new MemberVO();
		      
		      LOG.debug("doSelectOne");
		      String member_id = StringUtil.nvl(req.getParameter("member_id"));
		      String name = StringUtil.nvl(req.getParameter("name"));
		      LOG.debug("member_id=" + member_id);
		      LOG.debug("name=" + name);
		     
		      inVO.setMemberId(member_id);
		      inVO.setName(name);
		      
		      MemberVO outVO = (MemberVO)memberService.doSelectOnePw(inVO);
		      PrintWriter out = res.getWriter();
		      out.print (outVO.getPassword()); //request에 데이터 담기
		      
		      LOG.debug(outVO.getPassword());
		      
		      Gson gson=new Gson();
		       res.setContentType("text/html;charset=UTF-8");
		       String gsonStr = "";
		       gsonStr = gson.toJson(outVO);
		       LOG.debug("gsonStr"+gsonStr);
		       //out.print(gsonStr);
		      
		}
		
		
		@Override
		public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			LOG.debug("=======================");
			LOG.debug("=doDel=들어왔씁니");
			LOG.debug("=======================");
			
			//1.vo 변수 선언
			//2.param read
			//3.vo에 param set
			//4.service호출
			//5.Gson message
			//6.forward
		
			MemberVO inVO =new MemberVO();
			String member_id = StringUtil.nvl(req.getParameter("member_id"));
			inVO.setMemberId(member_id);;
			
			int flag = this.memberService.doDelete(inVO);
			
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

		@Override
		public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}
	}

