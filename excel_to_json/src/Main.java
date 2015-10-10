
import jxl.*; 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Main.export_excel("lng.xls", "_or_str.json");
	}
	
	public static void export_excel(String excelName, String jsonNamePrefix) {

		Sheet sheet;
		Workbook book;
		try {
			
			WorkbookSettings setting=new WorkbookSettings();  
			setting.setEncoding("iso-8859-1");
			
			Cell cell_tmp;
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File(excelName), setting);

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			

	        int nRows = sheet.getRows();
	        int nCols = sheet.getColumns();

	        int nHeadLine = 1;
	        for(int i = 3; i < nCols;i++)
	        {
				Cell cell_lng = sheet.getCell(i, nHeadLine);
				if(cell_lng != null)
				{
					String strLng = cell_lng.getContents();
					if(!"".equals(strLng))
					{
						Map jsonMapData = new HashMap();
						Map jsonHoldData = null;
						String arrKey = "";
						List<String> arrData = null;
						String holdname = "";
						for(int nLine = nHeadLine+1; nLine< nRows;nLine++)
						{

							Cell cell_hold = sheet.getCell(0, nLine);
							if(!"".equals(cell_hold.getContents() ) )
							{
								if(jsonHoldData != null)
								{
									if(arrData != null)
									{
										jsonHoldData.put(arrKey, arrData);
										arrData = null;
									}
									System.out.println(holdname+":"+jsonHoldData.toString());
									jsonMapData.put(holdname, jsonHoldData);
									jsonHoldData = null;
								}
								
								holdname = cell_hold.getContents();
								jsonHoldData = new HashMap();
								arrData = null;
							}
							

							Cell cell_key = sheet.getCell(1, nLine);
							Cell cell_v = sheet.getCell(i, nLine);

							String key = cell_key.getContents();
							String v = cell_v.getContents();
							
							if(!"".equals(key))
							{
							if(key.indexOf(":") > 0)
							{
								if(key.startsWith("$a:"))
								{
									arrKey = key.substring(key.indexOf(":")+1);
									if(arrData == null)
									{
										arrData = new ArrayList<String>();
									}
									arrData.add(v);
								}
							}else
							{
								if(arrData != null)
								{
									jsonHoldData.put(arrKey, arrData);
									arrData = null;
								}
								System.out.println(key+":"+v);
								jsonHoldData.put(key, v);
							}
							}
						}

						if(jsonHoldData != null)
						{
							jsonMapData.put(holdname, jsonHoldData);
							System.out.println(holdname+":"+jsonHoldData);
							jsonHoldData = null;
						}
						
						String jsonFileOut = "lng_"+strLng+jsonNamePrefix;
						FileUtils.writeFile(jsonFileOut, JSONValue.toJSONString(jsonMapData));
					}

				}
	        }

	        book.close(); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
