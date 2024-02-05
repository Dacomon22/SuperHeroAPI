package com.superHero.service;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.Repository.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = com.superHero.main.HeroApplication.class)
public class SuperHeroServiceTest {

    SuperHeroService superHeroService;

    @Mock
    SuperHeroRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        superHeroService = new SuperHeroService(repository);
    }

    @Test
    public void getSuperHeroByIdTest_nullParameter(){
    assertThrows(IllegalArgumentException.class,()->{superHeroService.getSuperHeroById(null);
    });
    }

    @Test
    public void getSuperHeroByIdTest_emptyParameter(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.getSuperHeroById("");
        });
    }

    @Test
    public void getSuperHeroByIdTest_correctParam(){
        SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
        expectedSuperHeroDTO.setId("Id1");
        expectedSuperHeroDTO.setName("Superman");
        when(repository.getReferenceById("")).thenReturn(expectedSuperHeroDTO);
        SuperHeroDTO actualSuperHeroDTO = superHeroService.getSuperHeroById("1");
        assertEquals(expectedSuperHeroDTO, actualSuperHeroDTO);
    }

    @Test
    public void getSuperHeroesByNameTest_nullParam(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.getSuperHeroesByName(null);
        });
    }
    @Test
    public void getSuperHeroesByNameTest_emptyParam(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.getSuperHeroesByName("");
        });
    }

    @Test
    public void getSuperHeroByNameTest_correctParam(){
        List<SuperHeroDTO> superHeroListExpected = new ArrayList<>();
        SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
        expectedSuperHeroDTO.setId("1");
        expectedSuperHeroDTO.setName("Superman");
        SuperHeroDTO expectedSuperHeroDTO2 = new SuperHeroDTO();
        expectedSuperHeroDTO2.setId("2");
        expectedSuperHeroDTO2.setName("Spiderman");
        superHeroListExpected.add(expectedSuperHeroDTO);
        superHeroListExpected.add(expectedSuperHeroDTO2);
        when(repository.findByNameContaining("man")).thenReturn(superHeroListExpected);

        List<SuperHeroDTO> actualList = superHeroService.getSuperHeroesByName("man");
        assertEquals(expectedSuperHeroDTO.getName(), actualList.get(0).getName());
        assertEquals(expectedSuperHeroDTO2.getName(), actualList.get(1).getName());
        assertEquals(2,actualList.size());
    }
    @Test
    public void getAllSuperHeroesTest(){
        List<SuperHeroDTO> superHeroListExpected = new ArrayList<>();
        SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
        expectedSuperHeroDTO.setId("1");
        expectedSuperHeroDTO.setName("Superman");
        SuperHeroDTO expectedSuperHeroDTO2 = new SuperHeroDTO();
        expectedSuperHeroDTO2.setId("2");
        expectedSuperHeroDTO2.setName("Spiderman");
        superHeroListExpected.add(expectedSuperHeroDTO);
        superHeroListExpected.add(expectedSuperHeroDTO2);

        when(repository.findAll()).thenReturn(superHeroListExpected);
        List<SuperHeroDTO> actualList = superHeroService.getAllSuperHeroes();
        assertEquals(expectedSuperHeroDTO.getName(), actualList.get(0).getName());
        assertEquals(expectedSuperHeroDTO2.getName(), actualList.get(1).getName());
        assertEquals(2,actualList.size());
    }

    @Test
    public void saveSuperHeroTest_nullParameters(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero(null,new SuperHeroDTO(null,null));
        });
    }
    @Test
    public void saveSuperHeroTest_emptyParameters(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero("",new SuperHeroDTO("",""));
        });
    }

    @Test
    public void updateSuperHeroTest(){
        SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
        expectedSuperHeroDTO.setId("1");
        expectedSuperHeroDTO.setName("Superman");
        when(repository.save(expectedSuperHeroDTO)).thenReturn(expectedSuperHeroDTO);

        SuperHeroDTO actual = superHeroService.getSuperHeroById("1");
        assertEquals(expectedSuperHeroDTO.getName(), actual.getName());
    }

    @Test
    public void deleteSuperHeroTest(){
        SuperHeroDTO expectedSuperHeroDTO = new SuperHeroDTO();
        expectedSuperHeroDTO.setId("1");
        expectedSuperHeroDTO.setName("Superman");
        when(repository.save(expectedSuperHeroDTO)).thenReturn(expectedSuperHeroDTO);
        superHeroService.deleteSuperHero(expectedSuperHeroDTO);
    }

}
