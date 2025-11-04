package com.canesblack.spring_project1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // 👈 [수정] Param 임포트 추가
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.canesblack.spring_project1.entity.Menu;


@Mapper
public interface MenuRestMapper {
	
	// 🔽 [수정] getLists 메서드 수정
	// 기존: getLists()
	// 수정: getLists(@Param("offset") int offset, @Param("limit") int limit)
	//       SQL에 LIMIT #{limit} OFFSET #{offset} 추가
	@Select("SELECT idx,memID,title,content,writer,indate,count FROM backend_spring_project.menu ORDER BY idx DESC LIMIT #{limit} OFFSET #{offset}")
	public List<Menu> getLists(@Param("offset") int offset, @Param("limit") int limit);
	
	// 🔽 [추가] getTotalCount 메서드 추가
	// (전체 게시글 개수를 세는 쿼리)
	@Select("SELECT COUNT(*) FROM backend_spring_project.menu")
	public int getTotalCount();
	
	
	// --- (이하 기존 코드 동일) ---
	
	@Insert("INSERT INTO backend_spring_project.menu(memID,title,content,writer,indate)VALUES(#{memID},#{title},#{content},#{writer},#{indate})")
	public void boardInsert(Menu menu);
	
	@Select("SELECT idx,memID,title,content,writer,indate,count FROM backend_spring_project.menu WHERE idx=#{idx}")
	public Menu boardContent(int idx); // 👈 [참고] Service에서 inx 오타가 있었는데, 이 메서드명(boardContent)은 idx가 맞습니다.
	
	@Delete("DELETE FROM backend_spring_project.menu WHERE idx =#{idx}")
	public void boardDelete (int idx);
	
	@Update("UPDATE backend_spring_project.menu SET title=#{title},content=#{content},writer=#{writer} WHERE idx=#{idx}")
	public void boardUpdate(Menu menu);
	
	@Update("UPDATE backend_spring_project.menu SET count=count+1 WHERE idx=#{idx}")
	public void boardCount(int idx);
	
}
