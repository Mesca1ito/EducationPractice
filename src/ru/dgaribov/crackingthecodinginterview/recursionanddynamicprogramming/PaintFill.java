package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;

public class PaintFill {
    boolean paintFill(Color[][] screen, int r, int c, Color color) {
        // paintFillDepthFirst(screen, r, c, screen[r][c], color);

        // or

        return paintFillBreadthFirst(screen, new Position(r, c), screen[r][c], color);
    }

    private boolean paintFillDepthFirst(Color[][] screen, int r, int c, Color oColor, Color nColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return false;
        }

        if (screen[r][c] == oColor) {
            screen[r][c] = nColor;
            paintFillDepthFirst(screen, r - 1, c, oColor, nColor);
            paintFillDepthFirst(screen, r + 1, c, oColor, nColor);
            paintFillDepthFirst(screen, r, c - 1, oColor, nColor);
            paintFillDepthFirst(screen, r, c + 1, oColor, nColor);
        }

        return true;
    }

    private boolean paintFillBreadthFirst(Color[][] screen, Position position, Color oColor, Color nColor) {
        if (positionIsBad(screen, position, oColor)) return false;
        Queue<Position> queue = new LinkedList<>();
        queue.add(position);
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            if (positionIsBad(screen, position, oColor))
                continue;
            screen[pos.r][pos.c] = nColor;
            Position top = new Position(pos.r - 1, pos.c);
            Position bottom = new Position(pos.r + 1, pos.c);
            Position left = new Position(pos.r, pos.c - 1);
            Position right = new Position(pos.r, pos.c + 1);
            queue.add(top);
            queue.add(bottom);
            queue.add(left);
            queue.add(right);
        }

        return true;
    }

    private boolean positionIsBad(Color[][] screen, Position position, Color oColor) {
        return position.r < 0 || position.r >= screen.length || position.c < 0 || position.c >= screen[0].length || screen[position.r][position.c] != oColor;
    }
}

enum Color {
    Black, White, Red, Yellow, Green
}

class Position {
    int r;
    int c;

    Position(int r, int c) {
        this.r = r;
        this.c = c;
    }
}