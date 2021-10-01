package org.example.main_test;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class test {
    @Test
    public void test() {
        assertTrue(main.luckyTicket("123321"));
    }

    @Test
    public void test1() {
        assertFalse(main.luckyTicket("123341"));
    }

}
