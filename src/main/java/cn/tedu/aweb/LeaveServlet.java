package cn.tedu.aweb;

import cn.tedu.pojo.Employee;
import cn.tedu.server.ResultBean;
import cn.tedu.service.DuserService;
import cn.tedu.service.EmployeeService;
import cn.tedu.service.LeaveService;
import cn.tedu.service.impl.DuserServiceImpl;
import cn.tedu.service.impl.EmployeeServiceImpl;
import cn.tedu.service.impl.LeaveServiceImpl;
import cn.tedu.utils.EncryptMd5;
import cn.tedu.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "LeaveServlet", value = "/LeaveServlet")
public class LeaveServlet extends HttpServlet {
    EmployeeService employeeservice=new EmployeeServiceImpl();
    LeaveService leaveService=new LeaveServiceImpl();
    DuserService duserService=new DuserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1设置获取参数的字符集
        request.setCharacterEncoding("utf-8");
        //2获取地址栏传入action的值
        String actionAddress=request.getParameter("action");
        // System.out.println("action:"+actionAddress);
        try {
            //获取当前类的实例
            Class cls=this.getClass();
            //根据actionAddress获取方法
            Method method= cls.getDeclaredMethod(actionAddress,HttpServletRequest.class,HttpServletResponse.class);
            //调用指定方法名
            method.invoke(this,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void updateAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        String id=request.getParameter("id");
        try {
            //5:调用服务层里的方法
            leaveService.updateStatus("批准",id);
        }catch (SQLException e){
            e.printStackTrace();
            //4:报错时给的提示信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新失败");
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void updateRefuse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        String id=request.getParameter("id");
        try {
            //5:调用服务层里的方法
            leaveService.updateStatus("驳回",id);
        }catch (SQLException e){
            e.printStackTrace();
            //4:报错时给的提示信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新失败");
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
}
