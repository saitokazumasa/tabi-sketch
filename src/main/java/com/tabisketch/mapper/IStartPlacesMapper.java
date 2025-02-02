package com.tabisketch.mapper;

import com.tabisketch.bean.entity.StartPlace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IStartPlacesMapper {
    @Insert("INSERT INTO start_places (place_id, departure_time, destination_list_id) " +
            "   VALUES (#{placeId}, #{departureTime}, #{destinationListId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final StartPlace record);
}
