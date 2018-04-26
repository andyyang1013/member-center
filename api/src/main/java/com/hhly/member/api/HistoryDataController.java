package com.hhly.member.api;/**
							* Created by dell on 2018/2/26.
							*/

import com.hhly.member.service.IUserTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 导入历史数据接口，方便通过接口调用
 *
 * @author jiasx
 * @create 2018-02-26 12:18
 **/
@RestController
@RequestMapping("/data")
public class HistoryDataController extends BaseController {

	@Autowired
	private IUserTempService userTempService;

	/**
	 * 导入历史数据
	 * @return
	 */
	@RequestMapping("/import")
	public String importHistoryData(){
		userTempService.importHistoryData();
		return SUCCESS;
	}
}
