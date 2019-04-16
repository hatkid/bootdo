package com.bootdo.finance.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.finance.domain.SalesReportDO;
import com.bootdo.finance.service.SalesReportService;
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
@RequestMapping("/manage/salesReport")
public class SalesReportController {

	@Autowired
	private SalesReportService salesReportService;

	@GetMapping()
	@RequiresPermissions("salesReport:list")
	String salesReportMain() {
		return "finance/salesreport/list";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("salesReport:list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		Page page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SalesReportDO> salesReportDOList = salesReportService.list(query);
		PageUtils pageUtils = new PageUtils(salesReportDOList, (int)page.getTotal());
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("salesReport:add")
	String add() {
		return "finance/salesreport/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("salesReport:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SalesReportDO salesReportDO = salesReportService.get(id);
		model.addAttribute("salesReportDO", salesReportDO);
		return "finance/salesreport/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("salesReport:add")
	public R save(SalesReportDO salesReportDO) {
		EntityUtil.setCommonInfo(salesReportDO);
		if (salesReportService.save(salesReportDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("salesReport:edit")
	public R update(SalesReportDO salesReportDO) {
		EntityUtil.setCommonInfo(salesReportDO);
		salesReportService.update(salesReportDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("salesReport:remove")
	public R remove(SalesReportDO salesReportDO) {
		EntityUtil.setCommonInfo(salesReportDO);
		if (salesReportService.remove(salesReportDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("salesReport:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		SalesReportDO salesReportDO = new SalesReportDO();
		EntityUtil.setCommonInfo(salesReportDO);
		salesReportService.batchRemove(ids, salesReportDO);
		return R.ok();
	}

	/**
	 * 导出
	 */
	@GetMapping("/download")
	@ResponseBody
	@RequiresPermissions("salesReport:export")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response) {
		String sheetName = "销售表";
		List<String> titleName = new ArrayList<>();
		titleName.add("日期");
		titleName.add("客户名称");
		titleName.add("品名");
		titleName.add("色别");
		titleName.add("件数 套数");
		titleName.add("发货件数");
		titleName.add("合计数量");
		titleName.add("单价");
		titleName.add("合计金额");
		titleName.add("小计");
		titleName.add("备注");

		List<String> keyList = new ArrayList<>();

		keyList.add("timedate");
		keyList.add("customerName");
		keyList.add("teaName");
		keyList.add("color");
		keyList.add("setCount");
		keyList.add("saleCount");
		keyList.add("totalCount");
		keyList.add("price");
		keyList.add("totalPrice");
		keyList.add("subtotal");
		keyList.add("remark");

		List<SalesReportDO> list = salesReportService.list(params);
		ExportExcel.createExcel(sheetName, list, titleName, keyList, response);
	}
}
