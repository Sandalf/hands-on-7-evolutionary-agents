package ai.genalgots;

/**
 *
 * @author luissandoval
 */

import jade.core.Agent;

import jade.core.behaviours.OneShotBehaviour;

public class MaxFunctionAgent extends Agent {

    private static final long serialVersionUID = 1L;

    protected void setup() {
        System.out.println("Agent " + getLocalName() + "started.");
        addBehaviour(new MaxFunctionBehaviour());
    }

    private class MaxFunctionBehaviour extends OneShotBehaviour {

        private static final long serialVersionUID = 1L;

        public void action() {
            int populationSize = 10;
            double mutationRate = 0.001;
            double crossoverRate = 0.95;
            int elitismCount = 0;
            int chromosomeLength = 5;
            GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, mutationRate, crossoverRate, elitismCount);
            Population population = ga.initPopulation(chromosomeLength);
            int generation = 1;

            while (!ga.isTerminationConditionMet(population)) {
                printGenerationData(generation, population);

                population = ga.crossoverPopulation(population);

                population = ga.mutatePopulation(population);

                ga.evalPopulation(population);

                generation++;
            }

            printGenerationData(generation, population);

            System.out.println("Found solution in " + generation + " generations");
            System.out.println("Best solution: " + population.getFittest(0).toString());
        }

        public void printGenerationData(int generation, Population population) {
            double populationFitness = population.getPopulationFitness();
            System.out.println("Generation:" + generation);
            System.out.println("Population fitness: " + populationFitness);

            System.out.println("Chromosomes:");
            for (Individual individual : population.getIndividuals()) {
                double individualFitness = individual.getFitness();
                double proportionateFitness = (individualFitness * 100) / populationFitness;
                System.out.println(individual.toString() + " | X Value: " + individual.getXValue() + " | Fitness: "
                        + individual.getFitness() + " | Proportionate fitness value: " + proportionateFitness + "%");
            }

            System.out.println();
        }

        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }

    }
}
