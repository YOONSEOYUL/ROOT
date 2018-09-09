package ml.seoyul.acapo.dao;

import ml.seoyul.acapo.vo.UserDevice;

public interface DeviceMapper {
	public UserDevice selectUserInfo(UserDevice UserDevice);
	public int insertUserDevice(UserDevice UserDevice);
}
