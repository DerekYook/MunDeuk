<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    <title>MunDeuk</title>
    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script src="<c:url value="/js/jquery/jquery-ui_1.12.1.js"/>"></script>
    <script src="<c:url value="/js/common/common.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
    <script>
      // $(document).ready(function() {
      // });
      function fn_logout(){
        var form = document.getElementById('frm');
        var formData = new FormData(form);
        var csrfToken = formData.get('_csrf');

        confirm("정말 로그아웃 하시겠습니까?");
        $.ajax({
          type: 'POST',
          url: '/ajax/logout',
          cache: false,
          contentType: 'application/json',
          beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          },
          success: function () {
            location.href = "/login";
          },
          error: function (){
            console.error("Logout Failed");
            alert("메인으로 이동합니다.");
            location.href = "/login";
          }
        });
      }
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
                    <form id="frm" name="frm">
                        <sec:csrfInput/>
                        <button type="button" onclick="fn_logout()">로그아웃</button>
                    </form>
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