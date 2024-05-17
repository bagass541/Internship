import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        int[][] prerequisites1 = new int[][]{{1, 0}, {0, 1}};
        int[][] prerequisites2 = new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {3, 4}};
        int[][] prerequisites3 = new int[0][0];

        System.out.println(canFinish(5, prerequisites1));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean dfs(ArrayList[] graph, int[] visited, int course) {
        visited[course] = 1;

        for (int i = 0; i < graph[course].size(); i++) {
            int toVisit = (int) graph[course].get(i);

            if (visited[toVisit] == 1) {
                return false;
            }

            if (visited[toVisit] == 0) {
                if (!dfs(graph, visited, toVisit)) {
                    return false;
                }
            }
        }

        visited[course] = 2;
        return true;
    }
}