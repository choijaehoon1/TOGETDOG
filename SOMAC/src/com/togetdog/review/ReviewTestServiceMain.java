package com.togetdog.review;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.community.CommunityVO1;
import com.togetdog.filemng.FileVO;
import com.togetdog.member.MemberVO;

public class ReviewTestServiceMain {

	private final Logger LOG = Logger.getLogger(this.getClass());
	
	private ReviewService revService;
	private ReviewDao dao;
	private ReviewVO reviewVO01;	
	private AdoptionVO adoptVO01;
	private AdoptImgVO aimgVO01;
	private QnAVO qnaVO01;
	private CommuVO communityVO01;
	private MemberVO memVO01;
	private JoinVO joinVO01;
	private FileVO fileVO01;
	private CommunityVO1 commVO;//게시판 단건조회용
	
	static String fileNo = null;
	
	public ReviewTestServiceMain() {
		revService = new ReviewService();
		dao = new ReviewDao();
		reviewVO01 = new ReviewVO("r_1","입양코드_s","디두","제목1","내용1","20200204","글등록일","글수정일",0,"Y");
		adoptVO01 = new AdoptionVO();
		aimgVO01 = new AdoptImgVO();
		qnaVO01 = new QnAVO();
		communityVO01 = new CommuVO();	
		memVO01 = new MemberVO();
		joinVO01 = new JoinVO();
		fileVO01 = new FileVO("d","원본파일명","저장파일명","이미지경로",22,"ㅇ","디두","20200204");
		commVO = new CommunityVO1();
	}
	
	
	//등록
	public void doInsert() {
		int mainFlag = revService.doInsert(reviewVO01);
		if(mainFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	public void doFindNo() {
		FileVO outVO = (FileVO) revService.doFindNo(reviewVO01);
		
		fileNo = outVO.getFileNo();
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
	}
	
	public void doInsertFile() {
		fileVO01.setFileNo(fileNo);
		int mainFlag = revService.doInsertFile(fileVO01);
		if(mainFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	//수정
	public void doUpdate() {
		reviewVO01.setTitle("제목2");
		reviewVO01.setContents("내용2_2");
		reviewVO01.setFamilyDt("19860930");
		reviewVO01.setRevNo("r_24");
		
		int updateFlag = revService.doUpdate(reviewVO01);
		
		if(updateFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	public void doUpdateMem() {
		memVO01.setPassword("1234");
		memVO01.setName("디두");;
		memVO01.setPhone("010-9999-7777");
		memVO01.setEmail("soju@naver.com");
		memVO01.setAuthor("1");
		memVO01.setMemberId("kimjh");
		
		int updateFlag = revService.doUpdateMem(memVO01);
		
		if(updateFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	
	//삭제
	public void doDelete() {
		reviewVO01.setRevNo("r_33");
		
		int delFlag = revService.doDelete(reviewVO01);
		
		if(delFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}

	
	
	//후기 단건조회
	public void doSelectOne() {
		joinVO01.setRevNo("r_1");
		JoinVO outVO = (JoinVO) revService.doSelectOne(joinVO01);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
		
	}
	
	//게시판 단건 조회	
	public void doSelectOneC() {
		commVO.setTotNo("m_71");
		CommunityVO1 outVO = (CommunityVO1) revService.doSelectOneC(commVO);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
		
	}
	
	//회원 조회
	public void doSelectOneMem() {
		memVO01.setMemberId("kimjh");
		MemberVO outVO = (MemberVO) revService.doSelectOneMem(memVO01);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
		
	}
	
	//후기글 작성용 doSelectOne
	public void doSelectOneInsert() {
		adoptVO01.setApplyNo("A_00");
		
		AdoptionVO outVO = (AdoptionVO) revService.doSelectOneInsert(adoptVO01);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
	}
	
	public void getAdoptImg() {
		aimgVO01.setMemberId("kimjh");
		
		AdoptImgVO outVO = (AdoptImgVO) revService.getAdoptImg(aimgVO01);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
	}
	
	//입양신청내역 다건조회
	public void doRetrieve() {		
		
		adoptVO01.setMemberId("kimjh");
		adoptVO01.setPageSize(10);
		adoptVO01.setPageNum(1);
		List<AdoptionVO> list = (List<AdoptionVO>) revService.doRetrieve(adoptVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(AdoptionVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//입양신청내역 - 심사중 리스트
	public void doRetrieveP() {		
		
		adoptVO01.setMemberId("kimjh");
		adoptVO01.setPageSize(10);
		adoptVO01.setPageNum(1);
		List<AdoptionVO> list = (List<AdoptionVO>) revService.doRetrieveP(adoptVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(AdoptionVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//입양신청내역 - 입양가능 리스트
	public void doRetrieveS() {		
		
		adoptVO01.setMemberId("kimjh");
		adoptVO01.setPageSize(10);
		adoptVO01.setPageNum(1);
		List<AdoptionVO> list = (List<AdoptionVO>) revService.doRetrieveS(adoptVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(AdoptionVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//입양신청내역 - 입양불가 리스트
	public void doRetrieveR() {		
		
		adoptVO01.setMemberId("kimjh");
		adoptVO01.setPageSize(10);
		adoptVO01.setPageNum(1);
		List<AdoptionVO> list = (List<AdoptionVO>) revService.doRetrieveR(adoptVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(AdoptionVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//게시판 다건조회
	public void doRetrieveCom() {
		communityVO01.setMemberId("kimjh");
		communityVO01.setPageSize(10);
		communityVO01.setPageNum(1);
		List<CommuVO> list = (List<CommuVO>) revService.doRetrieveCom(communityVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(CommuVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//후기 다건조회
	public void doRetrieveRev() {
		joinVO01.setMemberId("kimjh");
		joinVO01.setPageSize(10);
		joinVO01.setPageNum(1);
		List<JoinVO> list = (List<JoinVO>) revService.doRetrieveRev(joinVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(JoinVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//QNA다건조회
	public void doRetrieveQnA() {
		qnaVO01.setMemberId("kimjh");
		qnaVO01.setPageSize(10);
		qnaVO01.setPageNum(1);
		List<QnAVO> list = (List<QnAVO>) revService.doRetrieveQnA(qnaVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(QnAVO vo : list) {
		
			LOG.debug(vo);
		}
		
		LOG.debug("************************************************");
	}
	
	
	public static void main(String[] args) {
		ReviewTestServiceMain serviceMain = new ReviewTestServiceMain();
		//serviceMain.doInsert();
		//serviceMain.doUpdate();
		//serviceMain.doUpdateMem();
		//serviceMain.doDelete();		
		//serviceMain.doSelectOne();
		//serviceMain.doSelectOneC();
		//serviceMain.doSelectOneMem();
		//serviceMain.doSelectOneInsert();
		//serviceMain.getAdoptImg();
		//serviceMain.doRetrieve();	
		//serviceMain.doRetrieveP();//심사중 리스트 조회
		//serviceMain.doRetrieveS();
		//serviceMain.doRetrieveR();
		serviceMain.doRetrieveRev();
		//serviceMain.doRetrieveCom();
		//serviceMain.doRetrieveQnA();
		//serviceMain.doFindNo();	
		//serviceMain.doInsertFile();
		
	}

}
