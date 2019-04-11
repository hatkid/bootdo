package com.bootdo.finance.service.impl;

import com.bootdo.common.dao.DictDao;
import com.bootdo.common.domain.DictDO;
import com.bootdo.finance.dao.CompanyFinanceDao;
import com.bootdo.finance.domain.CompanyFinanceDO;
import com.bootdo.finance.service.CompanyFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CompanyFinanceServiceImpl implements CompanyFinanceService {
    @Autowired
    private DictDao dictDao;

    @Autowired
    private CompanyFinanceDao companyFinanceDao;

    @Override
    public DictDO get(Long id) {
        return dictDao.get(id);
    }

    @Override
    public List<CompanyFinanceDO> list(Map<String, Object> map) {
        return companyFinanceDao.list(map);
    }

    @Override
    public int save(CompanyFinanceDO companyFinanceDO) {
        return companyFinanceDao.save(companyFinanceDO);
    }

    @Override
    public int update(DictDO dict) {
        return dictDao.update(dict);
    }

    @Override
    public int remove(Long id) {
        return companyFinanceDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return companyFinanceDao.batchRemove(ids);
    }
}
