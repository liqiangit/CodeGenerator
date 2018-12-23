package com.liqiangit.cg;



import java.io.Serializable;
import java.util.Date;

import com.liqiangit.cg.ui.Comment;

public class Person implements Serializable{

	/**
	 * serialVersionUID:序列化ID,缓存需要  
	 */
	private static final long serialVersionUID = 4269416158052337256L;


	/**
	 * PERSON_ID
	 * 客户id   主键
	 */
	private String personId;
	/**
	 * LEGAL_ORG_CODE
	 * 机构代码(全路径)
	 */
	private String legalOrgCode;
	/**
	 * CUSTOMER_ID
	 * 核心客户号(编码)
	 */
	private String customerId;
	/**
	 * CTFTYPE
	 * 证件类型
	 */
	@Comment(comment="证件类型")
	private String ctftype;
	/**
	 * CTFNO
	 * 证件号码
	 */
	private String ctfno;

	/**
	 * CTFNAME
	 * 证件名称
	 */
	private String ctfname;

	/**
	 * ORG_NAME
	 * 机构名称
	 */
	private String orgName;

	/**
	 * PROPERTY
	 * 客户属性
	 */
	private String property;

	/**
	 * STATUS
	 * 客户状态 1正常0失效
	 */
	private Integer status;
	/**
	 * REMARK
	 * 备注
	 */
	private String remark;
	/**
	 * CREATOR
	 * 创建人
	 */
	private String creator;
	/**
	 * CREATE_TIME
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * UPDATOR
	 * 最后修改人
	 */
	private String updator;
	/**
	 * UPDATE_TIME
	 * 最后修改时间
	 */
	private Date updateTime;

	/**
	 * 分区ID
	 */
	private Integer partitionId;
	/**
	 * DEPT_CODE
	 * 部门代码
	 */
	private String deptCode;

	/**
	 * POSITION
	 * 职位
	 */
	private String position;

	/**
	 * USER_LEVEL
	 * 用户或客户级别　1代表一级　２代表二级依次类推
	 */
	private Integer userLevel;

	/**
	 * GENDER
	 * 性别
	 */
	private Integer gender;

	/**
	 * BIRTHDAY
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * EMAIL
	 * 邮箱
	 */
	private String email;

	/**
	 * TELEPHONE
	 * 联系电话
	 */
	private String telephone;
	/*************冗余特征信息****************/

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getLegalOrgCode() {
		return legalOrgCode;
	}

	public void setLegalOrgCode(String legalOrgCode) {
		this.legalOrgCode = legalOrgCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCtftype() {
		return ctftype;
	}

	public void setCtftype(String ctftype) {
		this.ctftype = ctftype;
	}

	public String getCtfno() {
		return ctfno;
	}

	public void setCtfno(String ctfno) {
		this.ctfno = ctfno;
	}

	public String getCtfname() {
		return ctfname;
	}

	public void setCtfname(String ctfname) {
		this.ctfname = ctfname;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(Integer partitionId) {
		this.partitionId = partitionId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}	
}
