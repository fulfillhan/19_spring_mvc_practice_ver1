package com.application.practiceVersion1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.practiceVersion1.dto.BoardDTO;
import com.application.practiceVersion1.service.BoardService;
import com.application.practiceVersion1.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//private BoardServiceImpl boardServiceImpl; 연결해도 되지만 유지보수를 위해 인터페이스와 연결시키는게 더 낫다
	
	@GetMapping("/createBoard")// createBoard HTML 화면 내보내기
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("/createBoard")
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		
		//System.out.println(boardId);
		//boardService.createBoard(boardDTO);
		
		return "";
		
	}
	
	
	
	

}
