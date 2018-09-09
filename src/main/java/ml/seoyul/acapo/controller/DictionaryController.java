package ml.seoyul.acapo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ml.seoyul.acapo.api.TranslationApi;

@RequestMapping("/dic")
@Controller
public class DictionaryController {
	
	@RequestMapping(value="/sagasu.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody JSONObject doSagasu (HttpServletRequest request, String searchKanji) {
		String word = searchKanji.substring(0, 1);
		String yomi = "";
		String imgName = word;
		String regex = "[\u4E00-\u9FFF]"; //Kanji
		JSONObject jObject = new JSONObject();
		//[\u3040-\u309Fー] Hiragana
		//[\u30A0-\u30FF] Katakana
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(word);
//		while(matcher.find()){
//			System.out.println(matcher.group(0));
//		}
		System.out.println("검색한 한자 : "+word);
		if(word.matches(regex)) {
			word = encodeURIComponent(word);
			String initUrl = "https://kakijun.jp//main/u_kensaku.php?KANJI="+word;
			String imgUrl = null;
			Connection con = Jsoup.connect(initUrl);
			Element el = null;
			try {	
				Document doc = con.get();
//				els = doc.select("span.mihon100"); //검색해서 나온한자
				yomi = doc.select(".yomipop").first().text(); //요미카타
				imgUrl="https://kakijun.jp"+doc.select("#kjanimation").first().attr("src");
				URL url=new URL(imgUrl);
				String thisPATH = request.getSession().getServletContext().getRealPath("/resources/images/kanji/");
				InputStream fis = url.openStream();
				File file = new File(thisPATH+imgName+".gif");
				OutputStream os = new FileOutputStream(file);
				FileCopyUtils.copy(fis, os);
				fis.close();
				os.close();
				jObject.put("kanji",word);
				jObject.put("yomi", yomi);
				System.out.println("kanji : "+word);
				System.out.println("yomi : "+yomi);
				return jObject;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		}else {
			jObject.put("kanji", "kanji");
			jObject.put("yomi", "한자를 입력해주세요.");
			return jObject;
		}
	}
	
	@RequestMapping(value = "/trans.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody JSONObject doTrans(String srcLang, String targetLang, String transQuery) {
		//TranslationResponse
		System.out.println("doTrans Method");
		TranslationApi trans = new TranslationApi(srcLang, targetLang, transQuery);
		String transResult = trans.doTranslation();
		if(transResult==null)
			transResult = "{\"message\": { \"result\": {\"translatedText\":\"지원하지 않는 형식입니다.\"}}}";
			JSONParser jParser = new JSONParser();
			System.out.println("response결과 "+transResult);
			JSONObject jObject = new JSONObject();
			try {
				jObject = (JSONObject) jParser.parse(transResult);
				JSONObject JSONmessage = (JSONObject)jObject.get("message");
				JSONObject JSONresult = (JSONObject)JSONmessage.get("result");
				System.out.println(JSONresult);
				return JSONresult;
			} catch (ParseException e) {
				return null;
			}
}
	public static String encodeURIComponent(String component)   {     
	    String result = null;      
	    try {       
	        result = URLEncoder.encode(component, "UTF-8");  
	    }
	    catch (UnsupportedEncodingException e) {       
	        result = component;     
	    }      
	    return result;   
	}
	
//	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json; charset=utf8")
//	public  @ResponseBody void hi(String hobbyArray) {
//		JSONParser jParser = new JSONParser();
//		JSONArray jArray = new JSONArray();
//		JSONObject jObject = new JSONObject();
//		if(hobbyArray!=null) {
//			try {
//				jArray = (JSONArray)jParser.parse(hobbyArray);
//			} catch (ParseException e) {
////				e.printStackTrace();
//				jArray = null;
//			}
//			if(jArray.size()>0) {
//			jObject = (JSONObject)jArray.get(jArray.size()-1);
//			String seq = jObject.get("seq").toString();
//			String hobby = jObject.get("hobby").toString();
//			System.out.println("seq는 "+seq+" hobby는 "+hobby);
//			}
//			}
//	}
	
}
