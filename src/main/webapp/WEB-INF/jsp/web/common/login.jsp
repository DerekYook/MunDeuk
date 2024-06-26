<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MoonDeuk</title>

    <%--<link rel="stylesheet" href="<c:url value="/vendors/mdi/css/materialdesignicons.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/vendors/base/vendor.bundle.base.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="shortcut icon" href="<c:url value="/images/main/slide.jpg"/><c:url value="/images/favicon.png"/>" />--%>
    <script src="<c:url value="/js/jquery/jquery-3.6.0.js"/>"></script>
    <script src="<c:url value="/js/common/common.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
<%--    <script type="text/javascript">--%>
    <script>
      $(document).ready(function(){
        $('#email').focus();
        /**버튼 이벤트**/
        $('#loginBtnImg').bind('click', function() {
          if($.trim($('#email').val()) == '' || $.trim($('#email').val()) =='email' ){
            $('#email').focus();
            alert("Email을 입력하여주세요.");
            return;
          }
          if($.trim($('#password').val()) == ''){
            $('#password').focus();
            alert("PASSWORD를 입력하여주세요.");
            return;
          }

          <%--fnAriAjaxString("<c:url value="/ajax/adm/admLoginProcess"/>", "loginFrm", loginProcessCallBack );--%>
          const serializedValues = $('#loginFrm').serializeObject();
          $.ajax({
            type: 'POST',
            url: '/ajax/loginProcess',
            cache: false,
            contentType: 'application/json',
            data: JSON.stringify(serializedValues),
            success: function (response) {
              if(response.redirectUrl) {
                window.location.href = response.redirectUrl;
              }else {
                console.log("Redirect URL is missing in the response");
              }
                // if (response.status === 'MemberSuccess'){
                //   window.location.href = '/main';
                // } else if (response.status === 'AdminSuccess'){
                //   window.location.href = '/admin/main';
                // } else {
                //   window.location.href = '/loginFail';
                // }
            }
          });
        });
      });

      $(document).keydown(function(e){
        if(e.keyCode == 13){
          loginGo();
          return false;
        }
      });

      function loginGo(){

        if($.trim($('#email').val()) == '' || $.trim($('#email').val()) =='email' ){
          $('#email').focus();
          alert("Email을 입력하여주세요.");
          return;
        }
        if($.trim($('#password').val()) == ''){
          $('#password').focus();
          alert("Password를 입력하여주세요.");
          return;
        }
        const serializedValues = $('#loginFrm').serializeObject();
        <%--fnAriAjaxString("<c:url value="/ajax/login"/>", "loginFrm", loginProcessCallBack );--%>
        $.ajax({
          type: 'POST',
          url: '/ajax/loginProcess',
          cache: false,
          contentType: 'application/json',
          data: JSON.stringify(serializedValues),
          success: function (response) {
            if(response.redirectUrl) {
              window.location.href = response.redirectUrl;
            }else {
              console.log("Redirect URL is missing in the response");
            }
          }
        });
      }

      <%--function loginProcessCallBack(result){--%>
      <%--  if(result == 'Y'){--%>
      <%--    loadingStart();--%>
      <%--    location.href="<c:url value="/tiles/adminMain"/>";--%>
      <%--  }else if(result == 'VALID_N'){--%>
      <%--    loadingStop();--%>
      <%--    alert("계정 유효기간이 만료 되었습니다.\n 관리자에게 문의하세요.");--%>
      <%--    document.body.style.cursor = "auto";--%>
      <%--  }else if(result == 'N'){--%>
      <%--    loadingStop();--%>
      <%--    alert("아이디 또는 패스워드가 일치하지 않습니다.");--%>
      <%--    document.body.style.cursor = "auto";--%>
      <%--  }--%>
      <%--}--%>
    </script>
</head>

<body>
<div>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                        </div>
                        <h3 style="width:100%;text-align:left;"><b>로그인</b></h3>
                        <form role="form" id="loginFrm" name="loginFrm" method="post" action="#" onsubmit="return false">
                            <div>
                                <label for="InputEmail">Email</label>
                                <div>
                                    <input type="text" id="email" name="email" value="" placeholder="Email">
                                </div>
                            </div>
                            <div>
                                <label for="InputPassword">Password</label>
                                <div>
                                    <input type="password" id="password" name="password" value="" placeholder="PW">
                                </div>
                            </div>
                            <div>
                                <button type="button" id="loginBtnImg">LOGIN</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div>
                    <p>Copyright MoonDeuk 2024  All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script src="<c:url value="/vendors/base/vendor.bundle.base.js"/>"></script>

<script src="<c:url value="/js/template.js"/>"></script>--%>

</body>

</html>