package jp.ne.noise.weblyrie.action;

import java.util.List;
import java.util.Map;

import jp.ne.noise.weblyrie.model.HtmlParser;
import jp.ne.noise.weblyrie.model.SongInfo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name = "input", location = "/WEB-INF/jsp/search/input.jsp"),
	@Result(name = "song-success", location = "/WEB-INF/jsp/search/result.jsp"),
	@Result(name = "lyric-success", location = "/WEB-INF/jsp/search/lyric.jsp"),
	@Result(name = "networkError", location = "/WEB-INF/jsp/error/networkerror.jsp"),
})

@ExceptionMappings({
	@ExceptionMapping(exception = "jp.ne.noise.weblyrie.exceptions.NetworkNotConnectException", result = "networkError"),
})

public class SearchAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SongInfo songInfoBean;
	private Map<String, List<SongInfo>> result;

	@Action("search-input")
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 楽曲情報検索用のメソッド
	 * @return String
	 * @throws Exception
	 */
	@Action("search-song")
	public String song() throws Exception  {
		HtmlParser parser = new HtmlParser();
		result = parser.parse(songInfoBean);

		return "song-success";
	}

	/**
	 * 歌詞情報取得用のメソッド
	 * @return String
	 * @throws Exception
	 */
	@Action("search-lyric")
	@SkipValidation
	public String lyric() throws Exception  {
		HtmlParser parser = new HtmlParser();
		setSongInfoBean(parser.parse(1, songInfoBean));

		return "lyric-success";
	}

	public SongInfo getSongInfoBean() {
		return songInfoBean;
	}

	public void setSongInfoBean(SongInfo songInfo) {
		songInfoBean = songInfo;
	}

	public Map<String, List<SongInfo>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<SongInfo>> result) {
		this.result = result;
	}

}
