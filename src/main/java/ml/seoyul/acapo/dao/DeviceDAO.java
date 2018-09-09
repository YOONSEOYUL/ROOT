package ml.seoyul.acapo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ml.seoyul.acapo.vo.UserDevice;

@Repository
public class DeviceDAO implements DeviceMapper {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public UserDevice selectUserInfo(UserDevice userDevice) {
		UserDevice device;
		try {
			DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
			device = mapper.selectUserInfo(userDevice);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("장치정보 불러오기 실패");
			return null;
		}
		return device;
	}

	@Override
	public int insertUserDevice(UserDevice UserDevice) {
		int cnt = 0;
		try {
			DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
			cnt = mapper.insertUserDevice(UserDevice);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("등록정보 저장 실패");
			return 0;
		}
		return cnt;
	}

}
