package aliyun.service.impl;

import aliyun.config.AliyunConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aliyun.service.AliyunService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.aliyuncs.rds.model.v20140815.*;

/**
 * ClassName: AliyunServiceImpl <br/>
 * Description: <br/>
 * date: 2021/1/8 11:24 <br/>
 *
 * @author lipengak <br/>
 * @since JDK 1.8
 */
@Service
@Slf4j
public class AliyunServiceImpl implements AliyunService {


    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private IAcsClient acsClient;


    @Override
    public Boolean addRdsWhiteList(String groupName, String ip) {
        log.info("rdsInstanceId:{}, groupName:{}, ip:{}",aliyunConfig.getRdsInstanceId(),groupName,ip);


        ModifySecurityIpsRequest request = new ModifySecurityIpsRequest();
        request.setDBInstanceId(aliyunConfig.getRdsInstanceId());
        request.setSecurityIps(ip);
        request.setDBInstanceIPArrayName(groupName);

        try {
            ModifySecurityIpsResponse response = acsClient.getAcsResponse(request);
            log.info(response.toString());
            return response.getTaskId() == null ? false : true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return false;
    }

    @Override
    public Boolean createDB(String dbName, String desc) {
        CreateDatabaseRequest request = new CreateDatabaseRequest();
        request.setDBInstanceId(aliyunConfig.getRdsInstanceId());
        request.setDBName(dbName);
        request.setCharacterSetName("utf8mb4");
        request.setDBDescription(desc);
        try {
            CreateDatabaseResponse response = acsClient.getAcsResponse(request);
            log.info(response.toString());
            if(response.getRequestId() != null){
               return grantAccountPrivilege(dbName,aliyunConfig.getDefaultAccountName(),aliyunConfig.getDefaultPrivilege());
            }
            return  false;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return false;
    }

    @Override
    public Boolean grantAccountPrivilege(String dbName, String account, String privilege) {
        GrantAccountPrivilegeRequest request = new GrantAccountPrivilegeRequest();
        request.setDBInstanceId(aliyunConfig.getRdsInstanceId());;
        request.setAccountName(account);
        request.setDBName(dbName);
        request.setAccountPrivilege(privilege);
        try {
            GrantAccountPrivilegeResponse response = acsClient.getAcsResponse(request);
            return response.getRequestId() == null ? false : true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return false;
    }



}
