package springBootTest2.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class QnaCommand {
	Integer qnaNum;         
	String qnaSubject;  
	String qnaContent;  

	
}
