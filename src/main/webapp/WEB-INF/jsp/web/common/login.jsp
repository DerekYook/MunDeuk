<%--
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
&lt;%&ndash;<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>&ndash;%&gt;
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MoonDeuk</title>

    &lt;%&ndash;<link rel="stylesheet" href="<c:url value="/vendors/mdi/css/materialdesignicons.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/vendors/base/vendor.bundle.base.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="shortcut icon" href="<c:url value="/images/main/slide.jpg"/><c:url value="/images/favicon.png"/>" />&ndash;%&gt;
    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script src="<c:url value="/js/jquery/jquery-ui_1.12.1.js"/>"></script>
    <script src="<c:url value="/js/common/common.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
    &lt;%&ndash;    <script type="text/javascript">&ndash;%&gt;
    <script>
      $(document).ready(function () {
        $('#email').focus();
        /**버튼 이벤트**/
        $('#loginBtnImg').bind('click', function () {
          if ($.trim($('#email').val()) == '' || $.trim($('#email').val()) == 'email') {
            $('#email').focus();
            alert("Email을 입력하여주세요.");
            return;
          }
          if ($.trim($('#password').val()) == '') {
            $('#password').focus();
            alert("PASSWORD를 입력하여주세요.");
            return;
          }

          &lt;%&ndash;fnAriAjaxString("<c:url value="/ajax/adm/admLoginProcess"/>", "loginFrm", loginProcessCallBack );&ndash;%&gt;

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
            beforeSend: function (xhr) {
              // Set CSRF token Header
              xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
              // console.log(xhr);
              // console.log('-----------------------------');
              // console.log(csrfToken);
            },
            // csrf 처리 end
            success: function (response) {
              if (response.redirectUrl) {
                location.href = response.redirectUrl;
              } else {
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

      $(document).keydown(function (e) {
        if (e.keyCode == 13) {
          loginGo();
          return false;
        }
      });

      function loginGo() {

        if ($.trim($('#email').val()) == '' || $.trim($('#email').val()) == 'email') {
          $('#email').focus();
          alert("Email을 입력하여주세요.");
          return;
        }
        if ($.trim($('#password').val()) == '') {
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
          beforeSend: function (xhr) {
            // Set CSRF token Header
            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          },
          // csrf 처리 end
          success: function (response) {
            if (response.redirectUrl) {
              location.href = response.redirectUrl;
            } else {
              console.log("Redirect URL is missing in the response");
            }
          }
        });
      }

      function fn_facebookSignUp() {
        window.name = "Parent_window";
        window.open('/oauth2/authorization/facebook?prompt=login', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }

      function fn_facebookLogin() {
        window.name = "Parent_window";
        window.open('/oauth2/authorization/facebook?prompt=login', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }

      // todo : 회원가입 / 로그인 구분( -> 통합으로 하고  oauth서버 응답이 성공하면 다시 한번 확인 하는 걸로 )
      function fn_kakaoSignUp() {
        window.name = "Parent_window";
        window.open('/oauthRedirect', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
        // window.open('/oauth2/authorization/kakao', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }

      function fn_kakaoLogin() {
        window.name = "Parent_window";
        window.open('/oauthRedirect', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
        // window.open('/oauth2/authorization/kakao', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
      }

      function fn_siginIn() {
        location.href = "/signUp";
      }

      // 보안문자
      function audioPlay(){

        var rand = Math.random();
        var url = "/captcha/captchaAudio";

        $.ajax({
          url: url,
          type: 'POST',
          dataType: 'text',
          data: 'rand=' + rand + '&ans=y',
          success: function(data, status, xhr) {
            var uAgent = navigator.userAgent;
            var soundUrl = '/captcha/captchaAudio?lan=kor&rand=' + rand;

            if(uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
              //winPlayer(soundUrl);
              winPlayer(soundUrl+'&agent=msie');
            } else if (!!document.createElement('audio').canPlayType) {
              try{
                new Audio(soundUrl).play();
              } catch(e) {
                winPlayer(soundUrl);
              }
            }else {
              window.open(soundUrl, '', 'width=1, height=1');
            }
          },
          error: function(xhr, textStatus, errorThrown) {
            console.log("xhr.responseText : "+xhr.responseText);
          }

        });

      }

      function refreshBtn(){
        var rand = Math.random();
        var url = "/captcha/captchaImg?rand=" + rand;
        $('#captchaImg').attr("src", url);
      }

      function innerRandom(){
        // random 취약점 수정
        var seed = new Date().getTime();
        seed = (seed * 9301 +49297) % 233280;
        alert(seed);
        return seed/(233280.0);
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
                            &lt;%&ndash;                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />&ndash;%&gt;
                            <sec:csrfInput/>
                            <div>
                                <label for="InputEmail">Email</label>
                                <div>
                                    <input type="text" id="email" name="email" value=""
                                           placeholder="Email">
                                </div>
                            </div>
                            <div>
                                <label for="InputPassword">Password</label>
                                <div>
                                    <input type="password" id="password" name="password" value=""
                                           placeholder="PW">
                                    <button class="btnPw" type="button"><span
                                            class="material-icons">visibility_off</span></button>
                                </div>
                                <div class="inputMsg"></div>
                            </div>

                            <div>아래 이미지(숫자 또는 문자)를 보이는대로 입력해주세요.</div>

                            <div class="captcha">
                                <div class="img">
                                    <img id="captchaImg" title="보안문자 이미지" src="/captcha/captchaImg"
                                         alt="보안문자 이미지" style="margin: 2px"/>
                                </div>
                                <div id="captchaAudio" style="display: none"></div>
                                <div class="btns">
                                    <button type="button" class="btn-lg btn-gray refreshBtn" onclick="refreshBtn();"><img src="/images/kt/captcha-icon01.svg" alt=""/> 새로고침 </button>
                                    <button type="button" class="btn-lg btn-gray refreshBtn" onclick="audioPlay();"><img src="/images/kt/captcha-icon02.svg" alt=""/> 음성듣기 </button>
                                </div>
                                <div class="input-text">
                                    <input type="text" class="w100" name="captchaAnswer"
                                           id="captchaAnswer" placeholder="보안문자를 입력해 주세요."/>
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
                    <p>Copyright MoonDeuk 2024 All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
</div>
&lt;%&ndash;<script src="<c:url value="/vendors/base/vendor.bundle.base.js"/>"></script>

<script src="<c:url value="/js/template.js"/>"></script>&ndash;%&gt;

</body>

</html>--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<style>
  /* fallback */
  @font-face {
    font-family: 'Material Icons';
    font-style: normal;
    font-weight: 400;
    src: url(https://fonts.gstatic.com/s/materialicons/v142/flUhRq6tzZclQEJ-Vdg-IuiaDsNc.woff2) format('woff2');
  }

  .material-icons {
    font-family: 'Material Icons';
    font-weight: normal;
    font-style: normal;
    font-size: 24px;
    line-height: 1;
    letter-spacing: normal;
    text-transform: none;
    display: inline-block;
    white-space: nowrap;
    word-wrap: normal;
    direction: ltr;
    -webkit-font-feature-settings: 'liga';
    -webkit-font-smoothing: antialiased;
  }
</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MoonDeuk</title>

    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script src="<c:url value="/js/jquery/jquery-ui_1.12.1.js"/>"></script>
    <script src="<c:url value="/js/common/common.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
    <script>

      function refreshBtn() {
        var rand = Math.random();
        var url = "/captcha/captchaImg?rand=" + rand;
        $('#captchaImg').attr("src", url);
      }

      function audioPlay() {
        // var form = document.getElementById('loginFrm');
        // var formData = new FormData(form);
        // var csrfToken = formData.get('_csrf');
        //
        // var rand = Math.random();
        // var url = "/captcha/captchaAudio";
        //
        //
        // $.ajax({
        //   url: url,
        //   type: 'GET',
        //   dataType: 'text',
        //   data: 'rand=' + rand + '&ans=y',
        //   beforeSend: function (xhr) {
        //     xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
        //   },
        //   success: function (data, status, xhr) {
        //     var uAgent = navigator.userAgent;
        //     var soundUrl = '/captcha/captchaAudio?lan=kor&rand=' + rand;
        //
        //     if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
        //       winPlayer(soundUrl + '&agent=msie');
        //     } else if (!!document.createElement('audio').canPlayType) {
        //       try {
        //         new Audio(soundUrl).play();
        //       } catch (e) {
        //         winPlayer(soundUrl);
        //       }
        //     } else {
        //       window.open(soundUrl, '', 'width=1, height=1');
        //     }
        //   },
        //   error: function (xhr, textStatus, errorThrown) {
        //     console.log("xhr.responseText : " + xhr.responseText);
        //   }
        // });
        alert("google cloud console 가입 이슈로 구현만 해놓음");
      }

      $(document).ready(function () {
        $('#email').focus();

        $('#loginBtnImg').bind('click', function () {
          if ($.trim($('#email').val()) == '' || $.trim($('#email').val()) == 'email') {
            $('#email').focus();
            alert("Email을 입력하여주세요.");
            return;
          }
          if ($.trim($('#password').val()) == '') {
            $('#password').focus();
            alert("PASSWORD를 입력하여주세요.");
            return;
          }
          if ($.trim($('#captchaAnswer').val()) == '') {
            $('#captchaAnswer').focus();
            alert("보안문자를 입력하여주세요.");
            return;
          }

          var form = document.getElementById('loginFrm');
          var formData = new FormData(form);
          var csrfToken = formData.get('_csrf');

          const serializedValues = $('#loginFrm').serializeObject();
          $.ajax({
            type: 'POST',
            url: '/ajax/loginProcess',
            cache: false,
            contentType: 'application/json',
            data: JSON.stringify(serializedValues),
            beforeSend: function (xhr) {
              xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (response) {
              if (response.redirectUrl) {
                location.href = response.redirectUrl;
              } else {
                console.log("Redirect URL is missing in the response");
              }
            },
            error: function (response) {
              var errorMsg = response.responseJSON.error;
              if (errorMsg === 'INVALID_PROVIDER') {
                console.error("UNKNOWN PROVIDER");
                // Redirect or handle accordingly
              } else if (errorMsg === 'NOT_FOUND') {
                console.error("NOT FOUND ACCOUNT");
                // Redirect or handle accordingly
              } else if (errorMsg === 'INVALID_ACCOUNT') {
                console.error("INVALID ACCOUNT");
                alert("계정 정보를 다시 확인하시기 바랍니다.");
              } else if (errorMsg === 'INVALID_CAPTCHA') {
                console.error("FAIL CAPTCHA VALIDATE");
                alert("보안 문자를 다시 확인하시기 바랍니다.");
              } else {
                console.error("Authentication failed: " + errorMsg);
              }
            }
          });
        });

        const pwInput = $("#password");
        $('.btnPwIv').bind('click', function () {
          document.getElementById("btnPwIv").style.display = 'none';
          document.getElementById("btnPwV").style.display = 'inline-flex';
          pwInput.prop("type", "text");
        });
        $('.btnPwV').bind('click', function () {
          document.getElementById("btnPwIv").style.display = 'inline-flex';
          document.getElementById("btnPwV").style.display = 'none';
          pwInput.prop("type", "password");
        });
      });

      $(document).keydown(function (e) {
        if (e.keyCode == 13) {
          loginGo();
          return false;
        }
      });

      function loginGo() {
        if ($.trim($('#email').val()) == '' || $.trim($('#email').val()) == 'email') {
          $('#email').focus();
          alert("Email을 입력하여주세요.");
          return;
        }
        if ($.trim($('#password').val()) == '') {
          $('#password').focus();
          alert("Password를 입력하여주세요.");
          return;
        }
        if ($.trim($('#captchaAnswer').val()) == '') {
          $('#captchaAnswer').focus();
          alert("보안문자를 입력하여주세요.");
          return;
        }

        var form = document.getElementById('loginFrm');
        var formData = new FormData(form);
        var csrfToken = formData.get('_csrf');

        const serializedValues = $('#loginFrm').serializeObject();
        $.ajax({
          type: 'POST',
          url: '/ajax/loginProcess',
          cache: false,
          contentType: 'application/json',
          data: JSON.stringify(serializedValues),
          beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          },
          success: function (response) {
            if (response.redirectUrl) {
              location.href = response.redirectUrl;
            } else {
              console.log("Redirect URL is missing in the response");
            }
          },
          error: function (response) {
            var errorMsg = response.responseJSON.error;
            if (errorMsg === 'INVALID_PROVIDER') {
              console.error("UNKNOWN PROVIDER");
              // Redirect or handle accordingly
            } else if (errorMsg === 'NOT_FOUND') {
              console.error("NOT FOUND ACCOUNT");
              // Redirect or handle accordingly
            } else if (errorMsg === 'INVALID_CAPTCHA') {
              console.error("FAIL CAPTCHA VALIDATE");
              alert("입력 정보를 다시 확인하시기 바랍니다.");
            } else {
              console.error("Authentication failed: " + errorMsg);
            }
          }
        });
      }

      function fn_googleLogin() {
        window.name = "Parent_window";
        window.open('/oauthRedirect?gb=google', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
       }

      function fn_kakaoLogin() {
        window.name = "Parent_window";
        window.open('/oauthRedirect?gb=kakao', 'popupChk',
            'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
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
                            <sec:csrfInput/>
                            <div>
                                <label for="InputEmail">Email</label>
                                <div>
                                    <input type="text" id="email" name="email" value=""
                                           placeholder="Email">
                                </div>
                            </div>
                            <div>
                                <label for="InputPassword">Password</label>
                                <div>
                                    <input type="password" id="password" name="password" value=""
                                           placeholder="PW">
                                    <button class="btnPwIv" id="btnPwIv" type="button" style="display: inline-flex"><span
                                            class="material-icons">visibility_off</span></button>
                                    <button class="btnPwV" id="btnPwV" type="button" style="display: none"><span
                                            class="material-icons">visibility</span></button>
                                </div>
                                <div class="inputMsg"></div>
                            </div>

                            <div>아래 이미지(숫자 또는 문자)를 보이는대로 입력해주세요.</div>

                            <div class="captcha">
                                <div class="img">
                                    <img id="captchaImg" title="보안문자 이미지" src="/captcha/captchaImg"
                                         alt="보안문자 이미지" style="margin: 2px"/>
                                </div>
                                <div class="btns">
                                    <button type="button" class="btn-lg btn-gray refreshBtn"
                                            onclick="refreshBtn();">새로고침
                                    </button>
                                    <button type="button" class="btn-lg btn-gray refreshBtn"
                                            onclick="audioPlay();">음성듣기
                                    </button>
                                </div>
                                <div class="input-text">
                                    <input type="text" class="w100" name="captchaAnswer"
                                           id="captchaAnswer" placeholder="보안문자를 입력해 주세요."/>
                                </div>
                            </div>

                            <div>
                                <button type="button" id="loginBtnImg">LOGIN</button>
                            </div>
                        </form>
                    </div>
                    <div>
                        <button type="button" onclick="fn_googleLogin()">구글 로그인</button>
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
                    <p>Copyright MoonDeuk 2024 All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

