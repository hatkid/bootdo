package com.bootdo.finance.service;

import com.bootdo.finance.domain.RunningAccountDO;

import java.util.List;
import java.util.Map;

/**
 * @文件名：CompanyFinanceService.java
 * @作者：lvzhi
 * @版本号：1.0
 * @生成日期：2019-4-3
 * @功能描述：
 */
public interface RunningAccountService {

	RunningAccountDO get(Long id);

	List<RunningAccountDO> list(Map<String, Object> map);

	int save(RunningAccountDO runningAccountDO);

	int update(RunningAccountDO runningAccountDO);

	int remove(RunningAccountDO runningAccountDO);

	int batchRemove(Long[] ids, RunningAccountDO runningAccountDO);
}
