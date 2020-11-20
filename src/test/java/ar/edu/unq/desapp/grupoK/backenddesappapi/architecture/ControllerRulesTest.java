package ar.edu.unq.desapp.grupoK.backenddesappapi.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.PackageMatchers;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.security.access.annotation.Secured;

import static com.tngtech.archunit.core.domain.JavaClass.Functions.GET_PACKAGE_NAME;
import static com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoK.backenddesappapi")
public class ControllerRulesTest {

    @ArchTest
    static final ArchRule controllersShouldOnlyCallSecuredMethods =
            classes().that().resideInAPackage("..controller..")
                    .should().onlyCallMethodsThat(areDeclaredInController().or(are(annotatedWith(Secured.class))));

    @ArchTest
    static final ArchRule controllersShouldOnlyCallSecuredConstructors =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().onlyCallConstructorsThat(areDeclaredInController().or(are(annotatedWith(Secured.class))));

    @ArchTest
    static final ArchRule controllersShouldOnlyCallSecuredCodeUnits =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().onlyCallCodeUnitsThat(areDeclaredInController().or(are(annotatedWith(Secured.class))));

    @ArchTest
    static final ArchRule controllersShouldOnlyAccessSecuredFields =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().onlyAccessFieldsThat(areDeclaredInController().or(are(annotatedWith(Secured.class))));

    @ArchTest
    static final ArchRule controllersShouldOnlyAccessSecuredMembers =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().onlyAccessMembersThat(areDeclaredInController().or(are(annotatedWith(Secured.class))));

    private static DescribedPredicate<JavaMember> areDeclaredInController() {
        DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME.is(PackageMatchers.of("..controller..", "java.."))
                .as("a package '..controller..'");
        return are(declaredIn(aPackageController));
    }
}
