package com.hhly.member.config.filter.shiro;/**
 * Created by dell on 2017/9/13.
 */


import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 前后端分离，不需要session。因此修改shiro的默认session配置
 *
 * @author jiasx
 * @create 2017-09-13 10:41
 **/
public class PlatformSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session.
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
