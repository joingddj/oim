package com.oimchat.client.general.kernel.work.module.account.data.dto;

import java.util.Date;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;
import com.onlyxiahui.common.action.description.annotation.DocTitle;

/**
 * 
 * 用户-基本信息<br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
public class UserRegister extends PrimaryStringEntity {

	/**
	 * 用户数字账号
	 */
	@DocTitle("用户数字账号")
	private Long number;

	/**
	 * 帐号
	 */
	@DocTitle("帐号")
	private String account;

	/**
	 * 手机号码
	 */
	@DocTitle("手机号码")
	private String mobile;

	/**
	 * 电子邮箱
	 */
	@DocTitle("电子邮箱")
	private String email;

	/**
	 * 密码
	 */
	@DocTitle("密码")
	private String password;

	/**
	 * 系统头像编号
	 */
	@DocTitle("系统头像编号")
	private String head;

	/**
	 * 自定义照片，（当用户选择系统头像时为空）
	 */
	@DocTitle("自定义照片，（当用户选择系统头像时为空）")
	private String avatar;

	/**
	 * 昵称
	 */
	@DocTitle("昵称")
	private String nickname;

	/**
	 * 个性签名
	 */
	@DocTitle("个性签名")
	private String signature;

	/**
	 * 出生日期
	 */
	@DocTitle("出生日期")
	private Date birthDate;

	/**
	 * 性别 1:男 2：女 3：保密
	 */
	@DocTitle("性别 1:男 2：女 3：保密")
	private String gender;

	/**
	 * 年龄
	 */
	@DocTitle("年龄")
	private Integer age;

	/**
	 * qq号码
	 */
	@DocTitle("qq号码")
	private String qq;

	/**
	 * 真实姓名
	 */
	@DocTitle("真实姓名")
	private String name;

	/**
	 * 所在地国家编码
	 */
	@DocTitle("所在地国家编码")
	private String locationCountryCode;

	/**
	 * 所在地州省编码
	 */
	@DocTitle("所在地州省编码")
	private String locationProvinceCode;

	/**
	 * 所在地城市编码
	 */
	@DocTitle("所在地城市编码")
	private String locationCityCode;

	/**
	 * 所在详细地址
	 */
	@DocTitle("所在详细地址")
	private String locationAddress;

	/**
	 * 所在地邮政编码
	 */
	@DocTitle("所在地邮政编码")
	private String locationZipCode;

	/**
	 * 家庭详细地址
	 */
	@DocTitle("家庭详细地址")
	private String homeAddress;

	/**
	 * 学历
	 */
	@DocTitle("学历")
	private String education;

	/**
	 * 毕业时间
	 */
	@DocTitle("毕业时间")
	private String graduationDate;

	/**
	 * 血型
	 */
	@DocTitle("血型")
	private String blood;

	/**
	 * 星座
	 */
	@DocTitle("星座")
	private String constellationCode;

	/**
	 * 家庭地邮政编码
	 */
	@DocTitle("家庭地邮政编码")
	private String homeZipCode;

	/**
	 * 身份证号码
	 */
	@DocTitle("身份证号码")
	private String identityCard;

	/**
	 * 介绍
	 */
	@DocTitle("介绍")
	private String introduce;

	/**
	 * 政治面貌
	 */
	@DocTitle("政治面貌")
	private String politicsStatus;

	/**
	 * 民族
	 */
	@DocTitle("民族")
	private String nationName;

	/**
	 * 婚姻状况: 1:未婚 2:已婚
	 */
	@DocTitle("婚姻状况: 1:未婚 2:已婚")
	private String maritalStatus;

	/**
	 * 籍贯
	 */
	@DocTitle("籍贯")
	private String nativePlace;

	/**
	 * 专业
	 */
	@DocTitle("专业")
	private String professional;

	/**
	 * 备注
	 */
	@DocTitle("备注")
	private String remark;

	/**
	 * 毕业学校
	 */
	@DocTitle("毕业学校")
	private String school;

	/**
	 * 姓名的简拼
	 */
	@DocTitle("姓名的简拼")
	private String spellSimple;

	/**
	 * 姓名的拼写
	 */
	@DocTitle("姓名的拼写")
	private String spellFull;

	/**
	 * 联系电话（固定电话）
	 */
	@DocTitle("联系电话（固定电话）")
	private String telephone;

	/**
	 * 参加工作时间
	 */
	@DocTitle("参加工作时间")
	private String workDate;

	/**
	 * 注销时间
	 */
	@DocTitle("注销时间")
	private Long canceledTimestamp;

	/**
	 * 是否禁用 0：启用 1：停用
	 */
	@DocTitle("是否禁用 0：启用 1：停用")
	private Integer isDisable;

	/**
	 * 用户类型 0:普通用户 1：管理员用户 2:超级管理员
	 */
	@DocTitle("用户类型 0:普通用户 1：管理员用户 2:超级管理员")
	private String type;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocationCountryCode() {
		return locationCountryCode;
	}

	public void setLocationCountryCode(String locationCountryCode) {
		this.locationCountryCode = locationCountryCode;
	}

	public String getLocationProvinceCode() {
		return locationProvinceCode;
	}

	public void setLocationProvinceCode(String locationProvinceCode) {
		this.locationProvinceCode = locationProvinceCode;
	}

	public String getLocationCityCode() {
		return locationCityCode;
	}

	public void setLocationCityCode(String locationCityCode) {
		this.locationCityCode = locationCityCode;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationZipCode() {
		return locationZipCode;
	}

	public void setLocationZipCode(String locationZipCode) {
		this.locationZipCode = locationZipCode;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getConstellationCode() {
		return constellationCode;
	}

	public void setConstellationCode(String constellationCode) {
		this.constellationCode = constellationCode;
	}

	public String getHomeZipCode() {
		return homeZipCode;
	}

	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSpellSimple() {
		return spellSimple;
	}

	public void setSpellSimple(String spellSimple) {
		this.spellSimple = spellSimple;
	}

	public String getSpellFull() {
		return spellFull;
	}

	public void setSpellFull(String spellFull) {
		this.spellFull = spellFull;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Long getCanceledTimestamp() {
		return canceledTimestamp;
	}

	public void setCanceledTimestamp(Long canceledTimestamp) {
		this.canceledTimestamp = canceledTimestamp;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}