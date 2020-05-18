<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 18.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2020 by SOMAC  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/view/member.do" var="logOut">
 <c:param name="work_div" value="do_logout"></c:param>   
</c:url>
   

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 

<!-- header-start -->
    <header>
        <div class="header-area ">
            <div id="sticky-header" class="main-header-area">
                <div class="container-fluid ">
                    <div class="header_bottom_border">
                        <div class="row align-items-center">
                            <div class="col-xl-3 col-lg-2">
                                <div class="logo">
                                    <a href="desertion_info.jsp">
                                        <img src="img/logo2.png" alt="">
                                    </a>
                                </div>
                            </div>
                                <div class="col-xl-6 col-lg-7">
                                    <div class="main-menu  d-none d-lg-block">
                                        <nav>
                                            <ul id="navigation">
                                                <li><a href="desertion_info.jsp">유기동물정보</a></li>
                                                
                                                <li><a href="community_index.jsp">종합커뮤니티 <i class="ti-angle-down"></i></a>
                                                    
                                                        <ul class="submenu">
                                                        <li><a href="/SOMAC/view/community.do?work_div=do_retrieve_m">목격자를 찾습니다</a></li>
                                                        <li><a href="/SOMAC/view/community.do?work_div=do_retrieve_j">주인을 찾습니다</a></li>
                                                        <li><a href="/SOMAC/view/community.do?work_div=do_retrieve_r">입양후기</a></li>
                                                    </ul>
                                                    
                                                </li>
                         
                                                <li><a href="qnaboard_index.jsp">QnA <i class="ti-angle-down"></i></a>
                                                    <ul class="submenu">
                                                        <li><a href="qnalist.html">자주하는질문</a></li>
                                                        <li><a href="qnaboard_index.jsp">묻고 답하기</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="mypage_index.jsp">마이페이지</a></li>
                                                <li><a href="contact.jsp">Contact</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            <div class="col-xl-3 col-lg-3 d-none d-lg-block">
                                <div class="Appointment">
                                    <div class="book_btn d-none d-lg-block">
                                       <li class="nav-item"><a href="${logOut}" class="nav-link" onclick="if(false == confirm('로그아웃 하시겠습니까?')){return false;}">로그아웃</a></li>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="mobile_menu d-block d-lg-none"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </header>
       
        <!-- header-end -->
      