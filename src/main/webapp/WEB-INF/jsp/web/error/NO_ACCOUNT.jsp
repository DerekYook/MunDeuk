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
    <script src="<c:url value="/js/jquery/jquery-3.7.1.js"/>"></script>
    <script src="<c:url value="/js/jquery/jquery-ui_1.12.1.js"/>"></script>
    <script src="<c:url value="/js/common/common.js"/>"></script>
    <script src="<c:url value="/js/common/util.js"/>"></script>
    <script>
      $(document).ready(function() {
        var chkAccount = confirm("등록된 회원 정보가 없습니다. 가입 하시겠습니까?");
          if (chkAccount) {
            // alert("회원 가입 페이지로 이동합니다.");
            // window.opener.location.href = '/signUp';
            // window.close();
            window.location.href = '/oAuth2SignUp';
          } else {
            alert("로그인 페이지로 이동합니다.");
            window.close();
            window.opener.reload();
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
                    <h1>가입되지 않은 회원입니다.</h1>
                </div>
                <div>
                    <p>Copyright MoonDeuk 2024  All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>