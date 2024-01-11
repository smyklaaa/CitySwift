package com.example.cityswift.server.model;

public class GlobalConfigModel {
    private int id;
    private String configName;
    private String configValue;

    public GlobalConfigModel() {
    }

    public GlobalConfigModel(int id, String configName, String configValue) {
        this.id = id;
        this.configName = configName;
        this.configValue = configValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
