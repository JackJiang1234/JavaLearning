package org.smart4j.framework;

/**
 * 配置常量定义
 *
 * @author jack
 * @since 1.0.0
 * */
public interface ConfigConstant {
    String CONFIG_FILE = "smart.properties";

    String JDBC_DEIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    String APP_ASSERT_PATH = "smart.framework.app.asset-path";
}
