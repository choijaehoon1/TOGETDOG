package com.togetdog.member;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.member.MemberVO;
import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.SearchVO;

public class MemberServiceMain {
	private MemberVO memberVO;
	private final Logger LOG = Logger.getLogger(MemberServiceMain.class);

	//Test Data
	private MemberVO memberVO01;
	private MemberService memberService;
	public MemberServiceMain() {
		memberService = new MemberService();
		memberVO01 = new MemberVO("jop123123","12341234","최재훈","010-4567-7777","jaehun@naver.com","9","CJH","","CJH","");
	}
	
	public void loginCheck() {
		//hr_0000001 /1234   
		memberVO.setMemberId("LJW");
		memberVO.setPassword("1234"
				+ "");
		MessageVO msg = (MessageVO) memberService.loginCheck(memberVO);
		LOG.debug("===================");
		LOG.debug("msg"+msg);
		LOG.debug("===================");
	}
	
	

	public void doInsert() {
		int flag = memberService.doInsert(memberVO01);
		if(1==flag) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");			
		}
	}
	
	public void doDelete() {
		int flag = memberService.doDelete(memberVO01);
		if(1==flag) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");			
		}
	}
	
	public void doSelectOne() {
		memberVO01.setMemberId("지또술");
		MemberVO vo =(MemberVO) memberService.doSelectOne(memberVO01);
		
		
		if(null != memberVO01) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+vo);
		LOG.debug("=====================");
		
	}
	
	public void doUpdate() {
		memberVO01 = new MemberVO("AAB","6789","zzz"
		,"010-5220-0860","qereqr@naver.com","9","zxczxc",""
		,"zxczxc","");
		
		int flag = memberService.doUpadte(memberVO01);
		if(1==flag) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
	}
	}
	
	public void doSelectOneId() {
		memberVO01.setName("이재원");
		memberVO01.setPhone("010-5220-0860");
		memberVO01 =(MemberVO) memberService.doSelectOneId(memberVO01);
		
		
		if(null != memberVO01) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+memberVO01);
		LOG.debug("=====================");
		
	}
	
	
	
	public void doSelectOnePw() {
		memberVO01.setMemberId("이재원");
		memberVO01.setPhone("010-5220-0860");
		memberVO01 =(MemberVO) memberService.doSelectOnePw(memberVO01);
		
		
		if(null != memberVO01) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+memberVO01);
		LOG.debug("=====================");
		
	}
	
	public static void main(String[] args) {
		MemberServiceMain memberTestMain=new MemberServiceMain();
		
		memberTestMain.doInsert();
		//memberTestMain.doDelete();
		//memberTestMain.doSelectOne();
		//memberTestMain.doUpdate();'
		//memberTestMain.doSelectOneId();
		//memberTestMain.doSelectOnePw();
	}

}




























