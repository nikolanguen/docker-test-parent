package test.extensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import test.annotations.Points;

import java.lang.reflect.Method;

public class CustomTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    public static int caseCnt = 0;
    public static int pointsSum = 0;
    private static final int caseCntInit = 1;
    private static int solutionPoints = 0;

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        Method currentMethod = extensionContext.getRequiredTestMethod();
        Points points = currentMethod.getAnnotation(Points.class);
        solutionPoints = points.value();
        caseCnt = caseCntInit;
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isEmpty()) {
            caseCnt--;
        }
        if (caseCnt == 0) {
            pointsSum += solutionPoints;
        }
    }
}
