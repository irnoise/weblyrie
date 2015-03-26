package jp.ne.noise.weblyrie.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.ne.noise.weblyrie.exceptions.NetworkNotConnectException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlParser {
	public static final int TRACK_NOT_FOUND = 0;
	public static final int GET_DOCUMENT_SUCCESSED = 1;
	public static final int GET_DOCUMENT_FAILED = 3;

	/**
	 * トラック情報の検索条件を基にDocumentを取得しArrayListに格納。
	 * ArrayListをLinkedHashMapに格納して返却
	 *
	 * @param songInfo トラック情報
	 * @return Map<String, List<SongInfo>>
	 * @throws NetworkNotConnectException
	 */
	public Map<String, List<SongInfo>> parse(SongInfo songInfo) throws NetworkNotConnectException {
		Map<String, List<SongInfo>> result = new LinkedHashMap<String, List<SongInfo>>();
    	List<SongInfo> songInfoList = null;
    	String current = "";
		int page = 1;
		Elements nextClass = new Elements();

		do {
			Document document;

			try {
				if (page == 1) {
					document = Jsoup.connect("http://www.kget.jp/search/")
					        .data("r", songInfo.getArtist())
					        .data("t", songInfo.getTrack())
					        .data("f", songInfo.getPhrase())
					        .timeout(5000)
					        .get();
				} else {
					document = Jsoup.connect("http://www.kget.jp/search/")
					        .data("r", songInfo.getArtist())
					        .data("t", songInfo.getTrack())
					        .data("f", songInfo.getPhrase())
					        .data("n", Integer.toString(page))
					        .timeout(5000)
					        .get();
				}

				Elements songlist = document.select(".songlist li");

				if (songlist.isEmpty()) {
		        	return null;
		        } else {
		        	for (Element element : songlist) {
		        		if (! element.getElementsByClass("artist").text().equals(current)) {
		        			current = element.getElementsByClass("artist").text();
		        			songInfoList = new ArrayList<SongInfo>();
		        		}

		        		SongInfo song = new SongInfo();
		        		song.setTrack(element.getElementsByClass("title").text());
		        		song.setArtist(element.getElementsByClass("artist").text());
		        		song.setBegin(element.select(".begin strong").text());

		        		for (Attribute attr : element.getElementsByClass("lyric-anchor").get(0).attributes()) {
		        			if (attr.getKey().equals("href")) {
		        				song.setUrl("http://www.kget.jp" + attr.getValue());
		        			}
		        		}

		        		songInfoList.add(song);
		        		result.put(current, songInfoList);
		        	}

		        	Elements kgPaging = document.getElementsByClass("kg-paging");
		        	for (Element element: kgPaging) {
		        		nextClass = element.getElementsByClass("next");
		        	}
		        	page++;
		        }

			} catch (IOException e) {
				throw new NetworkNotConnectException();
			}
		} while (! nextClass.isEmpty());

		return result;
	}

	/**
	 * SongInfo.urlを基にDocumentを取得し、パースした歌詞をSongInfo.lyricに保存して返却
	 *
	 * @param i メソッド分岐用の数値
	 * @param songInfo トラック情報
	 * @return SongInfo
	 * @throws NetworkNotConnectException
	 */
	public SongInfo parse(int i, SongInfo songInfo) throws NetworkNotConnectException {
		Document document = null;

		try {
			document = Jsoup.connect(songInfo.getUrl())
					.timeout(5000)
					.get();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			throw new NetworkNotConnectException();
		}

		String lyric = document.getElementById("lyric-trunk").toString();
//		BufferedReader reader = new BufferedReader(new StringReader(lyric));
//		String text = "";
//
//		try {
//			String s;
//
//			while ((s=reader.readLine()) != null) {
//				text += s;
//			}
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//
//		songInfo.setLyric(text);
		songInfo.setLyric(lyric);
		return songInfo;
	}
}