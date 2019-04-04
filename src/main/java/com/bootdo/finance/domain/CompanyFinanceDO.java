package com.bootdo.finance.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
@Data
public class CompanyFinanceDO extends CommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	// 公司名字
	private String companyName;

	// 货款金额
	private double loanAmount;

	// 年初欠款
	private double arrears;

	// 已付款
	private double paid;

	// 总欠款额
	private double totalArrears;

	// 时间
	private String timedate;

	// 渠道：0-pc；1-小程序
	private int channel;
}
