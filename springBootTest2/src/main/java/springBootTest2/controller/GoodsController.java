package springBootTest2.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.GoodsCommand;
import springBootTest2.service.goods.GoodsAutoNumService;
import springBootTest2.service.goods.GoodsDeleteService;
import springBootTest2.service.goods.GoodsInfoService;
import springBootTest2.service.goods.GoodsInsertService;
import springBootTest2.service.goods.GoodsListService;
import springBootTest2.service.goods.GoodsUpdateService;


@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsAutoNumService goodsAutoNumService;
	@Autowired
	GoodsInsertService goodsInsertService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsInfoService goodsInfoService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@RequestMapping("goodsUpdateOk")
	public String goodsUpdateOk(GoodsCommand goodsCommand, HttpSession session) {
		goodsUpdateService.execute(goodsCommand, session);
		return "redirect:goodsInfo?num="+goodsCommand.getGoodsNum();
	}
	
	@RequestMapping("goodsUpdate")
	public String goodsUpdate(@RequestParam("num") String num, Model model) {
		goodsInfoService.execute(num, model);
		return "thymeleaf/goods/goodsDetail";
	}
	@RequestMapping("goodsDelete")
	public String goodsDelete(@RequestParam("num") String num) {
		goodsDeleteService.execute(num);
		return "redirect:goodsList";
	}
	@RequestMapping("goodsInfo")
	public String goodsInfo(@RequestParam("num") String num, Model model) {
		goodsInfoService.execute(num,model);
		return "thymeleaf/goods/goodsInfo";
	}
	@RequestMapping("goodsRegist")
	public String goodsInsert(GoodsCommand goodsCommand, HttpSession session) {
		goodsInsertService.execute(goodsCommand, session);
		return "redirect:goodsList";
	}
	@RequestMapping("goodsWirte")
	public String goodsWrite(Model model) {
		goodsAutoNumService.execute(model);
		return "thymeleaf/goods/goodsForm";
	}
	@RequestMapping("goodsList")
	public String goodsList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}
}
