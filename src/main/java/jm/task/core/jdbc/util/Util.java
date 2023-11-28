package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
  private static SessionFactory sessionFactory;
  private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
  private static final String DB_USERNAME = "root1";
  private static final String DB_PASSWORD = "root1";

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Properties properties = createProperties();
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class).
            setProperties(properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.out.println("SessionFactory was SUCCESSFULLY built");
      } catch (Exception e) {
        System.out.println("SessionFactory was NOT built");
      }
    }
    return sessionFactory;
  }
  private static Properties createProperties() {
    Properties setting = new Properties();
    setting.put(Environment.DRIVER, DB_DRIVER);
    setting.put(Environment.URL, DB_URL + "?useSSL=false");
    setting.put(Environment.USER, DB_USERNAME);
    setting.put(Environment.PASS, DB_PASSWORD);
    setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
    setting.put(Environment.SHOW_SQL, "true");
    setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    setting.put(Environment.HBM2DDL_AUTO, "create");
    return setting;
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(DB_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      System.out.println("Conn OK");
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Conn is not exist");
    }
    return connection;
  }
}
