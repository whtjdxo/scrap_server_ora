package sales.api.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

//import javax.mail.internet.MimeUtility;
//import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
//import org.springframework.util.StringUtils;

//import com.google.gson.Gson;

/**
 * <pre>
 * Subject : String 공통 유틸
 * </pre>
 * @author min
 * @since 2011-02-21
 * @version 1.0
 */
public class StringUtil {
	
	/**
	 * 우편번호 짜르기 (앞자리3 idx=1, 뒷자리3 idx=2)
	 * @author choiye
	 * @param str (우편번호)
	 * @param idx (우편번호의 앞자리 1 , 뒷자리 2)
	 * @return 우편번호 앞지로 또는 뒷자리
	 */
	public static String getZipNo(String str, int idx){
		String resultStr="";
		if("".equals(nullCheck(str)) || null == str){
			return "";
		}else if(idx<1 && idx>2){
			return "";
		}else{
			if(idx == 1){
				resultStr = str.substring(0, 3);
			}else{
				resultStr = str.substring(3, 6);
			}
		}
		
		return resultStr;
	}
	
	/**
	 * 전화번호,휴대폰번호 자르기 (첫번째자리 4  idx=1, 두번째자리 4 idx=2, 세번째 자리4 idx=3)
	 * @author choiye
	 * @param str
	 * @param idx
	 * @return
	 */
	public static String getPhoneNo(String str, int idx){
		String resultStr="";
		
		int length = str.length();
		if((str == null || "".equals(nullCheck(str)) )|| length != 12){
			return "";
		}else{
			if(idx==1){
				resultStr = str.substring(0, 4).trim();				
			}else if(idx==2){
				resultStr = str.substring(4, 8).trim();
			}else{
				resultStr = str.substring(8, 12).trim();
			}
		}
		
		return resultStr;
	}
	
	/**
	 * 문자열 자르기
	 * @param str
	 * @param regex
	 * @param count
	 * @return
	 */
	public String[] getSplitArray(String str, String regex, int count)
	{
		String[] spt_result = str.split(regex);
		String[] return_result = new String[count];
		
		if(count <= spt_result.length)
		{
			for(int i = 0; i<count; i++)
			{
				return_result[i] = spt_result[i];
			}
		}
		else
		{
			for(int i = 0; i<spt_result.length; i++)
			{
				return_result[i] = spt_result[i];
			}
			for(int i = 0; i<return_result.length; i++)
			{
				if(return_result[i] == null)
				{
					return_result[i] = "";
				}
			}
		}
		
		return return_result;
	}
	
	/**
	 * 문자열 잘라서 ... 붙이기
	 * @param str
	 * @param regex
	 * @param count
	 * @return
	 */
	public String getSplitToDots(String str, int num)
	{
		if(str == null) return "";			//Null 처리
		if(num > str.length()) return str;	//설정하려는 num이 더 큰경우 그냥 str리턴
		
		String subNewStr = str.substring(0, num);
		return subNewStr + "...";
	}
	
	/**
	 * 널체크(Object)
	 * @param param
	 * @return
	 */
	public static String nullCheckObject(Object param)
	{
		if(param == null) return "";
		return param.toString();
	}
	
	/**
	 * 널체크
	 * @param param
	 * @return
	 */
	public static String nullCheck(String param)
	{
		if(param == null) return "";
		return param;
	}
	public static String encNullCheck(String param)
	{
		if(param == null) return "";
		return param;
	}
	
	/**
	 * 널체크
	 * @param param
	 * @return
	 */
	public static String nullCheck(String param, String value)
	{
		if(param == null || param.equals("")) return value;
		return param;
	}
	public static String encNullCheck(String param, String value)
	{
		if(param == null || param.equals("")) return value;
		return param;
	}
	
