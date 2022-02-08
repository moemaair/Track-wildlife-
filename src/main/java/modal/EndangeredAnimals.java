package modal;

import modal.Animals;

public class EndangeredAnimals extends Animals {
    public int id;
    public String name;
    public String health;
    public int age;

    public static final String HEALTH_1 = "healthy";
    public static final String HEALTH_2 = "okay";
    public static final String HEALTH_3 = "ill";
    public static final String AGE_1 = "newborn";
    public static final String AGE_2 = "young";
    public static final String AGE_3 = "adult";

    public EndangeredAnimals(int id, String name, String health, int age) {
        super(id, name);
        this.health = health;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
