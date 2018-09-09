package ml.seoyul.acapo.dao;

import java.util.ArrayList;
import java.util.Map;

import ml.seoyul.acapo.vo.AllAttendance;
import ml.seoyul.acapo.vo.Attendance;
import ml.seoyul.acapo.vo.UserDevice;

public interface AttendanceMapper {
	public ArrayList<Attendance> selectAttendance (String userId);
	public int insertAttendance (UserDevice userdevice);
	public ArrayList<AllAttendance> selectAllAttendance ();
	public int insertAbsence (UserDevice userdevice);
	public int updateAttend (Map<String, String> m);
	public int updateAbsence (Map<String, String> m);
}
