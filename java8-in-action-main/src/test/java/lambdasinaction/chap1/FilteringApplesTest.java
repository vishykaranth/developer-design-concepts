package lambdasinaction.chap1;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;

public class FilteringApplesTest {

    @Test
    public void testGreenApples(){
        List<FilteringApples.Apple> inventory = Arrays.asList(
                new FilteringApples.Apple(80,"green"),
                new FilteringApples.Apple(155, "green"),
                new FilteringApples.Apple(120, "red"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<FilteringApples.Apple> greenApples = FilteringApples.filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);
        assertNotNull(greenApples);
        assertThat(greenApples.size(), is(2));
    }
}