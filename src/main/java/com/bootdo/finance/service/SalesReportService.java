package com.bootdo.finance.service;

import com.bootdo.finance.domain.SalesReportDO;

import java.util.List;
import java.util.Map;

/**
 * @文件名：CompanyFinanceService.java
 * @作者：lvzhi
 * @版本号：1.0
 * @生成日期：2019-4-3
 * @功能描述：
 */
public interface SalesReportService {

	SalesReportDO get(Long id);

	List<SalesReportDO> list(Map<String, Object> map);

	int save(SalesReportDO salesReportDO);

	int update(SalesReportDO salesReportDO);

	int remove(SalesReportDO salesReportDO);

	int batchRemove(Long[] ids, SalesReportDO salesReportDO);
}
