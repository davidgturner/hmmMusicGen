package hmmproject;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;

import java.util.Vector;

/**
 * <p>
 * Title: Holds the algorithm we want to use when learning and traning the HMM
 * </p>
 */
public class HMMAlgorithm {

    public static final int KMEANS_CONST = 0;

    public static final int BAUM_WELCH_CONST = 1;

    public static final HMMAlgorithm KMEANS = new HMMAlgorithm(KMEANS_CONST);

    public static final HMMAlgorithm BAUM_WELCH = new HMMAlgorithm(
            BAUM_WELCH_CONST);

    private int algorithm;

    public HMMAlgorithm(int alg) {
        algorithm = alg;
    }

    public Hmm getHMM(Hmm hmm, int range, Vector sequences) throws Exception {

        if (algorithm == BAUM_WELCH_CONST) {
            /* Baum-Welch learning */
            BaumWelchLearner learner = new BaumWelchLearner(hmm.nbStates(),
                    new OpdfIntegerFactory(range), sequences);
            return learner.learn(hmm);
        } else if (algorithm == KMEANS_CONST) {
            KMeansLearner learner = new KMeansLearner(hmm.nbStates(),
                    new OpdfIntegerFactory(range), sequences);
            return learner.learn();
        }
        throw new Exception("Learner not specified!");
    }

    // public double kullbackDistance(){
    // KullbackLeiblerDistanceCalculator klc =
    // new KullbackLeiblerDistanceCalculator(hmm, learntHmm);
    // }

    public static void main(String[] args) {
        // HMMAlgorithm HMMAlgorithm1 = new HMMAlgorithm();
    }

}