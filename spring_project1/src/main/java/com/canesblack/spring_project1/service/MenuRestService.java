package com.canesblack.spring_project1.service;

import java.util.HashMap; // 👈 [수정] HashMap 임포트 추가
import java.util.List;
import java.util.Map; // 👈 [수정] Map 임포트 추가

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.mapper.MenuRestMapper;

@Service
public class MenuRestService {

	@Autowired
	private MenuRestMapper menuRestMapper;
	
	// 🔽 [수정] getList 메서드 수정
	// 기존: public List<Menu> getList()
	// 수정: public Map<String, Object> getList(int pageNum)
	// (게시글 목록 + 페이지 정보를 Map에 담아 반환)
	public Map<String, Object> getList(int pageNum) {
		
		int pageSize = 10; // 👈 [설정] 페이지당 10개씩
		int offset = (pageNum - 1) * pageSize; // 👈 DB에서 가져올 시작 위치 계산
		
		// 1. 요청한 페이지의 게시글 목록 (10개)
		List<Menu> menus = menuRestMapper.getLists(offset, pageSize);
		
		// 2. 전체 게시글 개수
		int totalCount = menuRestMapper.getTotalCount();
		
		// 3. 총 페이지 수 계산 (예: 105개 -> 11페이지)
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		
		// 4. 두 정보를 Map에 담아 컨트롤러로 반환
		Map<String, Object> response = new HashMap<>();
		response.put("menus", menus); // 👈 실제 게시글 목록
		response.put("totalPages", totalPages); // 👈 총 페이지 수
		response.put("currentPage", pageNum); // 👈 현재 페이지 번호
		
		return response;
	}
	
	// 🔽 [수정] boardContent 메서드 파라미터명 수정 (inx -> idx)
	// (기존 코드에서 inx라는 오타가 있었습니다. idx로 명확히 수정합니다.)
	public Menu boardContent(int idx) {
		return menuRestMapper.boardContent(idx);
	}
	
	
	// --- (이하 기존 코드 동일) ---
	
	//게시글을 추가하는 메소드
	public void boardInsert(Menu menu) {
		menuRestMapper.boardInsert(menu);
	}
	
	//특정 게시글을 삭제하는 메소드 
	public void boardDelete(int idx) {
		menuRestMapper.boardDelete(idx);
	}
	
	//특정 게시글을 수정하는 메소드
	public void boardUpdate(Menu menu) {
		menuRestMapper.boardUpdate(menu);
	}
	
	//게시글의 조회수를 증가시키는 메소드
	public void boardCount(int idx) {
		menuRestMapper.boardCount(idx);
	}
	
}
