import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class EcxcelOperation {

	/**
	 * 方法简述：导入Excel文件到数据库
	 * 
	 * @return 时间：2016-11-22 作者：刘群
	 */
	public static List<Contract> importExcel() {

		List<Contract> result = new ArrayList<Contract>();
		jxl.Workbook readwb = null;
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook
			InputStream instream = new FileInputStream("test.xlsx");
			readwb = Workbook.getWorkbook(instream);
			// Sheet的下标是从0开始

			// 获取第一张Sheet表
			Sheet readsheet = readwb.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int rsColumns = readsheet.getColumns();
			Map<String, String> map = XMLReader.loadconfig();
			Map<Integer, String> fieldOrder = new HashMap<Integer, String>();
			// 将上传的excel的title与java中的字段对应起来
			Contract contract = new Contract();
			for (int i = 0; i < rsColumns; i++) {
				Cell cell = readsheet.getCell(i, 0);
				if (map.get(cell.getContents()) != null) {
					fieldOrder.put(i, map.get(cell.getContents()));
				}
			}
			// 获取Sheet表中所包含的总行数
			int rsRows = readsheet.getRows();
			// 获取指定单元格的对象引用
			for (int i = 1; i < rsRows; i++) {
				Contract contract_item = new Contract();
				for (int j = 0; j < rsColumns; j++) {
					Class class1 = (Class) contract_item.getClass();
					Field f1 = class1.getDeclaredField(fieldOrder.get(j));
					f1.setAccessible(true);
					f1.set(contract_item, readsheet.getCell(j, i).getContents());
				}
				result.add(contract_item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
		return result;
	}

	/**
	 * 方法简述：从数据库导出Excel文件
	 * 
	 * @param os
	 *            文件输出流
	 * @param Contact
	 *            合同对象数组
	 * @return 时间：2016-11-22 作者：刘群
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static boolean exportExcel(OutputStream os, Contract[] contracts)
			throws IOException, RowsExceededException, WriteException {
		// 打开模板文件:数据汇总表模板.xlsx,复制一个模板，然后追加数据

		// 将模板对应的字段写入excel
		// 关闭

		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label ID = new Label(0, 0, "流程编号");

		sheet.addCell(ID);
		Label contractName = new Label(1, 0, "合同名称");
		sheet.addCell(contractName);
		Label partyA = new Label(2, 0, "合同甲方");
		sheet.addCell(partyA);
		Label partyB = new Label(4, 0, "合同乙方");
		sheet.addCell(partyB);
		Label contractType = new Label(4, 0, "合同类型");
		sheet.addCell(contractType);
		int r = 1;
		for (Contract contract : contracts) {
			int c = 0;
			ID = new Label(0, r, contract.getID());
			contractName = new Label(1, r, contract.getContractName());
			partyA = new Label(2, r, contract.getPartyA());
			partyB = new Label(3, r, contract.getPartyB());
			contractType = new Label(4, r, contract.getContractType());

			sheet.addCell(ID);
			sheet.addCell(contractName);
			sheet.addCell(partyA);
			sheet.addCell(partyB);
			sheet.addCell(contractType);
		}
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();

		return true;
	}

	public static void main(String[] args) throws RowsExceededException,
			WriteException {
		/*
		 * try { FileOutputStream fo = new FileOutputStream("test.xlsx");
		 * exportExcel(fo, null);
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */List<Contract> list = importExcel();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}

	}
}
