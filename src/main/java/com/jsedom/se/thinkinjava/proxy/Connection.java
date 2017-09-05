package com.jsedom.se.thinkinjava.proxy;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;

/**
 * Created by Administrator on 2017/6/9.
 */

public interface Connection extends Wrapper {
    Statement createStatement() throws SQLException;

    void close() throws SQLException;
}
