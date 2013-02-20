package icarus.operatingsoftware;

/**
 * Common interface for sources of random events
 * 
 * @author david
 */
public interface ProbabilitySource {
    public boolean trueOnceIn(int n);
    public int choiceFromZeroTo(int n);
}
