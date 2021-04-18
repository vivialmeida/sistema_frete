package br.com.frete.mapper.typehandler;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DefaultTypeHandler<T extends Enum> extends BaseTypeHandler<T> {

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        T enumValue = getter(rs, columnName);
        if (rs.wasNull())
            return null;
        return enumValue;
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        T enumValue = getter(rs, columnIndex);
        if (rs.wasNull())
            return null;
        return enumValue;
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        T enumValue = getter(cs, columnIndex);
        if (cs.wasNull())
            return null;
        return enumValue;
    }

    protected T getter(ResultSet rs, String columnName) throws SQLException {
        T[] values = values();
        int value = rs.getInt(columnName);
        if (value < 0 || value >= values.length) return null;
        return values()[value];
    }

    protected T getter(ResultSet rs, int columnIndex) throws SQLException {
        T[] values = values();
        int value = rs.getInt(columnIndex);
        if (value < 0 || value >= values.length) return null;
        return values()[value];
    }

    protected T getter(CallableStatement cs, int columnIndex) throws SQLException {
        T[] values = values();
        int value = cs.getInt(columnIndex);
        if (value < 0 || value >= values.length) return null;
        return values()[value];
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        setter(ps, i, parameter);
    }

    protected void setter(PreparedStatement ps, int i, T parameter) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    protected abstract T[] values();

}