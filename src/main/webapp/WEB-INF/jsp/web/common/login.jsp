<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script src="<c:url value="/js/jquery/jquery-ui_1.12.1.js"/>"></script>
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

          // Extract CSRF token from hidden input
          var form = document.getElementById('loginFrm');
          var formData = new FormData(form);
          var csrfToken = formData.get('_csrf');

          console.log(csrfToken);

          const serializedValues = $('#loginFrm').serializeObject();
          $.ajax({
            type: 'POST',
            url: '/ajax/loginProcess',
            cache: false,
            contentType: 'application/json',
            // contentType: 'text/html',
            data: JSON.stringify(serializedValues),
            // csrf 처리 start
            beforeSend: function(xhr){
              // Set CSRF token Header
              xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
              // console.log(xhr);
              // console.log('-----------------------------');
              // console.log(csrfToken);
            },
            // csrf 처리 end
            success: function (response) {
              if(response.redirectUrl) {
                location.href = response.redirectUrl;
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
        var form = document.getElementById('loginFrm');
        var formData = new FormData(form);
        var csrfToken = formData.get('_csrf');

        console.log(csrfToken);

        const serializedValues = $('#loginFrm').serializeObject();
        $.ajax({
          type: 'POST',
          url: '/ajax/loginProcess',
          cache: false,
          contentType: 'application/json',
          // contentType: 'text/html',
          data: JSON.stringify(serializedValues),
          // csrf 처리 start
          beforeSend: function(xhr){
            // Set CSRF token Header
            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          },
          // csrf 처리 end
          success: function (response) {
            if(response.redirectUrl) {
              location.href = response.redirectUrl;
            }else {
              console.log("Redirect URL is missing in the response");
            }
          }
        });
      }

      function fn_facebookSignUp(){
        window.name = "Parent_window";
        window.open('/oauth2/authorization/facebook?prompt=login', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }
      function fn_facebookLogin(){
        window.name = "Parent_window";
        window.open('/oauth2/authorization/facebook?prompt=login', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }
      // todo : 회원가입 / 로그인 구분( -> 통합으로 하고  oauth서버 응답이 성공하면 다시 한번 확인 하는 걸로 )
      function fn_kakaoSignUp(){
        window.name = "Parent_window";
        window.open('/oauthRedirect', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
        // window.open('/oauth2/authorization/kakao', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }
      function fn_kakaoLogin(){
        window.name = "Parent_window";
        window.open('/oauthRedirect', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
        // window.open('/oauth2/authorization/kakao', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }
      function fn_siginIn(){
        location.href = "/signUp";
      }

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
                        <form id="loginFrm" name="loginFrm">
<%--                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                            <sec:csrfInput/>
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
                    <div>
                        <button type="button" onclick="fn_facebookSignUp()">페이스북 회원가입</button>
                    </div>
                    <div>
                        <button type="button" onclick="fn_facebookLogin()">페이스북 로그인</button>
                    </div>
                    <div>
                        <button type="button" onclick="fn_kakaoSignUp()">카카오톡 회원가입</button>
                    </div>
                    <div>
                        <button type="button" onclick="fn_kakaoLogin()">카카오톡 로그인</button>
                    </div>
                    <div></div>
                    <div>
                        <button type="button" onclick="fn_siginIn()">회원가입</button>
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