package lambda.using_optionals;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return null;
//        return persons.java9_features.stream()
//                .map(Person::getCar)
//                .map(optCar -> optCar.flatMap(Car::getInsurance))
//                .map(optInsurance -> optInsurance.map(Insurance::getName))
//                .flatMap(Optional::java9_features.stream)
//                .collect(toSet());
    }
}
