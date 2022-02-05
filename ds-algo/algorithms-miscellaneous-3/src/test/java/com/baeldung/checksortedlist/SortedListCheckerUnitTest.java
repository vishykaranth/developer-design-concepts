package com.baeldung.checksortedlist;

import static com.baeldung.checksortedlist.SortedListChecker.checkIfSortedUsingIterativeApproach;
import static com.baeldung.checksortedlist.SortedListChecker.checkIfSortedUsingOrderingClass;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SortedListCheckerUnitTest {

    private List<String> sortedListOfString;
    private List<String> unsortedListOfString;
    private List<String> singletonList;

    private List<Employee> employeesSortedByName;
    private List<Employee> employeesNotSortedByName;

    @Before
    public void setUp() {
        sortedListOfString = asList("Canada", "HK", "LA", "NJ", "NY");
        unsortedListOfString = asList("LA", "HK", "NJ", "NY", "Canada");
        singletonList = Collections.singletonList("NY");

        employeesSortedByName = asList(new Employee(1L, "John"), new Employee(2L, "Kevin"), new Employee(3L, "Mike"));
        employeesNotSortedByName = asList(new Employee(1L, "Kevin"), new Employee(2L, "John"), new Employee(3L, "Mike"));
    }

    @Test
    public void givenSortedList_whenUsingIterativeApproach_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingIterativeApproach(sortedListOfString)).isTrue();
    }

    @Test
    public void givenSingleElementList_whenUsingIterativeApproach_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingIterativeApproach(singletonList)).isTrue();
    }

    @Test
    public void givenUnsortedList_whenUsingIterativeApproach_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingIterativeApproach(unsortedListOfString)).isFalse();
    }

    @Test
    public void givenSortedListOfEmployees_whenUsingIterativeApproach_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingIterativeApproach(employeesSortedByName, Comparator.comparing(Employee::getName))).isTrue();
    }

    @Test
    public void givenUnsortedListOfEmployees_whenUsingIterativeApproach_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingIterativeApproach(employeesNotSortedByName, Comparator.comparing(Employee::getName))).isFalse();
    }

    @Test
    public void givenSortedList_whenUsingRecursion_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingRecursion(sortedListOfString)).isTrue();
    }

    @Test
    public void givenSingleElementList_whenUsingRecursion_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingRecursion(singletonList)).isTrue();
    }

    @Test
    public void givenUnsortedList_whenUsingRecursion_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingRecursion(unsortedListOfString)).isFalse();
    }

    @Test
    public void givenSortedList_whenUsingGuavaOrdering_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingOrderingClass(sortedListOfString)).isTrue();
    }

    @Test
    public void givenUnsortedList_whenUsingGuavaOrdering_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingOrderingClass(unsortedListOfString)).isFalse();
    }

    @Test
    public void givenSortedListOfEmployees_whenUsingGuavaOrdering_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingOrderingClass(employeesSortedByName, Comparator.comparing(Employee::getName))).isTrue();
    }

    @Test
    public void givenUnsortedListOfEmployees_whenUsingGuavaOrdering_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingOrderingClass(employeesNotSortedByName, Comparator.comparing(Employee::getName))).isFalse();
    }

    @Test
    public void givenSortedList_whenUsingGuavaComparators_thenReturnTrue() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingComparators(sortedListOfString)).isTrue();
    }

    @Test
    public void givenUnsortedList_whenUsingGuavaComparators_thenReturnFalse() {
        Assertions.assertThat(SortedListChecker.checkIfSortedUsingComparators(unsortedListOfString)).isFalse();
    }

}
