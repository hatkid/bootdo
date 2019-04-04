package com.bootdo.finance.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lvzhi
 * @version 创建时间：2019年3月5日 上午10:40:14
 * @ClassName 类名称
 * @Description 类描述
 */
@Data
public class CommonEntity implements Serializable {

	private static final long serialVersionUID = 4431397413864840169L;

	// 创建者
	private Long row_creater;

	// 编辑者
	private Long row_modifier;

	// 创建时间
	private String row_create_time;

	// 修改时间
	private String row_modify_time;

	// 行状态
	private Integer row_state;
}
