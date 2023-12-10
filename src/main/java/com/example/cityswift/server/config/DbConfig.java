package com.example.cityswift.server.config;

public class DbConfig {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/CitySwift";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "admin";
    private static final int MAX_POOL_SIZE = 10;

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getDatabaseUser() {
        return DATABASE_USER;
    }

    public static String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }

    public static int getMaxPoolSize() {
        return MAX_POOL_SIZE;
    }


}
