<%@page import="com.togetdog.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>   

<%
	MemberVO mvo= (MemberVO)session.getAttribute("user");
    String smemberId= mvo.getMemberId();

    String hiddenFlag = (String)request.getAttribute("hiddenFlag");
%>
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

      <!-- bradcam_area  -->
      <div class="bradcam_area bradcam_bg_2">
             <div class="container">
                 <div class="row">
                     <div class="col-xl-12">
                         <div class="bradcam_text text-center">
                             <h3 id="desertionTitle">TOGETDOG</h3>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!--/ bradcam_area  -->

   <!--================Blog Area =================-->
   <section class="blog_area single-post-area section-padding">
      <div class="container">
         <div class="row">
            <div class="col-lg-8 posts-list">
               <div class="single-post">
                  <div class="feature-img">
                     <img class="img-fluid" src="${tvo.popFile }" alt="">
                  </div>
                  <div class="blog_details">
                     <h2 class="cmnTitle"> 유기동물 상세정보 </h2>
                 
                     <ul class="blog-info-link mt-4 mb-4">
                        <li><a href="#"><i class="fa fa-user"></i> 공고번호 </a></li>
                        <a>	${tvo.noticeNo }</a> <br>
                        
                        <li><a href="#"><i class="fa fa-user"></i> 색상 </a></li>
                        <a>${tvo.colorCd }</a> <br>
                        
                                                
                       <li><a href="#"><i class="fa fa-user"></i> 성별</a></li>
                        <a>${tvo.sexCd }</a> <br>
                        
                        <li><a href="#"><i class="fa fa-user"></i> 나이</a></li>
                        <a>${tvo.age }</a> <br>
                        
                        
                       <li><a href="#"><i class="fa fa-user"></i> 체중 </a></li>
                        <a>${tvo.weight }</a> <br>
                        
                       <li><a href="#"><i class="fa fa-user"></i> 접수일시</a></li>
                        <a>${tvo.happenDt }</a> <br>
                        
                                         
                       <li><a href="#"><i class="fa fa-user"></i> 발생장소</a></li>
                        <a>${tvo.happenPlace }</a> <br>
                        

                        <li><a href="#"><i class="fa fa-user"></i> 공고기한</a></li>
                        <a>${tvo.noticeSdt } ~ ${tvo.noticeEdt }</a> <br>
                     
                     
                        <li><a href="#"><i class="fa fa-user"></i> 특징</a></li> 
                        <a>${tvo.specialMark }</a> <br>
                        
                    </ul>

                  </div>
                  
                 
                  
                 <div class="blog_details" style="display: <%= hiddenFlag %>">
                     <h2 class="cmnTitle"> 입양 정보 </h2>
                 
                     <ul class="blog-info-link mt-4 mb-4">
                        <li><a href="#"><i class="fa fa-user"></i> 보호센터이름 </a></li>
                        <a>${tvo.careNm }</a> <br>
                        
                        <li><a href="#"><i class="fa fa-user"></i> 전화번호	</a></li>
                        <a>${tvo.officeTel }</a> <br>
                        
                        <li><a href="#"><i class="fa fa-user"></i> 보호장소</a></li>
                        <a>	${tvo.careAddr }</a> <br>
                        
                       <li><a href="#"><i class="fa fa-user"></i> 담당자</a></li>
                        <a>${tvo.chargeNm }</a> <br>
 
                    </ul>

                  </div>
               </div>
      

               <div class="comment-form">
                  <h2 class="cmnTitle"> 입양신청 하기  </h2>
                  <form class="form-contact comment_form" id="insert_frm" action='<%=HR_PATH %>/view/adop.do' method="post" >
                     <input type="hidden" name="work_div" value="tdo_insert" id="work_div" />
                     <!-- <input type="hidden" name="desertion_no" value="000000001" id="desertion_no" /> -->
                     <input type="hidden" name="desertion_no" value="${tvo.desertionNo }" id="desertion_no" />
                     <input type="hidden" name="member_id" value="<%=smemberId %>" id="member_id" />
                     <div class="row">
                       <div class="col-sm-12">
                           <div class="form-group">
                              <input class="form-control" name="member_id" id="member_id" type="text" value="<%=smemberId %>" readonly="readonly">
                           </div>
                        </div>
                        <div class="col-sm-12" style="padding-bottom: 30px">
                           <div class="form-group">
	                              <select class="wide" name="family_cnt" id="family_cnt">
						                <option value="">세대원 수 입력하세요</option>
						                <option value="1">1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명</option>
										<option value="5">5명</option>
										<option value="6">6명</option>
										<option value="9">기타</option>
	            				  </select>
                           </div>
                        </div>
                        <div class="col-12">
                           <div class="form-group" style="padding-bottom: 45px">
                           
                            <select class="wide" name="experience_yn" id="experience_yn">
						                <option value="">반려동물을 키워 보셨나요</option>
						                <option value="y">예</option>
										<option value="n">아니요</option>
	            			 </select>
                           </div>
                        </div>
                     
                        <div class="col-12">
                           <div class="form-group">
                              <textarea class="form-control w-100" name="apply_reason" id="apply_reason" cols="30" rows="9"
                                 placeholder="입양을 희망하는 이유는 무엇인가요?"></textarea>
                           </div>
                        </div>

                     </div>
                     <div class="form-group">
                    	<input class="button button-contactForm btn_1 boxed-btn"  type="button"  value="입양신청" id="insert_btn" />
                     </div>
                  </form>
               </div>
            </div>
            <div class="col-lg-4">
               <div class="blog_right_sidebar">
                  <aside class="single_sidebar_widget search_widget">
                     <form action="#">
                        <div class="form-group">
                           <div class="input-group mb-3">
                              <input type="text" class="form-control" placeholder='Search Keyword'
                                 onfocus="this.placeholder = ''" onblur="this.placeholder = 'Search Keyword'">
                              <div class="input-group-append">
                                 <button class="btn" type="button"><i class="ti-search"></i></button>
                              </div>
                           </div>
                        </div>
                        <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                           type="submit">Search</button>
                     </form>
                  </aside>
                  <aside class="single_sidebar_widget post_category_widget">
                    <h3 class="cmnTitle" class="widget_title">카테고리</h3>
                     <ul class="list cat-list">
                        <li>
                           <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=1&animal=dog" class="d-flex">
                              <p>견종백과</p>
                           </a>
                        </li>
                        <li>
                          <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f&animal=dog" class="d-flex">
                              <p>애견상식</p>
                           </a>
                        </li>
                        <li>
                          <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f4&animal=dog" class="d-flex">
                              <p>애견교육</p>
                           </a>
                        </li>
                        <li>
                           <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f2&animal=dog" class="d-flex">
                              <p>애견미용</p>
                           </a>
                        </li>
                        <li>
                            <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f3&animal=dog" class="d-flex">
                              <p>애견건강</p>
                           </a>
                        </li>
                        <li>
                            <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f3&animal=dog" class="d-flex">
                              <p>구출신고</p>
                           </a>
                        </li>
                     </ul>
                  </aside>
               </div>
            </div>
         </div>
      </div>
   </section>
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
   
	$("#insert_btn").on('click',function(){
		//console.log("#insert_btn");
		var work_div = $("#work_div").val();
		if(null == work_div || work_div.trim().length==0){
				return;
		}
		
		/* var desertionNo =  $("#desertion_no").val();
		var memberId = $("#member_id").val(); */
		
		var familyCnt    = $("#family_cnt").val();
		if(null == familyCnt || familyCnt.trim().length==0){
			$("#family_cnt").focus();//focus
			alert("세대원 수를 입력하세요");
			return;
		}		
		
		var experienceYn   = $("#experience_yn").val();
		if(null == experienceYn || experienceYn.trim().length==0){
			$("#experience_yn").focus();//focus
			alert("반려동물 경험 유무를 입력하세요");
			return;
		}
		
		var applyReason = $("#apply_reason").val();
		if(null == applyReason || applyReason.trim().length==0){
			$("#apply_reason").focus();//focus
			alert("입양신청 이유를 입력하세요");
			return;
		}
		
		if(false == confirm("입양 신청서를 \n등록 하시겠습니까?"))return;
		
		//동기통신
		//var frm = document.insert_frm;
		//frm.work_div.value = "do_insert";
		//frm.action = "/DaoWEB/board/board.do";
		//frm.submit();
		
		//비동기통신
		
  		//ajax
		$.ajax({
			 type:"POST",
			 url:"/SOMAC/view/adop.do",
			 dataType:"html", 
			 data:{
				   "work_div":work_div,
				   "desertion_no":$("#desertion_no").val(),
				   "member_id":$("#member_id").val(),
			       "family_cnt":$("#family_cnt").val(),
			       "experience_yn":$("#experience_yn").val(),
			       "apply_reason":$("#apply_reason").val()
			 },
			 success:function(data){ //성공
				 //json String --> json object
				   
			      //console.log("data="+data);
				  var jsonObj = JSON.parse(data);
				  //console.log("msgId="+jsonObj.msgId);
				  //console.log("msgContents="+jsonObj.msgContents);
			 	  //alert("data:"+data);
			 	  if(null != jsonObj && jsonObj.msgId=="1"){
			 		  alert(jsonObj.msgContents);
			 		  goRetrieve();
			 	  }else{
			 		 alert(jsonObj.msgContents);
			 	  }
			 },
			 error:function(xhr,status,error){
			  alert("error:"+error);
			 },
			 complete:function(data){
			 
			 }   
		
		});//--ajax
		
		
		//console.log("work_div="+work_div);
		//console.log("title="+title);
		//console.log("reg_id="+reg_id);
		//console.log("contents="+contents);
		
	});//--#insert_btn	
	
	
	$(document).ready(function(){
		
		//console.log("document ready");

	
	});


</script>	

</body>

</html>