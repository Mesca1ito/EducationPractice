package ru.dgaribov.yandexcode.sdcmeetup2021;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author David Garibov
 */
public class Robots {

    private final static int MAX_ROBOTS = 100;
    private final static int STEPS_PER_INTERACTION = 60;

    private List<Order> orders;
    private List<Robot> robots;
    private boolean[][] cityMap;

    // Where orders are located on the map
    private Map<String, List<Order>> ordersMap;

    public static void main(String[] args) {
        Robots app = new Robots();
        Scanner scanner = new Scanner(System.in);
        // N, MaxTips, Cost
        String[] line = scanner.nextLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int maxTips = Integer.parseInt(line[1]);
        long cost = Long.parseLong(line[2]);
        // true - cell is taken, false - free
        boolean[][] cityMap = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String ln = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                cityMap[i][j] = '#' == ln.charAt(j);
            }
        }
        app.cityMap = cityMap;

        line = scanner.nextLine().split(" ");

        // Number of iterations
        int T = Integer.parseInt(line[0]);
        // Number of orders
        int D = Integer.parseInt(line[1]);
        app.orders = new ArrayList<>(D);
        app.ordersMap = new HashMap<>();

        int[][] robotCoords = app.robotInitCoordinates(cityMap, maxTips, cost, T, D);
