package lambda.using_optionals;

import java.util.Optional;

public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
