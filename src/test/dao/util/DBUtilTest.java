package dao.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBUtilTest {

    @Test
    public void getConnection() {
        Connection connection = DbUtil.getConnection();
        Assert.assertNotNull(connection);
    }
}
