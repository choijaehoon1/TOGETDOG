<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 2. 28.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>


<%@page import="com.togetdog.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/cmn/common.jsp" %>
<%

	MemberVO mvo = (MemberVO)session.getAttribute("user");
	String id = mvo.getMemberId();

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
                            <h3 id="myPageTitle">후기 작성</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ bradcam_area  -->
  <!-- ================ contact section start ================= -->
  
  <section class="contact-section section_padding">
  
    <div class="container">
      <div class="d-none d-sm-block mb-5 pb-4">
        
      </div>


      <div class="row">
        <div class="col-12">
          <h3 class="myPageTitleFont">후기 작성</h3>
        </div>
        ${fileList} 
        <div class="col-lg-8">
          <form class="form-contact contact_form" action="<%=HR_PATH %>/view/mypage.do" method="post" name="insert_frm" id="insert_frm" novalidate="novalidate">
            <input type="hidden" name="work_div" value ="do_insert" id="work_div" />
            <input type="hidden" name="apply_no" id="apply_no" value="${apply_no}"/>
            <div class="row">    
              <div class="col-12">
                <div class="form-group">
                	<input class="form-control" name="title" id="title" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = '제목'" placeholder = '제목'>
                </div>
              </div>          
              <div class="col-sm-9">
                <div class="form-group">
                    <input class="form-control" name="member_id" id="member_id" value="<%=id%>" type="text"  onblur="this.placeholder = '<%=id%>'" placeholder = '<%=id%>' disabled>
                </div>
              </div>

              <div class="col-12">
                <div class="form-group">                  
                	<textarea class="form-control w-100" name="contents" id="contents" cols="30" rows="9" onfocus="this.placeholder = ''" onblur="this.placeholder = '내용을 입력해주세요.'" placeholder = '내용을 입력해주세요.'></textarea>
                </div>
              </div>
              <div class="col-sm-6">
	             <div class="form-group"><!-- 파일첨부 버튼자리 -->
                                    
                     <input type="text" class="form-control" name="fname" id="fname" ></br>
        
                    <input type="button"  class="button button-contactForm btn_4 boxed-btn" onclick="javascript:centerPopup(this.form);" value="파일 등록하기">
             
                             <!-- end 파일첨부 버튼자리 -->
                          
                 </div>
              </div>
              
            </div>
            <div class="form-group mt-3">
              <p>
                  <button type="button" id="insert_btn" class="button button-contactForm btn_4 boxed-btn">등록</button>
                  <button type="button" id="cancle_btn" class="button button-contactForm btn_4 boxed-btn">취소</button>
              </p>
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
  <!-- ================ contact section end ================= -->

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

      
      $("#insert_btn").on('click',function(){
          
          var work_div = $("#work_div").val();
          if(work_div == null || work_div.trim().length ==0){
              //alert("작업구분을 확인하세요.");
              return;
          }
          
          var title = $("#title").val(); 
          if(title == null || title.trim().length == 0){
              $("#title").focus();
              alert("제목을 입력하세요.");
              return;
          }
          
          var member_id = $("#member_id").val();
          if(member_id == null || member_id.trim().length == 0){
              $("#member_id").focus();//focus
              alert("등록자를 확인하세요.");
              return;
          }
          
          var contents = $("#contents").val();
          if(contents == null || contents.trim().length == 0){
              $("#contents").focus();//focus
              alert("내용을 입력하세요.");
              return;
          }
          
          var fname = $("#fname").val();
          console.log(fname);
          
          
          if(confirm(title+"을(를)\n등록하시겠습니까?") == false) return;
          
          
          $.ajax({
           
               type:"POST",
               url:"/SOMAC/view/mypage.do",
               dataType:"html",
               data:{"work_div":work_div,
            	    "apply_no":$("#apply_no").val(),
                    "title":$("#title").val(),
                    "contents":$("#contents").val(),
                    "member_id":$("#member_id").val(),
                    "fname":$("#fname").val()
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
                        goReview();
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
          
      });//insert_btn end
      
      
      function goReview(){
          window.location.href='/SOMAC/view/community.do?work_div=do_retrieve_r';
          //후기게시판으로
      }
      

      $("#cancle_btn").on('click',function(){
         window.location.href='/SOMAC/view/mypage.do?work_div=do_retrieve';

      }); 
      
     	function centerPopup(frm){
    		//console.log('centerPopup');
    		/*
    		window.open 옵션
    		no:0
    		yes:1
    		
    		width : 팝업창 가로길이
    		height : 팝업창 세로길이
    		toolbar=no : 단축도구창(툴바) 표시안함
    		menubar=no : 메뉴창(메뉴바) 표시안함
    		location=no : 주소창 표시안함
    		scrollbars=no : 스크롤바 표시안함
    		status=no : 아래 상태바창 표시안함
    		resizable=no : 창변형 하지않음
    		fullscreen=no : 전체화면 하지않음
    		channelmode=yes : F11 키 기능이랑 같음
    		left=0 : 왼쪽에 창을 고정(ex. left=30 이런식으로 조절)
    		top=0 : 위쪽에 창을 고정(ex. top=100 이런식으로 조절)
    		*/
    		
    		var title = "팝업(제목)";
    		var xWidth = window.screen.width/2;
    		var xHeight = window.screen.height/2;
    		console.log('xWidth:'+xWidth);
    		console.log('xHeight:'+xHeight);
    		
    		var pX = xWidth - 400/2;
    		var pY = xHeight - 300/2;
    		
    		console.log('pX:'+pX);
    		console.log('pY:'+pY);
    		
    		var status ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=400,height=300,left="+pX+',top='+pY;
    		window.open("",title,status);
    		
    		frm.target = title;
    		frm.method = "post";
    		frm.action = "<c:url value='/view/mypage_rfile_popup.jsp' /> ";
    		frm.submit();
    		
    		
    	}
       	
       	function setValue(nm){
       		document.getElementById("fname").value = nm;
       	}
       	
      
      
  </script>
</body>

</html>