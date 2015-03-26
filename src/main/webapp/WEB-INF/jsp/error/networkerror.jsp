<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web-Lyrie</title>
</head>
<body>

<jsp:include page="../../../header.jsp" />

<h3>ネットワークエラー</h3>
インターネットに接続できませんでした。<br>
ネットワークの状態を確認してからやり直して下さい。

<s:url action="search-input" var="searchInput" />
<p><a href="${searchInput}">検索画面へ</a></p>

</body>
</html>