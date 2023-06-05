public class Human {
    String name;
    int age;
    String phoneNum;
    Profession profession;
    public Human(String name, int age, String phoneNum, Profession profession) {
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return this.name + " " + this.age + " " + this.phoneNum + " " + this.profession;
    }
}
enum Profession {
    DOCTOR,
    ENGINEER,
    TEACHER,
    POLICEMAN,
    LAWYER,
    ACTOR,
    NOPE
}
