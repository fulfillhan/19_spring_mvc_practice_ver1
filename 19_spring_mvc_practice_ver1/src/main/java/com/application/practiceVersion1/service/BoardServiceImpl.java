package com.application.practiceVersion1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.practiceVersion1.dao.BoardDAO;
import com.application.practiceVersion1.dto.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createBoard(BoardDTO boardDTO) {
		
		// 패스워드 입력시 암호화 하여 보낸다.- passwordEncoder.encode(암호화하고 싶은 패스워드)  
		
		boardDTO.setPasswd(passwordEncoder.encode(boardDTO.getPasswd()));
		
		boardDAO.createBoard(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) {
		boardDAO.updateReadCnt(boardId);
		return boardDAO.getBoardDetail(boardId);
	}

	@Override
	public boolean checkAuthorized(BoardDTO boardDTO) {
		boolean isCheck = false;
		
		// 암호화된 패스워드 가지고 오기
		
		//passwordEncoder.matches(boardDTO.getPasswd(),boardDAO.getEncodePasswd(boardDTO.getBoardId()));
		if(passwordEncoder.matches(boardDTO.getPasswd(),boardDAO.getEncodePasswd(boardDTO.getBoardId()))) {
			isCheck = true;
		}
		return isCheck;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
	 boardDAO.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(long boardId) {
		boardDAO.deleteBoard(boardId);
	}

	
	
	
	

}
