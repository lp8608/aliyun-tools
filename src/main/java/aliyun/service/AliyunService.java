package aliyun.service;

/**
 * ClassName: AliyunService <br/>
 * Description: 阿里云api接口调用服务
 * date: 2021/1/8 11:22 <br/>
 *
 * @author lipengak <br/>
 * @since JDK 1.8
 */
public interface AliyunService {
    /**
     * 添加RDS白名单
     * @param groupName
     * @param ip
     */
    Boolean addRdsWhiteList(String groupName, String ip);


    /**
     * 测试RDS创建数据库
     * @return
     */
    Boolean createDB(String dbName, String desc);

    /**
     * 给数据库账号授权访问数据库
     * @return
     */
    Boolean grantAccountPrivilege(String dbName, String account,String privilege);
}
