<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%--<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MoonDeuk</title>
</head>
<script src="<c:url value="/js/jquery/jquery-3.6.0.js"/>"></script>
<script src="<c:url value="/js/common/common.js"/>"></script>
<script src="<c:url value="/js/common/util.js"/>"></script>
<script>
    function fn_register(){
      var nn = document.getElementById('nick_name').value;
      var e = document.getElementById('email').value;
      var p = document.getElementById('password').value;
      $.ajax({
        url: '/member/signUp',
        type: 'post',
        data: {
          nickName: nn,
          email: e,
          password: p
        },
        success: function() {
          alert("등록되었습니다.")
        }
      });
      location.href = "/login";
    }

    function fn_goback(){
      location.href = "/login";
    }
</script>
<body>
<div>
    <h1>회원가입</h1>
</div>
<div>
    <form>
        <div>
            <input type="text" id="nick_name" placeholder="별명"/>
        </div>
        <div>
            <input type="text"  id="email" placeholder="이메일"/>
        </div>
        <div>
            <input type="text"  id="password" placeholder="비밀번호"/>
        </div>
    </form>
    <div onclick="fn_register()">저장</div>
    <div onclick="fn_goback()">취소</div>
</div>

</body>

</html>