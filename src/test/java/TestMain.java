import org.junit.internal.TextListener;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.runner.JUnitCore;
import test.parent.Solution;
import test.parent.SolutionTest;
import test.parent.SolutionTwo;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class TestMain {

    public static SummaryGeneratingListener runTest(){
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(SolutionTest.class))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        return listener;
    }

    public static void main(String[] args) {
        SummaryGeneratingListener listener = runTest();
        TestExecutionSummary summary = listener.getSummary();
        summary.printTo(new PrintWriter(System.out));
        System.out.println("Result multiply: " + Solution.multiply(5,5));
        System.out.println("Result division: " + SolutionTwo.divide(10,2));
    }
}
