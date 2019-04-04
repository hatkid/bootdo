package com.bootdo.finance.service;

import com.bootdo.common.domain.DictDO;
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

	DictDO get(Long id);

	List<CompanyFinanceDO> list(Map<String, Object> map);

	int save(CompanyFinanceDO companyFinanceDO);

	int update(DictDO dict);

	int remove(Long id);

	int batchRemove(Long[] ids);

}
