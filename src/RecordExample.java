
/*
    As of JDK 14, we can replace our repetitious data classes with records.
    Records are immutable data classes that require only the type and name of fields.
 */
record RecordPerson(String name, int age) {
}

final class NoRecordPerson {
    private final String name;
    private final int age;

    public NoRecordPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }   // Getter (not getName())

    public int age() {
        return age;
    }        // Getter (not getAge())

    //public boolean equals(Object o) { ... }
    //public int hashCode() { ... }
    //public String toString() { ... }
}

public class RecordExample {

    public static void main(String... args) {
        RecordPerson recordPerson = new RecordPerson("RName", 14);
        NoRecordPerson noRecordPerson = new NoRecordPerson("NRName", 1);

        System.out.println(recordPerson.name());
        System.out.println(noRecordPerson.name());


    }

}
