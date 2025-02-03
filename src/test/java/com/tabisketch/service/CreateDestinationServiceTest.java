package com.tabisketch.service;

import com.tabisketch.bean.entity.ExampleDestination;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.mapper.IDestinationsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateDestinationServiceTest {
    @MockitoBean
    private IDestinationsMapper mapper;
    @Autowired
    private ICreateDestinationService service;

    @Test
    public void testExecute() throws FailedInsertException {
        final var entity = ExampleDestination.generate();

        when(this.mapper.insert(entity)).thenReturn(1);
        this.service.execute(entity);

        verify(this.mapper).insert(entity);
    }
}
