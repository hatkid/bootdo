package com.bootdo.finance.domain;

public class RunningAccountDO extends CommonEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	// 费用属性
	private String costAttribute;
	
	// 属性类别
	private String costAttributeType;
	
	// 单位名称
	private String companyName;
	
	// 项目
	private String projectName;
	
	// 摘要
	private String abstractContent;
	
	// 入账（借）
	private Double entrys;
	
	// 出账（贷）
	private Double outs;

	// 支付方式
	private String payment;
	
	// 收款人
	private String payee;
	
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
	
	
	public RunningAccountDO() {
		
	}


	public RunningAccountDO(Integer id, String costAttribute, String costAttributeType, String companyName,
							String projectName, String abstractContent, Double entrys, Double outs, String payment, String payee,
							String remark, String createTime, String createTimeStart, String createTimeEnd) {
		super();
		this.id = id;
		this.costAttribute = costAttribute;
		this.costAttributeType = costAttributeType;
		this.companyName = companyName;
		this.projectName = projectName;
		this.abstractContent = abstractContent;
		this.entrys = entrys;
		this.outs = outs;
		this.payment = payment;
		this.payee = payee;
		this.remark = remark;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCostAttribute() {
		return costAttribute;
	}


	public void setCostAttribute(String costAttribute) {
		this.costAttribute = costAttribute;
	}


	public String getCostAttributeType() {
		return costAttributeType;
	}


	public void setCostAttributeType(String costAttributeType) {
		this.costAttributeType = costAttributeType;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getAbstractContent() {
		return abstractContent;
	}


	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}


	public Double getEntrys() {
		return entrys;
	}


	public void setEntrys(Double entrys) {
		this.entrys = entrys;
	}


	public Double getOuts() {
		return outs;
	}


	public void setOuts(Double outs) {
		this.outs = outs;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getPayee() {
		return payee;
	}


	public void setPayee(String payee) {
		this.payee = payee;
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