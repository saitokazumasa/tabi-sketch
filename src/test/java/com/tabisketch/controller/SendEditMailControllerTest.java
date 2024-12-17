package com.tabisketch.controller;

import com.tabisketch.bean.form.EditMailAddressForm;
import com.tabisketch.service.IEditMailAddressService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

@WebMvcTest(SendEditMailController.class)
public class SendEditMailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IEditMailAddressService __; // DIで使用している

    @ParameterizedTest
    @MethodSource("sampleInitSendEditMailForm")
    @WithMockUser(username = "sample@example.com")
    public void getが動作するか(final EditMailAddressForm editMailAddressForm) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/edit/mail"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("editMailAddressForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("editMailAddressForm", editMailAddressForm))
                .andExpect(MockMvcResultMatchers.view().name("user/edit/mail/index"));
    }

    @ParameterizedTest
    @MethodSource("sampleSendEditMailForm")
    @WithMockUser(username = "sample@example.com", password = "$2a$10$G7Emd1ALL6ibttkgRZtBZeX6Qps6lgEGKq.njouwtiuE4uvjD2YMO")
    public void postが動作するか(final EditMailAddressForm editMailAddressForm) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/edit/mail")
                        .flashAttr("editMailAddressForm", editMailAddressForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/edit/mail/send"));
    }

    @ParameterizedTest
    @MethodSource("sampleMailAddress")
    @WithMockUser
    public void sendが動作するか(final String mailAddress) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/edit/mail/send")
                        .flashAttr("mailAddress", mailAddress)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/edit/mail/send"));
    }

    private static Stream<String> sampleMailAddress() {
        final var mailAddress = "sample@example.com";
        return Stream.of(mailAddress);
    }

    private static Stream<EditMailAddressForm> sampleInitSendEditMailForm() {
        final var sendEditMailForm = EditMailAddressForm.empty();
        sendEditMailForm.setCurrentMailAddress("sample@example.com");
        return Stream.of(sendEditMailForm);
    }

    private static Stream<EditMailAddressForm> sampleSendEditMailForm() {
        final var sendEditMailForm = new EditMailAddressForm(
                "sample@example.com",
                "sample2@example.com",
                "password"
        );
        return Stream.of(sendEditMailForm);
    }
}
