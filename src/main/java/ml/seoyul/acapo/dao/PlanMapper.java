package ml.seoyul.acapo.dao;

import java.util.ArrayList;

import ml.seoyul.acapo.vo.MonthPlan;
import ml.seoyul.acapo.vo.WeekPlan;


public interface PlanMapper {
	public ArrayList<WeekPlan> selectWeekPlan();
	public int insertWeekPlan(WeekPlan wp);
	public ArrayList<MonthPlan> selectMonthPlan();
	public int insertMonthPlan(MonthPlan mp);
}
