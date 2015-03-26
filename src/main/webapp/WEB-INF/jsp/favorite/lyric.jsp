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

  <h3><s:property value="songInfoBean.artist" /></h3>
  <h3><s:property value="songInfoBean.track" /></h3>

  <!-- "escape=false"パラメータを設定すると、文字列内のタグが有効になる -->
  <s:property value="songInfoBean.lyric" escape="false" />

</body>
</html>