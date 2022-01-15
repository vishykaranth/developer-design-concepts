//https://blog.indrek.io/articles/whats-new-in-java-9-streams/

import java.util.stream.Stream;

public class Features {

    public void takeWhile(){
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
