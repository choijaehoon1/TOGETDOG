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
<%@page import="com.togetdog.member.MemberVO"%>
<%@page import="com.togetdog.cmn.StringUtil"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.community.CommunityVO1"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="/cmn/error.jsp"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	//초기화
  	MemberVO mvo= (MemberVO)session.getAttribute("user");
   	String id = mvo.getMemberId();
	
	String pageSize = "10";
	String pageNum = "1";
	String searchDiv = "";
	String searchWord = "";
	
	SearchVO inVO= (SearchVO)request.getAttribute("paramVO");
	if(inVO !=null){
		pageNum = String.valueOf(inVO.getPageNum());
		pageSize = String.valueOf(inVO.getPageSize());
		searchDiv = inVO.getSearchDiv();
		searchWord = inVO.getSearchWord();
		
	}
	
	List<CommunityVO1> list =(List<CommunityVO1>)request.getAttribute("list");
	
	List<CommunityVO1> recentList =(List<CommunityVO1>)request.getAttribute("recentList");
		
	//paging
		int maxNum 		= 0;//총글수 
		int currPageNo	= 1;//현재페이지
		int rowsPerPage = 10;// page_size랑 같다 
		
		int bottomCount = 10;// 바닥페이지
		
		String url = HR_PATH + "/view/community.do";//호출url
		String scriptName = "doSearchPage";//JavaScript함수(url,no)  doSearchPage(url,no)
		
		if(null != inVO){
			maxNum =(Integer)request.getAttribute("totalCnt");
			currPageNo  = inVO.getPageNum();
			rowsPerPage = inVO.getPageSize();
		}
	//--paging

	
	
	
%>     



<!doctype html>
<html class="no-js" lang="zxx">

<head>
	<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
	<link rel="icon" type="image/png" href="http://example.com/myicon.png">
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>ToGetDog</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
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
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    
    
      <!-- 폰트 다운로드 -->
   	<link href="https://fonts.googleapis.com/css?family=Stylish&display=swap" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Stylish&display=swap" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Noto+Sans+KR&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
    <style>	
        #communityTitle {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 50px; 
        }
        
        .contentsTitle{
         font-family: 'Nanum Gothic';
         font-size: 25px;
       }
       
       .communityTitleFont{
         
         font-family: 'Do Hyeon', sans-serif; 
         font-size: 30px;
       }	
    	
    	 .subTitle{
        font-family: 'Nanum Gothic';
         font-size: 15px;
       }
    	
       #recentImgsize{
       	width: 100px;
       	height: 100px;
       }
       #imgsize{
       	width: 600px;
       	height: 400px;
       }
       
       .pageSize{
       	font-family: 'Stylish' ; 
       	font-size: 25px;
       }
       
    </style>
    
</head>

