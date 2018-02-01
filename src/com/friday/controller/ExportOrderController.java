package com.friday.controller;

import com.friday.service.OrderProductService;
import com.friday.service.impl.OrderProductServiceImpl;
import com.friday.utils.ExportExcel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportOrderController implements Controller {

	/**
	 * 查询订单列表
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			Date start = starttime.isEmpty() ? null : Date.valueOf(starttime);
			Date end = endtime.isEmpty() ? null : Date.valueOf(endtime);
			String orderId = request.getParameter("orderid");
			String state = request.getParameter("orderstate");
			int style = Integer.parseInt(state);
			OrderProductService orderProductService = new OrderProductServiceImpl();
			List<Object> list = orderProductService.queryOrder(start, end, style, orderId);
			int pagecurrent = 0, pagecount = (list.size()-1) / 10 + 1;
			String page = request.getParameter("page");
			if (page!=null) {
				pagecurrent = Integer.parseInt(page);
			}
			
			list = list.subList(pagecurrent * 10, (pagecurrent*10 + 10) > list.size() ? list.size() : (pagecurrent*10 + 10));
			String title ="订单列表";
			String[] rowsName = new String[]{"序号","订单号","订单金额"};
			Object[] objs = null;
			List<Object[]>  dataList = new ArrayList<Object[]>();
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = (Map<String,Object>)list.get(i);
				objs = new Object[rowsName.length];
				objs[0] = i;
				objs[1] =map.get("oId");
				objs[2] =map.get("price");
				dataList.add(objs);
			}
			ExportExcel ex = new ExportExcel(title, rowsName, dataList);
			ex.export(response);
			return null;
		} catch (Exception e) {
			model.put("error", "操作失败");
			e.printStackTrace();
			return new ModelAndView("error", model);
		}
	}

}
