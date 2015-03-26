package jp.ne.noise.weblyrie.model;

import java.util.List;
import java.util.Map;

import jp.ne.noise.weblyrie.dao.SongInfoDAO;

public class GetSongInfoListMapLogic {
	public Map<String, List<SongInfo>> execute() {
		SongInfoDAO dao = new SongInfoDAO();
		Map<String, List<SongInfo>> songInfoListMap = dao.findAll();
		return songInfoListMap;
	}
}
