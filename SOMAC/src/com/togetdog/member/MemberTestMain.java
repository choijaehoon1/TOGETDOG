package com.togetdog.member;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

public class MemberTestMain {
	private final Logger LOG = Logger.getLogger(MemberTestMain.class);

	//Test Data
	private MemberVO memberVO01;
	private MemberDao dao;
	
	public MemberTestMain() {
		memberVO01 = new MemberVO("CJH","1234","주정현"
				,"010-7979-8541","dfjkg@naver.com","1","VVV",""
				,"VVV","");
		dao = new MemberDao();
		
	}
	
	
	
	
	
	public void doInsert() {
		int flag = dao.doInsert(memberVO01);
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
		int flag = dao.doDelete(memberVO01);
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
		memberVO01.setMemberId("LJW");
		MemberVO outVO = (MemberVO) dao.doSelectOne(memberVO01);
		
		if(null != outVO) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("=====================");
		
	}
	
	public void doUpdate() {
		memberVO01 = new MemberVO("abcd","6789","zzz"
		,"010-5220-0860","qereqr@naver.com","9","zxczxc",""
		,"zxczxc","");
		
		int flag = dao.doUpdate(memberVO01);
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
		memberVO01.setName("주정현");
		memberVO01.setPhone("010-9878-7777");
		MemberVO outVO = (MemberVO) dao.doSelectOneId(memberVO01);
		
		if(null != outVO) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("=====================");
		
	}
	
	
	
	public void doSelectOnePw() {
		memberVO01.setMemberId("VVVV");
		memberVO01.setPhone("010-7979-8541");
		MemberVO outVO = (MemberVO) dao.doSelectOnePw(memberVO01);
		
		if(null != outVO) {
			LOG.debug("----------------------------");
			LOG.debug("성공");
			LOG.debug("----------------------------");
		}else {
			LOG.debug("----------------------------");
			LOG.debug("실패");
			LOG.debug("----------------------------");
		}
		
		LOG.debug("=====================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("=====================");
		
	}
	
	public static void main(String[] args) {
		MemberTestMain memberTestMain=new MemberTestMain();
		
		//memberTestMain.doInsert();
		//memberTestMain.doDelete();
		//memberTestMain.doSelectOne();
		//memberTestMain.doUpdate();
		
	
		memberTestMain.doSelectOneId();
		//memberTestMain.doSelectOnePw();
	}

}




























