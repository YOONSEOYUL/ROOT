<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>APOINT 사전</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" href="https://acapo.ml/resources/images/favicon.ico">
<!--===============================================================================================-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<!--===============================================================================================-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="https://acapo.ml/resources/css/footer.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="https://acapo.ml/resources/css/main.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="https://acapo.ml/resources/css/dictionary.css">
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--===============================================================================================-->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="https://acapo.ml/resources/js/dictionary.js"></script>
<!--===============================================================================================-->
</head>
<body>
<!-- Top nav page content -->
<%@ include file="top.jsp" %>

<!-- Begin page content -->
	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <div class="col-md-6">
        <h2>한자 쓰기사전</h2>
        <br>
        	<div class="input-group">
                    <input type="text"class="form-control" id="kanji" name="searchKanji" placeholder="한자 검색" required>
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default" id="kanjiSearch"><i class="fa fa-search"></i></button>
                    </span>
            </div>
        <br>
        <div>
        <div class="kanji">
        <div class="img-kanji">
        <div>
        <img src="https://acapo.ml/resources/images/kanji/orgin.png" id="kanjibg"alt="kanji" class="img-rounded"/>
        </div>
        </div>
        </div>
        <div class="panel panel-default transtxtbox">
        <div class="panel-body transtxt">
  		<c:out value="${kanjiResult}" escapeXml="false" default="음[カタカナ] / 훈[ひらがな]"></c:out>
  		</div>
        </div>
        </div>
        </div>   
        <div class="col-md-6">
        <span><h2>번역기</h2></span>
        <br>
        	<div class="input-group">
        	<form id=transdic>
        	<div>
        		<select class="form-control pull-left" id="src-lang">
        			<option value="ko">한글</option>
        			<option value="ja">일본어</option>
        			<option value="en">영어</option>
        		</select>
            <button type="button" class="btn btn-default pull-right btnsearch" id="transtxt"><i class="fa fa-search"></i></button>
        		<select class="form-control pull-right" id="target-lang">
        			<option value="ja">일본어</option>
        			<option value="ko">한글</option>
        			<option value="en">영어</option>
        		</select>
                
        		<button type="button" class="btn btn-default pull-left btnangle" ><i class="fa fa-angle-double-right"></i></button>
        	</div>
            <textarea class="form-control pull-right" id="trans-query" name="searchText" placeholder="문장 번역" required></textarea>
            </form>  
            </div>
        <div class="panel panel-default">
        
  		<div class="panel-body transbox">
  		<c:out value="${transResult}" escapeXml="false" default="한국어<->영어, 한국어<->일본어 번역을 지원합니다."></c:out>
  		</div>
  		 <a class="naverBi"href="http://developers.naver.com" target="_blank">
    <img src="https://acapo.ml/resources/images/naverBI.png" alt="NAVER 오픈 API" />
</a>
		</div>
        </div> 
      </div>
      </div> <!-- /container -->
    
<!-- footer page content -->
<%@ include file="foot.jsp" %>
    
</body>
</html>
