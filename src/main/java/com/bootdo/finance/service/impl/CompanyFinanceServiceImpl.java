package com.bootdo.finance.service.impl;
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
    private CompanyFinanceDao companyFinanceDao;

    @Override
    public CompanyFinanceDO get(Long id) {
        return companyFinanceDao.get(id);
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
    public int update(CompanyFinanceDO companyFinanceDO) {
        return companyFinanceDao.update(companyFinanceDO);
    }

    @Override
    public int remove(CompanyFinanceDO companyFinanceDO) {
        return companyFinanceDao.remove(companyFinanceDO);
    }

    @Override
    public int batchRemove(Long[] ids, CompanyFinanceDO companyFinanceDO) {
        return companyFinanceDao.batchRemove(ids,companyFinanceDO);
    }
}
