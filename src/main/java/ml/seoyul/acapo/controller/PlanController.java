package ml.seoyul.acapo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ml.seoyul.acapo.dao.PlanDAO;
import ml.seoyul.acapo.vo.MonthPlan;
import ml.seoyul.acapo.vo.WeekPlan;

@RequestMapping("/plan")
@Controller
public class PlanController {

	@Autowired
	PlanDAO dao;
	
	@RequestMapping(value = "/addWeekPlan", method = RequestMethod.POST)
	public String addWeekPlan(WeekPlan wp) {
		System.out.println(wp);
		dao.insertWeekPlan(wp);
		return "redirect:/plan.go";
		
	}
	
	@RequestMapping(value = "/addMonthPlan", method = RequestMethod.POST)
	public String addMonthPlan(MonthPlan mp) {
		System.out.println(mp);
		dao.insertMonthPlan(mp);
		return "redirect:/plan.go";
	}
}
