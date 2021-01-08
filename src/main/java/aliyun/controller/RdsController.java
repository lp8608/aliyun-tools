package aliyun.controller;

import aliyun.config.AliyunConfig;
import aliyun.service.AliyunService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@FXMLController
@Slf4j
public class RdsController   {

    @Autowired
    private AliyunService aliyunServiceImpl;
    @Autowired
    private AliyunConfig aliyunConfig;
    /** 添加白名单 **/

    @FXML
    private TextField groupName;

    @FXML
    private TextField ip;

    @FXML
    private Label addWhiteListResult;
    /** 创建数据库 **/
    @FXML
    private TextField dbName;

    @FXML
    private TextArea desc;

    @FXML
    private Label createDBResult;
    @FXML
    private TextArea connConfig;





    public void addRdsWhiteList(final Event e) {
        String groupNameStr = groupName.getText().trim();
        String ipStr = ip.getText().trim();
        String resultStr = "";
        if (!StringUtils.isEmpty(groupNameStr)&& !StringUtils.isEmpty(ipStr) && aliyunConfig.getItcodeList().contains(groupNameStr.toLowerCase())) {
            Boolean addResult = aliyunServiceImpl.addRdsWhiteList(groupNameStr.toLowerCase(),ipStr.trim());
            resultStr = addResult ? "添加成功！" : "添加失败";
        }else{
            resultStr = "校验失败，itcode请输入个人itcode,如lipengak, dcr_luopengc ， ip地址必输!";
        }
        addWhiteListResult.setText(resultStr);
    }

    public void createDB(final Event e){
        String dbNameStr = dbName.getText().trim();
        String descStr = desc.getText().trim();
        String resultStr = "";
        if(StringUtils.isEmpty(dbNameStr) || StringUtils.isEmpty(descStr)){
            resultStr = "请输入数据库名称和描述信息！";
        }else{
            Boolean addResult = aliyunServiceImpl.createDB(dbNameStr,descStr);
            resultStr = addResult ? "添加成功！" : "添加失败";
            String connn = "阿里云内网连接地址：\n" +
                    "url: jdbc:mysql://"+aliyunConfig.getInnerHost()+":3306/"+dbNameStr+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&useOldAliasMetadataBehavior=true&useSSL=false\n" +
                    "username: "+aliyunConfig.getDefaultAccountName()+"\n" +
                    "password: "+aliyunConfig.getAccountPass()+"\n\n" +
                    "外网连接地址：\n" +
                    "url: jdbc:mysql://"+aliyunConfig.getHost()+":3306/"+dbNameStr+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&useOldAliasMetadataBehavior=true&useSSL=false\n" +
                    "username: "+aliyunConfig.getDefaultAccountName()+"\n" +
                    "password: "+aliyunConfig.getAccountPass()+"\n\n";
            connConfig.setText(connn);
        }
        createDBResult.setText(resultStr);
    }


}
