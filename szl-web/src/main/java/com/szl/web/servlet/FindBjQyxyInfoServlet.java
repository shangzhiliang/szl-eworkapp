package com.szl.web.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szl.common.utils.DoRegex;
import com.szl.web.bean.ParametersBean;

/**
 * 
 * <pre>
 * <b>类描述:</b>
 *   本类处理页面发过来的请求，调用北京市企业信息用系统(简称BJXY系统)查询页面，
 *   BJXY系统 返回当次请求查询需要填写的验证码链接，本地获取链接展示在页面上，供人工填写，
 *   然后将结果再次提交给BJXY系统获取二级页面。
 *   
 * <b>作者:</b>商志亮
 * <b>创建日期:</b>2015年12月2日 下午4:19:21
 * </pre>
 */
public class FindBjQyxyInfoServlet extends HttpServlet {

    /**
     * 日志处理
     */
    private static final Logger logger = LoggerFactory
	    .getLogger(FindBjQyxyInfoServlet.class);
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	// jsoupGetData();
    }

    private static String jsoupGetAllCountryData() throws IOException {

	// http使用代理
	System.setProperty("http.proxyHost", "127.0.0.1");
	System.setProperty("http.proxyPort", "8888");
	String html = null;

	// 1. 访问首页，获取session值
	Connection.Response response = Jsoup
		.connect("http://qyxy.baic.gov.cn/beijing")
		.header("User-Agent",
			"Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
		.method(Method.GET).ignoreContentType(true).execute();
	html = response.body();

	System.out.println("html:" + html);

	String sessionId = DoRegex
		.getFirstString(
			html,
			"<input type=\"hidden\" name=\"credit_ticket\" id=\"credit_ticket\" value=\"(.*?)\"",
			1);

	logger.debug("sessionId:" + sessionId);
	System.out.println("sessionId:" + sessionId);

	String currentTimeMillis = DoRegex
		.getFirstString(
			html,
			"<input type=\"hidden\" name=\"currentTimeMillis\" id=\"currentTimeMillis\" value=\"(.*?)\"",
			1);

	logger.debug("currentTimeMillis:" + currentTimeMillis);
	System.out.println("currentTimeMillis:" + currentTimeMillis);

	Map<String, String> cookiesMap = new HashMap<String, String>();
	cookiesMap.put("JSESSIONID", response.cookie("JSESSIONID"));
	cookiesMap.put("BIGipServerpool_xy3_web",
		response.cookie("BIGipServerpool_xy3_web"));

	// String luceneUrl =
	// "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!findLiteralWord.dhtml";
	//
	Map<String, String> queryDataMap = new HashMap<String, String>();
	queryDataMap.put("currentTimeMillis", currentTimeMillis);
	queryDataMap.put("credit_ticket", sessionId);
	queryDataMap.put("checkcode", "1");
	queryDataMap.put("keyword", "北京简变快乐");
	//
	//
	// // 3. 获取越过验证码后的正式的页面
	// Connection.Response response3 = Jsoup.connect(luceneUrl)
	// .method(Method.POST)
	// .header("User-Agent",
	// "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
	// .cookies(cookiesMap)
	// .data(queryDataMap)
	// .execute();
	//
	// String body = response3.body();

	System.out
		.println("===========================================================================================");

	// System.out.println("body:"+body);

	// 3. 获取越过验证码后的正式的页面
	Connection.Response response4 = Jsoup
		.connect(
			"http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml")
		.method(Method.POST)
		.header("User-Agent",
			"Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
		.cookies(cookiesMap).data(queryDataMap).execute();
	String body2 = response4.body();

	System.out.println("body2:" + body2);

	System.out
		.println("===========================================================================================");

	return null;
    }

    /**
     * 抓取北京市独立的企业信息公示系统
     * 
     * @return
     * @throws IOException
     */
    private static String jsoupGetData() throws IOException {
	System.out.println("-----------------------------");
	String html = null;
	// http使用代理
	System.setProperty("http.proxyHost", "127.0.0.1");
	System.setProperty("http.proxyPort", "8888");

	// 1. 访问首页，获取session值
	Connection.Response response = Jsoup
		.connect("http://qyxy.baic.gov.cn/beijing")
		.header("User-Agent",
			"Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
		.method(Method.GET).ignoreContentType(true).execute();
	try {
	    html = response.body();

	    System.out.println("html:" + html);

	    String sessionId = DoRegex.getFirstString(html,
		    CrawlerConstants.sessionRegex, 1);

	    logger.debug("sessionId:" + sessionId);
	    //
	    String currentTimeMillis = DoRegex.getFirstString(html,
		    CrawlerConstants.currentTimeMillisRegex, 1);

	    logger.debug("currentTimeMillis:" + currentTimeMillis);

	    String imgSrc = DoRegex.getFirstString(html,
		    CrawlerConstants.imgsrcRegex, 1);

	    String imgSrcUrl = CrawlerConstants.rootUrl
		    + CrawlerConstants.imgsrc + imgSrc;

	    Map<String, String> cookiesMap = new HashMap<String, String>();
	    cookiesMap.put("JSESSIONID", response.cookie("JSESSIONID"));
	    cookiesMap.put("BIGipServerpool_xy3_web",
		    response.cookie("BIGipServerpool_xy3_web"));

	    // // 2. 带着第一次获取的cookies 信息请求验证码数据保存到本地，并获取本次的cookies信息。
	    // Connection.Response response2 = Jsoup.connect(imgSrcUrl)
	    // .method(Method.GET)
	    // .execute();
	    //
	    // Map<String,String> checkCodeCookiesMap = new
	    // HashMap<String,String>();
	    // checkCodeCookiesMap.put("JSESSIONID",
	    // response2.cookie("JSESSIONID"));
	    // checkCodeCookiesMap.put("BIGipServerpool_xy3_web",
	    // response2.cookie("BIGipServerpool_xy3_web"));
	    //
	    // 保存验证码到本地
	    // FileOutputStream out = (new FileOutputStream(new
	    // File("d:/temp/imgcode.jpg")));
	    // out.write(response2.bodyAsBytes());
	    // out.close();

	    String luceneUrl = CrawlerConstants.luceneUrl + currentTimeMillis
		    + "&credit_ticket=" + sessionId + "&check_code=18";

	    Map<String, String> queryDataMap = new HashMap<String, String>();
	    queryDataMap.put("queryStr", "林恩民");
	    queryDataMap.put("module", "");
	    queryDataMap.put("idFlag", "qyxy");

	    // 3. 获取越过验证码后的正式的页面
	    Connection.Response response3 = Jsoup
		    .connect(luceneUrl)
		    .method(Method.POST)
		    .header("User-Agent",
			    "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
		    .cookies(cookiesMap).data(queryDataMap).execute();
	    System.out.println("luceneUrl:" + luceneUrl);

	    byte[] bodyBytes = response3.bodyAsBytes();

	    String line = null;

	    byte[] buff = new byte[1024];
	    // 从字符串获取字节写入流
	    InputStream is = new ByteArrayInputStream(bodyBytes);
	    StringBuilder sb = new StringBuilder();
	    int len = -1;
	    while (-1 != (len = is.read(buff))) {
		// 将字节数组转换为字符串
		String res = new String(buff, 0, len);
		sb.append(res);
	    }

	    Document document = Jsoup.parse(sb.toString().trim());

	    System.out.println(sb.toString());

	    List<String> companyMainUrlList = getMainCompanyUrls(document);

	    if (companyMainUrlList != null && !companyMainUrlList.isEmpty()) {

		for (int i = 0; i < companyMainUrlList.size(); i++) {
		    String detailUrl = companyMainUrlList.get(i);

		    Connection.Response reseponseTemp = Jsoup
			    .connect(detailUrl)
			    .header("User-Agent",
				    "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0")
			    .cookies(cookiesMap).execute();
		    String detailHtml = reseponseTemp.body();
		    System.out.println(detailHtml);

		}

	    }

	    // PrintWriter pw = new PrintWriter(resp.getOutputStream());
	    // pw.write("ok");
	    // pw.flush();

	} catch (Exception e) {
	    logger.error("", e);
	} finally {

	}

	return html;
    }

    /**
     * 获取二级页面中所有公司的详情链接url地址
     * 
     * @param document
     * @return
     */
    private static List<String> getMainCompanyUrls(Document document) {
	List<String> urls = new LinkedList<String>();

	Elements links = document.select("a[onclick^=showDialog]");// 获取带有onclick属性的a节点

	if (links != null) {
	    for (Iterator<Element> iterElement = links.iterator(); iterElement
		    .hasNext();) {
		Element ele = iterElement.next();

		String companyName = ele.text();
		String urlRegex = "showDialog\\('(.*?)',";
		String companyDetailUrl = DoRegex.getFirstString(
			ele.attr("onclick"), urlRegex, 1);
		urls.add(CrawlerConstants.rootUrl
			+ companyDetailUrl.substring(1));
	    }
	}

	return urls;

    }

    /**
     * httpclient 方式获取数据
     * 
     * @param resp
     * @param session
     */
    @SuppressWarnings("unused")
    private void doHttpClientGet(HttpServletResponse resp, HttpSession session) {
	String html = null;
	try {

		ParametersBean paramBean = new ParametersBean();
		paramBean.setUrl(CrawlerConstants.URL_BJ_QYXY);
		paramBean.setIgnoreContentType(true);
		html = HttpConnectionManager.getInstance().getHtml(paramBean);
	    System.out.println(html);
	    String sessionId = DoRegex.getFirstString(html,
		    CrawlerConstants.sessionRegex, 1);

	    session.setAttribute("sessionId", sessionId);
	    logger.debug("sessionId:" + sessionId);
	    //
	    String currentTimeMillis = DoRegex.getFirstString(html,
		    CrawlerConstants.currentTimeMillisRegex, 1);
	    session.setAttribute("currentTimeMillis", currentTimeMillis);
	    logger.debug("currentTimeMillis:" + currentTimeMillis);

	    String imgSrc = DoRegex.getFirstString(html,
		    CrawlerConstants.imgsrcRegex, 1);

	    logger.debug("imgSrc:" + imgSrc);

	    imgSrc = CrawlerConstants.rootUrl + CrawlerConstants.imgsrc
		    + imgSrc;

	    session.setAttribute("imgSrc", imgSrc);

	    OutputStream os = null;
	    resp.setContentType("text/html");
	    resp.setHeader("Cache-Control", "no-store");
	    resp.setHeader("Pragma", "no-cache");
	    resp.setDateHeader("Expires", 0);
	    os = resp.getOutputStream();

	    PrintWriter pw = new PrintWriter(os);
	    pw.write(imgSrc);
	    pw.flush();
	    os.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 测试
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
		jsoupGetData();
    }
}
