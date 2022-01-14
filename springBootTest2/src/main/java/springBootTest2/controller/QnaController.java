package springBootTest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.BoardCommand;
import springBootTest2.command.QnaCommand;
import springBootTest2.service.qna.QnaDeleteService;
import springBootTest2.service.qna.QnaInfoSerivce;
import springBootTest2.service.qna.QnaListService;
import springBootTest2.service.qna.QnaUpdateService;
import springBootTest2.service.qna.QnaWriteService;

@Controller
@RequestMapping("qna")
public class QnaController {
	@Autowired
	QnaWriteService qnaWriteService;
	@Autowired
	QnaListService qnaListService; 
	@Autowired
	QnaInfoSerivce qnaInfoService;
	@Autowired
	QnaDeleteService qnaDeleteService;
	@Autowired
	QnaUpdateService qnaUpdateService;
	
	@RequestMapping("qnaUpdate")
	public String qnaUpdate(QnaCommand qnaCommand) {
		qnaUpdateService.execute(qnaCommand);
		return "redirect:qnaDetail?num="+qnaCommand.getQnaNum();
	}
	
	@RequestMapping("qnaModify")
	public String qnaModify(@RequestParam("num") Integer num, Model model) {
		qnaInfoService.execute(num, model);
		return "thymeleaf/qna/qnaModify";
	}
	
	@RequestMapping("qnaDelete")
	public String qnaDelete(@RequestParam("num") Integer num) {
		qnaDeleteService.execute(num);
		return "redirect:qnaList";
	}
	
	@RequestMapping("qnaDetail")
	public String qnaDetail(@RequestParam("num") Integer num, Model model) {
		qnaInfoService.execute(num, model);
		return "thymeleaf/qna/qnaInfo";
	}
	
	@RequestMapping("qnaRegist")
	public String qnaRegist(QnaCommand qnaCommand) {
		qnaWriteService.execute(qnaCommand);
		return "redirect:qnaList";
	}
	
	@RequestMapping("qnaWrite")
	public String qnaForm() {
		return "thymeleaf/qna/qnaForm"; 
	}
	
	@RequestMapping("qnaList")
	public String qnaHome(Model model) {
		qnaListService.execute(model);
		return "thymeleaf/qna/qnaList";
	}

}
