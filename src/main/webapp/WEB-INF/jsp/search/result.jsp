<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

<h3>
  <s:if test="songInfoBean.artist.length() != 0">
    歌手名:<s:property value="songInfoBean.artist" /><br>
  </s:if>
  <s:if test="songInfoBean.track.length() != 0">
    曲名:<s:property value="songInfoBean.track" /><br>
  </s:if>
  <s:if test="songInfoBean.phrase.length() != 0">
    歌詞:<s:property value="songInfoBean.phrase" /><br>
  </s:if>
  の検索結果
</h3>

<h4>
  <s:if test="result==null">
    検索条件に一致する曲はありませんでした。
  </s:if>
  <s:else>
    <s:set value="0" var="size" />
    <s:iterator value="result">
      <s:set value="#size+value.size()" var="size" />
    </s:iterator>
    <s:property value="#size" />件のトラックが見つかりました。
  </s:else>
</h4>

<s:iterator value="result">

  <!-- 展開式のリストを生成:idはsongInfoListMapのkey -->
  <div class="artist" onclick='obj=document.getElementById("${key}").style; obj.display=(obj.display=="none")?"block":"none";'>
    <p><a style="cursor:pointer;"><font color="blue"><s:property value="key" />(<s:property value="value.size()" />)</font></a></p>
  </div>

  <!-- 展開式リストの要素を生成:idはsongInfoListMapのkey -->
  <div class="trackList" id="${key}" style="display:none;clear:both;">
    <table width="100%">
      <s:iterator value="value" status="st">
        <s:if test="#st.first">
          <tr height="50px">
            <th width="25%" align="left">曲名</th>
            <th width="35%" align="left">歌い出し</th>
            <th width="10%"></th></tr>
        </s:if>
        <tr height="30px">
          <td><s:property value="track" /></td>
          <td><s:property value="begin" /></td>
          <td>
            <s:form action="search-lyric" theme="simple">
              <s:hidden name="songInfoBean.artist" value="%{artist}" />
              <s:hidden name="songInfoBean.track" value="%{track}" />
              <s:hidden name="songInfoBean.begin" value="%{begin}" />
              <s:hidden name="songInfoBean.url" value="%{url}" />
              <s:submit cssClass="button" value="歌詞" />
            </s:form>
          </td></tr>
      </s:iterator>
    </table>
  </div>
</s:iterator>

</div>

</body>
</html>