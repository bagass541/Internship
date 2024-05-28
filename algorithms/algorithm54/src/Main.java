import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] prerequisites = new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] res = findOrder(4, prerequisites);
        Arrays.stream(res).forEach(System.out::println);
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> graphs = new ArrayList<>(numCourses);
        initializeGraphs(incLinkCounts, prerequisites, graphs);
        return solveByBFS(incLinkCounts, graphs);
    }

    private static int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs){
        int[] order = new int[incLinkCounts.length];
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < incLinkCounts.length; i++) {
            if (incLinkCounts[i] == 0) {
                toVisit.add(i);
            }
        }
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                incLinkCounts[to]--;
                if (incLinkCounts[to] == 0) {
                    toVisit.add(to);
                }
            }
        }
        return visited == incLinkCounts.length ? order : new int[0];
    }

    private static void initializeGraphs(int[] incLinkCounts, int[][] prerequisites, List<List<Integer>> graphs) {
        int n = incLinkCounts.length;
        while(n-- > 0) {
            graphs.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            incLinkCounts[prerequisite[0]]++;
            graphs.get(prerequisite[1]).add(prerequisite[0]);
        }
    }
}