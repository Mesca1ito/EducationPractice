package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

import java.util.*;

import static ru.dgaribov.crackingthecodinginterview.treesandgraphs.BuildOrder.buildGraph;


public class BuildOrder {


    Project[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }


    static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for (String project: projects) {
            graph.getOrCreateNode(project);
        }
        for (String[] dependency: dependencies) {
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }
        return graph;
    }


    /**
     *
     * @return a list of projects a correct build order.
     */
    private Project[] orderProjects(List<Project> projects) {
        Project[] order = new Project[projects.size()];

        /* Add roots to the build order first. */
        int endOfList = addNonDependent(order, projects, 0);

        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];

            /* We have a circular dependency since
            there are remaining projects with zero dependencies. */
            if (current == null) {
                return null;
            }

            /*
             * Remove myself as a dependency.
             */
            List<Project> children = current.getChildren();
            for (Project child: children) {
                child.decrementDependencies();
            }

            endOfList = addNonDependent(order, children, endOfList);
            toBeProcessed++;


        }

        return order;
    }


    /**
     * A helper method to insert projects with zero dependencies into the order array, starting and index offset
     *
     * @param order
     * @param projects
     * @param offset
     * @return
     */
    private int addNonDependent(Project[] order, List<Project> projects, int offset) {
        for (Project project: projects) {
            if (project.getNumberOfDependencies() == 0) {
                order[offset] = project;
                offset++;
            }
        }

        return offset;
    }

    static class Graph {
        private List<Project> nodes = new ArrayList<>();
        private Map<String, Project> map = new HashMap<>();

        public Project getOrCreateNode(String name) {
            if (!map.containsKey(name)) {
                Project node = new Project(name);
                nodes.add(node);
                map.put(name, node);
            }
            return map.get(name);
        }

        public void addEdge(String startName, String endName) {
            Project start = getOrCreateNode(startName);
            Project end = getOrCreateNode(startName);
            start.addNeighbor(end);
        }

        public List<Project> getNodes() {
            return nodes;
        }

    }

    static class Project {
        public enum State {COMPLETE, PARTIAL, BLANK}

        private State state = State.BLANK;
        private List<Project> children = new ArrayList<>();
        private Map<String, Project> map = new HashMap<>();
        private String name;
        private int dependencies = 0;


        public Project(String n) {
            name = n;
        }


        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }


        public String getName() {
            return name;
        }

        public List<Project> getChildren() {
            return children;
        }

        public int getNumberOfDependencies() {
            return dependencies;
        }

        public void addNeighbor(Project node) {
            if (!map.containsKey(getName())) {
                children.add(node);
                map.put(node.getName(), node);
                incrementDependencies();
            }
        }

        private void incrementDependencies() {
            dependencies++;
        }

        private void decrementDependencies() {
            dependencies--;
        }
    }
}


class BuildOrderDFS {
    Stack<BuildOrder.Project> findBuildOrder(String[] projects, String[][] dependencies) {
        BuildOrder.Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }


    private Stack<BuildOrder.Project> orderProjects(List<BuildOrder.Project> projects) {
        Stack<BuildOrder.Project> stack = new Stack<>();
        for (BuildOrder.Project project: projects) {
            if (project.getState() == BuildOrder.Project.State.BLANK) {
                if (!doDFS(project, stack)) {
                    return null;
                }
            }
        }

        return stack;
    }


    private boolean doDFS(BuildOrder.Project project, Stack<BuildOrder.Project> stack) {
        if (project.getState() == BuildOrder.Project.State.PARTIAL) {
            return false; // Cycle
        }

        if (project.getState() == BuildOrder.Project.State.BLANK) {
            project.setState(BuildOrder.Project.State.PARTIAL);
            List<BuildOrder.Project> children = project.getChildren();
            for (BuildOrder.Project child: children) {
                if (!doDFS(child, stack)) return false;
            }

            project.setState(BuildOrder.Project.State.COMPLETE);
            stack.push(project);
        }

        return true;
    }

}