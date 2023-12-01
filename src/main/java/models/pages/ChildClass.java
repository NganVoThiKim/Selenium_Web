package models.pages;

public class ChildClass extends ParentPage{
    public ChildClass() {
        System.out.println("Initializing child class...");
    }

    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
    }
}
