package com.bootdo.finance.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.finance.domain.SupplierDetailDO;
import com.bootdo.finance.service.SupplierDetailService;
import com.bootdo.util.EntityUtil;
import com.bootdo.util.ExportExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/manage/supplierDetail")
public class SupplierDetailController {

	@Autowired
	private SupplierDetailService supplierDetailService;

	@GetMapping()
	@RequiresPermissions("supplierDetail:list")
	String supplierDetailMain() {
		return "finance/supplierdetail/list";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("supplierDetail:list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		Page page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SupplierDetailDO> supplierDetailDOList = supplierDetailService.list(query);
		PageUtils pageUtils = new PageUtils(supplierDetailDOList, (int)page.getTotal());
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("supplierDetail:add")
	String add() {
		return "finance/supplierdetail/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("supplierDetail:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SupplierDetailDO supplierDetailDO = supplierDetailService.get(id);
		model.addAttribute("supplierDetailDO", supplierDetailDO);
		return "finance/supplierdetail/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("supplierDetail:add")
	public R save(SupplierDetailDO supplierDetailDO) {
		EntityUtil.setCommonInfo(supplierDetailDO);
		if (supplierDetailService.save(supplierDetailDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("supplierDetail:edit")
	public R update(SupplierDetailDO supplierDetailDO) {
		EntityUtil.setCommonInfo(supplierDetailDO);
		supplierDetailService.update(supplierDetailDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("supplierDetail:remove")
	public R remove(SupplierDetailDO supplierDetailDO) {
		EntityUtil.setCommonInfo(supplierDetailDO);
		if (supplierDetailService.remove(supplierDetailDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("supplierDetail:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		SupplierDetailDO supplierDetailDO = new SupplierDetailDO();
		EntityUtil.setCommonInfo(supplierDetailDO);
		supplierDetailService.batchRemove(ids, supplierDetailDO);
		return R.ok();
	}

	/**
	 * 获取供应商名称
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyName")
	public List<SupplierDetailDO> getCompanyName() {
		List<SupplierDetailDO> list = supplierDetailService.getCompanyName();
		return list;
	}

	/**
	 * 导出
	 */
	@GetMapping("/download")
	@ResponseBody
	@RequiresPermissions("supplierDetail:export")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response) {
		String sheetName = "采购明细表";
		List<String> titleName = new ArrayList<>();
		titleName.add("日期");
		titleName.add("公司名称");
		titleName.add("采购名称");
		titleName.add("单位");
		titleName.add("数量");
		titleName.add("单价");
		titleName.add("金额");
		titleName.add("已付款");
		titleName.add("付款方式");

		List<String> keyList = new ArrayList<>();

		keyList.add("timedate");
		keyList.add("companyName");
		keyList.add("purchaseName");
		keyList.add("unit");
		keyList.add("amount");
		keyList.add("price");
		keyList.add("total");
		keyList.add("paid");
		keyList.add("payment");

		List<SupplierDetailDO> list = supplierDetailService.list(params);
		ExportExcel.createExcel(sheetName, list, titleName, keyList, response);
	}
}
