package com.bootdo.finance.dao;

import com.bootdo.finance.domain.RunningAccountDO;
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
public interface RunningAccountDao {

	RunningAccountDO get(Long id);

	List<RunningAccountDO> list(Map<String, Object> map);

	int save(RunningAccountDO runningAccountDO);

	int update(RunningAccountDO runningAccountDO);

	int remove(RunningAccountDO runningAccountDO);

	int batchRemove(@Param("ids") Long[] ids, @Param("runningAccountDO") RunningAccountDO runningAccountDO);
}
