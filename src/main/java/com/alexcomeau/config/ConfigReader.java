package com.alexcomeau.config;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ConfigReader
 */
public class ConfigReader {
    public static Config readConfig(String configFile) {
        Config config;
        try{
            ObjectMapper om = new ObjectMapper();
            config = om.readValue(new File(configFile), Config.class);
            return config;
        }catch(Exception e){
            e.printStackTrace();
            //go with defaults?
        }
        
        
        return null;
    }
    
}