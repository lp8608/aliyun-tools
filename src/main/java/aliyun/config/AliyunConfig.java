package aliyun.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ClassName: AliyunConfig <br/>
 * Description: <br/>
 * date: 2021/1/8 14:26 <br/>
 *
 * @author lipengak <br/>
 * @since JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {

    /**
     * 阿里云accessId
     */
    private String accessId;
    /**
     * 密钥
     */
    private String securet;
    /**
     * rds实例id
     */
    private String rdsInstanceId;
    /**
     * itcode 集合
     */
    private List<String> itcodeList;
    /**
     * 默认账号
     */
    private String defaultAccountName;
    /**
     * 默认权限
     */
    private String defaultPrivilege;
    /**
     * 默认密码
     */
    private String accountPass;
    /**
     * 内网地址
     */
    private String innerHost;
    /**
     * 外网地址
     */
    private String host;



    @Bean
    public IAcsClient acsClient(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", getAccessId(), getSecuret());
        return new DefaultAcsClient(profile);
    }


}
