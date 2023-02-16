package cn.tedu.server;

import java.io.Serializable;

/**
 * 服务器管理类：用于管理后端数据到前端数据管理者
 * 1：加一个序列化
 * 2：定义相应的属性
 *     服务器的状态
 *     数据data（一条记录就是一个对象，也有可能是一个集合）
 *     定义一个服务器异常信息
 * 3：添加相应的构造方法
 *     a、定义一个无参构造方法，用于创建对象
 *     b、需要定我只有初始化服务器状态的构造方法
 *     c、服务器状态和异常信息==》在状态为false时+异常信息
 *     d、服务器装态和处理结果data==》在状态为true时+data
 *     e、表示服务器状态、处理结果、异常信息==》所有信息操作
 * 4：添加各属性的set和get方法
 * 5:添加一个toString方法,用于显示服务器管理类的情况==》注：在实际开发，是不需要
 */
public class ResultBean implements Serializable {
    //定义一个服务器状态是否正常
    private Boolean flag;
    //定义一个data，表示请求的处理结果
    private Object data;
    //定义服务器异常信息
    private String errorMsg;
    //添加构造方法
    public ResultBean(){}
    public ResultBean(Boolean flag) {
        this.flag = flag;
    }
    public ResultBean(Boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
    public ResultBean(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }
    public ResultBean(Boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }
    //添加各属性的set和get方法===》便于我们单独处理某个属性用

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    //添加一个toString方法

    @Override
    public String toString() {
        return "ResultBean{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
