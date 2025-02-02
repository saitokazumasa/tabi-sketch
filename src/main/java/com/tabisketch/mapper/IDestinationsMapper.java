package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Destination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IDestinationsMapper {
    @Insert("INSERT INTO destinations (" +
            "   place_id, " +
            "   specified_start_time, " +
            "   duration_minutes, " +
            "   budget, " +
            "   destination_list_id" +
            ") VALUES (" +
            "   #{placeId}, " +
            "   #{specifiedStartTime}, " +
            "   #{durationMinutes}, " +
            "   #{budget}, " +
            "   #{destinationListId}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final Destination record);
}
