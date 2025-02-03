package com.tabisketch.mapper;

import com.tabisketch.bean.entity.StartPlace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IStartPlacesMapper {
    @Insert("INSERT INTO start_places (place_id, departure_datetime, destination_list_id) " +
            "   VALUES (#{placeId}, #{departureDateTime}, #{destinationListId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final StartPlace record);

    @Select("SELECT * FROM start_places WHERE id = #{id}")
    StartPlace selectById(final int id);
}
