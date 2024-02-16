package lab4;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {
    private TspProblem graphTask;

    public TspFitnessFunction(TspProblem task) {
        this.graphTask = task;
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        double totalDistance = 0.0;
        for (int i = 0; i < graphTask.getDim(); i++) {
            int startPoint = solution.get(i);
            int endPoint = solution.get((i + 1) % graphTask.getDim());
            totalDistance += calculateDistance(startPoint, endPoint);
        }
        return totalDistance;
    }

    public boolean isNatural() {
        return false;
    }

    public double calculateDistance(int from, int to) {
        
        double[] fromCoords = graphTask.getCoordinates(from);
        double[] toCoords = graphTask.getCoordinates(to);
        
        return euclideanDistance(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);
    }

    public double euclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}