package com.bootdo.finance.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.finance.domain.RunningAccountDO;
import com.bootdo.finance.service.RunningAccountService;
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
@RequestMapping("/manage/runningAccount")
public class RunningAccountController {

	@Autowired
	private RunningAccountService runningAccountService;

	@GetMapping()
	@RequiresPermissions("runningAccount:list")
	String supplierDetailMain() {
		return "finance/runningaccount/list";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("runningAccount:list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		Page page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<RunningAccountDO> runningAccountDOList = runningAccountService.list(query);
		PageUtils pageUtils = new PageUtils(runningAccountDOList, (int)page.getTotal());
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("runningAccount:add")
	String add() {
		return "finance/runningaccount/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("runningAccount:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		RunningAccountDO runningAccountDO = runningAccountService.get(id);
		model.addAttribute("runningAccountDO", runningAccountDO);
		return "finance/runningaccount/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("runningAccount:add")
	public R save(RunningAccountDO runningAccountDO) {
		EntityUtil.setCommonInfo(runningAccountDO);
		if (runningAccountService.save(runningAccountDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("runningAccount:edit")
	public R update(RunningAccountDO runningAccountDO) {
		EntityUtil.setCommonInfo(runningAccountDO);
		runningAccountService.update(runningAccountDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("runningAccount:remove")
	public R remove(RunningAccountDO runningAccountDO) {
		EntityUtil.setCommonInfo(runningAccountDO);
		if (runningAccountService.remove(runningAccountDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("runningAccount:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		RunningAccountDO runningAccountDO = new RunningAccountDO();
		EntityUtil.setCommonInfo(runningAccountDO);
		runningAccountService.batchRemove(ids, runningAccountDO);
		return R.ok();
	}

	/**
	 * 导出
	 */
	@GetMapping("/download")
	@ResponseBody
	@RequiresPermissions("runningAccount:export")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response) {
		String sheetName = "流水表";
		List<String> titleName = new ArrayList<>();
		titleName.add("日期");
		titleName.add("费用属性");
		titleName.add("属性类别");
		titleName.add("单位名称");
		titleName.add("项目");
		titleName.add("摘要");
		titleName.add("入账(借)");
		titleName.add("出账(贷)");
		titleName.add("支付方式");
		titleName.add("收款人");
		titleName.add("备注");

		List<String> keyList = new ArrayList<>();

		keyList.add("timedate");
		keyList.add("costAttribute");
		keyList.add("costAttributeType");
		keyList.add("companyName");
		keyList.add("projectName");
		keyList.add("abstractContent");
		keyList.add("entrys");
		keyList.add("outs");
		keyList.add("payment");
		keyList.add("payee");
		keyList.add("remark");

		List<RunningAccountDO> list = runningAccountService.list(params);
		ExportExcel.createExcel(sheetName, list, titleName, keyList, response);
	}
}
