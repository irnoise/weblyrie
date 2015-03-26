<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set value="songInfoBean" var="songInfo" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Web-Lyrie</title>
  <link rel="stylesheet" type="text/css" href="css/grobal.css">
</head>
<body>

  <jsp:include page="../../../header.jsp" />

  <div class="contents">

  <h3><s:property value="songInfoBean.artist" /></h3>
  <h3><s:property value="songInfoBean.track" /></h3>

  <!-- "escape=false"パラメータを設定すると、文字列内のタグが有効になる -->
  <p><s:property value="songInfoBean.lyric" escape="false" /></p>

  <s:form action="favorite-add" theme="simple" id="songInfoForm">
    <s:hidden key="songInfoBean.artist" />
    <s:hidden key="songInfoBean.track" />
    <s:hidden key="songInfoBean.begin" />
    <s:hidden key="songInfoBean.url" />
    <s:hidden key="songInfoBean.lyric" />
    <s:submit value="この歌詞を保存" />
  </s:form>

  </div>

</body>
</html>