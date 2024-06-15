package Activities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    public  ArrayList<String> list;

    @BeforeEach
    void setUp(){
        list = new ArrayList<String>();
        list.add("alpha");
        list.add("beta");
    }
    @Test
    void insertTest(){
        assertEquals(2, list.size(), "Wrong Size");
        list.add("Gama");
        assertEquals(3, list.size());
        assertEquals("alpha", list.get(0));
        assertEquals("beta", list.get(1));
        assertEquals("Gama", list.get(2));
    }
    @Test
    void replaceTest(){

        list.set(1, "Charlie");
        assertEquals(2, list.size(), "Wrong Size");
        assertEquals("alpha", list.get(0));
        assertEquals("Charlie", list.get(1));

    }
}
