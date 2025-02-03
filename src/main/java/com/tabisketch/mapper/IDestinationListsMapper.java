package com.tabisketch.mapper;

import com.tabisketch.bean.entity.DestinationList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IDestinationListsMapper {
    @Insert("INSERT INTO destination_lists (traveL_day, availabel_transportation_list_binary, travel_plan_id)" +
            "   VALUES (#{travelDay}, #{availableTransportationListBinary}, #{travelPlanId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final DestinationList record);

    @Update("UPDATE destination_lists SET " +
            "   traveL_day = #{travelDay}, " +
            "   availabel_transportation_list_binary = #{availableTransportationListBinary} " +
            "WHERE id = #{id}")
    int update(final DestinationList record);
}
