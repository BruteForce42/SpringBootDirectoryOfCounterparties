package com.zatsepin.controller;

import com.zatsepin.SpringBootDirectoryOfCounterpartiesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBootDirectoryOfCounterpartiesApplication.class)
@AutoConfigureMockMvc
class CounterpartyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CounterpartyController counterpartyController;

    @Test
    public void contextLoads() {
        assertThat(counterpartyController).isNotNull();
    }

    @Test
    void list() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties"))
                .andExpect(view().name("counterparties"))
                .andExpect(status().isOk());
    }

    @Test
    void showById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties/1"))
                .andExpect(view().name("show"))
                .andExpect(status().isOk());
    }

    @Test
    void updateForm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties/edit/1"))
                .andExpect(view().name("update"))
                .andExpect(status().isOk());
    }

    @Test
    void createForm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties/new"))
                .andExpect(view().name("new"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/counterparties")
                        .param("name", "Контрагент")
                        .param("inn", "5503248039")
                        .param("kpp", "550401001")
                        .param("accountNumber", "40702810445000093711")
                        .param("bic", "045209673")
                        .param("comment", "Комментарий"))
                .andExpect(redirectedUrl("/counterparties/10"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders.patch("/counterparties/4")
                        .param("id", "4")
                        .param("name", "Водоканал")
                        .param("inn", "5504097128")
                        .param("kpp", "550401001")
                        .param("accountNumber", "40702810045370100747")
                        .param("bic", "045209673")
                        .param("comment", "Обновлённый комментарий"))
                .andExpect(redirectedUrl("/counterparties/4"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void findByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties/name")
                        .param("name", "Водоканал"))
                .andExpect(view().name("show"))
                .andExpect(status().isOk());
    }

    @Test
    void findByAccountNumberAndBic() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/counterparties/accountandbic")
                        .param("accountNumber", "40603810701800000001")
                        .param("bic", "045004867"))
                .andExpect(view().name("show"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/counterparties/9"))
                .andExpect(redirectedUrl("/counterparties"))
                .andExpect(status().is3xxRedirection());
    }
}