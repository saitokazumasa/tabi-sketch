//package com.tabisketch.service;
//
//import com.tabisketch.bean.entity.ExampleMAAToken;
//import com.tabisketch.bean.entity.ExampleUser;
//import com.tabisketch.exception.DeleteFailedException;
//import com.tabisketch.exception.SelectFailedException;
//import com.tabisketch.exception.UpdateFailedException;
//import com.tabisketch.mapper.IMAATokensMapper;
//import com.tabisketch.mapper.IUsersMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class AuthMailAddressServiceTest {
//    @MockitoBean
//    private IMAATokensMapper maaTokensMapper;
//    @MockitoBean
//    private IUsersMapper usersMapper;
//    @Autowired
//    private IAuthMailAddressService authMailAddressService;
//
//    @Test
//    public void testExecute() throws DeleteFailedException, UpdateFailedException, SelectFailedException {
//        final var maaToken = ExampleMAAToken.generate("sample2@example.com");
//        final var user = ExampleUser.generate();
//
//        when(this.maaTokensMapper.selectByToken(any())).thenReturn(maaToken);
//        when(this.usersMapper.selectById(anyInt())).thenReturn(user);
//        when(this.usersMapper.updateMailAddress(anyInt(), anyString())).thenReturn(1);
//        when(this.usersMapper.updateMailAddressAuthenticated(anyInt(), anyBoolean())).thenReturn(1);
//        when(this.maaTokensMapper.delete(anyInt())).thenReturn(1);
//
//        this.authMailAddressService.execute(maaToken.getToken().toString());
//
//        verify(this.maaTokensMapper).selectByToken(any());
//        verify(this.usersMapper).selectById(anyInt());
//        verify(this.usersMapper).updateMailAddress(anyInt(), anyString());
//        verify(this.usersMapper).updateMailAddressAuthenticated(anyInt(), anyBoolean());
//        verify(this.maaTokensMapper).delete(anyInt());
//    }
//
//    @Test
//    public void testExecuteWhenEmptyMailAddress() throws DeleteFailedException, UpdateFailedException, SelectFailedException {
//        final var maaToken = ExampleMAAToken.generate("");
//        final var user = ExampleUser.generate();
//
//        when(this.maaTokensMapper.selectByToken(any())).thenReturn(maaToken);
//        when(this.usersMapper.selectById(anyInt())).thenReturn(user);
//        when(this.usersMapper.updateMailAddressAuthenticated(anyInt(), anyBoolean())).thenReturn(1);
//        when(this.maaTokensMapper.delete(anyInt())).thenReturn(1);
//
//        this.authMailAddressService.execute(maaToken.getToken().toString());
//
//        verify(this.maaTokensMapper).selectByToken(any());
//        verify(this.usersMapper).selectById(anyInt());
//        verify(this.usersMapper).updateMailAddressAuthenticated(anyInt(), anyBoolean());
//        verify(this.maaTokensMapper).delete(anyInt());
//    }
//}
