package com.application.practiceVersion1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.practiceVersion1.dto.BoardDTO;
import com.application.practiceVersion1.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//private BoardServiceImpl boardServiceImpl; 연결해도 되지만 유지보수를 위해 인터페이스와 연결시키는게 더 낫다
	
	@GetMapping("/createBoard")// createBoard HTML 화면 내보내기 기본 반환타입은  String
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("/createBoard")
	@ResponseBody
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		
		//System.out.println(boardDTO);
		
		boardService.createBoard(boardDTO);
		
		String jsScript = """
				<script>
					alert('작성 되었습니다');
					location.href='/board/boardList';
				</script>
				""";
		return jsScript;
		
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		//System.out.println(boardService.getBoardList());
		
		model.addAttribute("boardList", boardService.getBoardList());
		
		return "board/boardList";
		//에러발생:org.thymeleaf.exceptions.TemplateInputException:
		//An error happened during template parsing (template: "class path resource [templates/board/boardList.html]") 에러상황
		//해결: html에서  th:text="${#dates.format~ 에서 # 작성누락으로 오류 발생
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, @RequestParam("boardId") long boardId) {
		
		System.out.println(boardService.getBoardDetail(boardId));
		
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/boardDetail";
	}
	

}
