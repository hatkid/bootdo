package com.bootdo.finance.service.impl;
import com.bootdo.finance.dao.RunningAccountDao;
import com.bootdo.finance.domain.RunningAccountDO;
import com.bootdo.finance.service.RunningAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RunningAccountServiceImpl implements RunningAccountService {

    @Autowired
    private RunningAccountDao runningAccountDao;

    @Override
    public RunningAccountDO get(Long id) {
        return runningAccountDao.get(id);
    }

    @Override
    public List<RunningAccountDO> list(Map<String, Object> map) {
        return runningAccountDao.list(map);
    }

    @Override
    public int save(RunningAccountDO runningAccountDO) {
        return runningAccountDao.save(runningAccountDO);
    }

    @Override
    public int update(RunningAccountDO runningAccountDO) {
        return runningAccountDao.update(runningAccountDO);
    }

    @Override
    public int remove(RunningAccountDO runningAccountDO) {
        return runningAccountDao.remove(runningAccountDO);
    }

    @Override
    public int batchRemove(Long[] ids, RunningAccountDO runningAccountDO) {
        return runningAccountDao.batchRemove(ids,runningAccountDO);
    }
}
