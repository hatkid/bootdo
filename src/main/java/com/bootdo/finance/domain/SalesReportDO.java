package com.bootdo.finance.domain;

public class SalesReportDO extends CommonEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	// 客户名称
	private String customerName;
	
	// 品名
	private String teaName;
	
	// 色别
	private String color;
	
	// 件数 套数
	private Integer setCount;
	
	// 发货件数
	private Integer saleCount;
	
	// 合计数量
	private Integer totalCount;
	
	// 单价
	private Double price;

	// 合计金额
	private Double totalPrice;
	
	// 小计
	private String subtotal;
	
	// 备注
	private String remark;
	
	// 创建时间
	private String createTime;
	
	// 创建时间start
	private String createTimeStart;
	
	// 创建时间end
	private String createTimeEnd;
	
	// 时间
	private String timedate;
	
	
	public SalesReportDO() {
		
	}


	public SalesReportDO(Integer id, String customerName, String teaName, String color, Integer setCount,
						 Integer saleCount, Integer totalCount, Double price, Double totalPrice, String subtotal, String remark,
						 String createTime) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.teaName = teaName;
		this.color = color;
		this.setCount = setCount;
		this.saleCount = saleCount;
		this.totalCount = totalCount;
		this.price = price;
		this.totalPrice = totalPrice;
		this.subtotal = subtotal;
		this.remark = remark;
		this.createTime = createTime;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getTeaName() {
		return teaName;
	}


	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Integer getSetCount() {
		return setCount;
	}


	public void setSetCount(Integer setCount) {
		this.setCount = setCount;
	}


	public Integer getSaleCount() {
		return saleCount;
	}


	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}


	public Integer getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getCreateTimeStart() {
		return createTimeStart;
	}


	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}


	public String getCreateTimeEnd() {
		return createTimeEnd;
	}


	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}


	public String getTimedate() {
		return timedate;
	}


	public void setTimedate(String timedate) {
		this.timedate = timedate;
	}

	
	
	
}