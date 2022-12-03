package com.dev.warehouse.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.dev.warehouse.sys.common.ActiverUser;
import com.dev.warehouse.sys.common.ResultObj;
import com.dev.warehouse.sys.common.WebUtils;
import com.dev.warehouse.sys.entity.Loginfo;
import com.dev.warehouse.sys.service.ILoginfoService;
import com.dev.warehouse.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ILoginfoService loginfoService;

    //shiro控制登录中的权限问题
    @RequestMapping("login")
    public ResultObj login(UserVo userVo, String code, HttpSession session){
        String sessionCode = (String) session.getAttribute("code");
        //首先判断验证码对不对
        if (code!=null&&sessionCode.equals(code)){
            //subject就是对登录的用户进行认证，谁登录就认证谁
            Subject subject = SecurityUtils.getSubject();

            //AuthenticationToken是把用户名和密码一起封装起来
            AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(),userVo.getPwd());
            try {
                //进行登录
                subject.login(token);
                //对所登录的用户进行激活，就是把他的登录信息写到数据库里，接下来的的几句都是赋值
                ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
                WebUtils.getSession().setAttribute("user",activerUser.getUser());
                Loginfo entity = new Loginfo();
                entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
                entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
                entity.setLogintime(new Date());
                //save方法把登录信息写到数据库
                loginfoService.save(entity);
                return ResultObj.LOGIN_SUCCESS;
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ResultObj.LOGIN_ERROR_PASS;
            }
        }else {
            return ResultObj.LOGIN_ERROR_CODE;
        }
    }

    /**
     * 得到登陆验证码
     * @param response
     * @param session
     * @throws IOException
     */

    //使用maven中的hutool依赖进行验证码的生成，包括验证码的图片生成和值生成
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException{
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,5);
        session.setAttribute("code",lineCaptcha.getCode());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
