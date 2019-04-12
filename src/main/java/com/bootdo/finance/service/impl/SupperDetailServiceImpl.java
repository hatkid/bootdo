package com.bootdo.finance.service.impl;
import com.bootdo.finance.dao.SupplierDetailDao;
import com.bootdo.finance.domain.SupplierDetailDO;
import com.bootdo.finance.service.SupplierDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SupperDetailServiceImpl implements SupplierDetailService {

    @Autowired
    private SupplierDetailDao supplierDetailDao;

    @Override
    public SupplierDetailDO get(Long id) {
        return supplierDetailDao.get(id);
    }

    @Override
    public List<SupplierDetailDO> list(Map<String, Object> map) {
        return supplierDetailDao.list(map);
    }

    @Override
    public int save(SupplierDetailDO supplierDetailDO) {
        return supplierDetailDao.save(supplierDetailDO);
    }

    @Override
    public int update(SupplierDetailDO supplierDetailDO) {
        return supplierDetailDao.update(supplierDetailDO);
    }

    @Override
    public int remove(SupplierDetailDO supplierDetailDO) {
        return supplierDetailDao.remove(supplierDetailDO);
    }

    @Override
    public int batchRemove(Long[] ids, SupplierDetailDO supplierDetailDOO) {
        return supplierDetailDao.batchRemove(ids,supplierDetailDOO);
    }
}
