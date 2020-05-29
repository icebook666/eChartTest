package usb.department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import java.text.*;

/**
 * 處理部門成員USB寫入統計之看板內容
 * @author 10008433
 */
public class DepartmentUSBkanban {
	public Object result;

	/**
	 * 回傳ID所屬部門成員多組options中的屬性內容[mainTitle / subTitle / updateTime / legendData / categoryData / selectedFlag]
	 * @param leader_id 主管工號
	 * @param classify 查詢類型(amount/size)
	 * @return
	 */
	public Object dataJsonByDepartmentMember(String leader_id, String classify) {
		try {
			JSONObject myjson = new JSONObject();
			
			//TODO 查出主管下一階成員工號
			
			myjson.accumulate("options", genUSBSummaryByMonthOption("10001001", classify));
			myjson.accumulate("options", genUSBSummaryByMonthOption("10001002", classify));
			myjson.accumulate("options", genUSBSummaryByMonthOption("10001003", classify));
			myjson.accumulate("options", genUSBSummaryByMonthOption("10001004", classify));
			myjson.accumulate("options", genUSBSummaryByMonthOption("10001005", classify));
			
			result = myjson;
			
		} catch (java.lang.Exception ex) {
			System.out.print(ex.getMessage());
		} finally {
			
		}
		return result;
	}
	
	/**
	 * 取得指定人員三個月內寫入USB合計檔案數量/大小option屬性 JSON
	 * @param u_id 指定人員工號
	 * @param classify 查詢類型(amount/size)
	 * @return
	 */
	private Object genUSBSummaryByMonthOption(String u_id, String classify)
	{
		JSONObject myjson = new JSONObject();
		ArrayList arr_mainTitle = new ArrayList(); // 主標題
		ArrayList arr_subTitle = new ArrayList(); // 副標題
		ArrayList arr_updateTime = new ArrayList(); // 顯示時分秒
		ArrayList arr_legendData = new ArrayList(); // 圖例資料
		ArrayList arr_CategoryData = new ArrayList(); // 分群資料
		JSONObject no_selectedJO = new JSONObject(); // 標記不顯示圖例
		HashMap mapClassify = new HashMap();
		String [] myClassify = {"Office","Other"};
		
		Date date = new Date();
		String today_date = new SimpleDateFormat("HH:mm:ss").format(date).toString();
		arr_updateTime.add(today_date);
		arr_subTitle.add(today_date); // 副標題
		
		for (String stat : myClassify) {
			arr_legendData.add(stat);
		}
		
		//標記不顯示圖例為false
		no_selectedJO.put("Office", true);
		no_selectedJO.put("Other", true);
		//合計量
		arr_legendData.add("Total");
		
		myjson.put("seriesData", "[]");
		List<UsbVO> resultVO = new ArrayList<UsbVO>();
		//TODO 查詢SQL
		String sql_output = "";
		
		try {
			
			if (classify.equals("amount"))
			{
				arr_mainTitle.add(u_id + ", File#"); // 主標題
				
				//數量加總(按每月筆數)
				resultVO.add(0, new UsbVO("2018/1","10","Office"));
				resultVO.add(1, new UsbVO("2018/2","20","Office"));
				resultVO.add(2, new UsbVO("2018/3","30","Office"));
				resultVO.add(3, new UsbVO("2018/3","50","Other"));
			}
			else
			{
				arr_mainTitle.add(u_id + ", File Size"); // 主標題
				
				//檔案大小加總(按每月筆數)
				resultVO.add(0, new UsbVO("2018/1","1000","Office"));
				resultVO.add(1, new UsbVO("2018/3","3000","Office"));
				resultVO.add(2, new UsbVO("2018/2","5500","Other"));
				resultVO.add(3, new UsbVO("2018/3","2500","Other"));
			}
			
			for (UsbVO usbVO : resultVO) {
				if (!arr_CategoryData.contains(usbVO.getGroup_date()))
				{
					arr_CategoryData.add(usbVO.getGroup_date());	
				}
				mapClassify.put(usbVO.getGroup_date()+usbVO.getClassify(), usbVO.getAmount());
			}
			
			for (String stat : myClassify) {
				ArrayList arr_time_data = new ArrayList();
				for (Object category : arr_CategoryData) {
					if (mapClassify.get(category.toString()+stat)!= null)
						arr_time_data.add(mapClassify.get(category.toString()+stat).toString());
					else
						arr_time_data.add("0");
				}
				myjson.accumulate("seriesData", arr_time_data);
			}
			
			ArrayList arr_acc_data = new ArrayList();
			//計算合計量
			for (Object category : arr_CategoryData) {
				int amount = 0;	
				for (String stat : myClassify) {
					if (mapClassify.get(category.toString()+stat)!= null)
						amount += Integer.valueOf(mapClassify.get(category.toString()+stat).toString());
				}
				arr_acc_data.add(String.valueOf(amount));
			}
			myjson.accumulate("seriesData", arr_acc_data);
			
			myjson.put("mainTitle", arr_mainTitle);
			myjson.put("subTitle", arr_subTitle);
			myjson.put("updateTime", arr_updateTime);
			myjson.put("legendData", arr_legendData);
			myjson.put("categoryData", arr_CategoryData);
			myjson.put("selectedFlag", no_selectedJO.toString());
			
			result = myjson;
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		} finally {
			
		}
		return result;
	}
	
	public static void main(String[] args){
		DepartmentUSBkanban usb = new DepartmentUSBkanban();
		String returnValue = usb.dataJsonByDepartmentMember("10008433","amount").toString();
		System.out.println(returnValue);
	}
}
