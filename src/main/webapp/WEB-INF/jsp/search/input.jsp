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

<h4>
歌手名、曲名、歌詞のうち1つ以上の項目を入力して<br>
「検索」ボタンをクリックして下さい。
</h4>

<s:form action="search-song" >
  <s:textfield name="songInfoBean.artist" label="歌手名" />
  <s:textfield name="songInfoBean.track" label="曲名" />
  <s:textfield name="songInfoBean.phrase" label="歌詞" />
  <s:submit value="検索" />
</s:form>

<font color="red"><strong><s:actionerror theme="mytheme" /></strong></font>

</body>
</html>