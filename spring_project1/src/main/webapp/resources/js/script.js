//DOM 객체 연결
const container= document.getElementById("container");
const menuAdmin=document.getElementById("menuAdmin");
const menuList=document.getElementById("menuList");
// 🔽 [추가] 페이지네이션 컨테이너 DOM 연결
const paginationContainer = document.getElementById("pagination-container");


//CSRF 토큰과 헤더이름 가져오기 
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

 
// 🔽 [수정] fetchMenus 함수가 page 번호를 인자로 받도록 수정
function fetchMenus(page = 1){ // 기본값 1
	
	// 🔽 [수정] URL에 page 파라미터 추가
	fetch(`/menu/all?page=${page}`)
		.then(response => {
			if (response.status === 204) { // 204 No Content (게시글 없음)
				return null;
			}
			if (!response.ok) {
				throw new Error('데이터 로드 실패');
			}
			return response.json();
		})
		.then(data => { // 👈 [수정] 'menus' 배열이 아닌 'data' 객체로 받음
			
			menuList.innerHTML=''; // 기존 메뉴 목록 초기화
			
			if (data === null || data.menus.length === 0) {
				menuList.innerHTML = '<p>게시글이 없습니다.</p>';
				paginationContainer.innerHTML = ''; // 페이지 버튼도 비움
				return;
			}
			
			// 🔽 [수정] data.menus 배열을 순회
			data.menus.forEach(menu=>{
				//각 메뉴 아이템을 생성해서 리스트에 추가
				const menuItem=document.createElement('div');
				menuItem.className='menu-item';
				menuItem.innerHTML=`
				<a href="#" class="menu-link" style="text-decoration:none;color:black;">
					<h3>${menu.title}</h3>
					<p>${menu.content}</p>
					<small>작성자:${menu.writer},작성일:${menu.indate},조회수:${menu.count}</small>
				</a>
				<br/>
				<br/>
				`
				//게시글을 메인페이지에서 하나씩 클릭할때
				menuItem.querySelector(".menu-link").addEventListener('click',(event)=>{
					event.preventDefault();
					console.log(`event:${event}`);
					
					incrementCount(menu.idx).then(()=>window.location.href=`/noticeCheckPage?idx=${menu.idx}`)
				});
				menuList.appendChild(menuItem);
			}); // end of forEach
			
			// 🔽 [추가] 페이지네이션 버튼 렌더링 함수 호출
			renderPagination(data.totalPages, data.currentPage);
			
		}) // end of .then(data => ...)
		.catch(error => {
			console.error("Error fetching menus:", error);
			menuList.innerHTML = '<p>게시글을 불러오는 데 실패했습니다.</p>';
		});
}

// 🔽 [추가] 페이지네이션 버튼을 생성하는 함수
function renderPagination(totalPages, currentPage) {
	paginationContainer.innerHTML = ''; // 기존 버튼 비우기
	
	for (let i = 1; i <= totalPages; i++) {
		const pageBtn = document.createElement('button');
		pageBtn.className = 'pagination-btn';
		pageBtn.innerText = i;
		
		if (i === currentPage) {
			pageBtn.classList.add('active');
			pageBtn.disabled = true; // 현재 페이지 버튼은 비활성화
		}
		
		pageBtn.addEventListener('click', () => {
			fetchMenus(i); // 해당 페이지의 메뉴를 다시 불러옴
		});
		
		paginationContainer.appendChild(pageBtn);
	}
}


// --- (incrementCount 함수는 수정 없이 동일) ---
function incrementCount(idx){
	return fetch(`/menu/count/${idx}`,{
		method:'PUT',
		headers:{
			[csrfHeader]:csrfToken
		}
	}).then(response=>{
		if(!response.ok){
			console.log('데이터가 프론트서버에서 백엔드서러 잘 안넘어감');
		}
	}).catch(error=>{
		console.log(`Error:${error}`);
	})
}

// 🔽 [수정] 메인페이지가 열리면 1페이지를 로드하도록 수정
window.addEventListener('load', () => fetchMenus(1));
