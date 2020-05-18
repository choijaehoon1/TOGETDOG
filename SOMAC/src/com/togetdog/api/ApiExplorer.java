package com.togetdog.api;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.togetdog.desertion.DesertionVO;

public class ApiExplorer {
	static int count = 0;

	// 지역 별 페이지
	static String aUprCd;
	static int aPageNo;
	static int aNumOfRows;

	// 토탈 페이지
	static int tPageNo;
	static int tnumOfRows;
	static String tUprCd;

	// 강아지 페이지
	static int dPageNo;
	static int dNumOfRows;
	static String dUpKind;
	static String dUprCd;

	// 고양이 페이지
	static int cPageNo;
	static int cNumOfRows;
	static String cUpKind;
	static String cUprCd;

	// 기타 페이지
	static int ePageNo;
	static int eNumOfRows;
	static String eUpKind;
	static String eUprCd;

	public List<DesertionVO> areaRetrieve(int pageNo, int numOfRows, String upr_cd) {
		aPageNo = pageNo;
		aNumOfRows = numOfRows;
		aUprCd = upr_cd;
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -7);

		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, 7);

		String beforeDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());
		String afterDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week2.getTime());

		StringBuilder urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic");

		try {
			//김주희
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
			//김준혁
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//최재훈 
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(beforeDay, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(afterDay, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows); /* 페이지당 보여줄 개수 */
			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 :
			// 429900 */
			urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "="
					+ URLEncoder.encode(upr_cd, "UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "="
					+ URLEncoder.encode("notice", "UTF-8")); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */

			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 -
			// 기타 : 429900 */
			// urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" +
			// URLEncoder.encode("notice", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice -
			// 보호중 : protect */
			// urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */

			// urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String result = "";
			String line;
			while ((line = rd.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값

			}
			rd.close();
			conn.disconnect();

			BufferedReader br = null;
			// DocumentBuilderFactory 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			HashMap<String, String> resultMap = new HashMap<String, String>();
			List<DesertionVO> list = new ArrayList<DesertionVO>();

			try {
				// xml 파싱하기
				InputSource is = new InputSource(new StringReader(result));
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();
				XPathExpression expr = xpath.compile("/response/body/items/item");
				// XPathExpression expr = xpath.compile("//items/item");
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				DesertionVO inVO = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();

					for (int j = 0; j < child.getLength(); j++) {
						Node node = child.item(j);
						resultMap.put(node.getNodeName(), node.getTextContent());

					}
					inVO = new DesertionVO(resultMap.get("desertionNo"), resultMap.get("processState"),
							resultMap.get("filename"), resultMap.get("happenDt"), resultMap.get("happenPlace"),
							resultMap.get("kindCd"), resultMap.get("colorCd"), resultMap.get("age"),
							resultMap.get("weight"), resultMap.get("noticeNo"), resultMap.get("noticeSdt"),
							resultMap.get("noticeEdt"), resultMap.get("popfile"), resultMap.get("sexCd"),
							resultMap.get("neuterYn"), resultMap.get("specialMark"), resultMap.get("careNm"),
							resultMap.get("careTel"), resultMap.get("careAddr"), resultMap.get("orgNm"),
							resultMap.get("chargeNm"), resultMap.get("officetel"));
					list.add(inVO);

				}

				count = list.size();

			} catch (Exception e) {
				e.getMessage();
			}

			return list;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;

	}

	public DesertionVO areaSelectOne(String desertionNo) {
		List<DesertionVO> list = new ArrayList<DesertionVO>();
		DesertionVO outVO = new DesertionVO();
		list = areaRetrieve(aPageNo, aNumOfRows, aUprCd);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getDesertionNo().equals(desertionNo)) {
				outVO = new DesertionVO(list.get(i).getDesertionNo(), list.get(i).getProcessState(),
						list.get(i).getFileName(), list.get(i).getHappenDt(), list.get(i).getHappenPlace(),
						list.get(i).getKindCd(), list.get(i).getColorCd(), list.get(i).getAge(),
						list.get(i).getWeight(), list.get(i).getNoticeNo(), list.get(i).getNoticeSdt(),
						list.get(i).getNoticeEdt(), list.get(i).getPopFile(), list.get(i).getSexCd(),
						list.get(i).getNeuterYn(), list.get(i).getSpecialMark(), list.get(i).getCareNm(),
						list.get(i).getCareTel(), list.get(i).getCareAddr(), list.get(i).getOrgNm(),
						list.get(i).getChargeNm(), list.get(i).getOfficeTel());

				break;

			}

		}

		return outVO;
	}

	public List<DesertionVO> totalRetrieve(int pageNo, int numOfRows, String upr_cd) {

		tPageNo = pageNo;
		tnumOfRows = numOfRows;
		tUprCd = upr_cd;
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -7);

		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, 7);

		String beforeDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());
		String afterDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week2.getTime());

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /* URL */

		try {
			// 준혁이꺼
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+ "=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//김주희
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
			//김준혁
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//최재훈 
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(beforeDay, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(afterDay, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + tPageNo); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + tnumOfRows); /* 페이지당 보여줄 개수 */
			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 :
			// 429900 */
			urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "="
					+ URLEncoder.encode(tUprCd, "UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "="
					+ URLEncoder.encode("notice", "UTF-8")); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String result = "";
			String line;
			while ((line = rd.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값

			}
			rd.close();
			conn.disconnect();

			BufferedReader br = null;
			// DocumentBuilderFactory 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			HashMap<String, String> resultMap = new HashMap<String, String>();
			List<DesertionVO> list = new ArrayList<DesertionVO>();

			try {
				// xml 파싱하기
				InputSource is = new InputSource(new StringReader(result));
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();
				XPathExpression expr = xpath.compile("/response/body/items/item");
				// XPathExpression expr = xpath.compile("//items/item");
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				DesertionVO inVO = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();

					for (int j = 0; j < child.getLength(); j++) {
						Node node = child.item(j);
						resultMap.put(node.getNodeName(), node.getTextContent());

					}

					inVO = new DesertionVO(resultMap.get("desertionNo"), resultMap.get("processState"),
							resultMap.get("filename"), resultMap.get("happenDt"), resultMap.get("happenPlace"),
							resultMap.get("kindCd"), resultMap.get("colorCd"), resultMap.get("age"),
							resultMap.get("weight"), resultMap.get("noticeNo"), resultMap.get("noticeSdt"),
							resultMap.get("noticeEdt"), resultMap.get("popfile"), resultMap.get("sexCd"),
							resultMap.get("neuterYn"), resultMap.get("specialMark"), resultMap.get("careNm"),
							resultMap.get("careTel"), resultMap.get("careAddr"), resultMap.get("orgNm"),
							resultMap.get("chargeNm"), resultMap.get("officetel"));
					list.add(inVO);

				}

				count = list.size();

			} catch (Exception e) {
				e.getMessage();
			}

			return list;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;

	}

	public DesertionVO totalSelectOne(String desertionNo) {
		List<DesertionVO> list = new ArrayList<DesertionVO>();
		DesertionVO outVO = new DesertionVO();
		list = totalRetrieve(tPageNo, tnumOfRows, tUprCd);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getDesertionNo().equals(desertionNo)) {
				outVO = new DesertionVO(list.get(i).getDesertionNo(), list.get(i).getProcessState(),
						list.get(i).getFileName(), list.get(i).getHappenDt(), list.get(i).getHappenPlace(),
						list.get(i).getKindCd(), list.get(i).getColorCd(), list.get(i).getAge(),
						list.get(i).getWeight(), list.get(i).getNoticeNo(), list.get(i).getNoticeSdt(),
						list.get(i).getNoticeEdt(), list.get(i).getPopFile(), list.get(i).getSexCd(),
						list.get(i).getNeuterYn(), list.get(i).getSpecialMark(), list.get(i).getCareNm(),
						list.get(i).getCareTel(), list.get(i).getCareAddr(), list.get(i).getOrgNm(),
						list.get(i).getChargeNm(), list.get(i).getOfficeTel()

				);
				break;
			}
		}

		return outVO;
	}

	public List<DesertionVO> dogRetrieve(int pageNo, int numOfRows, String upKind, String uprcd) {

		dPageNo = pageNo;
		dNumOfRows = numOfRows;
		dUpKind = upKind;
		dUprCd = uprcd;
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -7);

		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, 7);

		String beforeDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());
		String afterDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week2.getTime());
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /* URL */
		try {
			//김주희
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
			//김준혁
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//최재훈 
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(beforeDay, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(afterDay, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows); /* 페이지당 보여줄 개수 */
			urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "="
					+ URLEncoder.encode(upKind, "UTF-8")); /* 축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
			urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "="
					+ URLEncoder.encode(uprcd, "UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "="
					+ URLEncoder.encode("notice", "UTF-8")); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */

			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 -
			// 기타 : 429900 */
			// urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" +
			// URLEncoder.encode("notice", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice -
			// 보호중 : protect */
			// urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" +
			// URLEncoder.encode("20140601", "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" +
			// URLEncoder.encode("20140630", "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String result = "";
			String line;
			while ((line = rd.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값

			}
			rd.close();
			conn.disconnect();

			BufferedReader br = null;
			// DocumentBuilderFactory 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			HashMap<String, String> resultMap = new HashMap<String, String>();
			List<DesertionVO> list = new ArrayList<DesertionVO>();

			try {
				// xml 파싱하기
				InputSource is = new InputSource(new StringReader(result));
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();
				XPathExpression expr = xpath.compile("/response/body/items/item");
				// XPathExpression expr = xpath.compile("//items/item");
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				DesertionVO inVO = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();

					for (int j = 0; j < child.getLength(); j++) {
						Node node = child.item(j);
						resultMap.put(node.getNodeName(), node.getTextContent());

					}

					inVO = new DesertionVO(resultMap.get("desertionNo"), resultMap.get("processState"),
							resultMap.get("filename"), resultMap.get("happenDt"), resultMap.get("happenPlace"),
							resultMap.get("kindCd"), resultMap.get("colorCd"), resultMap.get("age"),
							resultMap.get("weight"), resultMap.get("noticeNo"), resultMap.get("noticeSdt"),
							resultMap.get("noticeEdt"), resultMap.get("popfile"), resultMap.get("sexCd"),
							resultMap.get("neuterYn"), resultMap.get("specialMark"), resultMap.get("careNm"),
							resultMap.get("careTel"), resultMap.get("careAddr"), resultMap.get("orgNm"),
							resultMap.get("chargeNm"), resultMap.get("officetel"));
					list.add(inVO);
				}

				count = list.size();

			} catch (Exception e) {
				e.getMessage();
			}

			return list;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public DesertionVO dogSelectOne(String desertionNo) {
		List<DesertionVO> list = new ArrayList<DesertionVO>();
		DesertionVO outVO = new DesertionVO();
		list = dogRetrieve(dPageNo, dNumOfRows, dUpKind, dUprCd);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getDesertionNo().equals(desertionNo)) {
				outVO = new DesertionVO(list.get(i).getDesertionNo(), list.get(i).getProcessState(),
						list.get(i).getFileName(), list.get(i).getHappenDt(), list.get(i).getHappenPlace(),
						list.get(i).getKindCd(), list.get(i).getColorCd(), list.get(i).getAge(),
						list.get(i).getWeight(), list.get(i).getNoticeNo(), list.get(i).getNoticeSdt(),
						list.get(i).getNoticeEdt(), list.get(i).getPopFile(), list.get(i).getSexCd(),
						list.get(i).getNeuterYn(), list.get(i).getSpecialMark(), list.get(i).getCareNm(),
						list.get(i).getCareTel(), list.get(i).getCareAddr(), list.get(i).getOrgNm(),
						list.get(i).getChargeNm(), list.get(i).getOfficeTel());
				break;
			}
		}
		return outVO;
	}

	public List<DesertionVO> catRetrieve(int pageNo, int numOfRows, String upKind, String uprcd) {

		cPageNo = pageNo;
		cNumOfRows = numOfRows;
		cUpKind = upKind;
		cUprCd = uprcd;
		
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -7);

		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, 7);

		String beforeDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());
		String afterDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week2.getTime());
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /* URL */
		try {

			
			//김주희
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
			//김준혁
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//최재훈 
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(beforeDay, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(afterDay, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows); /* 페이지당 보여줄 개수 */
			urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "="
					+ URLEncoder.encode(upKind, "UTF-8")); /* 축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
			urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "="
					+ URLEncoder.encode(uprcd, "UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "="
					+ URLEncoder.encode("notice", "UTF-8")); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */

			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 -
			// 기타 : 429900 */
			// urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" +
			// URLEncoder.encode("notice", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice -
			// 보호중 : protect */
			// urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" +
			// URLEncoder.encode("20140601", "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" +
			// URLEncoder.encode("20140630", "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String result = "";
			String line;
			while ((line = rd.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값

			}
			rd.close();
			conn.disconnect();

			BufferedReader br = null;
			// DocumentBuilderFactory 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			HashMap<String, String> resultMap = new HashMap<String, String>();
			List<DesertionVO> list = new ArrayList<DesertionVO>();

			try {
				// xml 파싱하기
				InputSource is = new InputSource(new StringReader(result));
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();
				XPathExpression expr = xpath.compile("/response/body/items/item");
				// XPathExpression expr = xpath.compile("//items/item");
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				DesertionVO inVO = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();

					for (int j = 0; j < child.getLength(); j++) {
						Node node = child.item(j);
						resultMap.put(node.getNodeName(), node.getTextContent());

					}

					inVO = new DesertionVO(resultMap.get("desertionNo"), resultMap.get("processState"),
							resultMap.get("filename"), resultMap.get("happenDt"), resultMap.get("happenPlace"),
							resultMap.get("kindCd"), resultMap.get("colorCd"), resultMap.get("age"),
							resultMap.get("weight"), resultMap.get("noticeNo"), resultMap.get("noticeSdt"),
							resultMap.get("noticeEdt"), resultMap.get("popfile"), resultMap.get("sexCd"),
							resultMap.get("neuterYn"), resultMap.get("specialMark"), resultMap.get("careNm"),
							resultMap.get("careTel"), resultMap.get("careAddr"), resultMap.get("orgNm"),
							resultMap.get("chargeNm"), resultMap.get("officetel"));
					list.add(inVO);
				}

				count = list.size();

			} catch (Exception e) {
				e.getMessage();
			}

			return list;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public DesertionVO catSelectOne(String desertionNo) {
		List<DesertionVO> list = new ArrayList<DesertionVO>();
		DesertionVO outVO = new DesertionVO();
		list = catRetrieve(cPageNo, cNumOfRows, cUpKind, cUprCd);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getDesertionNo().equals(desertionNo)) {
				outVO = new DesertionVO(list.get(i).getDesertionNo(), list.get(i).getProcessState(),
						list.get(i).getFileName(), list.get(i).getHappenDt(), list.get(i).getHappenPlace(),
						list.get(i).getKindCd(), list.get(i).getColorCd(), list.get(i).getAge(),
						list.get(i).getWeight(), list.get(i).getNoticeNo(), list.get(i).getNoticeSdt(),
						list.get(i).getNoticeEdt(), list.get(i).getPopFile(), list.get(i).getSexCd(),
						list.get(i).getNeuterYn(), list.get(i).getSpecialMark(), list.get(i).getCareNm(),
						list.get(i).getCareTel(), list.get(i).getCareAddr(), list.get(i).getOrgNm(),
						list.get(i).getChargeNm(), list.get(i).getOfficeTel());
				break;
			}
		}
		return outVO;
	}

	public List<DesertionVO> etcRetrieve(int pageNo, int numOfRows, String upKind, String uprcd) {

		ePageNo = pageNo;
		eNumOfRows = numOfRows;
		eUpKind = upKind;
		eUprCd = uprcd;
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -7);

		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, 7);

		String beforeDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());
		String afterDay = new java.text.SimpleDateFormat("yyyyMMdd").format(week2.getTime());
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /* URL */
		try {
			// 준혁이꺼
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")	+ "=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//김주희
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
			//김준혁
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
			//최재훈 
			//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(beforeDay, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(afterDay, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows); /* 페이지당 보여줄 개수 */
			urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "="
					+ URLEncoder.encode(upKind, "UTF-8")); /* 축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
			urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "="
					+ URLEncoder.encode(uprcd, "UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "="
					+ URLEncoder.encode("notice", "UTF-8")); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */

			// urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" +
			// URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 -
			// 기타 : 429900 */
			// urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" +
			// URLEncoder.encode("notice", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice -
			// 보호중 : protect */
			// urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" +
			// URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
			// urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" +
			// URLEncoder.encode("20140601", "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" +
			// URLEncoder.encode("20140630", "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
			// urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String result = "";
			String line;
			while ((line = rd.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값

			}
			rd.close();
			conn.disconnect();

			BufferedReader br = null;
			// DocumentBuilderFactory 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			HashMap<String, String> resultMap = new HashMap<String, String>();
			List<DesertionVO> list = new ArrayList<DesertionVO>();

			try {
				// xml 파싱하기
				InputSource is = new InputSource(new StringReader(result));
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();
				XPathExpression expr = xpath.compile("/response/body/items/item");
				// XPathExpression expr = xpath.compile("//items/item");
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				DesertionVO inVO = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();

					for (int j = 0; j < child.getLength(); j++) {
						Node node = child.item(j);
						resultMap.put(node.getNodeName(), node.getTextContent());

					}

					inVO = new DesertionVO(resultMap.get("desertionNo"), resultMap.get("processState"),
							resultMap.get("filename"), resultMap.get("happenDt"), resultMap.get("happenPlace"),
							resultMap.get("kindCd"), resultMap.get("colorCd"), resultMap.get("age"),
							resultMap.get("weight"), resultMap.get("noticeNo"), resultMap.get("noticeSdt"),
							resultMap.get("noticeEdt"), resultMap.get("popfile"), resultMap.get("sexCd"),
							resultMap.get("neuterYn"), resultMap.get("specialMark"), resultMap.get("careNm"),
							resultMap.get("careTel"), resultMap.get("careAddr"), resultMap.get("orgNm"),
							resultMap.get("chargeNm"), resultMap.get("officetel"));
					list.add(inVO);
				}

				count = list.size();

			} catch (Exception e) {
				e.getMessage();
			}

			return list;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public DesertionVO etcSelectOne(String desertionNo) {
		List<DesertionVO> list = new ArrayList<DesertionVO>();
		DesertionVO outVO = new DesertionVO();
		list = catRetrieve(ePageNo, eNumOfRows, eUpKind, eUprCd);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getDesertionNo().equals(desertionNo)) {
				outVO = new DesertionVO(list.get(i).getDesertionNo(), list.get(i).getProcessState(),
						list.get(i).getFileName(), list.get(i).getHappenDt(), list.get(i).getHappenPlace(),
						list.get(i).getKindCd(), list.get(i).getColorCd(), list.get(i).getAge(),
						list.get(i).getWeight(), list.get(i).getNoticeNo(), list.get(i).getNoticeSdt(),
						list.get(i).getNoticeEdt(), list.get(i).getPopFile(), list.get(i).getSexCd(),
						list.get(i).getNeuterYn(), list.get(i).getSpecialMark(), list.get(i).getCareNm(),
						list.get(i).getCareTel(), list.get(i).getCareAddr(), list.get(i).getOrgNm(),
						list.get(i).getChargeNm(), list.get(i).getOfficeTel());
				break;
			}
		}
		return outVO;
	}
}
