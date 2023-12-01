package models.components;

public class FindComponent {
    public <T> String getComponentCSSSelector(Class<T> componentClass){
            return componentClass.getAnnotation(ComponentCSSSelector.class).value();
    }
}
