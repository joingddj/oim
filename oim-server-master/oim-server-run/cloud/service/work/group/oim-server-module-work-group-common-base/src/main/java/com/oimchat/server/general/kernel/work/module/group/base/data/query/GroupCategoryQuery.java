package com.oimchat.server.general.kernel.work.module.group.base.data.query;

import java.util.List;

import com.onlyxiahui.common.action.description.annotation.DocTitle;
import com.onlyxiahui.extend.query.hibernate.syntax.annotation.Condition;
import com.onlyxiahui.extend.query.hibernate.syntax.annotation.OrderBy;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */

@OrderBy(value = { "updatedTimestamp" }, sort = "desc")
public class GroupCategoryQuery {

	@Condition(equation = "in", column = "id")
	private List<String> ids;

	@DocTitle("查询更新时间开始时间戳")
	@Condition(equation = ">=", column = "updatedTimestamp")
	private Long startUpdatedTimestamp;

	@DocTitle("查询更新时间结束时间戳")
	@Condition(equation = "<=", column = "updatedTimestamp")
	private Long stopUpdatedTimestamp;

	@DocTitle("查询创建时间开始时间戳")
	@Condition(equation = ">=", column = "createdTimestamp")
	private Long startCreatedTimestamp;

	@DocTitle("查询创建时间结束时间戳")
	@Condition(equation = "<=", column = "createdTimestamp")
	private Long stopCreatedTimestamp;

	/**
	 * 主键id
	 */
	@DocTitle("主键id")
	private String id;

	/**
	 * 创建时间（格式：2019-01-01 00:00:00）
	 */
	@DocTitle("创建时间（格式：2019-01-01 00:00:00）")
	private String createdDateTime;

	/**
	 * 创建时间戳（毫秒）
	 */
	@DocTitle("创建时间戳（毫秒）")
	private Long createdTimestamp;

	/**
	 * 是否逻辑删除：0：否、1：是
	 */
	@DocTitle("是否逻辑删除：0：否、1：是")
	private Integer isDeleted;

	/**
	 * 修改时间（格式：2019-01-01 00:00:00）
	 */
	@DocTitle("修改时间（格式：2019-01-01 00:00:00）")
	private String updatedDateTime;

	/**
	 * 修改时间戳（毫秒）
	 */
	@DocTitle("修改时间戳（毫秒）")
	private Long updatedTimestamp;

	/**
	 * 分组名字
	 */
	@DocTitle("分组名字")
	private String name;

	/**
	 * 排序
	 */
	@DocTitle("排序")
	private Integer sort;

	/**
	 * 类型：1、系统默认生成的 2、用户自己新增的
	 */
	@DocTitle("类型：1、系统默认生成的 2、用户自己新增的")
	private Integer type;

	/**
	 * 所属用户id
	 */
	@DocTitle("所属用户id")
	private String userId;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	public Long getStartUpdatedTimestamp() {
		return startUpdatedTimestamp;
	}

	public void setStartUpdatedTimestamp(Long startUpdatedTimestamp) {
		this.startUpdatedTimestamp = startUpdatedTimestamp;
	}

	public Long getStopUpdatedTimestamp() {
		return stopUpdatedTimestamp;
	}

	public void setStopUpdatedTimestamp(Long endUpdatedTimestamp) {
		this.stopUpdatedTimestamp = endUpdatedTimestamp;
	}

	public Long getStartCreatedTimestamp() {
		return startCreatedTimestamp;
	}

	public void setStartCreatedTimestamp(Long startCreatedTimestamp) {
		this.startCreatedTimestamp = startCreatedTimestamp;
	}

	public Long getStopCreatedTimestamp() {
		return stopCreatedTimestamp;
	}

	public void setStopCreatedTimestamp(Long endCreatedTimestamp) {
		this.stopCreatedTimestamp = endCreatedTimestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Long getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public Long getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Long updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}