//        robotCoords = new int[][] {{0, 0}};
        System.out.println(robotCoords.length);
        app.robots = new ArrayList<>(robotCoords.length);
        for (int[] coords : robotCoords) {
            app.robots.add(new Robot(coords[0], coords[1]));
            System.out.println((coords[0] + 1) + " " + (coords[1] + 1));
        }

        // Начинаем итерации
        for (int i = 0; i < T; i++) {
            String ln = scanner.nextLine();
            // Количество курьерских заказов
            int k = Integer.parseInt(ln);
            for (int j = 0; j < k; j++) {
                line = scanner.nextLine().split(" ");
                Order newOrder = new Order(
                        Integer.parseInt(line[0]) - 1,
                        Integer.parseInt(line[1]) - 1,
                        Integer.parseInt(line[2]) - 1,
                        Integer.parseInt(line[3]) - 1,
                        i
                );

                // Не берём заказы, которые невозможно доставить
                Queue<Step> deliveryPath = app.findPath(newOrder.sRow, newOrder.sCol, newOrder.endRow, newOrder.endCol, cityMap);
                if (deliveryPath == null) continue;
                newOrder.deliveryPath = deliveryPath;

                app.orders.add(newOrder);
                List<Order> ordersInGivenLocation = app.ordersMap.computeIfAbsent(newOrder.sRow + "-" + newOrder.sCol, (s) -> new ArrayList<>());
                ordersInGivenLocation.add(newOrder);
            }
            String[] robotsActions = app.iteration();
            for (String robotActions : robotsActions) {
                System.out.println(robotActions);
            }
        }

    }

    /**
     * Для каждого робота определяем список необходимых действий и выполняем их
     *
     * @return список действий для каждого робота
     */
    private String[] iteration() {
        String[] robotsActions = new String[this.robots.size()];
        Arrays.fill(robotsActions, "");
        for (int step = 0; step < STEPS_PER_INTERACTION; step++) {


            robotsLoop:
            for (int i = 0; i < robots.size(); i++) {
                Robot robot = robots.get(i);
                // Если у робота есть заказ - идем к нему, или доставляем его
                if (robot.currentOrder != null) {
                    // Если робот собирается забрать заказ
                    if (robot.currentOrder.status == OrderStatus.PICKING) {
                        // Если робот уже на клетке с заказом
                        if (robot.curX == robot.currentOrder.sRow && robot.curY == robot.currentOrder.sCol) {
                            robot.currentOrder = pickOldestOrderFromTheCell(robot.curX, robot.curY);
                            if (robot.currentOrder == null) {
                                robotsActions[i] += 'S';
                                continue;
                            }
                            robotsActions[i] += 'T';
                            robot.path = robot.currentOrder.deliveryPath;
                            // Если нет - идём к нему
                        } else {
                            Step nextStep = robot.path.remove();
                            robotsActions[i] += nextStep.action;
                            robot.curX = nextStep.x;
                            robot.curY = nextStep.y;
                        }
                        // Если робот уже доставляет заказ
                    } else if (robot.currentOrder.status == OrderStatus.DELIVERING) {
                        // Если робот уже в конечной точке
                        if (robot.curX == robot.currentOrder.endRow && robot.curY == robot.currentOrder.endCol) {
                            robotsActions[i] += 'P';
                            orders.remove(robot.currentOrder);
                            robot.currentOrder = null;
                        } else {
                            Step nextStep = robot.path.remove();
                            robotsActions[i] += nextStep.action;
                            robot.curX = nextStep.x;
                            robot.curY = nextStep.y;
                        }
                    }
                }
                // А если у робота нет заказа
                else {
                    // Находим ближайшие из доступных заказов
                    Map<Order, Queue<Step>> orderDistanceMap = new HashMap<>();
                    List<Order> idleOrders = this.orders.stream().filter(o -> o.status == OrderStatus.IDLE).collect(Collectors.toList());
                    for (Order order : idleOrders) {
                        Queue<Step> path = findPath(robot.curX, robot.curY, order.sRow, order.sCol, cityMap);
                        if (path != null) orderDistanceMap.put(order, path);
                    }

                    List<Map.Entry<Order, Queue<Step>>> ordered = orderDistanceMap.entrySet().stream().sorted(Comparator.comparingInt(e -> e.getValue().size())).collect(Collectors.toList());
                    for (Map.Entry<Order, Queue<Step>> orderDistance : ordered) {
                        Order order = orderDistance.getKey();
                        Order oldestOrderInTheCell = findOldestOrderInTheCell(order.sRow, order.sCol);
                        if (oldestOrderInTheCell != order) continue;
                        robot.currentOrder = order;
                        robot.currentOrder.status = OrderStatus.PICKING;
                        robot.path = orderDistance.getValue();
                        // Если робот уже на клетке с заказом
                        if (robot.curX == robot.currentOrder.sRow && robot.curY == robot.currentOrder.sCol) {
                            robot.currentOrder = pickOldestOrderFromTheCell(robot.curX, robot.curY);
                            if (robot.currentOrder == null) {
                                robotsActions[i] += 'S';
                                continue robotsLoop;
                            }
                            robotsActions[i] += 'T';
                            robot.path = robot.currentOrder.deliveryPath;
                            continue robotsLoop;
                            // Если нет - идём к нему
                        } else {
                            Step nextStep = robot.path.remove();
                            robotsActions[i] += nextStep.action;
                            robot.curX = nextStep.x;
                            robot.curY = nextStep.y;
                            continue robotsLoop;
                        }
                    }

                    // Иначе просто стоим на месте
                    robotsActions[i] += 'S';

                }
            }
        }
        return robotsActions;
    }

    /**
     *
     * @param x
     * @param y
     * @return забирает самый старый заказ в текущей клетке
     */
    private Order pickOldestOrderFromTheCell(int x, int y) {
        List<Order> orders = this.ordersMap.get(x + "-" + y);
        Order oldestOrder = findOldestOrderInTheCell(x, y);
        if (oldestOrder == null) return null;
        orders.remove(oldestOrder);
        oldestOrder.status = OrderStatus.DELIVERING;
        return oldestOrder;
    }

    /**
     *
     * @param x
     * @param y
     * @return находит самый старый заказ в текущей клетке
     */
    private Order findOldestOrderInTheCell(int x, int y) {
        List<Order> orders = this.ordersMap.get(x + "-" + y);
        if (orders.isEmpty()) return null;
        Order oldestOrder = orders.get(0);
        if (orders.size() == 1) return oldestOrder;
        for (int i = 1; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.numberOfIteration < oldestOrder.numberOfIteration) oldestOrder = order;
        }
        return oldestOrder;
    }

    /**
     * Находит путь от одной ячейки до другой. Если путь невозможен - вернет null
     *
     * @param sRow
     * @param sCol
     * @param fRow
     * @param fCol
     * @param cityMap карта города с препятствиями
     * @return очередь шагов от одной точки до другой
     */
    private Queue<Step> findPath(int sRow, int sCol, int fRow, int fCol, boolean[][] cityMap) {
        Node start = new Node(sRow, sCol);
        start.trace = new ArrayDeque<>();
        boolean[][] visited = new boolean[cityMap.length][cityMap.length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == fRow && node.y == fCol) {
                return node.trace;
            }
            visited[node.x][node.y] = true;

            Queue<Step> trace;
            Step step;

            // U
            if (cellIsValid(node.x - 1, node.y, cityMap, visited)) {
                trace = new ArrayDeque<>(node.trace);
                step = new Step('U', node.x - 1, node.y);
                trace.add(step);
                queue.add(new Node(node.x - 1, node.y, trace));
            }
            // L
            if (cellIsValid(node.x, node.y - 1, cityMap, visited)) {
                trace = new ArrayDeque<>(node.trace);
                step = new Step('L', node.x, node.y - 1);
                trace.add(step);
                queue.add(new Node(node.x, node.y - 1, trace));
            }
            // D
            if (cellIsValid(node.x + 1, node.y, cityMap, visited)) {
                trace = new ArrayDeque<>(node.trace);
                step = new Step('D', node.x + 1, node.y);
                trace.add(step);
                queue.add(new Node(node.x + 1, node.y, trace));
            }
            // R
            if (cellIsValid(node.x, node.y + 1, cityMap, visited)) {
                trace = new ArrayDeque<>(node.trace);
                step = new Step('R', node.x, node.y + 1);
                trace.add(step);
                queue.add(new Node(node.x, node.y + 1, trace));
            }
        }

        return null;
    }

