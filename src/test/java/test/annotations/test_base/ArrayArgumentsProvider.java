package test.annotations.test_base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<ArraySources> {
    private List<int[]> args;
    private List<String[]> strArgs;

    public void accept(ArraySources source) {
        List<ArraySource> arrays = Arrays.asList(source.arrays());
        if (arrays.get(0).array().length > 0) {
            this.args = arrays.stream().map(ArraySource::array).collect(Collectors.toList());
        } else {
            this.strArgs = arrays.stream().map(ArraySource::strArray).collect(Collectors.toList());
        }
    }

    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        if (this.args != null) {
            return this.args.stream().map(Arguments::of);
        }
        return this.strArgs.stream().map(array -> Arguments.of((Object) array));
    }
}
