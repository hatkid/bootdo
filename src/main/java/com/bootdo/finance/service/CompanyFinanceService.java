package com.bootdo.finance.service;

import com.bootdo.finance.domain.CompanyFinanceDO;

import java.util.List;
import java.util.Map;

/**
 * @文件名：CompanyFinanceService.java
 * @作者：lvzhi
 * @版本号：1.0
 * @生成日期：2019-4-3
 * @功能描述：
 */
public interface CompanyFinanceService {

	CompanyFinanceDO get(Long id);

	List<CompanyFinanceDO> list(Map<String, Object> map);

	int save(CompanyFinanceDO companyFinanceDO);

	int update(CompanyFinanceDO companyFinanceDO);

	int remove(CompanyFinanceDO companyFinanceDO);

	int batchRemove(Long[] ids, CompanyFinanceDO companyFinanceDO);

    int checkExistedCompanyName(CompanyFinanceDO companyFinanceDO);
}
