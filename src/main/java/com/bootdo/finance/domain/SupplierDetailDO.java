package com.bootdo.finance.domain;

public class SupplierDetailDO extends CommonEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	// 采购名称
	private String purchaseName;
	
	// 单位
	private String unit;
	
	// 数量
	private Integer amount;
	
	// 单价
	private Double price;
	
	// 金额
	private Double total;
	
	// 已付款
	private Double paid;
	
	// 付款方式
	private String payment;

	// 公司名字
	private String companyName;
	
	// 创建时间start
	private String createTimeStart;
	
	// 创建时间end
	private String createTimeEnd;
	
	// 创建时间
	private String createTime;
	
	// 公司id
	private Integer companyId;
	
	// 时间
	private String timedate;
	
	
	
	public SupplierDetailDO() {
		
	}



	public SupplierDetailDO(Integer id, String purchaseName, String unit, Integer amount, Double price, Double total,
							Double paid, String payment, String companyName, String createTimeStart, String createTimeEnd,
							String createTime, Integer companyId) {
		super();
		this.id = id;
		this.purchaseName = purchaseName;
		this.unit = unit;
		this.amount = amount;
		this.price = price;
		this.total = total;
		this.paid = paid;
		this.payment = payment;
		this.companyName = companyName;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.createTime = createTime;
		this.companyId = companyId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getPurchaseName() {
		return purchaseName;
	}



	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public Integer getAmount() {
		return amount;
	}



	public void setAmount(Integer amount) {
		this.amount = amount;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



	public Double getPaid() {
		return paid;
	}



	public void setPaid(Double paid) {
		this.paid = paid;
	}



	public String getPayment() {
		return payment;
	}



	public void setPayment(String payment) {
		this.payment = payment;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public Integer getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



	public String getTimedate() {
		return timedate;
	}



	public void setTimedate(String timedate) {
		this.timedate = timedate;
	}

	
	
	
}