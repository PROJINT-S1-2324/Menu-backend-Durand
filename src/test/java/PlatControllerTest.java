import eafcuccle.be.backend.Authorization.UserRepository;
import eafcuccle.be.backend.controller.PlatController;
import eafcuccle.be.backend.model.Plat;
import eafcuccle.be.backend.repository.PlatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlatControllerTest {

    @InjectMocks
    private PlatController platController;

    @Mock
    private PlatRepository platRepository;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetPlats() {
        Plat plat = new Plat();
        when(platRepository.findAll()).thenReturn(Collections.singletonList(plat));

        ResponseEntity<List<Plat>> responseEntity = platController.getPlats();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(plat), responseEntity.getBody());
        verify(platRepository, times(1)).findAll();
    }
}