package com.employee.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.employee.entity.Employee;

public class ExcelHelper {

	// # check that file is of excel type or not #//
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// # convert excel to list of Employees #//

	public static List<Employee> convertExcelToListOfEmployee(InputStream is) {
		List<Employee> list = new ArrayList<>();
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.getSheet("data");

			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cid = 0;
				Employee emp = new Employee();
				emp.setEmptId(null);
				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cid == 0) {
						System.out.println(cell.getDateCellValue());
						Date date = cell.getDateCellValue();
						// Create a Calendar instance and set the date
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						// Extract the year
						int year = calendar.get(Calendar.YEAR);
						System.out.println("Year: " + year);
						if (year <= 2023) {
							break;
						}
					}
					switch (cid) {
					case 0:
						emp.setEmploymentDate(cell.getDateCellValue());
						break;
					case 1:
						emp.setEmptId((int) cell.getNumericCellValue());

						break;
					case 2:
						emp.setEmpName(cell.getStringCellValue());

						break;
					case 3:
						emp.setEmpDepartment(cell.getStringCellValue());
						break;
					case 4:
						emp.setEmpStatus(cell.getStringCellValue());
						break;
					default:
						break;
					}
					cid++;

				}
				if (!isNullOrEmpty(emp) && emp.getEmpDepartment().equals("Digital"))
					list.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public static boolean isNullOrEmpty(Employee emp) {
		return emp == null || (emp.getEmpDepartment() == null && emp.getEmploymentDate() == null
				&& emp.getEmpName() == null && emp.getEmptId() == null);
	}

}
