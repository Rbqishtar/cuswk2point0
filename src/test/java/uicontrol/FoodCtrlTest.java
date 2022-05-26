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
    void confirmNothingOption() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Nothing");
        assertFalse(new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothingOpt2131ion() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothin2323132123g");
        odr.setFoodType("Nothing");
        assertTrue(new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothin123123gOption() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothing");
        odr.setFoodType("Not232131223hing");
        assertTrue(new FoodCtrl().confirmNothingOption(odr));
    }

    @Test
    void confirmNothi12213213ngOption() {
        entity.Order odr = new entity.Order();
        odr.setDrink("Nothi31212ng");
        odr.setFoodType("Not12312312312323hing");
        assertTrue(new FoodCtrl().confirmNothingOption(odr));
    }
}