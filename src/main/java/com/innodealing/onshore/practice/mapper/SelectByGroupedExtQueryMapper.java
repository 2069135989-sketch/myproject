package com.innodealing.onshore.practice.mapper;

import com.github.wz2cool.dynamic.GroupedQuery;
import com.github.wz2cool.dynamic.mybatis.mapper.SelectByGroupedQueryMapper;
import com.github.wz2cool.dynamic.mybatis.mapper.constant.MapperConstants;
import com.innodealing.onshore.practice.provider.GroupedExtQueryProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * 分组查询扩展类
 * <p>
 * 与原项目 {@code com.innodealing.onshore.bondprimary.mapper.SelectByGroupedExtQueryMapper} 一致：
 * 在 tk/mybatis 的 {@link SelectByGroupedQueryMapper} 之上扩展一个带“表名参数”的分组聚合方法，
 * 由 {@link GroupedExtQueryProvider} 在运行时拼 SQL（不依赖 XML）。
 *
 * @param <Q> 泛型 query（源实体）
 * @param <S> 泛型 select（聚合结果实体）
 */
@RegisterMapper
public interface SelectByGroupedExtQueryMapper<Q, S> extends SelectByGroupedQueryMapper<Q, S> {

    /**
     * 按 table 进行聚合查询
     *
     * @param groupedQuery grouped query
     * @param table        表名（支持 schema.table 形式）
     * @return list of item
     */
    @SelectProvider(type = GroupedExtQueryProvider.class, method = "dynamicSQL")
    List<S> selectByGroupedQueryWithTable(@Param(MapperConstants.GROUPED_QUERY) GroupedQuery<Q, S> groupedQuery,
                                          @Param("table") String table);

}
