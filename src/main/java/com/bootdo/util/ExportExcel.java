package com.bootdo.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

	public static void createExcel(List list, List<String> titleName, List<String> keyList,
			HttpServletResponse response) {
		createExcel("sheet", list, titleName, keyList, response);
	}

	public static void createExcel(String sheetName, List list, List<String> titleName,
			List<String> keyList, HttpServletResponse response) {
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
				for (int valueIndex = 0; valueIndex < list.size(); valueIndex++) {
					row = sheet.createRow(valueIndex + 1);
					Object obj = null;
					Map<String, Object> map = null;
					try {
						obj = list.get(valueIndex);
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

			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
