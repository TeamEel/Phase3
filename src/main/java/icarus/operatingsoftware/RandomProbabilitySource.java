package icarus.operatingsoftware;

import java.util.Random;


public class RandomProbabilitySource implements ProbabilitySource {
    private Random randomGenerator;
    
    public RandomProbabilitySource() {
        randomGenerator = new Random();
    }
    
    @Override
    public boolean trueOnceIn(int n) {
        return randomGenerator.nextInt(n) == 0;
    }

    @Override
    public int choiceFromZeroTo(int n) {
        return randomGenerator.nextInt(n);
    }
    
}