	/**
	 * 파라미터에서 int형 리턴
	 * @param param
	 * @return
	 */
	public static int getIntParam(String param)
	{
		try
		{
			return Integer.parseInt(nullCheck(param));
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * 파라미터에서 float형 리턴
	 * @param param
	 * @return
	 */
	public static float getFloatParam(String param)
	{
		try
		{
			return Float.parseFloat(nullCheck(param));
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * 통신사 세팅
	 * @return
	 */
	public static String generateTelecomOptions(String select)
	{
		String returnStr = "";
		String telecoms[] = {"010","011","016","017","018","019","070"};
		String innerSelect = "";
		select = nullCheck(select);
		
		for(int i = 0; i<telecoms.length; i++)
		{
			innerSelect = "";
			if(select.equals(telecoms[i]) || select == telecoms[i])
			{
				innerSelect = "selected";
			}
			returnStr += "<option value='"+telecoms[i]+"' "+innerSelect+">"+telecoms[i]+"</option>";
		}
		
		//System.out.println(returnStr);
		
		return returnStr;
	}
	
	/**
	 * 통신사 세팅
	 * @param void
	 * @return String
	 */
	public static String generateTelecomOptions2()
	{
		String returnStr = "";
		String telecoms[] = {"010","011","016","017","018","019"};
		
		int tel_length = telecoms.length;
		
		for(int i = 0; i<tel_length; i++)
		{
			returnStr += "<option value='"+telecoms[i]+"'>"+telecoms[i]+"</option>";
		}
		
		//System.out.println(returnStr);
		
		return returnStr;
	}
	
	/**
	 *  지역번호 <select>
	 * @author choiye
	 * @param String sel(선택할 지역번호)
	 * @return String
	 */
	public static String getTelArea(String sel){
		StringBuilder returnStr= new StringBuilder();
		String comps[] ={"02","031","032","033","041","042","043","051","052","053","054","055","061","062","063","064"};
		String select = nullCheck(sel);
		int length = comps.length;
		String innerSel="";
		for(int i=0; i < length ; i++){
			innerSel="";
			if(select.equals(comps[i]) || select == comps[i]){
				innerSel="selected";
			}
			returnStr.append("<option value='"+comps[i]+"' "+innerSel+">"+comps[i]+"</option>");
		}
		
		return returnStr.toString();
	}
	
	
	/**
	 * 이메일 <select>
	 * @author choiye
	 * @param String sel(선택할 이메일)
	 * @return String
	 */
	public static String getEmail(String sel){
		StringBuilder returnStr= new StringBuilder();
		String emails[] = {"naver.com","nate.com","gmail.com","empal.com","hanmail.net","yahoo.co.kr","paran.com","hotmail.com"};
		String select = nullCheck(sel);
		int length = emails.length;
		String innerSel="";
		for(int i=0; i < length ; i++){
			innerSel="";
			if(select.equals(emails[i])){
				innerSel="selected";
			}
			returnStr.append("<option value='"+emails[i]+"' "+innerSel+">"+emails[i]+"</option>");
		}
		
		return returnStr.toString();
	}

	/**
	 * 시간리턴
	 * @return
	 */
	public static String getHour(){
		String hh = "";
		
		for(int i=0; i<24; i++){
			if(i < 10){
				hh += "<option value=0"+i+">0"+i+"</option>";
			} else {
				hh += "<option value="+i+">"+i+"</option>";
			}
			
		}
		return hh;
	}
	
	/**
	 * 분리턴
	 * @return
	 */
	public static String getMinute(){
		String mm = "";
		for(int i=0; i<60; i++){
			if(i < 10){
				mm += "<option value=0"+i+">0"+i+"</option>";
			} else {
				mm += "<option value="+i+">"+i+"</option>";
			}
		}
		return mm;
	}
	
	/**
	 * 콤마찍기
	 * @param money
	 * @return
	 */
	public static String getComma(String money)
	{
		money = (money == null || money.equals("")) ? "0" : money;
		money = money.replaceAll("[,]", "");
		DecimalFormat df = new DecimalFormat("###,###");
		
		//System.out.println("]"+df.format(Double.parseDouble(money))+"[");
		
		return df.format(Double.parseDouble(money));
	}
	
	/**
	 * 콤마찍기:만원
	 * @param money
	 * @return
	 */
	public static String getCommaAsMoney(String money)
	{
		money = (money == null || money.equals("")) ? "0" : money;
		
		if(money.length() > 4)
		{
			money = money.substring(0, money.length()-4);
		}
		else
		{
			//money = "0";
		}
		
		money = money.replaceAll("[,]", "");
		DecimalFormat df = new DecimalFormat("###,###");
		
		//System.out.println("]"+df.format(Double.parseDouble(money))+"[");
		
		return df.format(Double.parseDouble(money));
	}
	
	/**
	 * 콤마찍기:억원
	 * @param money
	 * @return
	 */
	public static String getCommaAsBill(String money)
	{
		money = (money == null || money.equals("")) ? "0" : money;
		
		if(money.length() > 8)
		{
			money = money.substring(0, money.length()-8);
		}
		else
		{
			//money = "0";
		}
		
		money = money.replaceAll("[,]", "");
		DecimalFormat df = new DecimalFormat("###,###");
		
		//System.out.println("]"+df.format(Double.parseDouble(money))+"[");
		
		return df.format(Double.parseDouble(money));
	}
	
	/**
	 * 년도 가져오기 <Select>
	 * @author choiye
	 * @param int sel
	 * @return int
	 */
	public static String getYearSelect(int sel){
		StringBuilder returnStr= new StringBuilder();
		Calendar c = Calendar.getInstance();
		int yy = c.get(Calendar.YEAR);
		String innerSel="";
		for(int i=1930;i<yy;i++){
			innerSel="";
			if(sel == i){
				innerSel="selected";
			}
			returnStr.append("<option value='"+i+"' "+innerSel+">"+i+"</option>");
		}
		return returnStr.toString();
	}
	
	/**
	 * 월 가져오기 <Select>
	 * @author choiye
	 * @param int sel
	 * @return int
	 */
	public static String getMonthSelect(int sel){
		StringBuilder returnStr= new StringBuilder();				
		String innerSel="";
		for(int i=1;i<13;i++){
			innerSel="";
			if(sel == i){
				innerSel="selected";
			}
			if(i <10){
				returnStr.append("<option value='0"+i+"' "+innerSel+">0"+i+"</option>");
			}else{
				returnStr.append("<option value='"+i+"' "+innerSel+">"+i+"</option>");
			}
		}
		return returnStr.toString();
	}
	
	/**
	 * 일 가져오기 <Select>
	 * @author choiye
	 * @param int sel
	 * @return int
	 */
	public static String getDaySelect(int sel){
		StringBuilder returnStr= new StringBuilder();				
		String innerSel="";
		for(int i=1;i<32;i++){
			innerSel="";
			if(sel == i){
				innerSel="selected";
			}
			if(i <10) returnStr.append("<option value='0"+i+"' "+innerSel+">0"+i+"</option>");
			else	  returnStr.append("<option value='"+i+"' "+innerSel+">"+i+"</option>");
		}
		return returnStr.toString();
	}
	
	/**
	 * email 뒷자리 '*'로 치환
	 * @author choiye
	 * @param email
	 * @return String email
	 */
	public static String getReplaceEmail(String email){
		
		String[] split = email.split("@");
		
		int length = split[0].length();
		int length2 = length/2;
		String result = "";
		for(int i=0;i<length;i++){
			if(i <length2){
				result+=split[0].charAt(i);
			}else{
				result +="*";
			}
		}
		return result += "@"+split[1];		
	}
	
	/**
	 * 전화번호 '-' 붙여서 치환
	 * @author yic
	 * @param phone
	 * @return String tel
	 */
	public static String getPhoneNoDash(String phone){
		
		String phone1 = phone.substring(0, 4).trim();
		String phone2 = phone.substring(4, 8).trim();
		String phone3 = phone.substring(8, 12).trim();
		String result = phone1 + "-" + phone2 + "-" + phone3;
		
		return result;
	} 
	
	/**
	 * 이메일 자르기
	 * @author yic
	 * @param email
	 * @return String
	 */
	public static String getEmailSplit(String email, int idx){
		String mail[] = email.split("@");
		String result = "";
		if(idx == 1){
			result = mail[0];
		} else {
			result = mail[1];
		}
		return result;
	}
	
	/**
	 * 숫자만 들어가야할 때 사용
	 * @param val
	 * @param defalVal
	 * @return
	 */
	public static String getNumber(String val,String defaultVal){
		val = nullCheck(val, defaultVal);
		boolean bln = Pattern.matches("^[0-9]*$",val );
		if(bln == false) val=defaultVal;
		return val;
	}
	
//	/**
//	 * 파라미터 PRINT
//	 * @param request
//	 */
//	@SuppressWarnings("rawtypes")
//	public static void printParameters(HttpServletRequest request, Logger logger)
//	{
//		try{
//				Enumeration reqEnum = request.getParameterNames();
//
//				//logger.warn("\n\n>-------------------------------------------<");
//				logger.warn("\n\n>START TRANS OF : "+request.getRequestURL());
//				logger.warn(">FROM : "+request.getRemoteAddr());
//				logger.warn(">REFER : "+request.getHeader("REFERER"));
//
//				while(reqEnum.hasMoreElements())
//				{
//					String key = (String)reqEnum.nextElement();
//					String value = request.getParameter(key);
//				    logger.warn(">>KEY : "+key+", VALUE : "+value);
//				}
//			}
//		catch(Exception e)
//		{
//			logger.error(e.getMessage());
//		}
//
//	}
//
//	/**
//	 * 텍스트파일 읽기
//	 * @param filenm
//	 */
//	public static String readTxtFile(String file_url){
//		String str = "";
//		int cnt = 0;
//    	URL url;
//    	URLConnection conn;
//    	InputStream is;
//    	InputStreamReader isr;
//    	BufferedReader br;
//
//    	try{
//    		url = new URL(file_url);
//    		conn = url.openConnection();
//
//    		is = conn.getInputStream();
//    		isr = new InputStreamReader(is, "euc-kr");
//    		br = new BufferedReader(isr);
//
//    		StringBuilder sb = new StringBuilder();
//    	    String buf = null;
//    	    while(true){
//    	    	cnt++;
//    	    	buf = br.readLine();
//    	    	if(cnt == 1)
//    	    		sb.append(buf);
//    	    	else
//    	    		sb.append("\n" + buf);
//    	    	if(buf == null) break;
//    	    }
//    	    sb.replace(sb.length()-4, sb.length(), "");
//    	    str = sb.toString();
//
//    	    br.close();
//    	    is.close();
//
//    	}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return str;
//	}
//
//	/**
//	 * ValueObject - > JSONString();
//	 * @param obj
//	 * @return
//	 */
//	public static String getJSON(Object obj)
//	{
//		Gson gson = new Gson();
//		return gson.toJson(obj);
//	}
//
//	 /**
//     * 파일명 처리
//     *
//     * @param filename
//     * @param request
//     * @return
//     */
//    public static String encodeFilename(String filename, HttpServletRequest request) {
//    	/**
//    	* IE ：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
//    	* Firefox ：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
//    	*/
//       String agent = request.getHeader("USER-AGENT");
//       try {
//         if ((agent != null) && (-1 == agent.indexOf("MSIE"))) {
//           String newFileName = URLEncoder.encode(filename, "UTF-8");
//           newFileName = StringUtils.replace(newFileName, "+", "%20");
//           if (newFileName.length() > 150) {
//             newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
//             newFileName = StringUtils.replace(newFileName, " ", "%20");
//           }
//           return newFileName;
//         }
//
//         if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
//           return MimeUtility.encodeText(filename, "UTF-8", "B");
//
//         return filename;
//       } catch (Exception ex) {
//         return filename;
//       }
//     }
//
//    public static  double getDoubleValue(String value){
//    	String s_value = value.equals("")?"0":value;
//    	double d_value = Double.parseDouble(s_value);
//    	return d_value ;
//    }
//    /**
//     * 문자열에서 숫자만 꺼내오기
//     * @param str
//     * @return
//     */
//    public static String onlyNum(String str){
//    	if(str == null) return "";
//    	StringBuffer sb = new StringBuffer();
//    	for(int i = 0; i<str.length();i++){
//    		if(Character.isDigit(str.charAt(i))){
//    			sb.append(str.charAt(i));
//    		}
//    	}
//    	return sb.toString();
//    }
//
//    public static boolean isStringDouble(String s) {
//        try {
//            Double.parseDouble(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//      }
//
//	public static String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		if (ip.equals("0:0:0:0:0:0:0:1")) {
//			ip = "local";
//		}
//		return ip;
//	}
//
//    /**
//     * 과거데이타 테이블 조회
//     * @param str
//     * @return
//     */
//    public static String getTableByYear(String table_name , String yearFrom){
//    	if(table_name == null||yearFrom == null||"".equals(yearFrom)) return table_name;
//    	if(yearFrom.length()< 10){
//    		if(yearFrom.length()== 7){
//    			yearFrom = yearFrom.concat("-01");
//    		}else if(yearFrom.length() == 4){
//    			yearFrom = yearFrom.concat("-01-01");
//    		}
//    	}
//
//    	Date dt =new Date() ;
//    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//    	SimpleDateFormat yfmt = new SimpleDateFormat("yyyy");
//
//    	String chkMonthStr = yfmt.format(dt).concat("-03-01");// 백업 데이타 생성 3월기준
//    	int lastYear = Integer.parseInt(yfmt.format(dt)) - 1 ;// 작년
//    	String lastChkDay = Integer.toString(lastYear).concat("-12-01");//조회 기준
//
//    	// 현재 3월 이전 이면 현재 상용중인 테이블 조회
//    	try {
//			Date chkMonthDt  = fmt.parse(chkMonthStr);
//			boolean chkFlag = dt.before(chkMonthDt);
//			if(chkFlag&& yearFrom.contains(Integer.toString(lastYear))){
//				return table_name;
//			}
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//
//    	// 조회 값이 12 월 이전 이면 과거 테이블 조회
//    	Date chkDate;
//		try {
//			chkDate = fmt.parse(yearFrom);
//	    	Date lastDate;
//			try {
//				lastDate = fmt.parse(lastChkDay);
//				boolean flag  = chkDate.before(lastDate);
//			  	if(flag){
//			  		return table_name.concat("_").concat(yearFrom.substring(0, 4));
//		    	}else{
//		    		 return table_name;
//		    	}
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return table_name;
//
//    }
    
}
