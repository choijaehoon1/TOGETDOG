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
  * Copyright (C) 2020 by SOMAC  All right reserved.
  */
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.togetdog.cmn.StringUtil"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.adoption.AdoptionDao"%>
<%@page import="com.togetdog.adoption.AdoptionVO"%>
<%@page import="com.togetdog.member.MemberDao"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!doctype html>
<html class="no-js" lang="zxx">

<head>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>관리자페이지</title>
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
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/gijgo.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/slick.css">
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
  <!-- 폰트 다운로드 -->
<link
   href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Noto+Sans+KR&display=swap"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap"
   rel="stylesheet">
    <style>
        #desertionTitle {
           font-family: 'Do Hyeon', sans-serif;
        }
        #desertionSubTitle {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 30px;
        }
        #adopTitle{
           font-family: 'Do Hyeon', sans-serif;
           font-size: 30px;
        }
        #noticeEd {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 25px;
        }
        #imgDiv {
           height: 300px;
        }
        .cmnTitle {
        font-size: 20px;
           font-family: 'Jua', sans-serif;
           font-family: 'Do Hyeon', sans-serif;
        }
        .cmnContent {
           font-family: 'Nanum Gothic';
           font-size: 20px;
        }
        .imgDiv{
             height: 300px;
        }
    </style>
</head>

<body>

<%
      
   SearchVO searchVO=new SearchVO();
   searchVO.setPageSize(1000);
   searchVO.setPageNum(1);
   
   AdoptionDao dao=new AdoptionDao();
   
   List<AdoptionVO> list=  (List<AdoptionVO>)dao.doRetrieve(searchVO);
   String num = request.getParameter("num");
   String num2 = request.getParameter("num2");
   
    
%> 

<%
   
   String mempop="";
   SearchVO sv=new SearchVO();
   sv.setPageSize(1000);
   sv.setPageNum(1);
   
   MemberDao memdao=new MemberDao();
   
   List<MemberVO> m_list=  (List<MemberVO>)memdao.doRetrieve(sv);
%> 
 
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
                            
                              <p id="desertionSubTitle">Admin Page</p>
                              <h3 id="desertionTitle">관리자 페이지</h3>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- slider_area_end -->

    <!-- explorer_europe start  -->
    <div class="explorer_europe list_wrap">
        <div class="container">
                    
            <div class="row">
            
                <div class="col-xl-4 col-lg-4">  
                       
              
               <h3 class="mb-30" id="adopTitle">　Member List</h3>  <hr/>   
                <div class="progress-table-wrap">
                    <div class="progress-table">
                        <div class="table-head">
                            <!-- 회원정보 열 이름-->
                            <div class="visit">　Id</div>
                            <div class="country">e-Mail</div>
                            <div class="visit">Name</div>
                            <div class="visit">Phone_Number</div>>
                        </div>
                        <!-- 회원정보 리스트 뽑기 -->
                        <%  for(MemberVO mv:m_list) {  %>    
                        <div class="table-row">
                          
                           <div class="visit"><a id="cmnContent" href="member_detail.jsp?memid=<%=mv.getMemberId() %>"  >　<%=mv.getMemberId()%></a></div>
                            <div class="country"><a id="cmnContent" href="member_detail.jsp?memid=<%=mv.getMemberId() %>"  ><%=mv.getEmail()%></a></div>
                            <div class="visit"><a id="cmnContent" href="member_detail.jsp?memid=<%=mv.getMemberId() %>"  ><%=mv.getName()%></a></div>
                             <div class="visit"><a id="cmnContent" href="member_detail.jsp?memid=<%=mv.getMemberId() %>"  ><%=mv.getPhone()%></a></div>
                            
                        </div>
                        <%  }    %>   
                        <!-- 회원정보 리스트 뽑기 END-->
        
                    </div>
                </div>
                     
      </div>
                <div class="col-xl-8 col-lg-8">
                       <h3 class="exp_title" id="adopTitle">회원 입양 신청 리스트</h3> <hr/>
                        <div class="row">
                         
                   <!---------------------------------------게시글  단락 -------------------------------------------->
          <%boolean check=false; %>      
          <%  for(AdoptionVO vo:list) {          %>     
          <% if(vo.getApplyState().equals("P")) { check=true;%>  <!-- 현재 승인상태가 P(진행중)인 것들만 리스트에 표시해주기 -->
                                <div class="col-xl-6 col-lg-6 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                           <!-- <img src="img/explorer/puppy.PNG" alt="">-->
                                        </div>
                                        <div class="explorer_bottom d-flex">
                                            <div class="icon">
                                                <i class="flaticon-beach"></i>
                                            </div>
                                            <div class="explorer_info">
                                            
                                               
                                                   <h3><a href="" id="cmnContent"><%=vo.getDesertionNo() %><%="  "%></h3><hr/>
                                                </p>
                                                <ul>
                                                   <li>id : <%=vo.getMemberId() %></a></li>
                                                   <li>입양포부: <%=vo.getApplyReason() %></li>
                                                    <li id="cmnContent"> 가족 수: <%=vo.getFamilyCnt()%>명</li>
                                                    <li id="cmnContent"> 과거경험: <%=vo.getExperienceYn().toUpperCase()%></li>
                                                    <li id="cmnContent"> 신청일: <%=vo.getRegDt().substring(0,16)%></li><hr/>
                                                    <a id="cmnContent" href="admin.jsp?num=<%=vo.getApplyNo()%>" class="genric-btn success circle" onclick="btnOk();">승인</a> <!-- 승인버튼 -->
                                                    <a id="cmnContent" href="admin.jsp?num2=<%=vo.getApplyNo()%>"  class="genric-btn danger circle" onclick="btnNo();">거절</a> <!-- 거절버튼 -->
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                   <%          }%>
                      
                            <%          }if(!check){%> <p class="copy_right text-center" style="color:red;">⚡️ 현재 승인요청이 없습니다.⚡️</p><%}%> <!--신청 없을 때 -->
                               
                     </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ explorer_europe start  -->

       <!-- footer start -->
     <%@ include file="/cmn/togetdog_footer.jsp" %>
        <!--/ footer end  -->

    <!-- link that opens popup -->
    <script src=" https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"> </script>
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
    <script src="js/jquery-ui.js"></script>
    <script src="js/plugins.js"></script>



    <!--contact js-->
    <script src="js/contact.js"></script>
    <script src="js/jquery.ajaxchimp.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/mail-script.js"></script>


    <script src="js/main.js"></script>

    <script type="text/javascript"> <!--승인버튼 눌렀을 때 도는 메소드// 상태를 S로 변환해준다.-->



    function btnOk(){
        <%AdoptionVO ao=new AdoptionVO();
        ao.setApplyNo(num);
        ao.setApplyState("S");
        SimpleDateFormat format1 = new SimpleDateFormat ("yy/MM/dd");
        Date time = new Date();
        String time1 = format1.format(time);
        ao.setAregDt(time1);
        dao.doUpdate(ao);%>
    }

    function btnNo(){ 
        <%AdoptionVO ao2=new AdoptionVO();
        ao2.setApplyNo(num2);
        ao2.setApplyState("R");
        ao2.setAregDt(time1);
        dao.doUpdate(ao2);%>
       
    }


</script>   
    <script>
            $( function() {
                $( "#slider-range" ).slider({
                    range: true,
                    min: 0,
                    max: 500,
                    values: [ 75, 300 ],
                    slide: function( event, ui ) {
                        $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
                    }
                });
                $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
                    " - $" + $( "#slider-range" ).slider( "values", 1 ) );
            } );
            </script>
</body>

</html>