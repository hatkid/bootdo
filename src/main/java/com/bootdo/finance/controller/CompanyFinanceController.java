package com.bootdo.finance.controller;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.finance.domain.CompanyFinanceDO;
import com.bootdo.finance.service.CompanyFinanceService;
import com.bootdo.util.EntityUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/manage/companyFinance")
public class CompanyFinanceController {
	@Autowired
	private DictService dictService;

	@Autowired
	private CompanyFinanceService companyFinanceService;

	@GetMapping()
	@RequiresPermissions("companyFinance:list")
	String companyFinanceMain() {
		return "finance/companyfinance/list";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("companyFinance:list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		Page page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<CompanyFinanceDO> dictList = companyFinanceService.list(query);
		PageUtils pageUtils = new PageUtils(dictList, (int)page.getTotal());
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("companyFinance:add")
	String add() {
		return "finance/companyfinance/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("companyFinance:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		DictDO dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("companyFinance:add")
	public R save(CompanyFinanceDO companyFinanceDO) {
		EntityUtil.setCommonInfo(companyFinanceDO);
		if (companyFinanceService.save(companyFinanceDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("companyFinance:edit")
	public R update(DictDO dict) {
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("companyFinance:remove")
	public R remove(Long id) {
		if (dictService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("companyFinance:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		dictService.batchRemove(ids);
		return R.ok();
	}
}
