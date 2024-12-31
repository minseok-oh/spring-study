package com.java.study;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Configuration
public class DatabaseConfig {

    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Bean
    public CommandLineRunner initialize() {
        return args -> {
            try (
                    Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    Statement statement = connection.createStatement();
            ) {
                String dropStatement = "DROP TABLE IF EXISTS %s";
                String createStatement = "CREATE TABLE %s (%s)";

                List<Class<?>> tableAnnotatedClasses = getTableAnnotatedClasses();
                for (Class<?> clazz : tableAnnotatedClasses) {
                    statement.execute(String.format(dropStatement, clazz.getSimpleName()));
                    statement.execute(String.format(createStatement, clazz.getSimpleName(),
                            getCreateFieldsQuery(clazz.getDeclaredFields())));
                }

                connection.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static List<Class<?>> getTableAnnotatedClasses() {
        String packageName = "com.java.study";

        String path = packageName.replace('.', '/');
        URL root = Thread.currentThread().getContextClassLoader().getResource(path);

        if (root == null) {
            throw new IllegalArgumentException("Invalid package path: " + path);
        }

        File directory = new File(root.getFile());
        List<Class<?>> tableClasses = new ArrayList<>();

        scanDirectory(directory, packageName, tableClasses);

        return tableClasses.stream().filter((clazz) -> clazz.isAnnotationPresent(Table.class)).toList();
    }

    private static void scanDirectory(File directory, String packageName, List<Class<?>> classes) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");

                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static String getCreateFieldsQuery(Field[] fields) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            builder.append(field.getName());

            if (field.getType() == long.class) {
                builder.append(" BIGINT");
            } else if (field.getType() == String.class) {
                builder.append(" VARCHAR(255)");
            }

            if (field.isAnnotationPresent(Id.class)) {
                builder.append(" PRIMARY KEY");
            }

            if (i != fields.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
