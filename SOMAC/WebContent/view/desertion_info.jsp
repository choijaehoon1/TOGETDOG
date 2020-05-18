<%@ page import="com.togetdog.desertion.DesertionVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.togetdog.cmn.SearchVO"%>
<%@ page import="com.togetdog.api.ApiExplorer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>      
<%@ include file="/cmn/common.jsp" %>
     
<%
   ApiExplorer search = new ApiExplorer();
   List<DesertionVO> tlist = search.totalRetrieve(1,24,"");
   /*
    * 축종코드 - 개: 417000 - 고양이 : 422400 - 기타 : 429900
    */
   String upKind; 
   String uprCd;
   List<DesertionVO> dlist = search.dogRetrieve(1, 24, "417000", "");
   List<DesertionVO> clist = search.catRetrieve(1, 24, "422400", "");
   List<DesertionVO> elist = search.etcRetrieve(1, 24, "429900", "");
   
%>


<!doctype html>
<html class="no-js" lang="zxx">

<head>
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
   <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

   <!-- header-start -->
 	 <%@ include file="/cmn/togetdog_header.jsp" %>
   <!-- header-end -->


   <!-- slider_area_start -->
   <div class="slider_area">
      <div class="single_slider  d-flex align-items-center slider_bg_1">
         <div class="container">
            <div class="row align-items-center justify-content-center">
               <div class="col-xl-10">
                  <div class="slider_text text-center justify-content-center">
                     <p id="desertionSubTitle">TOGETDOG</p>
                     <h3 id="desertionTitle">유기동물 조회 서비스</h3>
                     <div class="search_form">
                        <form action="/SOMAC/view/info.do" name="searchFrm" id="searchFrm"  method="get">
                           <input type="hidden" name="work_div"  value="do_retrieve" /> 
                  		   
                           <div class="row align-items-center">
                              <div class="col-xl-8 col-md-8">
                                     <div class="input_field">
                                                <select class="wide" name="search_area" id="search_area">
                                                        <option value="">지역을 입력하세요</option>
                                                        <option value="6110000">서울특별시</option>
                                                        <option value="6260000">부산광역시</option>
                                                        <option value="6270000">대구광역시</option>                                     
                                                        <option value="6280000">인천광역시</option>
                                                        <option value="6290000">광주광역시</option>                      
                                                        <option value="5690000">세종특별자치시</option>
                                                        <option value="6300000">대전광역시</option>
                                                        <option value="6310000">울산광역시</option>
                                                        <option value="6410000">경기도</option>                                     
                                                        <option value="6420000">강원도</option> 
                                                </select>
                                              </div>
                              </div>
                              <div class="col-xl-4 col-md-4">
                                 <div class="button_search">
                                    <input  type="button" class="boxed-btn2" value="지역검색" id="to_list_btn" />
                                 </div>
                              </div>
                           </div>
                        </form>
                     </div>
                     <div class="quality">
                        <ul>
                           <li>
                             <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=1&animal=dog"> <button>#견종백과</button> </a>
                           </li>
                           <li>
                             <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f&animal=dog"> <button>#애견상식</button> </a>
                           </li>
                           <li>
                              <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f4&animal=dog"> <button>#애견교육</button> </a>
                           </li>
                           <li>
                              <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f2&animal=dog"> <button>#애견미용</button> </a>
                           </li>
                           <li>
                              <a href="http://www.zooseyo.or.kr/Yu_board/know.html?gubun=f3&animal=dog"> <button>#애견건강</button> </a>
                           </li>
                           <li>
                              <button>#구출신고</button>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- slider_area_end -->


   <!-- explorer_europe start  -->
   <div class="explorer_europe">
      <div class="container">
         <div class="explorer_wrap">
            <div class="row align-items-center">
               <div class="col-xl-6 col-md-4">
                  <h2 id="adopTitle">유기동물정보</h2>
               </div>
               <div class="col-xl-6 col-md-8">
                  <div class="explorer_tab">
                     <nav>
                        <div class="nav" id="nav-tab" role="tablist">
                           <a id="tsearch" class="nav-item nav-link active" id="nav-home-tab"  data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">전체</a> 
                           <a id="dsearch" class="nav-item nav-link" id="nav-profile-tab"  data-toggle="tab" href="#nav-profile" role="tab"  aria-controls="nav-profile" aria-selected="false">강아지</a> 
                           <a id="csearch" class="nav-item nav-link" id="nav-contact-tab"  data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">고양이</a> 
                           <a id="esearch" class="nav-item nav-link" id="nav-contact-tab2" data-toggle="tab" href="#nav-contact2" role="tab" aria-controls="nav-contact" aria-selected="false">기타</a>
                        </div>
                     </nav>
                  </div>
               </div>
            </div>
         </div>
         <div class="tab-content" id="nav-tabContent">

            <!-- 전체 카테고리  시작-->

            <input type="hidden" name="tseq" />
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
               <div class="row">
               <% for (int i=0; i <tlist.size(); i++){  %>
              
                  <div class="col-xl-4 col-lg-4 col-md-6">
                     <div class="single_explorer">
                        <div class="thumb">
                            <table id="tlistTable" class="table">
			                   <tr>
			                       <td><img class="imgDiv" src="<%= tlist.get(i).getPopFile() %>" alt=""></td>
			                       <td style="display:none;"><%= tlist.get(i).getDesertionNo() %></td>
			                   </tr>
		                    </table>
                         
                        </div>
                        <div class="explorer_bottom d-flex">
                           <div class="icon">
                              <i class="flaticon-beach"></i>
                           </div>
                           <div class="explorer_info">
                              <h3>
                                 <a ><%=tlist.get(i).getNoticeNo() %></a>
                              </h3>
                              <p>공고종료: <%=tlist.get(i).getNoticeEdt() %></p>
                              <p>품종: <%=tlist.get(i).getKindCd() %></p>
                              <p>특징:  <%=tlist.get(i).getSpecialMark() %></p>
                           </div>
                        </div>
                     </div>
                  </div>
                  <%} %>
               </div>
            </div>
            <!-- 전체 카테고리 끝 -->

            <!-- 강아지 카테고리 시작 -->
            <input type="hidden" name="dseq" />
            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
               <div class="row">
                  <% for (int i=0; i <dlist.size(); i++){  %>
                  <div class="col-xl-4 col-lg-4 col-md-6">
                     <div class="single_explorer">
                      	<div class="thumb">
                            <table id="dlistTable" class="table">
			                   <tr>
			                       <td><img class="imgDiv" src="<%= dlist.get(i).getPopFile() %>" alt=""></td>
			                       <td style="display:none;"><%= dlist.get(i).getDesertionNo() %></td>
			                   </tr>
		                    </table>
                        </div>
                        
                        <div class="explorer_bottom d-flex">
                           <div class="icon">
                              <i class="flaticon-beach"></i>
                           </div>
                           <div class="explorer_info">
                              <h3>
                                 <a ><%=dlist.get(i).getNoticeNo() %></a>
                              </h3>
                              <p>공고종료:<%=dlist.get(i).getNoticeEdt() %></p>
                              <p>품종: <%=dlist.get(i).getKindCd() %></p>
                              <p>특징:  <%=dlist.get(i).getSpecialMark() %></p>
                           </div>
                        </div>
                     </div>
                  </div>
                  <%} %>
               </div>
            </div>
            <!-- 강아지 카테고리 끝 -->

            <!-- 고양이 카테고리 시작 -->
            <input type="hidden" name="cseq" />
            <div class="tab-pane fade" id="nav-contact" role="tabpanel"
               aria-labelledby="nav-contact-tab">
               <div class="row">
                  <% for (int i=0; i <clist.size(); i++){  %>
                     <div class="col-xl-4 col-lg-4 col-md-6">
                        <div class="single_explorer">
                           <div class="thumb">
                              <table id="clistTable" class="table">
			                   <tr>
			                       <td><img class="imgDiv" src="<%= clist.get(i).getPopFile() %>" alt=""></td>
			                       <td style="display:none;"><%= clist.get(i).getDesertionNo() %></td>
			                   </tr>
		                    </table>
                           </div>
                           <div class="explorer_bottom d-flex">
                              <div class="icon">
                                 <i class="flaticon-beach"></i>
                              </div>
                              <div class="explorer_info">
                                 <h3>
                                    <a ><%=clist.get(i).getNoticeNo() %></a>
                                 </h3>
                                 <p>공고종료:<%=clist.get(i).getNoticeEdt() %></p>
                                 <p>품종: <%=clist.get(i).getKindCd() %></p>
                                 <p>특징:  <%=clist.get(i).getSpecialMark() %></p>
                              </div>
                           </div>
                        </div>
                     </div>
                  <%} %>
               </div>
            </div>
            <!-- 고양이 카테고리 끝 -->


            <!-- 기타 카테고리 시작 -->
            <input type="hidden" name="eseq" />
            <div class="tab-pane fade" id="nav-contact2" role="tabpanel" aria-labelledby="nav-contact-tab2">
               <div class="row">
                  <% for (int i=0; i <elist.size(); i++){  %>
                     <div class="col-xl-4 col-lg-4 col-md-6">
                        <div class="single_explorer">
                           <div class="thumb">
                            <table id="elistTable" class="table">
			                   <tr>
			                       <td><img class="imgDiv" src="<%= elist.get(i).getPopFile() %>" alt=""></td>
			                       <td style="display:none;"><%= elist.get(i).getDesertionNo() %></td>
			                   </tr>
		                    </table>
                           </div>
                           <div class="explorer_bottom d-flex">
                              <div class="icon">
                                 <i class="flaticon-beach"></i>
                              </div>
                              <div class="explorer_info">
                                 <h3>
                                    <a ><%=elist.get(i).getNoticeNo() %></a>
                                 </h3>
                                 <p>공고종료:<%=elist.get(i).getNoticeEdt() %></p>
                                 <p>품종: <%=elist.get(i).getKindCd() %></p>
                                 <p>특징:  <%=elist.get(i).getSpecialMark() %></p>
                              </div>
                           </div>
                        </div>
                     </div>
                  <%} %>
               </div>
            </div>
            <!-- 기타 카테고리 끝 -->

            <!--================페이징 시작===============-->
            
            <!--================페이징 끝=================-->
         </div>
      </div>
   </div>
   <!--/ explorer_europe start  -->
   
   
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
         iconsLibrary : 'fontawesome',
         icons : {
            rightIcon : '<span class="fa fa-caret-down"></span>'
         }
      });
      $('#datepicker2').datepicker({
         iconsLibrary : 'fontawesome',
         icons : {
            rightIcon : '<span class="fa fa-caret-down"></span>'
         }

      });
   </script>


   <script type="text/javascript">
   
	$("#to_list_btn").on('click',function(){
		//console.log("#to_list_btn");
		var search_area = $("#search_area").val();
		goRetrieve(search_area);
	});
	
	//목록호출
	function goRetrieve(search_area){
		window.location.href='/SOMAC/view/info.do?work_div=do_retrieve&search_area='+search_area;
	}

	
	$("#tlistTable>tbody").on("dblclick","tr",function(event){
		//console.log("event:"+event);
		//console.log("#tlistTable>tbody>tr");
		var pTR = $(this);//tr
		var pTR_Children = pTR.children();
		var tseq = pTR_Children.eq(1).text();
		console.log("tseq:"+tseq);
		/* alert("tseq:"+tseq); */
		/* tfrm.work_div.value ="tdo_SelectOne"; */
		goTdoselectOne(tseq);
	});
	
	
	function goTdoselectOne(tseq){
		window.location.href='/SOMAC/view/info.do?work_div=tdo_selectOne&tseq='+tseq;
	}
	
	$("#dlistTable>tbody").on("dblclick","tr",function(event){
		//console.log("event:"+event);
		//console.log("#tlistTable>tbody>tr");
		var pTR = $(this);//tr
		var pTR_Children = pTR.children();
		var dseq = pTR_Children.eq(1).text();
		console.log("dseq:"+dseq);
		/* alert("dseq:"+dseq); */
		/* tfrm.work_div.value ="tdo_SelectOne"; */
		goDdoselectOne(dseq);
	});
	
	
	function goDdoselectOne(dseq){
		window.location.href='/SOMAC/view/info.do?work_div=ddo_selectOne&dseq='+dseq;
	}

	
	$("#clistTable>tbody").on("dblclick","tr",function(event){
		//console.log("event:"+event);
		//console.log("#tlistTable>tbody>tr");
		var pTR = $(this);//tr
		var pTR_Children = pTR.children();
		var cseq = pTR_Children.eq(1).text();
		console.log("cseq:"+cseq);
		/* alert("cseq:"+cseq); */
		/* tfrm.work_div.value ="tdo_SelectOne"; */
		goCdoselectOne(cseq);
	});
	
	function goCdoselectOne(cseq){
		window.location.href='/SOMAC/view/info.do?work_div=cdo_selectOne&cseq='+cseq;
	}

	
	$("#elistTable>tbody").on("dblclick","tr",function(event){
		//console.log("event:"+event);
		//console.log("#tlistTable>tbody>tr");
		var pTR = $(this);//tr
		var pTR_Children = pTR.children();
		var eseq = pTR_Children.eq(1).text();
		console.log("eseq:"+eseq);
		/* alert("eseq:"+eseq); */
		/* tfrm.work_div.value ="tdo_SelectOne"; */
		goEdoselectOne(eseq);
	});
	
	function goEdoselectOne(eseq){
		window.location.href='/SOMAC/view/info.do?work_div=edo_selectOne&eseq='+eseq;
	}

	</script>
</body>
</html>







