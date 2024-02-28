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
//오류발생2: [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: 
//Failed to convert value of type 'java.lang.String' to required type 'long'; For input string: ""
//@@ResponseBody 생략되어있었다.

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
		
		//System.out.println(boardService.getBoardDetail(boardId));
		
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/boardDetail";
	}
	
	@GetMapping("/authentication")
	public String authentication(Model model, 
					@RequestParam("boardId") long boardId, 
					@RequestParam("menu") String menu) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		model.addAttribute("menu", menu);
		
		return "board/authentication";
		
		//오류 org.thymeleaf.exceptions.TemplateInputException 발생
		//해결: authentication html에서 location.href='@{/board/boardList} 이 문장에서 || 를 생략하면 안된다.
		
	}
	
	@PostMapping("/authentication")
	@ResponseBody
	public String authentication(@ModelAttribute BoardDTO boardDTO, 
								@RequestParam("menu") String menu) {
		
		String  jsScript = "";
		
		if(boardService.checkAuthorized(boardDTO))  {
			
			if(menu.equals("update")) {
				jsScript = "<script>";
				jsScript +="location.href='/board/updateBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript +="</script>";
			}
			else if (menu.equals("delete")) {
				jsScript="<script>";
				jsScript += "location.href='/board/deleteBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript += "</script>";
				
			}
		}
		else {
			jsScript="""
					<script>
					alert('패스워드를 입력하세요!');
					history.go(-1);
					</script>
					""";
		}
		return jsScript;
		
	}
	
	//.오류 발생 : MissingServletRequestParameterException: Required request parameter 'menu' for method parameter type String is not present]
	//-> 파라메타로 넘어오는 "menu"를 못 찾고있다.
	// 해결: html에서 화면 넘길시 value=${} 가 아닌 value="" 이렇게 작성이 되어있었다.
	@GetMapping("/updateBoard")
	public String updateBoard(Model model, @RequestParam("boardId") long boardId) {
		model.addAttribute("boardDTO",boardService.getBoardDetail(boardId));
		return  "board/updateBoard";
	}
	
	@PostMapping("/updateBoard")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		boardService.updateBoard(boardDTO);
		String jsScript ="""
				<script>
				 alert('수정 완료되었습니다.');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard( Model model ,@RequestParam("boardId") long boardId) {
		model.addAttribute("boardId", boardId);
		return "board/deleteBoard";
	}
	
	@PostMapping("/deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestParam("boardId") long boardId) {
		boardService.deleteBoard(boardId);
		
		String jsScript ="""
				<script>
				 alert('게시글 삭제되었습니다.');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}

}
