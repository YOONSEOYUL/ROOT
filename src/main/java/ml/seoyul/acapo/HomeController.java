package ml.seoyul.acapo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ml.seoyul.acapo.dao.KanjiDAO;
import ml.seoyul.acapo.dao.PlanDAO;
import ml.seoyul.acapo.vo.KanjiRank;
import ml.seoyul.acapo.vo.MonthPlan;
import ml.seoyul.acapo.vo.WeekPlan;

@Controller
public class HomeController {

	@Autowired
	PlanDAO pdao;

	@Autowired
	KanjiDAO kandao;

	// 게시판 관련 상수
	final int countPerPage = 7;
	final int pagePerGroup = 5;

	@RequestMapping(value = "/time", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String time(Locale locale) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("a hh시 mm분 ss초");
		String serverTime = dateFormat.format(date);
		return serverTime;
	}

	@RequestMapping(value = "/oddit", method = RequestMethod.GET)
	public String oddit(Model model, HttpSession session) {
		return "/oddit";
	}

	@RequestMapping(value = "/oddjp", method = RequestMethod.GET)
	public String oddjp(Model model, HttpSession session) {
		return "/oddjp";
	}

	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String timetable(Model model, HttpSession session) {
		return "timetable";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model, HttpSession session) {
		return "plan/adminplan";
	}

	@RequestMapping(value = "/dictionary", method = RequestMethod.GET)
	public String goDic(Model model, HttpSession session,
			@RequestParam(value = "kanji", defaultValue = "kanji") String kanji,
			@RequestParam(value = "yomi", defaultValue = "yomi") String yomi) {
		model.addAttribute("yomi", yomi);
		model.addAttribute("kanji", kanji);
		return "dictionary";
	}
	

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String goHelp(Model model, HttpSession session) {

		ArrayList<KanjiRank> kanlist = kandao.selectAllKanjiRank();
		if (kanlist != null) {
			model.addAttribute("kanlist", kanlist);
		} else {
			model.addAttribute("kanlist", null);
		}

		return "help";
	}

	@RequestMapping(value = "/plan.go", method = RequestMethod.GET)
	public String goAdminplan(Model model, HttpSession session) {

		return "plan/adminplan";
	}

	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public String goOauth(Model model, String code, HttpSession session) {
		session.setAttribute("code", code);
		return "redirect:/login.go";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init() {
		return "redirect:/main";
	}

	// 메인 화면으로 이동하는 메소드
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model, HttpSession session) {
		ArrayList<WeekPlan> arryWeekPlan = pdao.selectWeekPlan();
		ArrayList<MonthPlan> arryMonthPlan = pdao.selectMonthPlan();
		int wpc = 0;
		int mpc = 0;
		if (arryWeekPlan != null) {
			System.out.println("arryWeekPlan 존재");
			for (WeekPlan wp : arryWeekPlan) {
				System.out.println(wp.getPdate() + " " + wp.getToDo());
				model.addAttribute("arryWeekPlan" + wpc, wp.getPdate() + " " + wp.getToDo());
				wpc++;
				if (wpc == 4)
					break;
			}
		}
		if (arryMonthPlan != null) {
			System.out.println("arryMonthPlan 존재");
			for (MonthPlan mp : arryMonthPlan) {
				System.out.println(mp.getPdate() + " " + mp.getToDo());
				model.addAttribute("arryMonthPlan" + mpc, mp.getPdate() + " " + mp.getToDo());
				mpc++;
				if (mpc == 4)
					break;
			}
		}
		model.addAttribute("wpc", wpc);
		model.addAttribute("mpc", mpc);

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("a hh시 mm분 ss초");
		SimpleDateFormat thisyearFormat = new SimpleDateFormat("yyyy年");
		SimpleDateFormat thismonthFormat = new SimpleDateFormat("MM月");
		SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
		String formattedDate = dateFormat.format(date);
		String thisyear = thisyearFormat.format(date);
		String thismonth = thismonthFormat.format(date);
		String today = todayFormat.format(date);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int lastday = cal.getMaximum(Calendar.DAY_OF_MONTH);
		int firstday = cal.get(Calendar.DAY_OF_WEEK);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("year", thisyear);
		model.addAttribute("month", thismonth);

		model.addAttribute("today", today);
		model.addAttribute("lastday", lastday);
		model.addAttribute("firstday", firstday);

		return "main";
	}
}