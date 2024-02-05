package com.superHero.Controller;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.Repository.SuperHeroRepository;
import com.superHero.service.SuperHeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroRepository superHeroRepository;

   @Test
        void testGetSuperHeroById() {
            // Arrange
            String validId = "1";
            SuperHeroService superHeroService = mock(SuperHeroService.class);
            SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
            expectedSuperHeroDTO.setId(validId);
            expectedSuperHeroDTO.setName("Superman");
            when(superHeroService.getSuperHeroById(validId)).thenReturn(expectedSuperHeroDTO);
            SuperHeroController controller = new SuperHeroController();

            // Act
            ResponseEntity<SuperHeroDTO> responseEntity = controller.getSuperHeroById(validId);

            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(expectedSuperHeroDTO, responseEntity.getBody());
        }

}
