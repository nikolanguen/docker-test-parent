import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import service.HttpService;
import test.extensions.CustomTestExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class TestMain {

    public static SummaryGeneratingListener runTest() {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("test.parent"))
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        return listener;
    }

    public static void formatFailures(List<TestExecutionSummary.Failure> failures) {
        failures.forEach(failure -> {
            System.out.println();
            System.out.println("---------------------------FAIL-------------------");
            System.out.println(failure.getTestIdentifier().getDisplayName());
            System.out.println(failure.getException().getMessage());
            System.out.println("--------------------------------------------------");
            System.out.println();
        });
    }

    public static void main(String[] args) {
        SummaryGeneratingListener listener = runTest();
        TestExecutionSummary summary = listener.getSummary();
//        summary.printTo(new PrintWriter(System.out));
        formatFailures(summary.getFailures());
//        System.out.println("Points won:");
//        System.out.println(CustomTestExtension.pointsSum);

        HttpService httpService = new HttpService();
        try {
            httpService.sendTestResult(CustomTestExtension.pointsSum, new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(args[0]);
    }
}
