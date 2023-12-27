import eafcuccle.be.backend.model.Commande;
import eafcuccle.be.backend.model.Plat;
import eafcuccle.be.backend.repository.PlatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CommandeTest {

    @Mock
    private PlatRepository platRepository;

    public CommandeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetterAndSetter() {

        Plat plat = new Plat("Plat de Test", 10.0);


        Commande commande = new Commande(plat, 3);

        when(platRepository.save(plat)).thenReturn(plat);

        assertEquals(plat, commande.getPlat());
        assertEquals(3, commande.getQuantite());
    }

    @Test
    public void testConstructor() {

        Plat plat = new Plat("Plat de Test", 10.0);

        Commande commande = new Commande(plat, 5);

        when(platRepository.save(plat)).thenReturn(plat);

        assertEquals(plat, commande.getPlat());
        assertEquals(5, commande.getQuantite());
    }
}