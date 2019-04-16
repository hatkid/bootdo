package com.bootdo.finance.dao;

import com.bootdo.finance.domain.SalesReportDO;
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
public interface SalesReportDao {

	SalesReportDO get(Long id);

	List<SalesReportDO> list(Map<String, Object> map);

	int save(SalesReportDO salesReportDO);

	int update(SalesReportDO salesReportDO);

	int remove(SalesReportDO salesReportDO);

	int batchRemove(@Param("ids") Long[] ids, @Param("salesReportDO") SalesReportDO salesReportDO);
}
