package ml.seoyul.acapo.dao;

import java.util.ArrayList;

import ml.seoyul.acapo.vo.KanjiRank;



public interface KanjiMapper {
	public int insertKanjiRank(KanjiRank kanjirank);
	public int updateKanjiRank(String kanji);
	public KanjiRank selectKanjiRank(String kanji);
	public ArrayList<KanjiRank> selectAllKanjiRank();
}