//    @Test
    public void testFindPath() {
        Robots app = new Robots();
        app.cityMap = new boolean[][]{{false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}};
        Queue<Step> steps = app.findPath(0, 0, 3, 3, app.cityMap);
        System.out.println(steps);
    }

    private boolean cellIsValid(int x, int y, boolean[][] cityMap, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < cityMap.length && y < cityMap.length && !cityMap[x][y] && !visited[x][y];
    }




    private int[][] robotInitCoordinates(boolean[][] cityMap, int maxTips, long cost,
                                         int numberOfIterations, int totalNumberOfOrders) {
        int optimalNumberOfRovers = findOptimalNumberOfRovers(totalNumberOfOrders, numberOfIterations, maxTips, cost, cityMap);
        int[][] robotsInitCoordinates = new int[optimalNumberOfRovers][2];
        robotsLoop:
        for (int i = 0; i < optimalNumberOfRovers; i++) {
            coordsSearchLoop:
            while (true) {
                int x = getRand(0, cityMap.length - 1);
                int y = getRand(0, cityMap.length - 1);
                if (cityMap[x][y]) continue;
                for (int[] robotCoords : robotsInitCoordinates) {
                    // Ячейка уже занята
                    if (robotCoords[0] == x && robotCoords[1] == y) continue coordsSearchLoop;
                }
                robotsInitCoordinates[i] = new int[]{x, y};
                continue robotsLoop;
            }
        }

        return robotsInitCoordinates;
    }


    private int findOptimalNumberOfRovers(int totalNumberOfOrders, int numberOfIterations, int maxTips, long cost, boolean[][] cityMap) {
        // Посчитать сколько можно заработать за один круг в среднем
        // Умножить на количество кругов и вычесть количество роверов, умноженное на их цену

        int averageNumberOfOrders = totalNumberOfOrders / numberOfIterations;

        int freeCells = countFreeCells(cityMap);

        // Грубо предположим, что за один раунд ровер может доставить количество заказов, равное 60 доступным шагам ровера, поделенным на диагональ квадрата
        double ordersPerRoverFor1Interaction = STEPS_PER_INTERACTION / (Math.sqrt(2) * cityMap.length);


        double maxProfit = 0;
        int optimalRoversNumber = 1;

        // Иметь роверов больше чем заказов бессмысленно
        // Иметь роверов больше чем свободных клеток так же бессмысленно
        // Роверов не может быть больше 100
        // Грубо считаем сколько мы можем заработать за один раунд с искомым количеством роверов,
        // умножаем на количество раундов и вычитаем стоимость постройки этих роверов
        for (int roversNumber = 1; roversNumber <= averageNumberOfOrders
                && roversNumber <= freeCells
                && roversNumber <= MAX_ROBOTS; roversNumber++) {
            long roversCost = roversNumber * cost;
            double totalTips = (long) roversNumber * ordersPerRoverFor1Interaction * maxTips * numberOfIterations;
            double profit = totalTips - roversCost;
            if (profit > maxProfit) {
                maxProfit = profit;
                optimalRoversNumber = roversNumber;
            }
        }

        return optimalRoversNumber;
    }


    private int countFreeCells(boolean[][] cityMap) {
        int count = 0;
        for (int i = 0; i < cityMap.length; i++) {
            for (int j = 0; j < cityMap[i].length; j++) {
                if (!cityMap[i][j]) count++;
            }
        }

        return count;
    }

    private int getRand(int left, int right) {
        Random random = new Random();
        return random.ints(left, right)
                .findFirst()
                .getAsInt();
    }
}

class Robot {
    int curX;
    int curY;
    Order currentOrder;
    Queue<Step> path;

    Robot(int x, int y) {
        this.curX = x;
        this.curY = y;
        this.path = new LinkedList<>();
    }
}

class Order {
    int sRow;
    int sCol;
    int endRow;
    int endCol;
    OrderStatus status;
    int numberOfIteration;
    Queue<Step> deliveryPath;

    public Order(int sRow, int sCol, int endRow, int endCol, int numberOfInteraction) {
        this.sRow = sRow;
        this.sCol = sCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.numberOfIteration = numberOfInteraction;
        this.status = OrderStatus.IDLE;
    }
}

class Node {
    int x;
    int y;
    Queue<Step> trace;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.trace = new ArrayDeque<>();
    }

    public Node(int x, int y, Queue<Step> trace) {
        this.x = x;
        this.y = y;
        this.trace = trace;
    }
}

class Step {
    char action;
    int x;
    int y;

    public Step(char action, int x, int y) {
        this.action = action;
        this.x = x;
        this.y = y;
    }
}

enum OrderStatus {
    IDLE, PICKING, DELIVERING
}
