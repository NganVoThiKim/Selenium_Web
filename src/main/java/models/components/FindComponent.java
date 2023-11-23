package models.components;

public class AnnotationTest {
    public <T> String getComponentCSSSelector(Class<T> componentClass){
            return componentClass.getAnnotation(ComponentCSSSelector.class).value();
    }
}
