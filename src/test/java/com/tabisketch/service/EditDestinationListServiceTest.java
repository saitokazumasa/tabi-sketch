package com.tabisketch.service;

import com.tabisketch.bean.entity.ExampleDestinationList;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.mapper.IDestinationListsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EditDestinationListServiceTest {
    @MockitoBean
    private IDestinationListsMapper mapper;
    @Autowired
    private IEditDestinationListService service;

    @Test
    public void testExecute() throws FailedUpdateException {
        final var entity = ExampleDestinationList.generate();

        when(this.mapper.update(entity)).thenReturn(1);
        this.service.execute(entity);

        verify(this.mapper).update(entity);
    }
}
