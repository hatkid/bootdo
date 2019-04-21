package com.bootdo.finance.service;

import com.bootdo.finance.domain.SupplierDetailDO;

import java.util.List;
import java.util.Map;

/**
 * @文件名：CompanyFinanceService.java
 * @作者：lvzhi
 * @版本号：1.0
 * @生成日期：2019-4-3
 * @功能描述：
 */
public interface SupplierDetailService {

	SupplierDetailDO get(Long id);

	List<SupplierDetailDO> list(Map<String, Object> map);

	int save(SupplierDetailDO supplierDetailDO);

	int update(SupplierDetailDO supplierDetailDO);

	int remove(SupplierDetailDO supplierDetailDO);

	int batchRemove(Long[] ids, SupplierDetailDO supplierDetailDO);

    List<SupplierDetailDO> getCompanyName();
}
