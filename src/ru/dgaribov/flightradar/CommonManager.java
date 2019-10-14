package ru.dgaribov.flightradar;

import java.util.*;

public class CommonManager {

    /*
     * Finds a common manager for two given employees. If one of them is a manger of another - return him
     *
     * Input: number of employees,
     * two employees we want to find a common manager for,
     * sequence of manager -> subordinant (first row in this sequence contains top manager)
     */

    static void OutputCommonManager(int count, Scanner in) {

        String emp1 = in.nextLine();
        String emp2 = in.nextLine();

        // Manager -> SubOrdinant
        Map<String, List<String>> employeesMap = new HashMap<>();

        String newLine = in.nextLine();
        String[] newLineArr = newLine.split(" ");

        employeesMap.put(newLineArr[0], new ArrayList<>(2));
        employeesMap.get(newLineArr[0]).add(newLineArr[1]);
        String top = newLineArr[0];
        while (in.hasNext()) {
            newLine = in.nextLine();
            newLineArr = newLine.split(" ");
            employeesMap.computeIfAbsent(newLineArr[0], k -> new ArrayList<>()).add(newLineArr[1]);

        }

        List<String> path1 = findPath(top, emp1, employeesMap);
        List<String> path2 = findPath(top, emp2, employeesMap);


    }


    /**
     * Finds a path from one employee to another
     *
     * @param emp1
     * @param emp2
     * @param map of employees: manager -> list of subordinants
     * @return
     */
    static LinkedList<String> findPath(String emp1, String emp2, Map<String, List<String>> map) {
        if (map.get(emp1) == null || map.get(emp1).isEmpty()) return null;
        if (map.get(emp1).contains(emp2)) {
            LinkedList<String> result = new LinkedList<>();
            result.add(emp1);
            return result;
        }

        LinkedList<String> result = findPath(map.get(emp1).get(0), emp2, map);
        if (result != null) {
            result.addFirst(emp1);
            return result;
        } else if (map.get(emp1).size() > 1) {
            result = findPath(map.get(emp1).get(1), emp2, map);
            if (result == null) return null;
            result.addFirst(emp1);
            return result;
        }

        return null;
    }

    class Employee {
        private String name;
        List<Employee> subOrdinates = new ArrayList<>(2);

        public Employee(String name) {
            this.name = name;
        }

    }

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputCommonManager(_count, in);
    }
}