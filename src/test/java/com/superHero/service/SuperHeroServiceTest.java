package com.superHero.service;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.HeroApplication;
import com.superHero.Repository.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = HeroApplication.class)
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
        when(repository.getReferenceById("Id1")).thenReturn(expectedSuperHeroDTO);
        SuperHeroDTO actualSuperHeroDTO = superHeroService.getSuperHeroById("Id1");
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
    public void saveSuperHeroTest_nullId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero(null,new SuperHeroDTO(null,null));
        });
    }
    @Test
    public void saveSuperHeroTest_diferentId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero("1",new SuperHeroDTO("2",null));
        });
    }
    @Test
    public void saveSuperHeroTest_emptyId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero("",new SuperHeroDTO("2","man"));
        });
    }

    @Test
    public void deleteSuperHeroTest_nullParameter(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.deleteSuperHero(null);
        });
    }
    @Test
    public void deleteSuperHeroTest_emptyParameter(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.deleteSuperHero("");
        });
    }

    @Test
    public void deleteSuperHeroTest_ValidateOnesDeletion(){
        superHeroService.deleteSuperHero("1");
        verify(repository,times(1)).deleteById("1");

    }

}
