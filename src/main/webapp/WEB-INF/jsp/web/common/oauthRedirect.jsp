<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>카카오 로그인</title>

    <!-- 필요한 JS 파일 포함 -->
    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script>
      $(document).ready(function () {
        // 로그인 성공 여부를 확인합니다.
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('gb') && urlParams.get('gb') === 'kakao') {
          if (urlParams.has('loginSuccess') && urlParams.get('loginSuccess') === 'true') {
            alert("카카오 로그인에 성공하였습니다.");
            //   window.opener.location.href = '/main'; // 부모 창을 리다이렉트합니다.
            //   window.close(); // 팝업 창을 닫습니다.
            // // } else if (urlParams.has('error') && urlParams.get('error') === 'true') {
            // //   var chkAccount = confirm('회원가입이 필요합니다.');
            // //   if (chkAccount) {
            // //     alert("회원 가입 페이지로 이동합니다.");
            // //     window.opener.location.href = '/signUp';
            // //     window.close();
            // //   } else {
            // //     alert("로그인 페이지로 이동합니다.");
            // //     window.close();
            // //     window.opener.reload();
            // //   }
            //
            //   // todo : ajax로그인 체크
            //   $.ajax({
            //     method: POST,
            //     url: '/ajax/accountChk',
            //     success: function (response){
            //       if (response) {
            //         alert("회원 가입 페이지로 이동합니다.");
            //         window.opener.location.href = '/signUp';
            //       } else {
            //         alert("로그인 페이지로 이동합니다.");
            //         window.opener.reload();
            //       }
            //     }
            //   });
            window.opener.location.href = '/main'; // 부모 창을 리다이렉트합니다.
            window.close(); // 팝업 창을 닫습니다.
          } else {
            // init url
            window.location.href = '/oauth2/authorization/kakao';
          }
        } else if (urlParams.has('gb') && urlParams.get('gb') === 'google') {
          if (urlParams.has('loginSuccess') && urlParams.get('loginSuccess') === 'true') {
            alert("구글 로그인에 성공하였습니다.");
            window.opener.location.href = '/main'; // 부모 창을 리다이렉트합니다.
            window.close(); // 팝업 창을 닫습니다.
          } else {
            // init url
            window.location.href = '/oauth2/authorization/google';
          }
        }
      });
    </script>
</head>
<body>

</body>
</html>
