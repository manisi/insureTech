package ir.insurance.startup.cucumber.stepdefs;

import ir.insurance.startup.InsurancestartApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = InsurancestartApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
