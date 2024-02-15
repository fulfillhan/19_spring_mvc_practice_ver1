package com.application.practiceVersion1.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	
	private long boardId;
	private String passWd;
	private String writer;
	private String subject;
	private String content;
	private long readCnt;
	private Date enrollAt;
	

}
