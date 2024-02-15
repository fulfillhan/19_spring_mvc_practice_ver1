package com.application.practiceVersion1.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.practiceVersion1.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	public void createBoard(BoardDTO boardDTO);

}
