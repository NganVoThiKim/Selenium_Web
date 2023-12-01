package tests;

import models.components.FindComponent;
import models.components.FooterComponent;

public class AnnotationTests {
    public static void main(String[] args) {
        String footerCompSel = new FindComponent().getComponentCSSSelector(FooterComponent.class);
        System.out.println(footerCompSel);
    }
}
