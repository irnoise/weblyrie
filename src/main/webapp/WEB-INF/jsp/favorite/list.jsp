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

  <div>
  <h3>保存した歌詞</h3>

  <s:iterator value="songInfoListMap">

    <!-- 展開式のリストを生成:idはsongInfoListMapのkey -->
    <div class="artist" onclick='obj=document.getElementById("${key}").style; obj.display=(obj.display=="none")?"block":"none";'>
      <p><a style="cursor:pointer;"><font color="blue"><s:property value="key" />(<s:property value="value.size()" />)</font></a></p>
    </div>

    <!-- 展開式リストの要素を生成:idはsongInfoListMapのkey -->
    <div class="trackList" id="${key}" style="display:none;clear:both;">
      <table style="width: 80%; margin-left: 30px;" cellspacing="5" cellpadding="5">
        <s:iterator value="value" status="st">
          <s:if test="#st.first">
              <tr>
                <th width="25%" align="left">曲名</th>
                <th width="30%" align="left">歌い出し</th></tr>
            </s:if>
          <tr height="30px">
            <td><s:property value="track" /></td>
            <td><s:property value="begin" /></td>
            <td>
              <s:form action="favorite-lyric" theme="simple">
                <s:hidden name="songInfoBean.artist" value="%{artist}" />
                <s:hidden name="songInfoBean.track" value="%{track}" />
                <s:hidden name="songInfoBean.lyric" value="%{lyric}" />
                <s:submit value="歌詞" />
              </s:form>
            </td></tr>
          </s:iterator>
      </table>
    </div>

  </s:iterator>
  </div>

  <jsp:include page="../../../footer.jsp" />

</body>
</html>