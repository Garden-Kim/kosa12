package springBootTest2.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GoodsDTO {

	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	Date goodsDate;
	String goodsContent;
	Integer goodsQty;
	String goodsCompany;
	String empNum;
}
