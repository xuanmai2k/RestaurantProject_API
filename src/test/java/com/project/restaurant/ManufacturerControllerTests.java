//package com.project.restaurant;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.restaurant.dtos.PageDTO;
//import com.project.restaurant.category.entities.Category;
//import com.project.restaurant.category.services.CategoryService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * I will define CategoryControllerTests to unit test the Rest API endpoints
// * which has the following methods: POST, GET, PUT and DELETE
// *
// * @author xuanmai
// * @since 2023-11-07
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ManufacturerControllerTests {
//
//    @MockBean
//    private CategoryService manufacturerService;
//
//    @Autowired
//    protected MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Value("${manufacturer}")
//    private String endpoint;
//
//    @Value("${manufacturer}/{id}")
//    private String getEndpoint;
//
//    @Test
//    @WithMockUser(authorities = "ROLE_ADMIN")
//    void shouldCreateManufacturer() throws Exception {
//
//        Category manufacturer = new Category(1L, "apple");
//
//        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(manufacturer)))
//                .andExpect(status().isCreated())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldCreateManufacturerForbidden() throws Exception {
//
//        Category manufacturer = new Category(1L, "apple");
//
//        mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(manufacturer)))
//                .andExpect(status().isForbidden())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_ADMIN")
//    void shouldUpdateManufacturer() throws Exception {
//
//        Long id = 1L;
//        Category manufacturer = new Category(id, "apple");
//        Category updateManufacturer = new Category(id, "samsung");
//
//        when(manufacturerService.getManufacturerById(id)).thenReturn(Optional.of(manufacturer));
//        when(manufacturerService.save(any(Category.class))).thenReturn(updateManufacturer);
//
//        mockMvc.perform(put(getEndpoint, id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateManufacturer)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.manufacturerName").value(updateManufacturer.getManufacturerName()))
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldUpdateManufacturerForbidden() throws Exception {
//
//        Long id = 1L;
//        Category manufacturer = new Category(id, "apple");
//        Category updateManufacturer = new Category(id, "samsung");
//
//        when(manufacturerService.getManufacturerById(id)).thenReturn(Optional.of(manufacturer));
//        when(manufacturerService.save(any(Category.class))).thenReturn(updateManufacturer);
//
//        mockMvc.perform(put(getEndpoint, id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateManufacturer)))
//                .andExpect(status().isForbidden())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_ADMIN")
//    void shouldDeleteManufacturer() throws Exception {
//        Long id = 1L;
//        Category manufacturer = new Category(id, "apple");
//
//        when(manufacturerService.getManufacturerById(id)).thenReturn(Optional.of(manufacturer));
//
//        doNothing().when(manufacturerService).delete(id);
//        mockMvc.perform(delete(getEndpoint, id))
//                .andExpect(status().isNoContent())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldDeleteManufacturerForbidden() throws Exception {
//        Long id = 1L;
//        Category manufacturer = new Category(id, "apple");
//
//        when(manufacturerService.getManufacturerById(id)).thenReturn(Optional.of(manufacturer));
//
//        doNothing().when(manufacturerService).delete(id);
//        mockMvc.perform(delete(getEndpoint, id))
//                .andExpect(status().isForbidden())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_ADMIN")
//    void shouldDeleteManufacturerNotFound() throws Exception {
//        Long id = 1L;
//
//        doNothing().when(manufacturerService).delete(id);
//        mockMvc.perform(delete(getEndpoint, id))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldReturnManufacturerById() throws Exception {
//        Long id = 1L;
//        Category manufacturer = new Category(id, "apple");
//
//        when(manufacturerService.getManufacturerById(id)).thenReturn(Optional.of(manufacturer));
//
//        mockMvc.perform(get(getEndpoint, id)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.manufacturerName").value(manufacturer.getManufacturerName()))
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldReturnManufacturerByIdNotFound() throws Exception {
//        Long id = 1L;
//
//        mockMvc.perform(get(getEndpoint, id))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldReturnPageOfManufacturer() throws Exception {
//
//        List<Category> manufacturerList = Arrays.asList(
//                new Category(1L, "apple"),
//                new Category(2L, "samsung")
//        );
//
//        Page<Category> manufacturerPage = new PageImpl<>(manufacturerList);
//
//        PageDTO pageDTO = new PageDTO();
//
//        when(manufacturerService.listAll(pageDTO)).thenReturn(manufacturerPage);
//        mockMvc.perform(get(endpoint)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pageDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content", hasSize(2)))
//                .andDo(print());
//    }
//
//    @Test
//    @WithMockUser(authorities = "ROLE_USER")
//    void shouldReturnPageOfManufacturerByKeyword() throws Exception {
//
//        List<Category> manufacturerList = Arrays.asList(
//                new Category(1L, "apple"),
//                new Category(2L, "apple1")
//        );
//
//        Page<Category> manufacturerPage = new PageImpl<>(manufacturerList);
//
//        PageDTO pageDTO = new PageDTO();
//
//        when(manufacturerService.search("apple", pageDTO)).thenReturn(manufacturerPage);
//        mockMvc.perform(get(endpoint + "/search")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pageDTO))
//                        .param("keyword", "apple"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content", hasSize(2)))
//                .andDo(print());
//    }
//}
