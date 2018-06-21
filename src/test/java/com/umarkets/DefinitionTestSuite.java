package com.umarkets;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/consult_dictionary/LookupADefinition.feature",
        glue={"com.umarkets.steps", "com.umarkets.steps.serenity"})

public class DefinitionTestSuite {}
