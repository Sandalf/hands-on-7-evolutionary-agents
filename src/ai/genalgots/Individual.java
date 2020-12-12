package ai.genalgots;

public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public Individual(int length) {
        this.chromosome = new int[length];

        for (int i = 0; i < chromosome.length; i++) {
            int gene = 1;
            if (0.5 < Math.random()) {                
                this.setGene(i, gene);
            } else {
                gene = 0;
                this.setGene(i, gene);
            }
        }

        // this.setXValue(xValue);
    }

    public int getXValue() {
        int xValue = 0;
        for (int i = 0; i < chromosome.length; i++) {
            xValue += this.getGene(i) * Math.pow(2, i);
        }
        return xValue;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }
    
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        String output = "";

        for(int gene = 0; gene < this.getChromosomeLength(); gene++) {
            output += this.getGene(gene);
        }

        return output;
    }
}
