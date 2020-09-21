package config;
import common.AppConstants;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class ConfigReader {
    public static ConfigReader INSTANCE;
    Map<String, String> map = new HashMap<>();

    private ConfigReader(){

    }

    public static synchronized ConfigReader getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ConfigReader();
        return INSTANCE;
    }
    private void loadConfiguration(){
        Yaml yaml = new Yaml();
        InputStream is = this.getClass()
                .getClassLoader()
                .getResourceAsStream(AppConstants.CONFIG_FILE);
        map = yaml.load(is);
    }

    public String getValue(String key){
        if(map.size() == 0){
            loadConfiguration();
        }
        return map.get(key);
    }

}
