<%@page import="java.util.List"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.qna.QnaDao"%>
<%@page import="com.togetdog.qna.QnaVO"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@ include file="/cmn/common.jsp" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    errorPage="/cmn/error.jsp"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<%
   MemberVO vo123=(MemberVO)session.getAttribute("user");
   String id =vo123.getMemberId();
%>
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>
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
   
  <!-- header-start -->
  <%@ include file="/cmn/togetdog_header.jsp" %>
 <!-- header-end -->
      <!-- bradcam_area  -->
      <div class="bradcam_area bradcam_bg_2">
             <div class="container">
                 <div class="row">
                 
                     <div class="col-xl-12">
                         
                            <div class="slider_text text-center justify-content-center">
                              <h3 id="desertionTitle">질문하기</h3>
                            
                        </div>
                         
                     </div>
                 </div>
             </div>
         </div>
         <!--/ bradcam_area  -->

   <!--================Blog Area =================-->
    
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h2 id="desertionSubTitle" class="contact-title">　</h2>
        </div>
          ${fileList} 
          ${vo}                  
        <div class="col-lg-8">
          <form class="form-contact contact_form" action='<%=HR_PATH %>/view/community.do' method="post" name="insert_frm" novalidate="novalidate" >
            <div class="row">
              <input type="hidden" id="work_div" name="work_div" value="do_insert2" />
               <div class="col-12"></div>
              
              <div class="col-12">
                <div class="form-group">
                  <label for="title" class="col-lg-4 col-md-4 col-sm-4 col-xs-4">제목</label>
                  <input type="button"  class="genric-btn primary small" onclick="javascript:centerPopup(this.form);" value="사진 등록">
                  <input type="hidden" name="fname" id="fname"  >
                  <input class="form-control" name="title" id="title" type="text" >
                </div>
              </div>
             
              <div class="col-sm-8">
                <div class="form-group">
                  <label for="title" class="col-lg-4 col-md-4 col-sm-4 col-xs-4">작성자</label>
                  <input class="form-control" name="member_id" id="member_id" type="text" value="<%=id%>" disabled >
                </div>
              </div>
              
              <div class="col-12">
                <div class="form-group">
                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2">내용</label>
                    <textarea class="form-control w-100" name="contents" id="contents" cols="30" rows="9"></textarea>
                </div>
              </div>
              
            </div>
            
            <div class="form-group mt-3">
              <button type="button" id="insert_btn" class="button button-contactForm btn_4 boxed-btn">작성하기</button>
            <a class="button button-contactForm btn_1 boxed-btn" href='/SOMAC/view/qna.do?work_div=do_retrieve'>목록으로</a> 
            </div>
          </form>
         
        </div>
        
        <div class="col-lg-4" >
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-home"></i></span>
            <div class="media-body">
          
              <p id="cmnContent">Mapo,  Republic of Korea. 21<br/> World Cup buk-ro</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-tablet"></i></span>
            <div class="media-body">
              <p id=cmnTitle> (02) 336 8546 <br/>Mon to Fri 9am to 6pm</p>
              
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-email"></i></span>
            <div class="media-body">
       
              <p id="cmnContent">helpdog@togetdog.com</p>
             
            </div>
          </div>
        </div>
        
      </div>
    </div>
  
   <!--================ Blog Area end =================-->

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
   
   
   <script type="text/javascript">
   
    //위에서 아래로 해석(인터프리터 방식)
        $("#to_list_btn").on('click',function(){
            //console.log("#to_list_btn");
            goRetrieve();
        });
        
      //목록호출
                 
            function goRetrieve(){
                window.location.href='/SOMAC/view/qna.do?work_div=do_retrieve';
    }
        		
    $("#insert_btn").on('click',function(){
    	var work_div = $("#work_div").val();
        var title = $("#title").val();
        if(null == title || title.trim().length==0){
            $("#title").focus();//focus
            alert("제목을 확인 하세요.");
            return;
        }       

       
        var contents = $("#contents").val();
        if(null == contents || contents.trim().length==0){
            $("#contents").focus();//focus
            alert("내용을 입력 하세요.");
            return;
        }
        
        if(false == confirm(title+"을(를)\n등록 하시겠습니까?")){return;}
  
        //ajax
       
        $.ajax({
             type:"POST",
             url:"/SOMAC/view/qna.do",
             dataType:"html", 
             data:{"work_div":work_div,
                   "title":$("#title").val(),
                   "contents":$("#contents").val(),
                   "member_id":$("#member_id").val(),
                    "fname":$("#fname").val()
             },
             success:function(data){ //성공
                 //json String --> json object
               
                  var jsonObj = JSON.parse(data);
                  if(null != jsonObj && jsonObj.msgId=="1"){
                      alert(jsonObj.msgContents);
                      goRetrieve();
                  } else{
                      alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
                      
                  }
                  
              },error:function(xhr,status,error){
                  alert("error:" + error);
              },complete:function(data){
              
              }
              
            });//--ajax
          
      });//--#insert_btn  
    
    
    function centerPopup(frm){
        console.log('centerPopup');
      
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
        frm.action = "<c:url value='/view/community_popup.jsp' /> ";
        frm.submit();
        
        
    }
    
    function setValue(nm){
        document.getElementById("fname").value = nm;
    }


</script> 

</body>

</html>