package org.smart4j.test;

import org.junit.Test;
import org.smart4j.framework.helper.ConfigHelper;

import static org.junit.Assert.assertNotNull;

public class ConfigHelperTest {

    @Test
    public void getConfigValueNotNull(){
        assertNotNull(ConfigHelper.getAppAssetPath());
        assertNotNull(ConfigHelper.getAppBasePackage());
        assertNotNull(ConfigHelper.getAppJspPath());
        assertNotNull(ConfigHelper.getJdbcDriver());
        assertNotNull(ConfigHelper.getJdbcPassword());
        assertNotNull(ConfigHelper.getJdbcUrl());
        assertNotNull(ConfigHelper.getJdbcUsername());
    }
}
