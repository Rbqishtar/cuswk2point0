package uicontrol;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCtrlTest {

    @Test
    void calculateMealExtra() {
        assertEquals(new FoodCtrl().calculateMealExtra("Gourmet"), 10);
        assertEquals(new FoodCtrl().calculateMealExtra("Hyperfresh"), 100);
        assertEquals(new FoodCtrl().calculateMealExtra("Halal"), 0);
        assertEquals(new FoodCtrl().calculateMealExtra("Regular"), 0);
        assertEquals(new FoodCtrl().calculateMealExtra("Haldkewjopifcjiopwdefhoierhfeal"), 0);
    }

    @Test
    void confirmNothingOptionYESYES() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Nothing");
        assertTrue(new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothingOptionYESNO() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Nothing");
        assertTrue(!new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothingOptionNOYES() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Nothing");
        assertTrue(!new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothingOptionNONO() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Nothing");
        assertTrue(!new FoodCtrl().confirmNothingOption(odr));
    }


    @Test
    void confirmNothingOptionNOYESNEEDED() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothi31212ng");
        odr.setFoodType("Not12312312312323hing");
        assertTrue(new FoodCtrl().confirmNothingOption(odr));
    }
}