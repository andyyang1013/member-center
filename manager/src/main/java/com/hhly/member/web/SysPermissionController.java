package com.hhly.member.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.member.exception.BizException;
import com.hhly.member.exception.CodeMsg;
import com.hhly.member.util.Toolkit;
import com.hhly.member.vo.Page;
import com.hhly.member.entity.SysPermission;
import com.hhly.member.service.ISysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统权限资源表 前端控制器
 * </p>
 *
 * @author jiasx
 * @since 2018-02-05
 */
@RestController
@RequestMapping("/sysPermission")
@Api(tags = {"系统权限资源表"}, description = "系统权限表服务")
public class SysPermissionController extends BaseController {

    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(SysPermissionController.class);


    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * 查询系统权限表,带分页
     *
     * @param sysPermission 系统权限表对象
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation(value = "查询系统权限表列表", notes = "根据条件查询系统权限表列表", httpMethod = "POST", response = PageInfo.class)
    public PageInfo<SysPermission> getList(SysPermission sysPermission, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), true);
        List<SysPermission> list = sysPermissionService.selectList(new EntityWrapper<>(sysPermission));
        PageInfo<SysPermission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 查询系统权限表，按照树结构生成
     *
     * @return
     */
    @RequestMapping(value = "/treelist")
    @ApiOperation(value = "查询系统权限表，按照树结构生成", notes = "查询系统权限表，按照树结构生成", httpMethod = "POST", response = List.class)
    public List<SysPermission> getTreeList() {
        PageHelper.startPage(1, 0);
        return sysPermissionService.getTreeList();
    }

    /**
     * 新增系统权限表
     *
     * @param sysPermission 系统权限表对象
     */
    @RequestMapping("/insert")
    @ApiOperation(value = "新增系统权限表", notes = "新增系统权限表，必填项不能为空", httpMethod = "POST", response = String.class)
    public String insert(SysPermission sysPermission) {
        sysPermission.setCreateUid(getCurUserId());
        sysPermission.setCreateTime(Toolkit.getCurDate());
        sysPermission.setUpdateUid(getCurUserId());
        sysPermission.setUpdateTime(Toolkit.getCurDate());
        sysPermissionService.insert(sysPermission);
        return SUCCESS;
    }

    /**
     * 修改系统权限表
     *
     * @param sysPermission 系统权限表对象
     */
    @RequestMapping("/update")
    @ApiOperation(value = "更新系统权限表", notes = "更新系统权限表，需要主键Id，必填项不能为空", httpMethod = "POST", response = String.class)
    public String update(SysPermission sysPermission) {
        SysPermission dbSysPermission = sysPermissionService.selectById(sysPermission.getId());
        if (dbSysPermission == null || StringUtils.isEmpty(dbSysPermission.getId())) {
            throw new BizException(CodeMsg.record_not_exist);
        }
        dbSysPermission.setDisabled(sysPermission.getDisabled());
        dbSysPermission.setResourceCode(sysPermission.getResourceCode());
        dbSysPermission.setResourceName(sysPermission.getResourceName());
        dbSysPermission.setParentId(sysPermission.getParentId());
        dbSysPermission.setResourceType(sysPermission.getResourceType());
        dbSysPermission.setUrl(sysPermission.getUrl());
        dbSysPermission.setUpdateUid(getCurUserId());
        dbSysPermission.setUpdateTime(Toolkit.getCurDate());
        sysPermissionService.updateById(dbSysPermission);
        return SUCCESS;
    }

    /**
     * 根据Id查询系统权限表
     *
     * @param id 主键id
     */
    @RequestMapping("/get")
    @ApiOperation(value = "获取系统权限表对象", notes = "根据主键Id获取系统权限表对象", httpMethod = "POST", response = String.class)
    public SysPermission getById(Long id) {
        if (id == null) {
            throw new BizException(CodeMsg.id_param_blank);
        }
        SysPermission sysPermission = sysPermissionService.selectById(id);
        if (sysPermission == null) {
            throw new BizException(CodeMsg.record_not_exist);
        }
        return sysPermission;
    }

    /**
     * 删除系统权限表
     *
     * @param id 主键id
     */
    @RequestMapping("/delete")
    @ApiOperation(value = "删除系统权限表对象", notes = "根据主键Id删除系统权限表对象", httpMethod = "POST", response = String.class)
    public String deleteById(Long id) {
        if (id == null) {
            throw new BizException(CodeMsg.id_param_blank);
        }
        sysPermissionService.deleteById(id);
        return SUCCESS;
    }

}
