package com.superhero.service;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.HeroApplication;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        SuperHero expectedSuperHeroModel = new SuperHero("Id1","Superman");
        SuperHeroDTO expectedSuperHero = new SuperHeroDTO("Id1","Superman");
        when(repository.getReferenceById("Id1")).thenReturn(expectedSuperHeroModel);
        Optional<SuperHeroDTO> actualSuperHeroDTO = superHeroService.getSuperHeroById("Id1");
        assertEquals(expectedSuperHero, actualSuperHeroDTO.get());
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
        List<SuperHero> superHeroListExpected = new ArrayList<>();
        SuperHero expectedSuperHero = new SuperHero();
        expectedSuperHero.setId("1");
        expectedSuperHero.setName("Superman");
        SuperHero expectedSuperHero2 = new SuperHero();
        expectedSuperHero2.setId("2");
        expectedSuperHero2.setName("Spiderman");
        superHeroListExpected.add(expectedSuperHero);
        superHeroListExpected.add(expectedSuperHero2);
        when(repository.findByNameContainingIgnoreCase("man")).thenReturn(superHeroListExpected);

        List<SuperHero> actualList = superHeroService.getSuperHeroesByName("man");
        assertEquals(expectedSuperHero.getName(), actualList.get(0).getName());
        assertEquals(expectedSuperHero2.getName(), actualList.get(1).getName());
        assertEquals(2,actualList.size());
    }
    @Test
    public void getAllSuperHeroesTest(){
        List<SuperHero> superHeroListExpected = new ArrayList<>();
        SuperHero expectedSuperHero = new SuperHero();
        expectedSuperHero.setId("1");
        expectedSuperHero.setName("Superman");
        SuperHero expectedSuperHero2 = new SuperHero();
        expectedSuperHero2.setId("2");
        expectedSuperHero2.setName("Spiderman");
        superHeroListExpected.add(expectedSuperHero);
        superHeroListExpected.add(expectedSuperHero2);

        when(repository.findAll()).thenReturn(superHeroListExpected);
        List<SuperHero> actualList = superHeroService.getAllSuperHeroes();
        assertEquals(expectedSuperHero.getName(), actualList.get(0).getName());
        assertEquals(expectedSuperHero2.getName(), actualList.get(1).getName());
        assertEquals(2,actualList.size());
    }

    @Test
    public void saveSuperHeroTest_nullId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero(null,new SuperHero(null,null));
        });
    }
    @Test
    public void saveSuperHeroTest_diferentId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero("1",new SuperHero("2",null));
        });
    }
    @Test
    public void saveSuperHeroTest_emptyId(){
        assertThrows(IllegalArgumentException.class,()->{superHeroService.saveSuperHero("",new SuperHero("2","man"));
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
