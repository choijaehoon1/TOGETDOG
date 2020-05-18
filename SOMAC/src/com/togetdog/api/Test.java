package com.togetdog.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
    	
    	
        StringBuilder urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/			
        //김주희
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=UwwarVQqrJEzuiNFiOo0CtYn2AOkf9gVP4%2BlQWZ8qlRZ%2BAXBrSh1JM0hewZ3x7Xb4D7c6DwwA6jE3OjcWC6nrA%3D%3D");
		//김준혁
		//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=XbxQBGYFrvifV6QaSjyW%2FhztED3Hr%2BSsXh9oWX9x9P0YWCYs%2BO1jBgIt5yToZ3rnF%2BsRy0oq97Nged9aAKvjnA%3D%3D");
		//최재훈 
		//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=T2fN5Etn04G%2F74Ldp8enkfCWlZ6phh5RlqY8L%2Fli3xn40p5qtUEVbdYKC6dxiZPHNThJtl2p8HcGuM6jtRkwoQ%3D%3D");
		//이재원
		//urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") +"=2jwcZv4elta6Cc57rMFWEfK8RhY%2FiGz0%2FyBFqhhBLvwahwUBrlaxU2KAel3PI5N3BYwDD1XtMGS7iVC7bzVMcg%3D%3D");
		
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode("20200316", "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode("20200325", "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode("notice", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 보여줄 개수*/
        urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}