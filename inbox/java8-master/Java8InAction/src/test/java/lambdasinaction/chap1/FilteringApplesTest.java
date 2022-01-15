package lambdasinaction.chap1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class FilteringApplesTest {

    private FilteringApples filteringApples;

    @Before
    public void init(){
        filteringApples = new FilteringApples();
    }

    @Test
    public void testGreenApple(){
        //Given

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //When
        List<Apple> greenApples = filteringApples.filterApples(inventory, FilteringApples::isGreenApple);


        //Then
        List<Apple> expected = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"));
        assertArrayEquals(greenApples.toArray(), expected.toArray());
    }

    @Test
    public void testHeavyApples(){
        //Given
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        //When
        List<Apple> heavyApples = filteringApples.filterApples(inventory, FilteringApples::isHeavyApple);

        //Then
        List<Apple> expected = Arrays.asList(new Apple(155, "green"));
        assertArrayEquals(heavyApples.toArray(), expected.toArray());
    }

    @Test
    public void testGreenApple2(){
        //Given

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //When
        List<Apple> greenApples = filteringApples.filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));


        //Then
        List<Apple> expected = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"));
        assertArrayEquals(greenApples.toArray(), expected.toArray());
    }

    @Test
    public void testHeavyApples2(){
        //Given
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        //When
        List<Apple> heavyApples = filteringApples.filterApples(inventory, (Apple a) -> a.getWeight() > 150);

        //Then
        List<Apple> expected = Arrays.asList(new Apple(155, "green"));
        assertArrayEquals(heavyApples.toArray(), expected.toArray());
    }

    @Test
    public void testWeirdApple(){
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // []
        List<Apple> weirdApples = filteringApples.filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        assertTrue(weirdApples.isEmpty());
    }
}