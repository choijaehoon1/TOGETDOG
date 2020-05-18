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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.togetdog.cmn.StringUtil"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.qna.QnaVO2"%>
<%@page import="com.togetdog.qna.QnaVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    errorPage="/cmn/error.jsp"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
List<QnaVO2> replyList = (List<QnaVO2>)request.getAttribute("replyList");
    //초기화
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
    
    List<QnaVO> list =(List<QnaVO>)request.getAttribute("list");
    

        
  //paging
    int maxNum     = 0;//총글수 
    int currPageNo = 1;//현재 페이지 
    int rowsPerPage= 10;// page_size 
    
    int bottomCount= 10;// 바닥 page_cnt
    
    String url = HR_PATH+"/view/qna.do";//호출 URL 
    String scriptName = "doSearchPage";//JavaScript함수: doSearchPage(url,no)
    if(null !=inVO){
        maxNum     = (Integer)request.getAttribute("totalCnt");
        currPageNo = inVO.getPageNum();
        rowsPerPage= inVO.getPageSize();
        
        LOG.debug("=======================");
        LOG.debug("url="+url);
        LOG.debug("scriptName="+scriptName);
        LOG.debug("maxNum="+maxNum);
        LOG.debug("currPageNo="+currPageNo);
        LOG.debug("rowsPerPage="+rowsPerPage);
        LOG.debug("=======================");
    }
    
    
    //--paging
%>     

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
    <!-- <link rel="icon" type="image/png" href="http://example.com/myicon.png"> -->
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>TOGETEDOG</title>
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
   <link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Noto+Sans+KR&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
   <style>
        #desertionTitle{
            
            font-family: 'Do Hyeon', sans-serif;
        }
        
        #desertionSubTitle{
            font-family: 'Do Hyeon', sans-serif;
            font-size: 30px;
        }
    
        
        .cmnTitle{
            font-family: 'Jua', sans-serif;
            font-family: 'Do Hyeon', sans-serif;
        }
        
        .cmnContent{
            font-family: 'Nanum Gothic';
            font-size: 20px;
        }
        
    </style>
    
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
  <%@ include file="/cmn/togetdog_header.jsp" %>
 <!-- header-end -->
 
 
<!-- slider_area_start -->
    <div class="slider_area">
        <div class="single_slider single_listing  d-flex align-items-center slider_bg_1">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-xl-10">
                       <div class="slider_text text-center justify-content-center">
                             <p  id="desertionSubTitle">Question and Answer</p>
                            <h3 id="desertionTitle">묻고답하기</h3>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- slider_area_end -->


       <!--================Blog Area =================-->
    <section class="blog_area section-padding">
        <div class="container">
        <form action='<%=HR_PATH%>/view/qna.do' name="searchFrm" id="searchFrm" method="get">
            <input type="hidden" name="work_div" />
            <input type="hidden" name="page_num" />     <!-- 페이징 용 -->
            <input type="hidden" name="qna_no" />          <!-- 데이터 선택하기 용  -->
            
        <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                    <div class="blog_left_sidebar">
                    <article class="blog_item">
                    
                             <c:choose>
                                <c:when test="${list.size() >0}">
                                    <c:forEach var="vo" items="${list}">
                                     <div class="blog_item_img">
                                      <c:set var="msg" value="Y"/>
                                        <c:if test="${msg == vo.ableYn}">
                                          <td><img id = "imgsize"class="card-img rounded-0" src="<c:out value="${vo.imgPath}"></c:out><c:out value="${vo.ext}"></c:out>" alt=""></td>
                                           <!--  <img id = "imgsize"class="card-img rounded-0" src="<c:out value="img/blog/single_blog_1.png"></c:out><c:out value="확장자"></c:out>" alt=""> -->
                                                <h3 id="desertionTitle" class="blog_item_date"><c:out value="${vo.qna_no}"></c:out></h3>                               
                                        </div>
                                        <div class="blog_details">
                                            <table id="listTable">
                                                <tr>
                                                    <td><p id="cmnContent"><c:out value= "${vo.title}"></c:out></p></td>
                                                    <td style="display:none;"><c:out value="${vo.qna_no}"></c:out></td>
                                                </tr>
                                            </table>
                                           
                                            <ul class="blog-info-link">
                                               
                                             <c:set var="TextValue" value="${vo.regDt}"/>
                                                <li id="cmnContent"><i class="fa fa-user"></i>작성자&nbsp;<c:out value="${vo.memberId}"></c:out></li><!-- 작성자표시 -->
                                                <li id="cmnContent"><i class="fa fa-user"></i>조회수&nbsp;<c:out value="${vo.count}"></c:out></li><!-- 조회수 표시 -->
                                                <li id="cmnContent"><i class="fa fa-user"></i>작성일&nbsp;<c:out value="${fn:substring(TextValue,0,16)}"></c:out></li><!-- 작성일 표시 -->   
                                              </ul>
                                             </c:if>
                                        </div>
                                    </c:forEach>
                                </c:when>
                             <c:otherwise></c:otherwise>
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
                                          <select class="wide" name="search_div" id="search_div">
                                                  <option value="">검색구분을 선택하세요</option>
                                                  <option value="10" <%if(searchDiv.equals("10")) out.print("selected"); %> >제목</option>
                                                  <option value="20" <%if(searchDiv.equals("20")) out.print("selected"); %> >내용</option>
                                                  <option value="30" <%if(searchDiv.equals("30")) out.print("selected"); %> >등록자</option>
                                          </select>
                                    </div>
                                    <div class="input_field">
                                          <select class="wide" name="page_size" id="page_size" />
                                                  <option value="5"  <%if(pageSize.equals("5")) out.print("selected"); %> >5</option>
                                                  <option value="10" <%if(pageSize.equals("10")) out.print("selected"); %> >10</option>
                                                  <option value="50" <%if(pageSize.equals("50")) out.print("selected"); %> >50</option>
                                                  <option value="100" <%if(pageSize.equals("100")) out.print("selected"); %> >100</option>
                                          </select>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" name="search_word" id="search_word" value="<%=searchWord%>" class="form-control" placeholder='검색단어를 입력하세요.'/>
                                    </div>
                                </div>
                                <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                                    type="button" onclick="javascript:doRetrieve();" id="to_list_btn">검색하기</button>
                                    
                                     <div class="input-group mb-3">
                           
                                     </div>
                                      <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                                    type="button" onclick="javascript:moveToSave();" >질문하기</button>
                        </aside>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </section>
<!--============================================Blog Area =============================================-->

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
            frm.action = '/SOMAC/view/qna.do';
            frm.submit();
        }
        
        function goRetrieve(){
            window.location.href='/SOMAC/view/qna.do?work_div=do_retrieve';
        }
        
        
        function moveToSave(){
           
            var frm = document.searchFrm;
            frm.work_div.value ="move_to_save";
            frm.action = '/SOMAC/view/qna.do';
            frm.submit();
        }
        

        $("#listTable>tbody").on("click","tr",function(event){
            var pTR = $(this); //tr
            var pTR_Children = pTR.children();
            var qna_no = pTR_Children.eq(1).text();
            //alert("qna_no:" + qna_no);
            
            var frm = document.searchFrm;
            frm.work_div.value ="do_selectOne";
            frm.qna_no.value = qna_no;
            frm.action = '/SOMAC/view/qna.do?qna_no=' + qna_no;
            frm.submit();
            
        });
    
    
    </script>
    
</body>
</html>