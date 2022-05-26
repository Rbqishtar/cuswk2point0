package entitydao;

import entity.Plane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneDAOTest {

    @Test
    void getPlane() {
        Plane p = new Plane();
        String[] eachline = new String[]{"AAAAAA", "B738", "6", "180"};
        p.setRegisterno((String) eachline[0]);
        p.setModel((String) eachline[1]);
        p.setColumnNum((String) eachline[2]);
        p.setMaxPsgr((String) eachline[3]);
        assertEquals(new PlaneDAO().getPlane("AAAAAA"), p);
    }
}