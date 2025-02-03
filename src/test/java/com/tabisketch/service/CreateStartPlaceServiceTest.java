package com.tabisketch.service;

import com.tabisketch.bean.entity.ExampleStartPlace;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.mapper.IStartPlacesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateStartPlaceServiceTest {
    @MockitoBean
    private IStartPlacesMapper mapper;
    @Autowired
    private ICreateStartPlaceService service;

    @Test
    public void testExecute() throws FailedInsertException, FailedSelectException {
        final var entity = ExampleStartPlace.generate();

        when(this.mapper.insert(entity)).thenReturn(1);
        when(this.mapper.selectById(entity.getId())).thenReturn(entity);
        this.service.execute(entity);

        verify(this.mapper).insert(entity);
        verify(this.mapper).selectById(entity.getId());
    }
}
