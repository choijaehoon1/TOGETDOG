<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 2. 21.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>


<%@page import="com.togetdog.qna.QnaVO"%>
<%@page import="com.togetdog.review.JoinVO"%>
<%@page import="com.togetdog.cmn.StringUtil"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@page import="com.togetdog.review.ReviewService"%>
<%@page import="com.togetdog.review.QnAVO"%>
<%@page import="com.togetdog.review.CommuVO"%>
<%@page import="com.togetdog.review.ReviewVO"%>
<%@page import="com.togetdog.review.ReviewTestServiceMain"%>
<%@page import="java.util.List"%>
<%@page import="com.togetdog.review.ReviewDao"%>
<%@page import="com.togetdog.review.AdoptionVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    errorPage="/cmn/error.jsp"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="/cmn/common.jsp" %>    
    
<%

        //paging
        int maxNumRev     = 0;//후기 페이징 총글수 
        int currPageNoRev = 1;//후기 페이징 현재 페이지 
        int rowsPerPageRev = 3;//후기 페이징 page_size 
        
        int maxNumComm     = 0;//게시판 페이징 총글수 
        int currPageNoComm = 1;//게시판 페이징 현재 페이지 
        int rowsPerPageComm = 3;//게시판 페이징 page_size 
        
        int maxNumQnA     = 0;//QnA 페이징 총글수 
        int currPageNoQnA = 1;//QnA 페이징 현재 페이지 
        int rowsPerPageQnA = 3;//QnA 페이징 page_size 
        
        int bottomCount= 5;// 바닥 page_cnt
        
        String url = HR_PATH+"/view/mypage.do";//호출 URL
        String scriptNameR = "doSearchPageR";//JavaScript함수: doSearchPage(url,no)
        String scriptNameC = "doSearchPageC";//JavaScript함수: doSearchPage(url,no)
        String scriptNameQ = "doSearchPageQ";//JavaScript함수: doSearchPage(url,no)
        
        
        //회원정보 수정
           MemberVO mvo = (MemberVO)session.getAttribute("user");
           String id = mvo.getMemberId();
           
           MemberVO memSelect = (MemberVO)request.getAttribute("memSelect");  
           
          //입양신청 내역
          String pageNumAdopt = "1";
          String pageSizeAdopt = "6";
          
          AdoptionVO adoptVO = (AdoptionVO)request.getAttribute("paramVOAdopt");
          if(adoptVO != null){
               
               pageNumAdopt = String.valueOf(adoptVO.getPageNum());
               pageSizeAdopt = String.valueOf(adoptVO.getPageSize());
                       
          }
          
          List<AdoptionVO> adoptList = (List<AdoptionVO>)request.getAttribute("adoptList"); 
          
          List<AdoptionVO> adoptSList = (List<AdoptionVO>)request.getAttribute("adoptSList");
          
          List<AdoptionVO> adoptRList = (List<AdoptionVO>)request.getAttribute("adoptRList");
            
          List<AdoptionVO> adoptPList = (List<AdoptionVO>)request.getAttribute("adoptPList");
               
            
        //내가 쓴 글 - 게시판                                                                              
                                                                                 
        CommuVO commVO = (CommuVO)request.getAttribute("paramVOComm"); 
        
        List<CommuVO> commList = (List<CommuVO>)request.getAttribute("commList"); 
        

        
        if(commVO != null){
            maxNumComm     = (Integer)request.getAttribute("totalCntComm");
            currPageNoComm = commVO.getPageNum();
            rowsPerPageComm= commVO.getPageSize();
            
            LOG.debug("=======================");
            LOG.debug("url="+url);
            LOG.debug("scriptNameC="+scriptNameC);
            LOG.debug("maxNum="+maxNumComm);
            LOG.debug("currPageNoComm="+currPageNoComm);
            LOG.debug("rowsPerPageComm="+rowsPerPageComm);
            LOG.debug("=======================");
        }
                                
        
        //내가 쓴 글 - 후기
        
        JoinVO revVO = (JoinVO)request.getAttribute("paramVORev"); 
        
        List<JoinVO> revList = (List<JoinVO>)request.getAttribute("revList"); 
        
        if(revVO != null){
            maxNumRev     = (Integer)request.getAttribute("totalCntRev");
            currPageNoRev = revVO.getPageNum();
            rowsPerPageRev = revVO.getPageSize();
            
            LOG.debug("=======================");
            LOG.debug("url="+url);
            LOG.debug("scriptNameR="+scriptNameR);
            LOG.debug("maxNum="+maxNumRev);
            LOG.debug("currPageNo="+currPageNoRev);
            LOG.debug("rowsPerPage="+rowsPerPageRev);
            LOG.debug("=======================");
        }
        
        //내가 쓴 글 - qna 
        
        QnAVO qnaVO = (QnAVO)request.getAttribute("paramVOQnA");
        
        List<QnAVO> qnaList = (List<QnAVO>)request.getAttribute("qnaList");             
        
        if(qnaVO != null){
            maxNumQnA     = (Integer)request.getAttribute("totalCntQnA");
            currPageNoQnA = qnaVO.getPageNum();
            rowsPerPageQnA= qnaVO.getPageSize();
            
            LOG.debug("=======================");
            LOG.debug("url="+url);
            LOG.debug("scriptNameQ="+scriptNameQ);
            LOG.debug("maxNum="+maxNumQnA);
            LOG.debug("currPageNoQnA="+currPageNoQnA);
            LOG.debug("rowsPerPageQnA="+rowsPerPageQnA);
            LOG.debug("=======================");
        }
        %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>TOGETDOG</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/gijgo.css">
    <link rel="stylesheet" href="css/animate.min.css">
    <link rel="stylesheet" href="css/slick.css">
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->

    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
      <style>
       #myPageTitle{
         font-family: 'Do Hyeon', sans-serif;
         font-size: 50px; 
       }
      
       .myPageTitleFont{
         font-family: 'Do Hyeon', sans-serif;
         font-size: 40px; 
       }
       
       .myPageSubTitleFont{
         font-family: 'Do Hyeon', sans-serif; 
         font-size: 35px;
       }

       .contentsTitle{
         font-family: 'Nanum Gothic';
         font-size: 25px;
       }
       
       .cmnContent{
         font-family: 'Nanum Gothic';
         font-size: 20px;
       }
       
       .boardImgsize{
        width: 600px;
        height: 400px;
       }
       
       #imgsizeQ{
        width: 600px;
        height: 400px;
       }
       
       #imgsizeC{
        width: 600px;
        height: 400px;
       }
       
       .imgDiv{
             height: 300px;
        }
       
    </style>
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
    <header>
         <%@ include file="/cmn/togetdog_header.jsp" %>    
    </header>
    <!-- header-end -->
     <!-- bradcam_area  -->
     <div class="bradcam_area bradcam_bg_2">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="bradcam_text text-center">
                            <h3 id="myPageTitle">My Page</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ bradcam_area  -->
        
  <!-- ================ 회원정보 수정 start ================= -->
  
  <section class="contact-section section_padding">
    <div class="container">
      <div class="d-none d-sm-block mb-0 pb-0">
      </div>
      <div class="row">
        <div class="col-xl-6 col-md-4">
          <h3 class="contact-title myPageTitleFont" >회원 정보 수정</h3><hr/>
        </div>
        <div class="col-lg-8">
          <form class="form-contact contact_form" action="<%=HR_PATH %>/view/mypage.do" method="post" id="contactForm" novalidate="novalidate">
            <input type="hidden" name="work_div" id="work_div" value="do_updateMem" />
            <input type="hidden" name="member_id" id="member_id" value="<%=id %>" />
            <div class="row">
              <div class="col-sm-9">
                <div class="form-group">
                                    비밀번호<input class="form-control" name="pw" id="pw" type="password" onfocus="this.placeholder = ''"  value = "${memSelect.password }">
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                                    이름<input class="form-control" name="name" id="name" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = '${memSelect.name }'" value = "${memSelect.name }">
                </div>
              </div>
              
              <div class="col-9">
                <div class="form-group">
                                    핸드폰 번호<input class="form-control" name="phone_num" id="phone_num" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = '${memSelect.phone }'" value = "${memSelect.phone }">
                </div>
              </div>
              
              <div class="col-9">
                <div class="form-group">
                    E-mail<input class="form-control" name="email" id="email" type="email" onfocus="this.placeholder = ''" onblur="this.placeholder = '${memSelect.email }'" value = "${memSelect.email }">
                </div>
              </div>
             
          
            </div>
            <div class="form-group mt-3">
              <button type="button" id="update_btn" class="button button-contactForm btn_4 boxed-btn">변경하기</button>
            </div>
          </form>
        </div>
   
        <div class="col-lg-4">
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-home"></i></span>
            <div class="media-body">
              <h3>Mapo,  Republic of Korea.</h3>
              <p>21, World Cup buk-ro</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-tablet"></i></span>
            <div class="media-body">
              <h3> (02) 336 8546</h3>
              <p>Mon to Fri 9am to 6pm</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-email"></i></span>
            <div class="media-body">
              <h3>helpdog@togetdog.com</h3>
              <p>Send us your story anytime!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- ================ 회원 정보 수정 end ================= -->


 <!-- ================ 입양신청 내역 start ================= -->  
  
 <div class="explorer_europe">
        <div class="container">
            <div class="explorer_wrap">
                <div class="row align-items-center">
                    <div class="col-xl-6 col-md-4" >
                        <h3 class="contact-title myPageTitleFont">입양신청 내역 확인</h3>
                    </div>
                    <div class="col-xl-6 col-md-8">
                        <div class="explorer_tab">
                            <nav>
                                <div class="nav" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab"
                                        href="#nav-home" role="tab" aria-controls="nav-home"
                                        aria-selected="true">전체</a>
                                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
                                        href="#nav-profile" role="tab" aria-controls="nav-profile"
                                        aria-selected="false">입양 가능</a>
                                    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab"
                                        href="#nav-contact" role="tab" aria-controls="nav-contact"
                                        aria-selected="false">입양 불가</a>
                                    <a class="nav-item nav-link" id="nav-contact-tab2" data-toggle="tab"
                                        href="#nav-contact2" role="tab" aria-controls="nav-contact"
                                        aria-selected="false">심사 진행중</a>

                                </div>
                            </nav>

                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-content" id="nav-tabContent">
            
             <!-- **************전체************** -->
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <form action='<%=HR_PATH%>/view/mypage.do' name="reviewInsertFrm1" id="reviewInsertFrm1" method="get">
                    <input type="hidden" name="work_div"/>
                    <input type="hidden" name="page_num" />
                    <input type="hidden" name="apply_no"/>
                    <div class="row">
                    <c:choose>
                        <c:when test="${adoptList.size()>0 }"> 
                            <c:forEach var="vo" items="${adoptList}">
                                <div class="col-xl-4 col-lg-4 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                            <img class="imgDiv" src="<c:out value="${vo.popFile }"></c:out>" alt="">
                                        </div>
                                        <div class="explorer_bottom d-flex">
                                            <div class="explorer_info">
                                                <p>유기번호&nbsp;<br></p> 
                                                <h3 class="contentsTitle"><c:out value="${vo.desertionNo }"></c:out></h3><hr/>
                                                                                       
                                                <p>입양신청일&nbsp; <c:out value="${vo.regDt}"></c:out></p>
                                                <p>신청이유&nbsp;&nbsp;&nbsp;&nbsp;  <c:out value="${vo.applyReason }"></c:out></p>
                                                <p>심사일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.aregDt }"></c:out></p>
                                                
                                                <c:set var="S" value="입양 가능"/>
                                                <c:set var="R" value="입양 불가"/>
                                                     <c:choose>
                                                        <c:when test="${vo.applyState == S }">
                                                            <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${vo.applyState }"></c:out></p> 
                                                            <table id="revbtnTable1">
                                                                <tr>
                                                                  <hr/>                                                  
                                                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="review_btn1" class="genric-btn danger circle">후기 작성</button></td>
                                                                  <td style="display:none;"><c:out value="${vo.applyNo}"></c:out></td> 
                                                                 </tr>
                                                             </table>   
                                                        </c:when>
                                                        <c:when test="${vo.applyState == R }">
                                                            <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="<font color='red'>${vo.applyState }</font>" escapeXml="false"></c:out></p>
                                                        </c:when>
                                                        <c:otherwise>     
                                                            <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.applyState }"></c:out></p>  
                                                        </c:otherwise>
                                                    </c:choose>
                                                
                                                <ul>
                                                    <li></li>
                                                    <li></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>입양신청한 내역이 없습니다.</li>
                        </c:otherwise>
                    </c:choose> 
                    </div>
                    </form>
                </div>
                <!-- **************전체 end************** -->
                
                
                <!-- **************입양가능************** -->
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <form action='<%=HR_PATH%>/view/mypage.do' name="reviewInsertFrm2" id="reviewInsertFrm2" method="get">
                    <input type="hidden" name="work_div"/>
                     <input type="hidden" name="page_num" />
                    <input type="hidden" name="apply_no"/>
                    <div class="row">
                    <c:choose>
                        <c:when test="${adoptSList.size()>0 }">
                            <c:forEach var="vo" items="${adoptSList }">
                                <div class="col-xl-4 col-lg-4 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                            <img class="imgDiv" src="<c:out value="${vo.popFile }"></c:out>" alt="">
                                        </div>
                                        <div class="explorer_bottom d-flex">                                            
                                            <div class="explorer_info">
                                            
                                                <p>유기번호&nbsp;<br> 
                                                <h3 class="contentsTitle"><c:out value="${vo.desertionNo }"></c:out></h3><hr/>
                                                 </p>
                                                                                        
                                                <p>입양신청일&nbsp; <c:out value="${vo.regDt}"></c:out></p>
                                                <p>신청이유&nbsp;&nbsp;&nbsp;&nbsp;  <c:out value="${vo.applyReason }"></c:out></p>
                                                <p>심사일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.aregDt }"></c:out></p>
                                                <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.applyState }"></c:out></p>
                                                <table id="revbtnTable2">
                                                    <tr>
                                                        <hr/>                                              
                                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="review_btn1" class="genric-btn danger circle">후기 작성</button></td>
                                                        <td style="display:none;"><c:out value="${vo.applyNo}"></c:out></td>                                                       
                                                    </tr>
                                                </table>
                                                
                                                <ul>
                                                    <li></li>
                                                    <li></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>입양 가능한 내역이 없습니다.</li>
                        </c:otherwise>
                    </c:choose>    
                    </div>
                    </form>
                </div>
                <!-- **************입양가능 end************** -->
                
                
                <!-- **************입양불가************** -->
                  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <div class="row">
                    <c:choose>
                        <c:when test="${adoptRList.size()>0 }">
                            <c:forEach var="vo" items="${adoptRList }">
                                <div class="col-xl-4 col-lg-4 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                            <img class="imgDiv" src="<c:out value="${vo.popFile }"></c:out>" alt="">
                                        </div>
                                        <div class="explorer_bottom d-flex">
                                            <div class="explorer_info">
                                                <p>유기번호&nbsp;<br> 
                                                <h3 class="contentsTitle"><c:out value="${vo.desertionNo }"></c:out></h3><hr/>
                                                 </p>
                                                
                                                <p>입양신청일&nbsp; <c:out value="${vo.regDt}"></c:out></p>
                                                <p>신청이유&nbsp;&nbsp;&nbsp;&nbsp;  <c:out value="${vo.applyReason }"></c:out></p>
                                                <p>심사일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.aregDt }"></c:out></p>
                                                <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="<font color='red'>${vo.applyState }</font>" escapeXml="false"></c:out> </p>
                                                <ul>
                                                    <li> </li>
                                                    <li></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>입양 불가능한 내역이 없습니다.</li>
                        </c:otherwise>
                    </c:choose>    
                    </div>
                </div>
                <!-- **************입양불가 end************** -->
                
                
                <!-- **************심사진행중************** -->
                <div class="tab-pane fade" id="nav-contact2" role="tabpanel" aria-labelledby="nav-contact-tab2">
                    <div class="row">
                    <c:choose>
                        <c:when test="${adoptPList.size()>0 }">
                            <c:forEach var="vo" items="${adoptPList }">
                                <div class="col-xl-4 col-lg-4 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                            <img class="imgDiv" src="<c:out value="${vo.popFile }"></c:out>" alt="">
                                        </div>
                                        <div class="explorer_bottom d-flex">
                                            <div class="explorer_info">
                                                <p>유기번호&nbsp;<br> 
                                                <h3 class="contentsTitle"><c:out value="${vo.desertionNo }"></c:out></h3><hr/>
                                                 </p>                                                
                                                <p>입양신청일&nbsp; <c:out value="${vo.regDt}"></c:out></p>
                                                <p>신청이유&nbsp;&nbsp;&nbsp;&nbsp;  <c:out value="${vo.applyReason }"></c:out></p>
                                                <p>심사일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.aregDt }"></c:out></p>
                                                <p>심사결과&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${vo.applyState }"></c:out> </p>
                                                
                                                <ul>
                                                    <li> </li>
                                                    <li></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>승인 심사 진행중인 내역이 없습니다.</li>
                        </c:otherwise>
                    </c:choose>    
                    </div>
                </div>
                <!-- **************심사진행중 end************** -->
            </div>
        </div>
    </div>
  <!-- ================ 입양신청 내역 end ================= -->  

 
  <!-- ================ 내가 쓴 글 확인 start================== -->
  <section class="contact-section section_padding">
  <div class="container">
    <div class="row">
        <div class="col-xl-6 col-md-4">
          <h3 class="contact-title myPageTitleFont" >내가 쓴 글</h3><hr/>
        </div>
  <!-- **************종합게시판 start************** -->     
        <div class="container">
        <form action="<%=HR_PATH%>/view/mypage.do" name="commPageFrm" id="commPageFrm" method="get">                
            <input type="hidden" name="work_div"   />
            <input type="hidden" name="page_num_c"   />
            <input type="hidden" name="tot_no"   />            
            <div class="col-xl-6 col-md-4" >
                <h3 class="contact-title myPageSubTitleFont" >종합게시판</h3>
            </div>
            <div class="col-xl-6 col-md-8">
                        <div class="explorer_tab">
                        </div>
                    </div>
            <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                   <div class="blog_left_sidebar">
                       <article class="blog_item">
                           <c:choose>
                               <c:when test="${commList.size()>0 }">
                                   <c:forEach var="vo" items="${commList }">
                                   <c:set var="msg" value="Y"/>
                                        <c:if test="${msg == vo.ableYn}"> 
                                       <div class="blog_item_img">
                                           <table id="listTableC">
                                               <tr>
                                                   <td><img class="card-img rounded-0 boardImgsize" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt=""></td>
                                                   <td style="display:none;"><c:out value="${vo.totNo}"></c:out></td>
                                               </tr>
                                           </table>
                                           <h3 class="blog_item_date"><c:out value="${vo.count}"></c:out></h3>
                                        </div>
                                        <div class="blog_details">
                                            <p class="contentsTitle"><c:out value="${vo.title}"></c:out></p>
                                            <table id="commDelTable">
                                                <tr>
                                                    <ul class="blog-info-link">
                                                        <li>작성자&nbsp;<c:out value="${vo.memberId}"></c:out></li>
                                                        <li>작성일&nbsp;<c:out value="${vo.modDt}"></c:out></li>                                                
                                                    </ul>
                                                </tr><hr/>
                                               <tr>
                                                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="genric-btn danger circle">삭제</button></td>
                                                   <td style="display:none;"><c:out value="${vo.totNo}"></c:out></td>
                                               </tr>                           
                                           </table>
                                        </div>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li>작성한 게시글이 없습니다.</li>
                                </c:otherwise>
                           </c:choose>
                           
                       </article>
                       <nav class="blog-pagination justify-content-center d-flex" class="page-link">
                       <!-- paging -->
                           <div>
                           <%=StringUtil.renderPaging(maxNumComm, currPageNoComm, rowsPerPageComm, bottomCount, url, scriptNameC)%>
                           
                           </div>
                       <!--// paging -->  
                       </nav>
                   </div>
               </div>
               <div class="col-lg-4">
                    <div class="blog_right_sidebar">

                    </div>
               </div> 
            </div>
        </form>
        </div>
    

 <!-- **************종합게시판 end************** -->    
 

 <!-- **************후기 start************** -->

        <div class="container">
        <form action="<%=HR_PATH%>/view/mypage.do" name="reviewPageFrm" id="reviewPageFrm" method="get">                
            <input type="hidden" name="work_div"   />
            <input type="hidden" name="page_num_r"   />
            <input type="hidden" name="rev_no"   />           
            <div class="col-xl-6 col-md-4" >
                <hr/><h3 class="contact-title myPageSubTitleFont">입양 후기</h3>
            </div>
            
            <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                   <div class="blog_left_sidebar">
                       <article class="blog_item">
                           <c:choose>
                               <c:when test="${revList.size()>0 }">
                                   <c:forEach var="vo" items="${revList }">
                                   <c:set var="msg" value="Y"/>
                                        <c:if test="${msg == vo.ableYn}"> 
                                       <div class="blog_item_img">
                                           <table id="listTableR">
                                               <tr>
                                                   <td><img class="card-img rounded-0 boardImgsize" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt=""></td>
                                                   <td style="display:none;"><c:out value="${vo.revNo}"></c:out></td>
                                               </tr>
                                           </table>
                                           <h3 class="blog_item_date"><c:out value="${vo.count}"></c:out></h3>
                                        </div>
                                        <div class="blog_details">
                                            <p class="contentsTitle"><c:out value="${vo.title}"></c:out></p>
                                            <table id="revDelTable">
                                                <tr>
                                                    <ul class="blog-info-link">
                                                        <li>작성자&nbsp;<c:out value="${vo.memberId}"></c:out></li>
                                                        <li>작성일&nbsp;<c:out value="${vo.modDt}"></c:out></li>                                                
                                                    </ul>
                                                </tr><hr/>
                                               <tr>
                                                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="genric-btn danger circle">삭제</button></td>
                                                   <td style="display:none;"><c:out value="${vo.revNo}"></c:out></td>
                                               </tr>                           
                                           </table>
                                        </div>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li>작성한 게시글이 없습니다.</li>
                                </c:otherwise>
                           </c:choose>
                           
                       </article>
                       <nav class="blog-pagination justify-content-center d-flex" class="page-link">
                       <!-- paging -->
                           <div>
                           <%=StringUtil.renderPaging(maxNumRev, currPageNoRev, rowsPerPageRev, bottomCount, url, scriptNameR)%>
                           </div>
                       <!--// paging -->  
                       </nav>
                   </div>
               </div>
               <div class="col-lg-4">
                    <div class="blog_right_sidebar">

                    </div>
               </div> 
            </div>
        </form>
        </div>

   
 <!-- **************후기 end************** -->
 
  <!-- **************QnA start************** -->
      
        <div class="container">
        <form action="<%=HR_PATH%>/view/mypage.do" name="qnaPageFrm" id="qnaPageFrm" method="get">                
            <input type="hidden" name="work_div"   />
            <input type="hidden" name="page_num_q"   />
            <input type="hidden" name="qna_no"   />            
            <div class="col-xl-6 col-md-4" >
                <hr/><h3 class="contact-title myPageSubTitleFont">QnA 게시판</h3>
            </div>
            
            <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                   <div class="blog_left_sidebar">
                       <article class="blog_item">
                           <c:choose>
                               <c:when test="${qnaList.size()>0 }">
                                   <c:forEach var="vo" items="${qnaList }">
                                   <c:set var="msg" value="Y"/>
                                        <c:if test="${msg == vo.ableYn}"> 
                                       <div class="blog_item_img">
                                           <table id="listTableQ">
                                               <tr>
                                                   <td><img class="card-img rounded-0 boardImgsize" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt=""></td>
                                                   <td style="display:none;"><c:out value="${vo.qnaNo}"></c:out></td>
                                               </tr>
                                           </table>
                                           <h3 class="blog_item_date"><c:out value="${vo.count}"></c:out></h3>
                                        </div>
                                        <div class="blog_details">
                                            <p class="contentsTitle"><c:out value="${vo.title}"></c:out></p>
                                            <table id="qnaDelTable">
                                                <tr>
                                                    <ul class="blog-info-link">
                                                        <li>작성자&nbsp;<c:out value="${vo.memberId}"></c:out></li>
                                                        <li>작성일&nbsp;<c:out value="${vo.modDt}"></c:out></li>                                                
                                                    </ul>
                                                </tr><hr/>
                                               <tr>
                                                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="genric-btn danger circle">삭제</button></td>
                                                   <td style="display:none;"><c:out value="${vo.qnaNo}"></c:out></td>
                                               </tr>                           
                                           </table>
                                        </div>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li>작성한 게시글이 없습니다.</li>
                                </c:otherwise>
                           </c:choose>
                       </article>
                       <nav class="blog-pagination justify-content-center d-flex" class="page-link">
                       <!-- paging -->
                           <div>
                           <%=StringUtil.renderPaging(maxNumQnA, currPageNoQnA, rowsPerPageQnA, bottomCount, url, scriptNameQ)%>
                           </div>
                       <!--// paging -->  
                       </nav>
                   </div>
               </div>
               <div class="col-lg-4">
                    <div class="blog_right_sidebar">

                    </div>
               </div> 
            </div>
        </form>
        </div>

 <!-- **************QnA end************** -->
     </div>
    </div>
 </section>
   <!-- ================ 내가 쓴 글 확인 end================== -->


    <!-- ===============footer start======================= -->
    <footer class="footer">
        <%@ include file="/cmn/togetdog_footer.jsp" %>
    </footer>
    <!-- ======================footer end==========================  -->


  <!-- JS here -->
  <script src="js/vendor/modernizr-3.5.0.min.js"></script>
  <script src="js/vendor/jquery-1.12.4.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/isotope.pkgd.min.js"></script>
  <script src="js/ajax-form.js"></script>
  <script src="js/waypoints.min.js"></script>
  <script src="js/jquery.counterup.min.js"></script>
  <script src="js/imagesloaded.pkgd.min.js"></script>
  <script src="js/scrollIt.js"></script>
  <script src="js/jquery.scrollUp.min.js"></script>
  <script src="js/wow.min.js"></script>
  <script src="js/nice-select.min.js"></script>
  <script src="js/jquery.slicknav.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/plugins.js"></script>
  <script src="js/gijgo.min.js"></script>

  <!--contact js-->
  <script src="js/contact.js"></script>
  <script src="js/jquery.ajaxchimp.min.js"></script>
  <script src="js/jquery.form.js"></script>
  <script src="js/jquery.validate.min.js"></script>
  <script src="js/mail-script.js"></script>

  <script src="js/main.js"></script>
  
  <script type="text/javascript">
  
  
      function doSearchPageR(url,no){
          //console.log("url:"+url);
          //console.log("no:"+no);
          var frm = document.reviewPageFrm;
          frm.work_div.value = "do_retrieve";
          frm.page_num_r.value = no;
          frm.action = url;
          frm.submit();           
          
      }    
      
      function doSearchPageC(url,no){
          //console.log("url:"+url);
          //console.log("no:"+no);
          var frm = document.commPageFrm;
          frm.work_div.value = "do_retrieve";
          frm.page_num_c.value = no;
          frm.action = url;
          frm.submit();           
          
      }   
      
      function doSearchPageQ(url,no){
          //console.log("url:"+url);
          //console.log("no:"+no);
          var frm = document.qnaPageFrm;
          frm.work_div.value = "do_retrieve";
          frm.page_num_q.value = no;
          frm.action = url;
          frm.submit();           
          
      }    

      function doRetrieve(){
          window.location.href='/SOMAC/view/mypage.do?work_div=do_retrieve';
      }
      
      <%-- 게시글 삭제버튼 --%>
      
      $("#commDelTable>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var totNo = pTR_Children.eq(1).text();
          //alert("totNo:" + totNo);
          
          if(false == confirm("게시글을 \n삭제하시겠습니까?")) return;
          
          $.ajax({
              
              type:"POST",
              url:"/SOMAC/view/mypage.do",
              dataType:"html",
              data:{"work_div":"do_deleteC",
                    "tot_no":totNo              
              },
              success:function(data){ //성공     
                  //console.log("data="+data);
                  var jsonObj = JSON.parse(data);
                  if(jsonObj != null && jsonObj.msgId=="1"){//msgId=="1"은 BoardCont의 doInsert에서 service.doInsert의 결과가 성공일 경우 flag에 담기는 값
                       alert(jsonObj.msgContents);
                       doRetrieve();
                  } else{
                       alert(jsonObj.msgContents);
                  }
              },                                 
              error:function(xhr,status,error){ 
                   alert("error:"+error);        
              },                                 
              complete:function(data){          
              }
         });//ajax end
          
      });
      
      $("#revDelTable>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var revNo = pTR_Children.eq(1).text();
          //alert("revNo:" + revNo);
          
          if(false == confirm("게시글을 \n삭제하시겠습니까?")) return;
          
          $.ajax({
              
              type:"POST",
              url:"/SOMAC/view/mypage.do",
              dataType:"html",
              data:{"work_div":"do_deleteR",
                    "rev_no":revNo              
              },
              success:function(data){ //성공     
                  //console.log("data="+data);
                  var jsonObj = JSON.parse(data);
                  if(jsonObj != null && jsonObj.msgId=="1"){//msgId=="1"은 BoardCont의 doInsert에서 service.doInsert의 결과가 성공일 경우 flag에 담기는 값
                       alert(jsonObj.msgContents);
                       doRetrieve();
                  } else{
                       alert(jsonObj.msgContents);
                  }
              },                                 
              error:function(xhr,status,error){ 
                   alert("error:"+error);        
              },                                 
              complete:function(data){          
              }
         });//ajax end
          
      });
      
      $("#qnaDelTable>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var qnaNo = pTR_Children.eq(1).text();
          //alert("qnaNo:" + qnaNo);
          
          if(false == confirm("게시글을 \n삭제하시겠습니까?")) return;
          
          $.ajax({
              
              type:"POST",
              url:"/SOMAC/view/mypage.do",
              dataType:"html",
              data:{"work_div":"do_deleteQ",
                    "qna_no":qnaNo              
              },
              success:function(data){ //성공     
                  //console.log("data="+data);
                  var jsonObj = JSON.parse(data);
                  if(jsonObj != null && jsonObj.msgId=="1"){//msgId=="1"은 BoardCont의 doInsert에서 service.doInsert의 결과가 성공일 경우 flag에 담기는 값
                       alert(jsonObj.msgContents);
                       doRetrieve();
                  } else{
                       alert(jsonObj.msgContents);
                  }
              },                                 
              error:function(xhr,status,error){ 
                   alert("error:"+error);        
              },                                 
              complete:function(data){          
              }
         });//ajax end
          
      });
      
      <%-- 이미지 클릭시 디테일페이지로 이동 메서드 --%>

      $("#listTableC>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var totNo = pTR_Children.eq(1).text();
        //alert("totNo:" + totNo);
          
          var frm = document.commPageFrm;
          frm.work_div.value ="do_selectOneC";
          frm.tot_no.value = totNo;
          frm.action = '/SOMAC/view/mypage.do?tot_no'+totNo;
          frm.submit();
          
      });
      
      $("#listTableR>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var revNo = pTR_Children.eq(1).text();
        //alert("revNo:" + revNo);
          
          var frm = document.reviewPageFrm;
          frm.work_div.value ="do_selectOneR";
          frm.rev_no.value = revNo;
          frm.action = '/SOMAC/view/mypage.do?rev_no'+revNo;
          frm.submit();
          
      });
      
      $("#listTableQ>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var qnaNo = pTR_Children.eq(1).text();
        //alert("qnaNo:" + qnaNo);
          
          var frm = document.qnaPageFrm;
          frm.work_div.value ="do_selectOneQ";
          frm.qna_no.value = qnaNo;
          frm.action = '/SOMAC/view/mypage.do?qna_no'+qnaNo;
          frm.submit();
          
      });
      
      
      <%-- 후기작성 버튼 메서드 --%>
      
      $("#revbtnTable1>tbody").on("click","tr",function(event){
          var pTR = $(this); //tr
          var pTR_Children = pTR.children();
          var applyNo = pTR_Children.eq(1).text();
          //alert("applyNo:" + applyNo);
            
          var frm = document.reviewInsertFrm1;
          frm.work_div.value = "move_to_save";
          frm.apply_no.value = applyNo;
          frm.action = '/SOMAC/view/mypage.do';
          frm.submit(); 
          
          
      });
      
       $("#revbtnTable2>tbody").on("click","tr",function(event){
              var pTR = $(this); //tr
              var pTR_Children = pTR.children();
              var applyNo = pTR_Children.eq(1).text();
              //alert("applyNo:" + applyNo);
                
              var frm = document.reviewInsertFrm2;
              frm.work_div.value = "move_to_save";
              frm.apply_no.value = applyNo;
              frm.action = '/SOMAC/view/mypage.do';
              frm.submit(); 
              
              
          });
 
     
      <%-- 회원정보 수정 버튼 메서드 --%>
      $("#update_btn").on('click',function(){
          
          var work_div = $("#work_div").val();
          if(work_div == null || work_div.trim().length ==0){
              //alert("작업구분을 확인하세요.");
              return;
          }
          
          var pw = $("#pw").val(); 
          if(pw == null || pw.trim().length == 0){
              $("#pw").focus();
              alert("비밀번호를 입력하세요.");
              return;
          }
          
          var name = $("#name").val(); 
          if(name == null || name.trim().length == 0){
              $("#name").focus();
              alert("이름을 입력하세요.");
              return;
          }
          
          var phoneNum = $("#phone_num").val(); 
          if(phoneNum == null || phoneNum.trim().length == 0){
              $("#phone_num").focus();
              alert("핸드폰 번호를 입력하세요.");
              return;
          }
          
          var email = $("#email").val(); 
          if(email == null || email.trim().length == 0){
              $("#email").focus();
              alert("이메일 주소를 입력하세요.");
              return;
          }
          
          if(false == confirm("개인정보를\n수정하시겠습니까?")) return;
          
          $.ajax({
              
              type:"POST",
              url:"/SOMAC/view/mypage.do",
              dataType:"html",
              data:{"work_div":work_div,
                   "pw":$("#pw").val(),
                   "name":$("#name").val(),
                   "phone_num":$("#phone_num").val(),
                   "email":$("#email").val(),
                   "member_id":$("#member_id").val()                
              },
              success:function(data){ //성공     
                  //json String --> json Object
                  //console.log("data="+data);
                  var jsonObj = JSON.parse(data);
                  //console.log("msgId="+jsonObj.msgId);
                  //console.log("msgContents="+jsonObj.msgContents);
                  //alert("data:"+data);
                  if(jsonObj != null && jsonObj.msgId=="1"){//msgId=="1"은 BoardCont의 doInsert에서 service.doInsert의 결과가 성공일 경우 flag에 담기는 값
                       alert(jsonObj.msgContents);
                  } else{
                       alert(jsonObj.msgContents);
                  }
              },                                 
              error:function(xhr,status,error){ 
                   alert("error:"+error);        
              },                                 
              complete:function(data){          
              }
         });//ajax end
          
      });//update_btn end
      
      
  
  </script>
</body>

</html>