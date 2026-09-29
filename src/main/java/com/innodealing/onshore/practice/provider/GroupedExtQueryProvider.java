package com.innodealing.onshore.practice.provider;

import com.github.wz2cool.dynamic.mybatis.mapper.helper.GroupedQuerySqlHelper;
import com.github.wz2cool.dynamic.mybatis.mapper.provider.GroupedQueryProvider;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * GroupedExtQueryProvider
 * <p>
 * 与原项目 {@code com.innodealing.onshore.bondprimary.provider.GroupedExtQueryProvider} 一致：
 * 在 {@link GroupedQueryProvider} 的基础上，把固定的源表名改成由参数 {@code table} 动态传入，
 * 其余 SELECT / WHERE / GROUP BY / HAVING / ORDER BY 仍由 {@link GroupedQuerySqlHelper} 在运行时拼装。
 */
public class GroupedExtQueryProvider extends GroupedQueryProvider {

    /**
     * 构造函数
     *
     * @param mapperClass  mapperClass
     * @param mapperHelper mapperHelper
     */
    public GroupedExtQueryProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据 table 进行聚合查询
     *
     * @param ms MappedStatement
     * @return String
     */
    public String selectByGroupedQueryWithTable(MappedStatement ms) {
        Class<?> selectClass = getSelectClass(ms);
        setResultType(ms, selectClass);
        StringBuilder sql = new StringBuilder();
        sql.append(GroupedQuerySqlHelper.getBindFilterParams(ms.getConfiguration().isMapUnderscoreToCamelCase()));
        sql.append("SELECT");
        // 支持查询指定列（聚合列由结果实体 @Column 注解声明，例如 sum(actual_issue_amount)）
        sql.append(GroupedQuerySqlHelper.getSelectColumnsClause());
        sql.append(" from ${table}");
        sql.append(GroupedQuerySqlHelper.getWhereClause());
        sql.append(GroupedQuerySqlHelper.getGroupByClause());
        sql.append(GroupedQuerySqlHelper.getHavingClause());
        sql.append(GroupedQuerySqlHelper.getSortClause());
        return sql.toString();
    }
}
