package ru.dgaribov.yandexcode.interview20200729;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Дан массив точек с целочисленными координатами (x, y). Определить, существует
 * ли вертикальная прямая, делящая точки на 2 симметричных относительно этой
 * прямой множества.
 *
 *
 * @author David Garibov
 */
public class VerticalLine {

    public static void main(String[] args) {
        VerticalLine verticalLine = new VerticalLine();
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(7, 1));
        points.add(new Point(3, 1));
        points.add(new Point(5, 1));
        points.add(new Point(2, 2));
        points.add(new Point(6, 2));
        System.out.println(verticalLine.isThereSymmetryLine(points));

        points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(7, 1));
        points.add(new Point(3, 1));
        points.add(new Point(5, 1));
        points.add(new Point(2, 2));
        points.add(new Point(5, 2));
        System.out.println(verticalLine.isThereSymmetryLine(points));

    }

    public boolean isThereSymmetryLine(List<Point> points) {
        float symmetryLine = findLineX(points);
        Map<Integer, List<Integer>> yMap = buildYMap(points);
        for (int y: yMap.keySet()) {
            List<Integer> xListForThatY = yMap.get(y);
            if (!areAllPointsMirrored(xListForThatY, symmetryLine)) return false;
        }

        return true;
    }


    /**
     *
     * @param points
     * @return список координат x для каждой y
     */
    Map<Integer, List<Integer>> buildYMap(List<Point> points) {
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (Point point: points) {
            List<Integer> xListForThatY = yMap.computeIfAbsent(point.y, k -> new ArrayList<>());
            xListForThatY.add(point.x);
        }
        return yMap;
    }

    /**
     *
     * @param points
     * @return Предполагаемая координата X вертикальной линии
     */
    float findLineX(List<Point> points) {
        int sum = 0;
        for (Point point: points) {
            sum += point.x;
        }

        return 1.0f * sum / points.size();
    }

    /**
     *
     * @param xPoints
     * @param symmetryLine
     * @return находятся ли точки на одинаковом расстоянии от предполагаемого центра
     */
    boolean areAllPointsMirrored(List<Integer> xPoints, float symmetryLine) {
        float sum = 0;
        for (int xPoint: xPoints) {
            sum += xPoint - symmetryLine;
        }
        return sum == 0;
    }
}


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
