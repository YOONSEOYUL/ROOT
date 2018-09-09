package ml.seoyul.acapo.dao;

import java.util.ArrayList;

import ml.seoyul.acapo.vo.KakaoUserInfo;



public interface KakaoMapper {
	public KakaoUserInfo selectUserInfo(KakaoUserInfo kakaoUserInfo);
	public int insertUserInfo(KakaoUserInfo kakaoUserInfo);
	public int gradeUp(KakaoUserInfo kakaoUserInfo);
	public ArrayList<KakaoUserInfo> selectAllKakaoUserInfo();
}
