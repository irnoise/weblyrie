package jp.ne.noise.weblyrie.action;

import java.util.List;
import java.util.Map;

import jp.ne.noise.weblyrie.model.GetSongInfoListMapLogic;
import jp.ne.noise.weblyrie.model.SetSongInfoLogic;
import jp.ne.noise.weblyrie.model.SongInfo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name = "list", location = "/WEB-INF/jsp/favorite/list.jsp"),
	@Result(name = "lyric", location = "/WEB-INF/jsp/favorite/lyric.jsp"),
	@Result(name = "add", location = "/WEB-INF/jsp/search/lyric.jsp"),
})

public class FavoriteListAction extends ActionSupport {
	private Map<String, List<SongInfo>> songInfoListMap;
	private SongInfo songInfoBean;

	@Action("favorite-list")
	@SkipValidation
	public String list() throws Exception {
		GetSongInfoListMapLogic getSongInfoListMapLogic = new GetSongInfoListMapLogic();
		songInfoListMap = getSongInfoListMapLogic.execute();

		return "list";
	}

	@Action("favorite-lyric")
	@SkipValidation
	public String lyric() throws Exception {
		return "lyric";
	}

	@Action("favorite-add")
	@SkipValidation
	public String add() throws Exception {
		SetSongInfoLogic setSongInfoLogic = new SetSongInfoLogic();
		setSongInfoLogic.execute(songInfoBean);
		return "add";
	}

	public Map<String, List<SongInfo>> getSongInfoListMap() {
		return songInfoListMap;
	}

	public void setSongInfoListMap(Map<String, List<SongInfo>> songInfoListMap) {
		this.songInfoListMap = songInfoListMap;
	}

	public SongInfo getSongInfoBean() {
		return songInfoBean;
	}

	public void setSongInfoBean(SongInfo songInfoBean) {
		this.songInfoBean = songInfoBean;
	}

}
