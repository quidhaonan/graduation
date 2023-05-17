package com.lmyxlf.config;

import java.sql.*;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;


/**
 * 将时间进行格式化：2019-10-29T16:00:00.000+00:00   --> 2019-10-29
 *
 * @author lmy
 */
@Component
public class DateTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        // 自己编写，原本是没实现此方法
        Date date = callableStatement.getDate(i);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }
}