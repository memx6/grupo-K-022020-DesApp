package ar.edu.unq.desapp.grupoK.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoK.backenddesappapi")
public class LayeredArchitectureTest {

    @Test
    public void layerDependenciesAreRespected() {
        JavaClasses classes = new ClassFileImporter(new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)).importPackages("ar.edu.unq.desapp.grupoK.backenddesappapi");
        Architectures.LayeredArchitecture rule = layeredArchitecture()

                .layer("Controllers").definedBy("ar.edu.unq.desapp.grupoK.backenddesappapi.webservices..")
                .layer("Services").definedBy("ar.edu.unq.desapp.grupoK.backenddesappapi.services..")
                .layer("Repository").definedBy("ar.edu.unq.desapp.grupoK.backenddesappapi.repositories..")

                .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
                .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Services");
        rule.check(classes);
    }
}

