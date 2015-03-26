package jp.ne.noise.weblyrie.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.ne.noise.weblyrie.model.SongInfo;

public class SongInfoDAO implements DataBaseConnectionService {

	@Override
	public Map<String, List<SongInfo>> findAll() {
		Map<String, List<SongInfo>> songInfoListMap = new LinkedHashMap<String, List<SongInfo>>();
		Connection conn = null;

		try {
			// JDBCドライバの読み込み
			Class.forName(DRIVER_NAME);

			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT文を準備(アーティスト名を基準に降順で並び替え)
			String sql = "SELECT * FROM SONGINFO ORDER BY ARTIST ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容をSongInfoインスタンスに保存し
			// songInfoListに追加、songInfoListMapにセット
			List<SongInfo> songInfoList = null;
			String current = "";

			while (rs.next()) {
				if (! current.equals(rs.getString("ARTIST"))) {
					songInfoList = new ArrayList<SongInfo>();
					current = rs.getString("ARTIST");
				}

				SongInfo songInfo = new SongInfo();
				songInfo.setArtist(rs.getString("ARTIST"));
				songInfo.setTrack(rs.getString("TRACK"));
				songInfo.setBegin(rs.getString("BEGIN"));
				songInfo.setUrl(rs.getString("URL"));
				songInfo.setLyric(rs.getString("LYRIC"));

				songInfoList.add(songInfo);
				songInfoListMap.put(current, songInfoList);
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

		return songInfoListMap;
	}

	@Override
	public boolean create(Object obj) {
		SongInfo songInfo = (SongInfo)obj;
		Connection conn = null;

		try {
			// JDBCドライバの読み込み
			Class.forName(DRIVER_NAME);

			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// INSERT文を準備
			String sql = "INSERT INTO SONGINFO ("
					+ "ARTIST, TRACK, BEGIN, URL, LYRIC)"
					+ "VALUES ("
					+ "?, ?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, songInfo.getArtist());
			pStmt.setString(2, songInfo.getTrack());
			pStmt.setString(3, songInfo.getBegin());
			pStmt.setString(4, songInfo.getUrl());
			pStmt.setString(5, songInfo.getLyric());

			// INSERT文を実行
			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	@Override
	public boolean update(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean delete(String any, String target) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
