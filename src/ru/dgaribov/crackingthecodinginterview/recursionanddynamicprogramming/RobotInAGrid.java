package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotInAGrid {
    /**
     * Searches for a pth from the upper left corner to the bottom right
     *
     * @param maze r X c array
     * @return list of the points on the path
     */
    public List<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return null;
        List<Point> path = new ArrayList<>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path, new HashSet<>())) {
            return path;
        }
        return null;
    }

    private boolean getPath(boolean[][] maze, int row, int col, List<Point> path, Set<Point> failedPoints) {
        if (row < 0 || col < 0 || !maze[row][col]) return false;
        Point point = new Point(row, col);
        if (failedPoints.contains(point)) return false;
        boolean isAtOrigin = row == 0 && col == 0;
        if (isAtOrigin
                || getPath(maze, row - 1, col, path, failedPoints)
                || getPath(maze, row, col - 1, path, failedPoints)) {
            path.add(point);
            return true;
        }

        failedPoints.add(point);
        return false;
    }
}

class Point {
    private int r;
    private int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (r != point.r) return false;
        return c == point.c;
    }

    @Override
    public int hashCode() {
        int result = r;
        result = 31 * result + c;
        return result;
    }
}
