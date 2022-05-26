package entitydao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuDAOTest {

    @Test
    void getMenu1() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("h1");
        arrayList.add("h2");
        arrayList.add("h3");
        arrayList.add("h4");
        assertEquals(new MenuDAO().getMenu("Halal"), arrayList);
    }

    @Test
    void getMenu2() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("g1");
        arrayList.add("g2");
        arrayList.add("g3");
        arrayList.add("g4");
        assertEquals(new MenuDAO().getMenu("Gourmet"), arrayList);
    }

    @Test
    void getMenu3() {
        ArrayList<String> arrayList = new ArrayList<String>();
        assertNotEquals(new MenuDAO().getMenu("Halal"), arrayList);
    }

    @Test
    void getMenu4() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("r1");
        arrayList.add("r2");
        arrayList.add("r3");
        arrayList.add("r4");
        assertEquals(new MenuDAO().getMenu("Regular"), arrayList);
    }


}