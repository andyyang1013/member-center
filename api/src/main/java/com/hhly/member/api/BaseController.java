package com.hhly.member.api;/**
 * Created by dell on 2017/11/24.
 */

import com.hhly.member.config.convert.DateEditor;
import com.hhly.member.repository.IRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 基础控制器类
 *
 * @author jiasx
 * @create 2017-11-24 15:02
 **/
public class BaseController {

    public static final String SUCCESS = "success";

    public static final boolean TRUE = true;

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected IRedisRepository redisRepository;


    /**
     * 表单日期提交，支持多种格式
     * @param webDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

}
