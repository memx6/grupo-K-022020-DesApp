package ar.edu.unq.desapp.grupoK.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class LayerDependencyRulesTest {

    private final JavaClasses classes = new ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importClasspath();

    @Test
    public void servicesShouldNotAccessControllers() {
        noClasses().that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..controller..").check(classes);
    }

    @Test
    public void repositoryShouldNotAccessServices() {
        noClasses().that().resideInAPackage("..repository..")
                .should().accessClassesThat().resideInAPackage("..service..").check(classes);
    }

    @Test
    public void servicesShouldOnlyBeAccessedByControllersOrOtherServices() {
        classes().that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..").check(classes);
    }

    @Test
    public void servicesShouldNotDependOnControllers() {
        noClasses().that().resideInAPackage("..service..")
                .should().dependOnClassesThat().resideInAPackage("..controller..").check(classes);
    }

    @Test
    public void repositoryShouldNotDependOnServices() {
        noClasses().that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..service..").check(classes);
    }

    @Test
    public void servicesShouldOnlyBeDependedOnByControllersOrOtherServices() {
        classes().that().resideInAPackage("..service..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..controller..", "..service..").check(classes);
    }

    @Test
    public void servicesShouldOnlyDependOnRepositoryOrOtherServices() {
        classes().that().resideInAPackage("..service..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..service..", "..repository..", "java..", "javax..")
                .check(classes);
    }

    @Test
    public void servicesShouldOnlyAccessRepositoryOrOtherServices() {
        classes().that().resideInAPackage("..service..")
                .should().onlyAccessClassesThat().resideInAnyPackage("..service..", "..repository..", "java..").check(classes);
    }
}
