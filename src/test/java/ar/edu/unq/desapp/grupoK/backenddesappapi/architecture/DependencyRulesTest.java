package ar.edu.unq.desapp.grupoK.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.DependencyRules.NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;

public class DependencyRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoK.backenddesappapi");

    @Test
    public void noAccessesToUpperPackage() {
        NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES.check(classes);
    }


}