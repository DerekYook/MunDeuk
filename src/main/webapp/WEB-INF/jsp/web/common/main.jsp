<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>--%>
<%--<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MunDeuk</title>

    <%--<link rel="stylesheet" href="<c:url value="/vendors/mdi/css/materialdesignicons.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/vendors/base/vendor.bundle.base.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="shortcut icon" href="<c:url value="/images/main/slide.jpg"/><c:url value="/images/favicon.png"/>" />--%>
    <script src="<c:url value="/js/jquery/jquery-3.6.0.min.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
    <script type="text/javascript">
      $(document).ready(function(){
        $('#admId').focus();
        /**버튼 이벤트**/
        $('#loginBtnImg').bind('click', function() {
          if($.trim($('#admId').val()) == '' || $.trim($('#admId').val()) =='ID' ){
            $('#admId').focus();
            alert("ID를 입력하여주세요.");
            return;
          }
          if($.trim($('#admPw').val()) == ''){
            $('#admPw').focus();
            alert("PASSWORD를 입력하여주세요.");
            return;
          }

          fnAriAjaxString("<c:url value="/ajax/adm/admLoginProcess"/>", "loginFrm", loginProcessCallBack );
        });

      });

      $(document).keydown(function(e){
        if(e.keyCode == 13){
          loginGo();
          return false;
        }
      });

      function loginGo(){

        if($.trim($('#admId').val()) == '' || $.trim($('#admId').val()) =='ID' ){
          $('#admId').focus();
          alert("ID를 입력하여주세요.");
          return;
        }
        if($.trim($('#admPw').val()) == ''){
          $('#admPw').focus();
          alert("PASSWORD를 입력하여주세요.");
          return;
        }
        fnAriAjaxString("<c:url value="/ajax/adm/admLoginProcess"/>", "loginFrm", loginProcessCallBack );
      }

      function loginProcessCallBack(result){
        if(result == 'Y'){
          loadingStart();
          location.href="<c:url value="/tiles/adminMain"/>";
        }else if(result == 'VALID_N'){
          loadingStop();
          alert("계정 유효기간이 만료 되었습니다.\n 관리자에게 문의하세요.");
          document.body.style.cursor = "auto";
        }else if(result == 'N'){
          loadingStop();
          alert("아이디 또는 패스워드가 일치하지 않습니다.");
          document.body.style.cursor = "auto";
        }
      }
    </script>
</head>

<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-stretch auth auth-img-bg">
            <div class="row flex-grow">
                <div class="col-lg-6 d-flex align-items-center justify-content-center">
                    <div class="auth-form-transparent text-left p-3">
                        <div class="brand-logo">
                        </div>
                        <h3 style="width:100%;text-align:center;"><b>관리자 로그인</b></h3>
                        <form role="form" class="pt-3" id="loginFrm" name="loginFrm" method="post" action="#" onsubmit="return false">
                            <div class="form-group">
                                <label for="exampleInputEmail">관리자 ID</label>
                                <div class="input-group">
                                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-account-outline text-primary"></i>
                      </span>
                                    </div>
                                    <input type="text" class="form-control form-control-lg border-left-0" id="admId" name="admId" value="admin" placeholder="관리자 ID">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword">비밀번호</label>
                                <div class="input-group">
                                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-lock-outline text-primary"></i>
                      </span>
                                    </div>
                                    <input type="password" class="form-control form-control-lg border-left-0" id="admPw" name="admPw" value="" placeholder="비밀번호">
                                </div>
                            </div>
                            <div class="my-3">
                                <button type="button" id="loginBtnImg" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">LOGIN</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-6 login-half-bg d-flex flex-row">
                    <p class="text-white font-weight-medium text-center flex-grow align-self-end">Copyright MoonDeuk 2024  All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script src="<c:url value="/vendors/base/vendor.bundle.base.js"/>"></script>

<script src="<c:url value="/js/template.js"/>"></script>--%>

</body>

</html>