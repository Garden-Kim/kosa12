package model.DTO;

public class BoardDTO {
	Integer boardNum;
	String boardWrite;
	String boardSubject;
	String boardContent;
	String writerIp;
	Integer visitCount;
	public Integer getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Integer boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardWrite() {
		return boardWrite;
	}
	public void setBoardWrite(String boardWrite) {
		this.boardWrite = boardWrite;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getWriterIp() {
		return writerIp;
	}
	public void setWriterIp(String writerIp) {
		this.writerIp = writerIp;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

}
