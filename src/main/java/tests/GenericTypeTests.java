package tests;

import models.components.ComponentExploring;
import models.pages.ExternalLoginPage;
import models.pages.InternalLoginPage;

public class GenericTypeTests {
    public static void main(String[] args) {
        ComponentExploring componentExploring = new ComponentExploring();
        try {
            componentExploring.login(InternalLoginPage.class, "Internal login page");
            componentExploring.login(ExternalLoginPage.class, "External login page");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
