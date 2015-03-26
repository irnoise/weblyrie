package jp.ne.noise.weblyrie.model;

import jp.ne.noise.weblyrie.dao.SongInfoDAO;

public class SetSongInfoLogic {
	public void execute(SongInfo songInfo) {
		SongInfoDAO dao = new SongInfoDAO();
		dao.create(songInfo);
		System.out.println("SetSongInfoLogic.execute()");
	}
}
