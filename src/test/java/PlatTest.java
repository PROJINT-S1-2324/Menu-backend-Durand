import eafcuccle.be.backend.model.Plat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlatTest {
    @Test
    public void testGetterAndSetter() {
        Plat plat = new Plat();

        plat.setId(1L);
        plat.setNom("Plat de Test");
        plat.setPrix(10.0);

        assertEquals("Plat de Test", plat.getNom());
        assertEquals(10.0, plat.getPrix(), 0.01);
    }

    @Test
    public void testConstructor() {
        Plat plat = new Plat("Plat de Test", 10.0);

        assertEquals("Plat de Test", plat.getNom());
        assertEquals(10.0, plat.getPrix(), 0.01);
    }

    @Test
    public void testEquals() {
        Plat plat1 = new Plat("Plat 1", 10.0);
        Plat plat2 = new Plat("Plat 2", 15.0);

        assertNotEquals(plat1, plat2);
    }

    @Test
    public void testHashCode() {
        Plat plat1 = new Plat("Plat 1", 10.0);
        Plat plat3 = new Plat("Plat 2", 15.0);

        assertNotEquals(plat1.hashCode(), plat3.hashCode());
    }
}