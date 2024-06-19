<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MunDeuk</title>
    <script>
      $(document).ready(function() {
        // 로그인 성공 여부를 확인합니다.
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('loginSuccess') && urlParams.get('loginSuccess') === 'true') {
          alert('로그인에 성공하였습니다.');
          window.opener.location.href = '/main'; // 부모 창을 리다이렉트합니다.
          window.close(); // 팝업 창을 닫습니다.
        }
      });
    </script>
</head>

<body>
<div>
    <div>
        <div>
            <div>
                <div>
                    <h3>로그인 성공</h3>
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