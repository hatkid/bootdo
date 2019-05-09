package com.bootdo.finance.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.finance.domain.CompanyFinanceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface CompanyFinanceDao {

	CompanyFinanceDO get(Long id);

	List<CompanyFinanceDO> list(Map<String, Object> map);

	int save(CompanyFinanceDO companyFinanceDO);

	int update(CompanyFinanceDO companyFinanceDO);

	int remove(CompanyFinanceDO companyFinanceDO);

	int batchRemove(@Param("ids") Long[] ids, @Param("companyFinaceDO") CompanyFinanceDO companyFinanceDO);

    int checkExistedCompanyName(CompanyFinanceDO companyFinanceDO);
}
