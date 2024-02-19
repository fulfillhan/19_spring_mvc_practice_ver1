package com.application.practiceVersion1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.practiceVersion1.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	public void createBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);
	
	public void updateReadCnt(long boardId);

	public String getEncodePasswd(long boardId);

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(long boardId);

}
