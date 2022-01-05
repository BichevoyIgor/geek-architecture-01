package ru.geekbrains;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile implements Config {
    private final int PORT;
    private final String WWW;

    public ConfigFile(String propFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.PORT = Integer.parseInt(properties.getProperty("PORT"));
        this.WWW = properties.getProperty("WWW");
    }

    @Override
    public int getPORT() {
        return PORT;
    }

    @Override
    public String getWWW() {
        return WWW;
    }
}
