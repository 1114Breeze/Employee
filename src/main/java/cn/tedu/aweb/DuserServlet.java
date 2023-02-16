package cn.tedu.aweb;

import cn.tedu.pojo.*;
import cn.tedu.server.ResultBean;
import cn.tedu.service.*;
import cn.tedu.service.impl.*;
import cn.tedu.utils.EncryptMd5;
import cn.tedu.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DuserServlet", value = "/DuserServlet")
public class DuserServlet extends HttpServlet {
    //在成员区创建服务层接口对象并实例化，在任何一下方法中，都可以用
    DuserService duserService=new DuserServiceImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();
    LeaveService leaveService=new LeaveServiceImpl();
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
//        System.out.println("我是post方法");
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String name=request.getParameter("username");
        try {
            List<Duser> dusers= duserService.findAll();
            for (Duser duser1:dusers){
                if (duser1.getName().equals(name)){
                    resultBean.setErrorMsg("已有该用户名,请重新注册");
                    JsonUtils.printResult(response,resultBean);
                    return;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        String pwd=request.getParameter("userpwd1");
        //给pwd进行加密
        pwd= EncryptMd5.getMd5(pwd);
        String email=request.getParameter("useremail");
        System.out.println("我是post方法"+name+"\t"+pwd+"\t"+email);
        //创建Duser类对象
        Duser duser=new Duser(name,pwd,email);
        //创建服务层接口对象并实例化
        DuserService duserService=new DuserServiceImpl();
        try {
            //定义一个集合用于装服务层返回来的数据
            List<Duser> dusers= duserService.findAll();
            for (Duser duser1:dusers){
                System.out.println(duser1.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //定义一个变量用于接收返回的值
        try {
            int i=duserService.add(duser);
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
    //定定义一个方法用于完成登陆
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean = new ResultBean(true);
        //定义两个变量用接收login.html页面传入的值
        String name=request.getParameter("username");
        String pwd=request.getParameter("userpwd1");
        pwd=EncryptMd5.getMd5(pwd);
        //System.out.println(name+pwd);  打点：看是否了取到值，验证一下页面调用servlet这个login方法是否执行
        //对服务层进行操作
        try {
            //写义一个Duser对象用于接服务层返回来的duser
            Duser duser= duserService.findByUser(name,pwd);
            if (duser==null){
                resultBean.setErrorMsg("用户名账号密码错误");
                resultBean.setFlag(false);
                JsonUtils.printResult(response,resultBean);
                return;
            }
            //判断duser对象是否null
            if (duser!=null){
                //创建一个session对象==>权限管理
                HttpSession session= request.getSession();
                //给session对象进行装值，键-值对方式完成（键名可以任起）
                session.setAttribute("user",duser);
                //重新定向到list.html
                response.sendRedirect("list2.html");
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
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.html");
    }
    //自定一个方法用于完成添加
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是add方法");
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        System.out.println("duser:"+duser);
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            JsonUtils.printResult(response,resultBean);
            return;
        }else {
            try {
            //定义变量接收页面传入的值
            String sid=request.getParameter("sid");
                List<Employee> employees= employeeService.findAll();
                for (Employee employee1:employees){
                    if (employee1.getSid().equals(sid)){
                        System.out.println("已有该学号");
                        System.out.println(employee1.getSid());
                        response.sendRedirect("error.html");
                        return;
                    }
                }
            String spwd=request.getParameter("spwd1");
            //给pwd进行加密
            spwd= EncryptMd5.getMd5(spwd);
            String sex=request.getParameter("sex");
            Integer age= Integer.valueOf(request.getParameter("age"));
            String tel=request.getParameter("tel");
            String name=request.getParameter("name");
            String position=request.getParameter("position");
            String department=request.getParameter("department");
            String salary=request.getParameter("salary");
            System.out.println(department);
            if (position.equals("")){
                position="员工";
                System.out.println(position+"1");
            }else {
                System.out.println(position+"4");
            }

            //创建Duser类对象
            Employee employee=new Employee(sid,name,spwd,age,sex,tel,position,department,salary);
            //创建服务层接口对象并实例化
            EmployeeService employeeService=new EmployeeServiceImpl() {
            };
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
    }
    //定定义一个方法用于查询所有的数据
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Employee> employees= employeeService.findAll();
//                for (Employee employee1:employees){
//                    System.out.println(employee1.toString());
//                }
                //将employees集合装给服务器管理类的data中
                resultBean.setData(employees);
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
//    protected void findEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1创建服务器管理类的对象并实例化
//        ResultBean resultBean=new ResultBean(true);
//        //2取session的值，判断是否登陆
//        HttpSession session= request.getSession();
//        Employee employee= (Employee) session.getAttribute("user");
//        if (employee==null){
//            //没有登陆
//            resultBean.setFlag(false);
//            resultBean.setErrorMsg("请登陆");
//        }else {
//            //开始操作
//            try {
//                //定义一个集合用于装服务层返回来的数据
//                List<Employee> employees= employeeService.findAll();
//                for (Employee employee1:employees){
//                    System.out.println(employee1.toString());
//                }
//                Employee employee1=employees.get(1);
//
//                //将dusers集合装给服务器管理类的data中
//                resultBean.setData(employee1);
//            }catch (SQLException e){
//                e.printStackTrace();
//                //当duserService.findAll()返回异常信息后要处理服务器装态
//                resultBean.setFlag(false);
//                resultBean.setErrorMsg("获取员工信息失败");
//            }
//        }
//        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
//        JsonUtils.printResult(response,resultBean);
//    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        String sid=request.getParameter("sid");
        System.out.println("sid:"+sid);
        System.out.println("id:"+id);
        try {
            employeeService.deleteById(id);
            leaveService.deleteBySid(sid);
            attendanceService.deleteBySid(sid);
            salaryService.deleteBySid(sid);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    //定义一个方法用于根据id查询数据
    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        //2:取联系人的id
        Integer id=Integer.valueOf(request.getParameter("id"));//这个id参数，是址址栏里的id变量
        //3:调用服务层完成操作
        try {
            //5:定义一个Duser对象用于接收DuserService服层返回的对象duser
            Employee employee= employeeService.findById(id);
            session.setAttribute("employee",employee);
            //6:将duser对象加入服务器管理类data中
            resultBean.setData(employee);
        }catch (SQLException e){
            e.printStackTrace();
            //4:先写报错信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新错误");
        }
        //7:将服务器管理类情况输出客户端
        JsonUtils.printResult(response,resultBean);
    }
//    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ResultBean resultBean=new ResultBean(true);
//        Integer id=Integer.valueOf(request.getParameter("id"));
//        try {
//            duserService.findById(id);
//        }catch (SQLException e){
//            e.printStackTrace();
//            resultBean.setFlag(false);
//            resultBean.setErrorMsg("查找失败");
//        }
//        JsonUtils.printResult(response,resultBean);
//    }
//    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("我是update方法");
//        //设置获取表单的字符集
//        request.setCharacterEncoding("utf-8");
//        //定义变量接收页面传入的值
//        String name=request.getParameter("username");
//        String pwd=request.getParameter("userpwd1");
//        String email=request.getParameter("useremail");
//        System.out.println("我是update方法"+name+"\t"+pwd+"\t"+email);
//        //创建Duser类对象
//        Duser duser=new Duser();
//        //创建服务层接口对象并实例化
//        DuserService duserService=new DuserServiceImpl();
//        //定义一个变量用于接收返回的值
//        try {
//            duser.setName(name);
//            duser.setPwd(pwd);
//            duser.setEmail(email);
//            duserService.update(duser);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//定义一个方法用于根据id查询数据
    //定义一个方法用于根据id查询数据
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取页面转过来的值，employee对象，我们需要转换
        Duser duser=JsonUtils.parseJSON2Object(request, Duser.class);
        //进行密码加密
        duser.setPwd(EncryptMd5.getMd5(duser.getPwd()));
        //3：调用服务层的方法
        try {
            //5:调用服务层里的方法
            duserService.update(duser);
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
    protected void updateemployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            employeeService.update(employee);
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
    protected void findByemployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        String sid= request.getParameter("sid");
        System.out.println(sid);
            //开始操作
        try {
            //定义一个集合用于装服务层返回来的数据
            List<Employee> employees= employeeService.findList(sid);
            for (Employee employee1:employees){
                System.out.println(employee1.toString());
            }
            //将dusers集合装给服务器管理类的data中
            resultBean.setData(employees);
        }catch (SQLException e){
            e.printStackTrace();
            //当duserService.findAll()返回异常信息后要处理服务器装态
            resultBean.setFlag(false);
            resultBean.setErrorMsg("获取员工信息失败");
        }
        JsonUtils.printResult(response,resultBean);

    }
    protected void findByLeaveSid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        String sid= request.getParameter("sid");
        String status= request.getParameter("status");
        //开始操作
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Leave> leave=leaveService.findList(sid,status);
//                System.out.println("sid:"+sid);
//                System.out.println("status:"+status);
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
    protected void addLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addleave方法");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            return;
        }
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String sid= request.getParameter("sid");
        String status=request.getParameter("status");
        String cause=request.getParameter("cause");
        String name= request.getParameter("name");
        String time1=request.getParameter("time1");
        System.out.println(time1);
        String time2=request.getParameter("time2");
        time1=time1.replace("T"," ");
        time2=time2.replace("T"," ");
        String time=time1+"到"+time2;
        if (status==null){
            status="批准";
        }
        try {
            Employee employee=employeeService.findBySid(sid);
            if (employee==null){
                response.sendRedirect("error.html");
                return;
            }
            String name1=employee.getName();
            if (name.equals(name1)){
                //创建Leave类对象
                Leave leave=new Leave(name,sid,cause,time,status);
                int i=leaveService.add(leave);
                if (i==1){
                    //重新定向
                    response.sendRedirect("success.html");
                }else {
                    response.sendRedirect("error.html");
                }
            }
            else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void findAllLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Leave> leaves= leaveService.findAll();
//                for (Leave leave1:leaves){
//                    System.out.println(leave1.toString());
//                }
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(leaves);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取请假信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void findAllannouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是findAllannouncement");
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
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
    protected void deleteAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        System.out.println("id:"+id);
        try {
            announcementService.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void addAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addAnnouncement方法");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            return;
        }
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String title= request.getParameter("title");
        String content=request.getParameter("content");
        try {
                //创建Leave类对象
                Announcement announcement=new Announcement(title,content);
                int i=announcementService.add(announcement);
                if (i==1){
                    //重新定向
                    response.sendRedirect("success.html");
                }else {
                    response.sendRedirect("error.html");
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void findByIdAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取联系人的id
        Integer id=Integer.valueOf(request.getParameter("id"));//这个id参数，是址址栏里的id变量
        //3:调用服务层完成操作
        try {
            //5:定义一个Duser对象用于接收DuserService服层返回的对象duser
            Announcement announcement= announcementService.findById(id);
            //6:将duser对象加入服务器管理类data中
            resultBean.setData(announcement);
        }catch (SQLException e){
            e.printStackTrace();
            //4:先写报错信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新错误");
        }
        //7:将服务器管理类情况输出客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void updateAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取页面转过来的值，employee对象，我们需要转换
        Announcement announcement=JsonUtils.parseJSON2Object(request, Announcement.class);
        //3：调用服务层的方法
        try {
            //5:调用服务层里的方法
            announcementService.update(announcement);
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
    protected void findAllAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Attendance> attendances= attendanceService.findAll();
//                for (Leave leave1:leaves){
//                    System.out.println(leave1.toString());
//                }
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(attendances);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取出勤信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void findByAttendanceSid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        String sid= request.getParameter("sid");
        String status= request.getParameter("status");
        //开始操作
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Attendance> attendances=attendanceService.findList(sid,status);
                System.out.println("sid:"+sid);
                System.out.println("status:"+status);
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
    protected void findByIdAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取联系人的id
        Integer id=Integer.valueOf(request.getParameter("id"));//这个id参数，是址址栏里的id变量
        //3:调用服务层完成操作
        try {
            //5:定义一个Duser对象用于接收DuserService服层返回的对象duser
            Attendance attendance= attendanceService.findById(id);
            //6:将duser对象加入服务器管理类data中
            resultBean.setData(attendance);
        }catch (SQLException e){
            e.printStackTrace();
            //4:先写报错信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新错误");
        }
        //7:将服务器管理类情况输出客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void updateAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取页面转过来的值，employee对象，我们需要转换
        Attendance attendance=JsonUtils.parseJSON2Object(request, Attendance.class);
        String time=request.getParameter("time");
        time=time.replace("T"," ");
        attendance.setTime(time);
        //3：调用服务层的方法
        try {
            //5:调用服务层里的方法
            attendanceService.update(attendance);
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
    protected void addAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addAttendance方法");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            return;
        }
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String sid= request.getParameter("sid");
        String status=request.getParameter("status");
        String name= request.getParameter("name");
        String time=request.getParameter("time");
        time=time.replace("T"," ");
        try {
            Employee employee=employeeService.findBySid(sid);
            if (employee==null){
                response.sendRedirect("error.html");
                return;
            }
            String name1=employee.getName();
            if (name.equals(name1)){
                //创建Leave类对象
                Attendance attendance=new Attendance(name,sid,status,time);
                int i=attendanceService.add(attendance);
                if (i==1){
                    //重新定向
                    response.sendRedirect("success.html");
                }else {
                    response.sendRedirect("error.html");
                }
            }
            else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void findAlldepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                List<Department> departments= departmentService.findAll();
                resultBean.setData(departments);
            }catch (SQLException e){
                e.printStackTrace();
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取公告信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean(true);
        Integer id=Integer.valueOf(request.getParameter("id"));
        System.out.println("id:"+id);
        try {
            departmentService.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }
        JsonUtils.printResult(response,resultBean);
    }
    protected void findByIdDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        //2:取联系人的id
        Integer id=Integer.valueOf(request.getParameter("id"));//这个id参数，是址址栏里的id变量
        HttpSession session=request.getSession();
        //3:调用服务层完成操作
        try {
            //5:定义一个Duser对象用于接收DuserService服层返回的对象duser
            Department department= departmentService.findById(id);
            session.setAttribute("department",department);
            //6:将duser对象加入服务器管理类data中
            resultBean.setData(department);
        }catch (SQLException e){
            e.printStackTrace();
            //4:先写报错信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新错误");
        }
        //7:将服务器管理类情况输出客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        //2:取页面转过来的值，employee对象，我们需要转换
        Department department=JsonUtils.parseJSON2Object(request, Department.class);
        Department department2= (Department) session.getAttribute("department");
        String department1=department2.getDepartment();
        //3：调用服务层的方法
        try {
            List<Employee> employees=employeeService.findList(department1);
            for (Employee employees1:employees){
                employees1.setDepartment(department.getDepartment());
                employeeService.update(employees1);
            }
            //5:调用服务层里的方法
            departmentService.update(department);
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
    protected void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addDepartment方法");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            return;
        }
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String department= request.getParameter("department");
        try {
            //创建Leave类对象
            Department department1=new Department(department);
            int i=departmentService.add(department1);
            if (i==1){
                //重新定向
                response.sendRedirect("success.html");
            }else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public float parseFloat(String strNumber){
        if (strNumber.length() > 0) {
                return Float.parseFloat(strNumber);
            }else {
            return Float.parseFloat("0");
        }
    }
    protected void addSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultBean resultBean=new ResultBean();
        System.out.println("我是addSalary方法");
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
            return;
        }
        //设置获取表单的字符集
        request.setCharacterEncoding("utf-8");
        //定义变量接收页面传入的值
        String name= request.getParameter("name");
        String sid= request.getParameter("sid");
        String time=request.getParameter("time");
//        System.out.println(time);
        String late= request.getParameter("late");
        float late1= parseFloat(late);
        late= String.valueOf(late1);
        String leave=request.getParameter("leave");
        float leave1= parseFloat(leave);
        leave= String.valueOf(leave1);
        String prize=request.getParameter("prize");
        float prize1= parseFloat(prize);
        prize= String.valueOf(prize1);
        String tax=request.getParameter("tax");
        float tax1= parseFloat(tax);
        tax= String.valueOf(tax1);
        String benefits=request.getParameter("benefits");
        float benefits1= parseFloat(benefits);
        benefits= String.valueOf(benefits1);
//        time=time.replace("T"," ");
        try {
            Employee employee=employeeService.findBySid(sid);
            if (employee==null){
                response.sendRedirect("error.html");
                return;
            }
            String sid1=employee.getSid();
            String base=employee.getSalary();
            float base1= Float.parseFloat(base);
            float total1=base1-late1-leave1+prize1-tax1-benefits1;
            String total= String.valueOf(total1);
            if (sid.equals(sid1)){
                //创建Leave类对象
                Salary salary=new Salary(name,sid,time,base,late,leave,prize,tax,benefits,total);
                int i=salaryService.add(salary);
                if (i==1){
                    //重新定向
                    response.sendRedirect("success.html");
                }else {
                    response.sendRedirect("error.html");
                }
            }
            else {
                response.sendRedirect("error.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void findAllSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1创建服务器管理类的对象并实例化
        ResultBean resultBean=new ResultBean(true);
        //2取session的值，判断是否登陆
        HttpSession session= request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            //开始操作
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Salary> salaries= salaryService.findAll();
//                for (Salary salary:salaries){
//                    System.out.println(salary.toString());
//                }
                //将dusers集合装给服务器管理类的data中
                resultBean.setData(salaries);
            }catch (SQLException e){
                e.printStackTrace();
                //当duserService.findAll()返回异常信息后要处理服务器装态
                resultBean.setFlag(false);
                resultBean.setErrorMsg("获取信息失败");
            }
        }
        //将结果输出到客户端业面：使用JsonUtils将resultBean对转换成json字符串输出到客户端list页面
        JsonUtils.printResult(response,resultBean);
    }
    protected void findBySalarySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Duser duser= (Duser) session.getAttribute("user");
        String sid= request.getParameter("sid");
        String time= request.getParameter("time");
        //开始操作
        if (duser==null){
            //没有登陆
            resultBean.setFlag(false);
            resultBean.setErrorMsg("请登陆");
        }else {
            try {
                //定义一个集合用于装服务层返回来的数据
                List<Salary> salaries=salaryService.findList(sid,time);
//                System.out.println("sid:"+sid);
//                System.out.println("status:"+status);
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
    protected void findByIdSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        //2:取联系人的id
        Integer id=Integer.valueOf(request.getParameter("id"));//这个id参数，是址址栏里的id变量
        //3:调用服务层完成操作
        try {
            //5:定义一个Duser对象用于接收DuserService服层返回的对象duser
            Salary salary= salaryService.findById(id);
            session.setAttribute("salary",salary);
            //6:将duser对象加入服务器管理类data中
            resultBean.setData(salary);
        }catch (SQLException e){
            e.printStackTrace();
            //4:先写报错信息
            resultBean.setFlag(false);
            resultBean.setErrorMsg("更新错误");
        }
        //7:将服务器管理类情况输出客户端
        JsonUtils.printResult(response,resultBean);
    }
    protected void updateSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1:创建服务器管理类对象并实例化赋服务器状态初始为true
        ResultBean resultBean = new ResultBean(true);
        HttpSession session=request.getSession();
        Salary salary1 = (Salary) session.getAttribute("salary");
        //2:取页面转过来的值，employee对象，我们需要转换
        Salary salary=JsonUtils.parseJSON2Object(request, Salary.class);
        String base= salary.getBase();
        float base1= Float.parseFloat(base);
        String base2= salary1.getBase();
        String late= salary.getLate();
        float late1= Float.parseFloat(late);
        String late2= salary1.getLate();
        String leave=salary.getLeave1();
        float leave1= Float.parseFloat(leave);
        String leave2= salary1.getLeave1();
        String prize=salary.getPrize();
        float prize1= Float.parseFloat(prize);
        String prize2= salary1.getPrize();
        String tax=salary.getTax();
        float tax1= Float.parseFloat(tax);
        String tax2= salary1.getTax();
        String benefits=salary.getBenefits();
        float benefits1= Float.parseFloat(benefits);
        String benefits2= salary1.getBenefits();
        String total2=salary.getTotal();
        float total1=base1-late1-leave1+prize1-tax1-benefits1;
        String total= String.valueOf(total1);
        if (base.equals(base2)&&late.equals(late2)&&leave.equals(leave2)&&prize.equals(prize2)&&tax.equals(tax2)&&benefits.equals(benefits2)){
            salary.setTotal(total2);
        }
        else {
            salary.setTotal(total);
        }
        //3：调用服务层的方法
        try {
            //5:调用服务层里的方法
            salaryService.update(salary);
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
}

