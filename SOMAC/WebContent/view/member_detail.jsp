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
<%@page import="java.util.List"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.adoption.AdoptionDao"%>
<%@page import="com.togetdog.adoption.AdoptionVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/cmn/common.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@page import="com.togetdog.member.MemberDao"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>TOGETDOG</title>
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
           font-size: 25px;
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
 
 String memid = request.getParameter("memid");
 
 
MemberDao mv=new MemberDao();
MemberVO inVO=new MemberVO();
MemberVO outVO=new MemberVO();
inVO.setMemberId(memid);
outVO= (MemberVO)mv.doSelectOne(inVO);
 
 %>
 

 <!-- header-start -->
  <%@ include file="/cmn/togetdog_header.jsp" %>
 <!-- header-end -->

        
        
    <!-- slider_area_start -->
    <div class="slider_area">
        <div class="single_slider single_listing  d-flex align-items-center slider_bg_1">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-xl-8 col-lg-8">
                        <div class="slider_text text-center justify-content-center">
                            <p  id="desertionSubTitle">Member Detail Page</p>
                            <h3 id="desertionTitle">회원정보 상세페이지</h3>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- slider_area_end -->

     <div class="explorer_europe list_wrap">
        <div class="container">
            <div class="row">
                <div class="col-xl-4 col-lg-4">  </div>
                           <div class="col-xl-6 col-lg-6 col-md-6">
                                    <div class="single_explorer">
                                        <div class="thumb">
                                        
                                        </div>
                                        <div class="explorer_bottom d-flex">
                                            <div class="icon">
                                                <i class="flaticon-beach"></i>
                                            </div>
                                            <div class="explorer_info">
                                            
                                                <h5 id="adopTitle">Member Details</h5><hr/>
                                                
                                                <ul>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> ID: <%=outVO.getMemberId() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> PW: <%=outVO.getPassword() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> NAME: <%=outVO.getName() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> E-MAIL: <%=outVO.getEmail() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> PHONE: <%=outVO.getPhone()%><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> 회원권한:<%=outVO.getAuthor() %><br/><br/></li> 
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> 회원 등록일: <%=outVO.getRegDt() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> 회원정보 등록자: <%=outVO.getRegId() %><br/><br/></li>
                                                    <li id="cmnContent"><i class=" fa fa-user"></i> 회원정보 수정일: <%=outVO.getModDt() %><br/><br/></li>
                                                 </ul>
                                                <a href="admin.jsp" class="genric-btn success circle" onclick="btnOk();">◀ 이전</a> <!-- 이전버튼 -->
                                               <input  type="button" class="genric-btn danger circle" value="회원삭제" id="delete_btn" /><!-- 회원탈퇴 버튼 -->
                                            </div>
                                        </div>
                                    </div>
                             </div>
                       </div>
                  </div>
             </div>



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
                               
<script type="text/javascript"> 

    function goAdmin(){
    
        window.location.href='/SOMAC/view/admin.jsp';
        
     }
    
//멤버 삭제
    $("#delete_btn").on("click",function(){
      console.log("#delete_btn");
        if(false==confirm('삭제 하시겠습니까?'))return;
        //ajax
        $.ajax({
            type:"POST",
            url:"member.do",
            dataType:"html", 
            data:{"work_div":"do_delete",
                  "member_id":"<%=outVO.getMemberId()%>"
            },
            success:function(data){ //성공
               console.log("data="+data);
               
               var jData = JSON.parse(data);
               //성공
               if(null !=jData && jData.msgId=="1"){
                   alert(jData.msgContents);
                   goAdmin();
               }else{
                   alert(jData.msgId+"||"+jData.msgContents);
               }

            },
            error:function(xhr,status,error){
             alert("error:"+error);
            },
            complete:function(data){

            }   
        });//--ajax     
        
        
    });


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