<body >
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
    	<%@ include file="/cmn/togetdog_header.jsp" %>        
    <!-- header-end -->
    
         <!-- bradcam_area  -->
         <div class="bradcam_area bradcam_bg_2">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="bradcam_text text-center">
                                <h3 id="communityTitle" >종합커뮤니티</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ bradcam_area  -->


       <!--================Blog Area =================-->
    <section class="blog_area section-padding">
        <div class="container">
        <form action='<%=HR_PATH%>/view/community.do' name="searchFrm" id="searchFrm" method="get">
	        <input type="hidden" name="work_div" />
			<input type="hidden" name="page_num" />		<!-- 페이징 용 -->
			<input type="hidden" name="seq" />			<!-- 데이터 선택하기 용  -->
            <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                    <div class="blog_left_sidebar">
                        <article class="blog_item">
                        	 <c:choose>
                    			<c:when test="${list.size() >0 }">
                    				<c:forEach var="vo" items="${list}">
                    					<c:if test="${vo.ableYn =='Y'}">
			                            	<div class="blog_item_img">	
				                            	<table id="listTable">
				                            		<tr>
						                                <td><img id = "imgsize"class="card-img rounded-0" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt=""></td>
						                                <td style="display:none;"><c:out value="${vo.totNo}"></c:out></td>
				                                	</tr>
				                                </table>
				                                <h3 class="blog_item_date"><c:out value="${vo.count}"></c:out></h3>                               
			                            	</div>
			                            <div class="blog_details">
			                                <p class="contentsTitle"><c:out value="${vo.title}"></c:out></p>
			                                <ul class="blog-info-link">
			                                    <li>작성자&nbsp;<c:out value="${vo.memberId}"></c:out></li>
			                                    <li>작성일&nbsp;<c:out value="${vo.modDt}"></c:out></li>
			                                </ul>
			                            </div>
			                            </c:if>
                            		</c:forEach>
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
                   			</c:choose>
                        </article>
                        <nav class="blog-pagination justify-content-center d-flex" class="page-link">
                        <!-- paging -->
                        	<div class="pageSize">
                            <%=StringUtil.renderPaging(maxNum, currPageNo, rowsPerPage, bottomCount, url, scriptName)%>
                            </div>
                        <!-- //paging -->	   
                        </nav>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="blog_right_sidebar">
                        <aside class="single_sidebar_widget search_widget">
                                <div class="form-group">
                               		<div class="input_field">
                                          <select class="wide" name="search_div" id="search_div" data-placeholder="Choose a Country">
                                                  <option value="" disabled>검색 구분을 확인하세요.</option>
                                                  <option value="10" <%if(searchDiv.equals("10")) out.print("selected"); %> >제목</option>
                                                  <option value="20" <%if(searchDiv.equals("20")) out.print("selected"); %> >내용</option>
                                                  <option value="30" <%if(searchDiv.equals("30")) out.print("selected"); %> >등록자</option>
                                          </select>
                                    </div>
                                    <div class="input_field">
                                          <select class="wide" name="page_size" id="page_size">
                                                  <option value="5"  <%if(pageSize.equals("5")) out.print("selected"); %> >5</option>
												  <option value="10" <%if(pageSize.equals("10")) out.print("selected"); %> >10</option>
												  <option value="50" <%if(pageSize.equals("50")) out.print("selected"); %> >50</option>
												  <option value="100" <%if(pageSize.equals("100")) out.print("selected"); %> >100</option>
                                          </select>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" name="search_word" id="search_word" value="<%=searchWord%>" class="form-control" placeholder='Search Keyword'/>
                                    </div>
                                </div>
                                <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                                    type="button" onclick="javascript:doRetrieve();" id="to_list_btn">Search
                                </button>    	
                        </aside>
						
                        <aside class="single_sidebar_widget post_category_widget">
                            <h4 class="communityTitleFont">Category</h4>
                            <ul class="list cat-list subTitle">
                                <li>
                                    <a href="/SOMAC/view/community.do?work_div=do_retrieve_m">목격자를 찾습니다</a>
                                </li>
                                <li>
                                    <a href="/SOMAC/view/community.do?work_div=do_retrieve_j">주인을 찾습니다</a>
                                </li>
                                <li>
                                    <a href="/SOMAC/view/community.do?work_div=do_retrieve_r">입양후기</a>
                                </li>
                            </ul>
                        </aside>
  							<table id="listTable">
			                        <tr>
                                  		<td><h2><c:out value="${vo.num}"></c:out></h2></td>
                                  		<td style="display:none;"><c:out value="${vo.totNo}"></c:out></td>
			                        </tr>
			                 </table>
                        <aside class="single_sidebar_widget popular_post_widget">
                            <h3 class="communityTitleFont">최근 게시글</h3>
                            	<c:choose>
                            		<c:when test="${recentList.size() >0}">
                            			<c:forEach var="vo" items="${recentList}">	
                            				<c:if test="${vo.ableYn =='Y'}">		
				                            <div class="media post_item">
				                            	<table id="listTable">
				                            		<tr>
				                                		<td><img id="recentImgsize" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt="post"></td>
				                                		<td style="display:none;"><c:out value="${vo.totNo}"></c:out></td>
				                                	</tr>
				                                </table>
				                                <div class="media-body">
				                                	<h3 class="contentsTitle"><c:out value="${vo.title}"></c:out></h3>
				                                    <p><c:out value="${vo.modDt}"></c:out></p>
				                                </div>
				                            </div>
				                            </c:if>
                            	 		</c:forEach>
                            	 	</c:when>
                            	 	<c:otherwise>
									
									</c:otherwise>
                            	</c:choose>
                        </aside>
                      
                        <aside class="single_sidebar_widget newsletter_widget">
                            <h4 class="communityTitleFont">게시글 등록</h4>
                                <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                                    type="button" onclick="javascript:moveToSave();" id="insert_btn">등록하기
                                </button>
                        </aside>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </section>
    <!--================Blog Area =================-->

    <!-- footer start -->
    	<%@ include file="/cmn/togetdog_footer.jsp" %>
    <!--/ footer end  -->


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
    <script>
        $('#datepicker').datepicker({
            iconsLibrary: 'fontawesome',
            icons: {
             rightIcon: '<span class="fa fa-caret-down"></span>'
         }
        });
        $('#datepicker2').datepicker({
            iconsLibrary: 'fontawesome',
            icons: {
             rightIcon: '<span class="fa fa-caret-down"></span>'
         }

        });
    </script>
    
    <script type="text/javascript">
	    function doSearchPage(url,no){
			var frm = document.searchFrm;
			frm.work_div.value ="do_retrieve";
			frm.page_num.value = no;
			frm.action = url;
	        frm.submit();
		}	
    
    
    	function doRetrieve(){
    		var frm = document.searchFrm;
			frm.work_div.value ="do_retrieve";
			frm.page_num.value ="1";
	        frm.action = '/SOMAC/view/community.do';
	        frm.submit();
    	}
    	
    	
    	function moveToSave(){
    		if(false==confirm('등록 화면으로 이동 하시겠습니까?')) return;
    		var frm = document.searchFrm;
    		frm.work_div.value ="move_to_save";
	        frm.action = '/SOMAC/view/community.do';
	        frm.submit();
    	}
    	

		$("#listTable>tbody").on("click","tr",function(event){
			var pTR = $(this); //tr
			var pTR_Children = pTR.children();
			var seq = pTR_Children.eq(1).text();
//			alert("seq:" + seq);
			
			var frm = document.searchFrm;
			frm.work_div.value ="do_selectOne";
			frm.seq.value = seq;
	        frm.action = '/SOMAC/view/community.do?seq=' + seq;
	        frm.submit();
			
		});
		
		
	
		$("#search_word").keypress(function(e){
			if(e.which ==13){
				doRetrieve();
			}
		});
    </script>
    
</body>
</html>