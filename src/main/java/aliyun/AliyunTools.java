package aliyun;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import aliyun.view.IndexView;

@SpringBootApplication
public class AliyunTools extends AbstractJavaFxApplicationSupport{
    public static void main(String[] args) {

        launch(AliyunTools.class, IndexView.class, args);
    }
}
