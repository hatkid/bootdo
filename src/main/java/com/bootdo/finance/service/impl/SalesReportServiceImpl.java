package com.bootdo.finance.service.impl;
import com.bootdo.finance.dao.SalesReportDao;
import com.bootdo.finance.domain.SalesReportDO;
import com.bootdo.finance.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalesReportServiceImpl implements SalesReportService {

    @Autowired
    private SalesReportDao salesReportDao;

    @Override
    public SalesReportDO get(Long id) {
        return salesReportDao.get(id);
    }

    @Override
    public List<SalesReportDO> list(Map<String, Object> map) {
        return salesReportDao.list(map);
    }

    @Override
    public int save(SalesReportDO salesReportDO) {
        return salesReportDao.save(salesReportDO);
    }

    @Override
    public int update(SalesReportDO salesReportDO) {
        return salesReportDao.update(salesReportDO);
    }

    @Override
    public int remove(SalesReportDO salesReportDO) {
        return salesReportDao.remove(salesReportDO);
    }

    @Override
    public int batchRemove(Long[] ids, SalesReportDO salesReportDO) {
        return salesReportDao.batchRemove(ids,salesReportDO);
    }
}
