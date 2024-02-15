package com.application.practiceVersion1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.practiceVersion1.dao.BoardDAO;
import com.application.practiceVersion1.dto.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void createBoard(BoardDTO boardDTO) {
		boardDAO.createBoard(boardDTO);
		
	}

	
	
	

}
