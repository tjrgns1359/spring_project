package com.canesblack.spring_project1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map; // 👈 [수정] Map 임포트 추가

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; // 👈 [수정] RequestParam 임포트 추가
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils; // 👈 [추가] StringUtils 임포트
import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;

@RestController
public class MenuRestController {
	
	@Autowired
	private MenuRestService menuRestService;
	
	// 🔽 [수정] getAllMenus 메서드 수정
	// 1. @RequestParam으로 페이지 번호 받기 (기본값 1)
	// 2. 반환 타입을 ResponseEntity<Map<String, Object>>로 변경
	@GetMapping("/menu/all")
	public ResponseEntity<Map<String, Object>> getAllMenus(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		// 3. 서비스에서 Map 데이터 받아오기
		Map<String, Object> response = menuRestService.getList(page);
		
		// 4. Map에서 menus 리스트 꺼내서 비어있는지 확인 (기존 로직 유지)
		List<Menu> menus = (List<Menu>) response.get("menus");
		
		if(menus != null && !menus.isEmpty()){
			return ResponseEntity.ok(response); // 👈 Map 전체를 JSON으로 반환
		}else {
			// (참고: 1페이지가 아닌데 목록이 비었을 수도 있지만, 일단 기존 로직대로 noContent 반환)
			return ResponseEntity.noContent().build();
		}
	}
	
	
	// --- (이하 기존 코드 동일) ---

	//2.메뉴(한개의 게시판 생성)생성
		@PostMapping("/menu/add")
		public ResponseEntity<String>addMenu(@RequestBody Menu menu){
			
			// 🔽 [수정] 날짜 자동 설정 부분 수정
			// 기존: if(menu.getIndate()==null|| menu.getIndate().isEmpty()) {
			// 수정: StringUtils.hasText()를 사용하여 null, "", " " (공백)을 모두 안전하게 검사
			if(!StringUtils.hasText(menu.getIndate())) {
				menu.setIndate(LocalDate.now().toString());
			}
			
			//조회수는 처음에는 0으로 설정
			menu.setCount(0);
			//메뉴를 데이터베이스에 삽입
			menuRestService.boardInsert(menu);
			return ResponseEntity.ok("게시글 잘 작성됨");
		}
	
	//메뉴(한개의 게시판 수정)수정
	//localhost:8080/menu/update/2    
	@PutMapping("/menu/update/{idx}")
	public void updateMenu(@RequestBody Menu menu,@PathVariable("idx")int idx) {
		menu.setIdx(idx);//특정idx를 가진 게시글을 menu안의 title과 content를 가져와서 수정해준다.
		menuRestService.boardUpdate(menu);
	}
	
	//4.메뉴(한개의 게시판 삭제)삭제
	@DeleteMapping("/menu/delete/{idx}")
	public void deleteMenu(@PathVariable("idx")int idx) {
		menuRestService.boardDelete(idx);
	}
	
	//5.특정메뉴(한개의 게시판의 내용을 조회)조회
	@GetMapping("/menu/{idx}")
	public ResponseEntity<Menu>getMenuById(@PathVariable("idx")int idx){
		Menu menu=menuRestService.boardContent(idx); // 👈 [수정] Service의 오타(inx)를 수정했으므로, 이제 정상 동작합니다.
		if(menu!=null) {
			return ResponseEntity.ok(menu);
			//200번대의 상태코드와 menu객체를 백엔드에서 프론트앤드 영역으로 데이터를 넘긴다.
		}else {
			return ResponseEntity.notFound().build();
			//메뉴가 존재하지 않을경우 NOT FOUND =>404에러를 반환하게끔 설정
		}
	}
	
	//6.특정메뉴(게시판조회수 증가)조회수 증가
	@PutMapping("/menu/count/{idx}")
	public void incrementMenuCount(@PathVariable("idx")int idx) {
		menuRestService.boardCount(idx);
	}
}
