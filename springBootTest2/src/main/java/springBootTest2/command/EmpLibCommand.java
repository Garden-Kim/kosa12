package springBootTest2.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmpLibCommand {
	String libWriter;
	String libSubject;
	String libContent;
	String libPw;
	String libNum;
	MultipartFile [] report; // input type="file"

}