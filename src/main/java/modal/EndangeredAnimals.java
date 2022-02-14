package modal;


public class EndangeredAnimals {


    private int id;
    private String name;
    private String health;
    private String age;


    public EndangeredAnimals(String name, String health, String age) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.age = age;

    }

    public static final String HEALTH_1 = "healthy";
    public static final String HEALTH_2 = "okay";
    public static final String HEALTH_3 = "ill";
    public static final String AGE_1 = "newborn";
    public static final String AGE_2 = "young";
    public static final String AGE_3 = "adult";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }

}