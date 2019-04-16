package com.bootdo.finance.dao;

import com.bootdo.finance.domain.SupplierDetailDO;
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
public interface SupplierDetailDao {

	SupplierDetailDO get(Long id);

	List<SupplierDetailDO> list(Map<String, Object> map);

	int save(SupplierDetailDO supplierDetailDO);

	int update(SupplierDetailDO supplierDetailDO);

	int remove(SupplierDetailDO supplierDetailDO);

	int batchRemove(@Param("ids") Long[] ids, @Param("supplierDetailDO") SupplierDetailDO supplierDetailDO);

    List<SupplierDetailDO> getCompanyName();
}
