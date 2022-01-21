package springBootTest2.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("libdto")
public class EmpLibDTO {
	Integer libNum;
	String libWriter;
	String libSubject;
	String libContent;
	String empNum;
	String ipAddr;
	String libPw;

}