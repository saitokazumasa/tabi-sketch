package com.tabisketch.service;

import com.tabisketch.bean.entity.Place;
import com.tabisketch.bean.form.UpdatePlaceForm;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPlacesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdatePlaceServiceTest {
    @Autowired
    private IUpdatePlaceService updatePlaceService;
    @MockitoBean
    private IPlacesMapper placesMapper;

    @Test
    public void testExecute() throws UpdateFailedException {
        final var place = new Place(
                1,
                1,
                1,
                0,
                LocalTime.now(),
                LocalTime.now(),
                null,
                null,
                null,
                null,
                null
        );

        when(this.placesMapper.selectById(anyInt())).thenReturn(place);
        when(this.placesMapper.update(any())).thenReturn(1);

        final var updatePlaceForm = new UpdatePlaceForm(
                1,
                0,
                0,
                LocalTime.of(10, 0),
                LocalTime.of(11,0),
                null,
                null,
                null,
                null,
                null
        );

        this.updatePlaceService.execute(updatePlaceForm);

        verify(this.placesMapper).selectById(anyInt());
        verify(this.placesMapper).update(any());
    }
}
