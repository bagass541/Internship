import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                result[i] = -1.0;
            } else {
                result[i] = getPathWeight(dividend, divisor, new HashSet<>(), graph);
            }
        }

        return result;
    }

    private double getPathWeight(String start, String end, Set<String> visited,
                                 Map<String, Map<String, Double>> graph) {

        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }

        visited.add(start);
        for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
            if(!visited.contains(neighbour.getKey())) {
                double productWeight = getPathWeight(neighbour.getKey(), end, visited, graph);
                if(productWeight != -1.0) {
                    return neighbour.getValue() * productWeight;
                }
            }
        }

        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            map.putIfAbsent(dividend, new HashMap<>());
            map.putIfAbsent(divisor, new HashMap<>());

            map.get(dividend).put(divisor, values[i]);
            map.get(divisor).put(dividend, 1.0 / values[i]);
        }

        return map;
    }
}