package com.lmyxlf.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * PercentageDoubleTypeHandler：百分比双精度类型处理程序
 * 实现数据库 varchar 类型的数据向 Double 类型转换（去除 % 和 h）
 *
 * @author lmy
 */
@Component
public class PercentageDoubleTypeHandler extends BaseTypeHandler<Double> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Double parameter, JdbcType jdbcType) throws SQLException {
        ps.setDouble(i, parameter);
    }

    @Override
    public Double getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return parsePercentageDouble(value);
    }

    @Override
    public Double getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return parsePercentageDouble(value);
    }

    @Override
    public Double getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return parsePercentageDouble(value);
    }

    private Double parsePercentageDouble(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String cleanedValue = value.replace("%", "").replace("h", "");
        return Double.parseDouble(cleanedValue);
    }
}