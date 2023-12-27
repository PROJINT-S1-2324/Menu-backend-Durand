package eafcuccle.be.backend;

import eafcuccle.be.backend.model.Plat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class BackendApplicationTests {



    public static class PlatTest {

        @Test
        public void testGetterAndSetter() {
            Plat plat = new Plat();

            plat.setId(1L);
            plat.setNom("Plat de Test");
            plat.setPrix(10.0);

            assertEquals(1L, plat.getId());
            assertEquals("Plat de Test", plat.getNom());
            assertEquals(10.0, plat.getPrix(), 0.01);
        }

        @Test
        public void testConstructor() {
            Plat plat = new Plat("Plat de Test", 10.0);

            assertEquals(1L, plat.getId());
            assertEquals("Plat de Test", plat.getNom());
            assertEquals(10.0, plat.getPrix(), 0.01);
        }

        @Test
        public void testEquals() {
            Plat plat1 = new Plat("Plat 1", 10.0);
            Plat plat2 = new Plat("Plat 1", 10.0);
            Plat plat3 = new Plat("Plat 2", 15.0);

            assertEquals(plat1, plat2);
            assertNotEquals(plat1, plat3);
        }

        @Test
        public void testHashCode() {
            Plat plat1 = new Plat("Plat 1", 10.0);
            Plat plat2 = new Plat("Plat 1", 10.0);
            Plat plat3 = new Plat("Plat 2", 15.0);

            assertEquals(plat1.hashCode(), plat2.hashCode());
            assertNotEquals(plat1.hashCode(), plat3.hashCode());
        }
    }
}
