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
        var chkAccount = alert("가입이 완료되었습니다. 로그인 페이지로 되돌아갑니다.");
        window.close();
        window.opener.reload();
      });
    </script>
</head>

<body>
<div>
    <div>
        <div>
            <div>
                <div>
                    <h1>간편 회원가입에 성공하였습니다.</h1>
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