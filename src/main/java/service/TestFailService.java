package service;

import model.FailedTestCase;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.ArrayList;
import java.util.List;

public class TestFailService {

    public List<FailedTestCase> formatFailures(List<TestExecutionSummary.Failure> failures) {
        List<FailedTestCase> fails = new ArrayList<>();
        failures.forEach(failure -> {
            FailedTestCase failedTestCase = new FailedTestCase();
            failedTestCase.setMethodName(formatMethodName(failure.getTestIdentifier().getLegacyReportingName(),
                    formatInput(failure.getTestIdentifier().getDisplayName())));
            failedTestCase.setExpected(failure.getException().getMessage());
            fails.add(failedTestCase);
        });
        return fails;
    }

    public void printFails(List<FailedTestCase> fails) {
        fails.forEach(failedSolution -> {
            System.out.println();
            System.out.println("-----------------------FAIL------------------------");
            System.out.println(failedSolution.getMethodName());
            System.out.println(failedSolution.getExpected());
            System.out.println("--------------------------------------------------");
            System.out.println();
        });
    }

    public String formatMethodName(String methodName, String input){
        String methodNameReplace = methodName.replaceAll("Test", "")
                .replaceAll("\\[\\d+\\]$", "");
        return methodNameReplace.replaceAll("(\\w+\\[])", input);
    }

    public String formatInput(String input){
        String methodInput = input.split("]")[1].replace("[", "");
        return methodInput.replaceAll(" -?\\d+$", "")
                .replaceAll(",$", "")
                .trim();
    }
}
