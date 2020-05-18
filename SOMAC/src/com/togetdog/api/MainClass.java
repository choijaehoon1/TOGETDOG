package com.togetdog.api;

import java.util.List;

import com.togetdog.desertion.DesertionVO;

public class MainClass {

	public static void main(String[] args) {

		ApiExplorer search = new ApiExplorer();

		// 용도: 다건조회, 설명: 공고 중인 모든 데이터를 조회한다.

		List<DesertionVO> desertionData = search.areaRetrieve(1, 12, "");
		for (int i = 0; i < desertionData.size(); i++) {
			System.out.println(desertionData.get(i));
		}
	

		// 용도: 다건조회, 설명: 공고 중인 모든 데이터에서 축종 과 지역으로 검색하여 데이터를 조회한다.

		/*
		 * List<DesertionVO> desertio i < desertionData2.size(); i++) {
		 * 
		 * 
		 * System.out.println(desertionData2.get(i)); }
		 * 
		 * 
		 * 
		 * 
		 * System.out.println("토탈입니다:" + ApiExplorer.tCount);
		 */

		// 용도: 단건조회 설명: desertionNo를 파라미터 값으로 받으며, 해당 desertionNo의 데이터를 DesertionVO 형태로
		// 담아 출력한다.

		/*
		 * DesertionVO vo = search.selectOne("426336202000133");
		 * 
		 * System.out.println(vo);
		 */

	}

}
