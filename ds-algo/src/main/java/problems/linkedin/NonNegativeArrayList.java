package problems.linkedin;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class NonNegativeArrayList extends ArrayList<Integer> {

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet() {{
            add(1);
            add(-3);
            add(10);
            add(0);
        }};

        ArrayList<Integer> arrayList = new ArrayList<>() {{
            add(-17);
            add(11);
            add(48);
            add(-8);
            add(-5);
        }};

        List<Integer> hashNonNegativeList = new NonNegativeArrayList(hashSet);
        List<Integer> arrayNonNegativeList = new NonNegativeArrayList(
                arrayList);
        List<Integer> nonNegativeArrayList = new NonNegativeArrayList(
                -10, 2, 3, -2, 0);

        System.out.println(hashNonNegativeList);
        System.out.println(arrayNonNegativeList);
        System.out.println(nonNegativeArrayList);
    }

    public NonNegativeArrayList(Integer... numbers) {
        super(new NonNegativeArrayList(Arrays.asList(numbers)));
    }

    public NonNegativeArrayList(Collection<Integer> list) {
        super(list.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList()));
    }

    @Override
    public void add(int index, Integer element) {
        if (element >= 0) {
            super.add(index, element);
        }
    }

    @Override
    public boolean add(Integer element) {
        if (element >= 0) {
            return super.add(element);
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return super.addAll(c.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return super.addAll(index, c.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList()));
    }

    @Override
    public Integer set(int index, Integer element) {
        if (element >= 0) {
            return super.set(index, element);
        } else {
            System.out.println("Item is less than 0 so it is not added");
            return element;
        }
    }

    @Override
    public void replaceAll(UnaryOperator<Integer> operator) {
        super.replaceAll(operator);
        super.removeIf(n -> n < 0);
    }

}
