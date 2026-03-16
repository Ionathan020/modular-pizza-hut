package dev.jonathanstronkhorst.modularpizzahut;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@SpringBootTest
@AnalyzeClasses(packagesOf = ModularPizzaHutApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
class ModularPizzaHutApplicationTests {

    @ArchTest
    static final ArchRule domain_should_not_depend_on_infrastructure =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule infrastructure_implementations_should_reside_in_infrastructure_package =
            classes().that().haveSimpleNameEndingWith("Adapter")
                    .or().haveSimpleNameEndingWith("RepositoryImpl")
                    .should().resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule repository_interfaces_should_reside_in_domain_or_infrastructure =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAnyPackage("..domain..", "..infrastructure..");

    @ArchTest
    static final ArchRule services_should_be_in_domain_and_annotated_with_service =
            classes().that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("..domain..")
                    .andShould().beAnnotatedWith(org.springframework.stereotype.Service.class);

    @ArchTest
    static final ArchRule controllers_should_be_in_infrastructure_and_annotated_with_restcontroller =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("..infrastructure..")
                    .andShould().beAnnotatedWith(org.springframework.web.bind.annotation.RestController.class);

    @ArchTest
    static final ArchRule views_should_be_in_infrastructure_and_annotated_with_route =
            classes().that().haveSimpleNameEndingWith("View")
                    .should().resideInAPackage("..infrastructure..")
                    .andShould().beAnnotatedWith(com.vaadin.flow.router.Route.class);

    @ArchTest
    static final ArchRule spring_data_repositories_should_be_in_infrastructure =
            classes().that().areInterfaces()
                    .and().areAssignableTo(org.springframework.data.repository.Repository.class)
                    .should().resideInAPackage("..infrastructure..");

    @Test
    void contextLoads() {
    }

    @Test
    void createApplicationModuleModel() {
        ApplicationModules modules = ApplicationModules.of(ModularPizzaHutApplication.class);
        modules.forEach(System.out::println);
    }

    @Test
    void verifiesModularStructure() {
        ApplicationModules modules = ApplicationModules.of(ModularPizzaHutApplication.class);
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        ApplicationModules modules = ApplicationModules.of(ModularPizzaHutApplication.class);
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }

}
