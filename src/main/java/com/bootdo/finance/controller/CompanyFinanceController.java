package com.bootdo.finance.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.finance.domain.CompanyFinanceDO;
import com.bootdo.finance.service.CompanyFinanceService;
import com.bootdo.util.EntityUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
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
@RequestMapping("/manage/companyFinance")
public class CompanyFinanceController {

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
		CompanyFinanceDO companyFinanceDO = companyFinanceService.get(id);
		model.addAttribute("companyFinanceDO", companyFinanceDO);
		return "finance/companyfinance/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("companyFinance:add")
	public R save(CompanyFinanceDO companyFinanceDO) {
		EntityUtil.setCommonInfo(companyFinanceDO);
		// 判断捕捉公司名字重复的提醒
		try {
			if (companyFinanceService.save(companyFinanceDO) > 0) {
				return R.ok();
			}
		} catch (Exception e) {
			if (e instanceof DuplicateKeyException) {
				return R.error("公司名称重复!");
			}
		}


		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("companyFinance:edit")
	public R update(CompanyFinanceDO companyFinanceDO) {
		EntityUtil.setCommonInfo(companyFinanceDO);
		companyFinanceService.update(companyFinanceDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("companyFinance:remove")
	public R remove(CompanyFinanceDO companyFinanceDO) {
		EntityUtil.setCommonInfo(companyFinanceDO);
		if (companyFinanceService.remove(companyFinanceDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("companyFinance:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		CompanyFinanceDO companyFinanceDO = new CompanyFinanceDO();
		EntityUtil.setCommonInfo(companyFinanceDO);
		companyFinanceService.batchRemove(ids, companyFinanceDO);
		return R.ok();
	}

	/**
	 * 导出
	 */
	@GetMapping("/download")
	@ResponseBody
	@RequiresPermissions("companyFinance:export")
	public R download(@RequestParam Map<String, Object> params, HttpServletResponse response) {
		Query query = new Query(params);
		String sheetName = "采购明细表";
		List<String> titleName = new ArrayList<>();
		titleName.add("公司名字");
		titleName.add("货款金额");
		titleName.add("年初欠款");
		titleName.add("已付款");
		titleName.add("总欠款额");

		List<String> keyList = new ArrayList<>();

		keyList.add("companyName");
		keyList.add("loanAmount");
		keyList.add("arrears");
		keyList.add("paid");
		keyList.add("totalArrears");
		List<CompanyFinanceDO> dictList = companyFinanceService.list(query);
		try {
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode("result.xls", "UTF-8"));
			OutputStream os = response.getOutputStream();
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			// 表头不为null，不为空
			if (titleName != null && titleName.size() > 0 && keyList != null && keyList.size() == titleName.size()) {
				for (int titleIndex = 0; titleIndex < titleName.size(); titleIndex++) {
					HSSFCell cell = row.createCell(titleIndex);
					cell.setCellValue(titleName.get(titleIndex).toString());
					cell.setCellStyle(style);
				}

				// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
				for (int valueIndex = 0; valueIndex < dictList.size(); valueIndex++) {
					row = sheet.createRow(valueIndex + 1);
					Object obj = null;
					Map<String, Object> map = null;
					try {
						obj = dictList.get(valueIndex);
						map = EntityUtil.object2Map(obj);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 第四步，创建单元格，并设置值
					for (int cellIndex = 0; cellIndex < keyList.size(); cellIndex++) {
						Object temp = map.get(keyList.get(cellIndex));
						String result = temp == null ? "" : temp.toString();
						row.createCell(cellIndex).setCellValue(result);
					}

				}
				// 将每个公司的明细都写入到excel中
				/*SupplierDetail supplierDetail = null;
				List<SupplierDetail> supplierDetailsList = null;
				titleName = new ArrayList<>();
				titleName.add("日期");
				titleName.add("公司名称");
				titleName.add("采购名称");
				titleName.add("单位");
				titleName.add("数量");
				titleName.add("单价");
				titleName.add("金额");
				titleName.add("已付款");
				titleName.add("付款方式");
				keyList = new ArrayList<>();
				keyList.add("timedate");
				keyList.add("companyName");
				keyList.add("purchaseName");
				keyList.add("unit");
				keyList.add("amount");
				keyList.add("price");
				keyList.add("total");
				keyList.add("paid");
				keyList.add("payment");
				for (CompanyFinance cf: list) {
					supplierDetail = new SupplierDetail();
					supplierDetail.setCompanyId(cf.getId());
					supplierDetailsList = supplierDetailService.searchByPage(supplierDetail);
					sheetName = cf.getCompanyName();
					// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
					HSSFSheet sheetCompany = wb.createSheet(sheetName);
					// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
					HSSFRow rowCompany = sheetCompany.createRow(0);
					// 第四步，创建单元格，并设置值表头 设置表头居中
					HSSFCellStyle styleCompany = wb.createCellStyle();
					styleCompany.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
					for (int titleIndex = 0; titleIndex < titleName.size(); titleIndex++) {
						HSSFCell cell = rowCompany.createCell(titleIndex);
						cell.setCellValue(titleName.get(titleIndex).toString());
						cell.setCellStyle(styleCompany);
					}

					// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
					for (int valueIndex = 0; valueIndex < supplierDetailsList.size(); valueIndex++) {
						rowCompany = sheetCompany.createRow(valueIndex + 1);
						Object obj = null;
						Map<String, Object> map = null;
						try {
							obj = supplierDetailsList.get(valueIndex);
							map = EntityUtil.object2Map(obj);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 第四步，创建单元格，并设置值
						for (int cellIndex = 0; cellIndex < keyList.size(); cellIndex++) {
							Object temp = map.get(keyList.get(cellIndex));
							String result = temp == null ? "" : temp.toString();
							rowCompany.createCell(cellIndex).setCellValue(result);
						}

					}
				}*/

			} else {
				System.out.println("error");
			}

			// 第六步，将文件存到指定位置
			try {
				wb.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (wb != null) {
					wb.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok();
	}
}
