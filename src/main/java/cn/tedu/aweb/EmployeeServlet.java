package cn.tedu.aweb;

import cn.tedu.pojo.*;
import cn.tedu.server.ResultBean;
import cn.tedu.service.*;
import cn.tedu.service.impl.*;
import cn.tedu.utils.EncryptMd5;
import cn.tedu.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    //在成员区创建服务层接口对象并实例化，在任何一下方法中，都可以用
    EmployeeService employeeservice=new EmployeeServiceImpl();
    LeaveService leaveService=new LeaveServiceImpl();
    DuserService duserService=new DuserServiceImpl();
    AnnouncementService announcementService=new AnnouncementServiceImpl();
    AttendanceService attendanceService=new AttendanceServiceImpl();
    DepartmentService departmentService=new DepartmentServiceImpl();
    SalaryService salaryService=new SalaryServiceImpl();
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
    //自定一个方法用于完成注册
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是post方法1");
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String sid=request.getParameter("sid");
        try {
            List<Employee> employees= employeeservice.findAll();
            for (Employee employee1:employees){
                if (employee1.getSid().equals(sid)){
                    resultBean.setErrorMsg("已有该工号,请重新注册");
                    JsonUtils.printResult(response,resultBean);
                    return;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        String spwd=request.getParameter("spwd1");
        //给pwd进行加密
        spwd= EncryptMd5.getMd5(spwd);
        String sex=request.getParameter("sex");
        Integer age= Integer.valueOf(request.getParameter("age"));
        String tel=request.getParameter("tel");
        String name=request.getParameter("name");
        String position="员工";
        String department="待审核";
        //创建Duser类对象
        Employee employee=new Employee(sid,name,spwd,age,sex,tel,position,department);
        //创建服务层接口对象并实例化
        EmployeeService employeeService=new EmployeeServiceImpl() {
        };
        try {
            //定义一个集合用于装服务层返回来的数据
            List<Employee> employees= employeeService.findAll();
            for (Employee employee1:employees){
                System.out.println(employee1.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //定义一个变量用于接收返回的值
        try {
            int i=employeeService.add(employee);
            if (i==1){
                //重新定向
                response.sendRedirect("success.html");
            }else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean = new ResultBean(true);
        //定义两个变量用接收login.html页面传入的值
        String pwd=request.getParameter("spwd1");
        String sid=request.getParameter("sid");
        pwd=EncryptMd5.getMd5(pwd);
        //System.out.println(name+pwd);  打点：看是否了取到值，验证一下页面调用servlet这个login方法是否执行
        //对服务层进行操作
        try {
            //写义一个Duser对象用于接服务层返回来的duser
            Employee employee= employeeservice.findByUser(sid,pwd);
            if (employee==null){
                resultBean.setErrorMsg("用户名工号密码错误");
                resultBean.setFlag(false);
                JsonUtils.printResult(response,resultBean);
                return;
            }
            if (employee.getDepartment().equals("待审核")){
                resultBean.setErrorMsg("信息未被审核");
                resultBean.setFlag(false);
                JsonUtils.printResult(response,resultBean);
                return;
            }
            //判断duser对象是否null
            if (employee!=null){
                //创建一个session对象==>权限管理
                HttpSession session= request.getSession();
                //给session对象进行装值，键-值对方式完成（键名可以任起）
                session.setAttribute("employee",employee);
                session.setAttribute("sid",sid);
                String name=employee.getName();
                session.setAttribute("name",name);
                //重新定向到list.html
                response.sendRedirect("/aweb_war_exploded/employee/announcement.html");
            }else {
                //重新定向error.html
                response.sendRedirect("error.html");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是loginout方法");
        request.getSession().removeAttribute("employee");
        response.sendRedirect("index.html");
    }
    protected void findBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        String sid= (String) session.getAttribute("sid");
        Employee employee= (Employee) session.getAttribute("employee");
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            JsonUtils.printResult(response,resultBean);
            return;
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                Employee employee2=employeeservice.findBySid(sid);
//                System.out.println(employee2.toString());
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(employee2);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取员工信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        HttpSession session=request.getSession();
        String sid= (String) session.getAttribute("sid");
        try {
            employeeservice.deleteById(id);
            leaveService.deleteBySid(sid);
            attendanceService.deleteBySid(sid);
            salaryService.deleteBySid(sid);
            request.getSession().removeAttribute("employee");
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void findBySid1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Employee employee= (Employee) session.getAttribute("employee");
        String sid= request.getParameter("sid");
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                Employee employee2=employeeservice.findBySid(sid);
//                System.out.println(employee2.toString());
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(employee2);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取员工信息失败");
            }
        }
}
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        //2:取页面转过来的值，employee对象，我们需要转换
        Employee employee=JsonUtils.parseJSON2Object(request, Employee.class);
        Employee employee1= (Employee) session.getAttribute("employee");
        String sid=employee1.getSid();
        if (!employee1.getSpwd().equals(employee.getSpwd())){
            employee.setSpwd(EncryptMd5.getMd5(employee.getSpwd()));
        }
        //3：调用服务层的方法
        try {
            employeeservice.update(employee);
            List<Attendance> attendances=attendanceService.findList(sid,"undefined");
            for (Attendance attendance:attendances){
                attendance.setName(employee.getName());
                attendance.setSid(employee.getSid());
                attendanceService.update(attendance);
            }
            List<Salary> salaries=salaryService.findList(sid,"undefined");
            for (Salary salary:salaries){
                salary.setName(employee.getName());
                salary.setSid(employee.getSid());
                salaryService.update(salary);
            }
            List<Leave> leaves=leaveService.findList(sid,"undefined");
            for (Leave leave:leaves){
                leave.setName(employee.getName());
                leave.setSid(employee.getSid());
                leaveService.update(leave);
            }
            //5:调用服务层里的方法
            resultBean.setErrorMsg("修改成功");
        }catch (SQLException e){
            e.printStackTrace();
            //4:报错时给的提示信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新失败");
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void findByLeaveSid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Employee employee= (Employee) session.getAttribute("employee");
        String sid= (String) session.getAttribute("sid");
        //开始操作
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Leave> leave=leaveService.findList(sid,"undefined");
//                System.out.println(sid);
//                System.out.println(leave.toString());
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(leave);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取员工信息失败");
            }
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
    //自定一个方法用于完成添加
    protected void addLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addleave方法");
        HttpSession session=request.getSession();
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String sid= (String) session.getAttribute("sid");
        String status=request.getParameter("status");
        String cause=request.getParameter("cause");
        String name= (String) session.getAttribute("name");
        String time1=request.getParameter("time1");
        String time2=request.getParameter("time2");
        time1=time1.replace("T"," ");
        time2=time2.replace("T"," ");
        String time=time1+"到"+time2;
        if (status==null){
            status="未审核";
        }
        //创建Leave类对象
        Leave leave=new Leave(name,sid,cause,time,status);
        try {
            int i=leaveService.add(leave);
            if (i==1){
                //重新定向
                response.sendRedirect("success.html");
            }else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        try {
            leaveService.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void findAllannouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是findAllannouncement");
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Employee employee= (Employee) session.getAttribute("employee");
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Announcement> announcements= announcementService.findAll();
//                for (Announcement announcements1:announcements){
//                    System.out.println(announcements1.toString());
//                }
                resultBean.setData(announcements);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取公告信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        try {
            attendanceService.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void findByAttendanceSid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Employee employee= (Employee) session.getAttribute("employee");
        String sid= (String) session.getAttribute("sid");
        //开始操作
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Attendance> attendances=attendanceService.findList(sid,"undefined");
                System.out.println(sid);
//                System.out.println(attendances.toString());
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(attendances);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取出勤信息失败");
            }
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void morning(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean();
        HttpSession session=request.getSession();
        String sid= (String) session.getAttribute("sid");
        String name= (String) session.getAttribute("name");
        String status="早班打卡";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(System.currentTimeMillis());
        try {
            Attendance attendance=new Attendance(name,sid,status,time);
            int i=attendanceService.add(attendance);
            if (i==1){
                //重新定向
                resultBean.setErrorMsg("打卡成功");
            }else {
                response.sendRedirect("error.html");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void evening(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean = new ResultBean();
        HttpSession session=request.getSession();
        String sid= (String) session.getAttribute("sid");
        String name= (String) session.getAttribute("name");
        String status="晚班打卡";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(System.currentTimeMillis());
        try {
            Attendance attendance=new Attendance(name,sid,status,time);
            int i=attendanceService.add(attendance);
            if (i==1){
                resultBean.setErrorMsg("打卡成功");
                //重新定向
//                response.sendRedirect("success.html");
            }else {
                response.sendRedirect("error.html");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void work(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean = new ResultBean();
        HttpSession session=request.getSession();
        String sid= (String) session.getAttribute("sid");
        String name= (String) session.getAttribute("name");
        String status="加班打卡";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(System.currentTimeMillis());
        try {
            Attendance attendance=new Attendance(name,sid,status,time);
            int i=attendanceService.add(attendance);
            if (i==1){
                //重新定向
                resultBean.setErrorMsg("打卡成功");
            }else {
                response.sendRedirect("error.html");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void deleteSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        try {
            salaryService.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void findBySalarySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Employee employee= (Employee) session.getAttribute("employee");
        String sid= (String) session.getAttribute("sid");
        //开始操作
        if (employee==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Salary> salaries=salaryService.findList(sid,"undefined");
//                System.out.println(sid);
//                System.out.println(leave.toString());
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(salaries);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取工资信息失败");
            }
        }
        //6：将服务器管理类对象输出到客户端
        JsonUtils.printResult(response,resultBean);
    }
}
