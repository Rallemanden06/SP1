
public class Character {

    // Character information
    private String name;
    private char role;

    // Health
    private int health = 100;
    private int maxHealth = 100;

    // Level
    private int level = 1;
    private int maxLevel = 99;

    // Experience
    private int experience = 0;
    private int maxExperience = 1000;

    // Gold
    private double gold = 150.0;

    // Alive
    private boolean isAlive = true;

    // Inventory
    private String[] inventory;

    private int damage;


    // Constructor
    public Character(String name, char role, int damage) {

        this.name = name;
        this.role = role;
        this.damage = damage;

        createInventory();
    }

    // Character sheet
    public void printCharacterSheet() {
        if (this.health == 0) {
            System.out.println();
        } else {
            System.out.println("=== CHARACTER SHEET ==");

            System.out.println("Name: " + name);

            switch (role) {
                case 'W':
                case 'w':
                    System.out.println("Class: (W) Warrior");
                    break;

                case 'M':
                case 'm':
                    System.out.println("Class: (M) Mage");
                    break;

                case 'R':
                case 'r':
                    System.out.println("Class: (R) Rogue");
                    break;

                default:
                    System.out.println("Class: (W) Warrior");
            }

            System.out.println(
                    "Level: " + level + "/" + maxLevel +
                            "\nHealth: " + health + "/" + maxHealth +
                            "\nXP: " + experience + "/" + maxExperience +
                            "\nGold: " + gold +
                            "\nAlive: " + isAlive);

            System.out.println();

            printInventory();

            System.out.println();
        }
    }


    // Inventory setup
    void printInventory() {

        System.out.println("=== INVENTORY ===");

        System.out.println("Inventory (" + inventory.length + "):");

        for (int i = 0; i < inventory.length; i++) {

            System.out.println(inventory[i]);

        }
    }

    // Create inventory based on role
    void createInventory() {
        if (role == 'W' || role == 'w') {
            inventory = new String[]{"Sword", "Shield", "Potion", "Map"};

        } else if (role == 'M' || role == 'm') {
            inventory = new String[]{"Staff", "Spellbook", "Potion", "Map"};

        } else if (role == 'R' || role == 'r') {
            inventory = new String[]{"Dagger", "Dagger", "Potion", "Map"};

        } else {
            inventory = new String[]{"Sword", "Shield", "Potion", "Map"};
        }
    }

    // Attack another character
    public void attack(Character target) {

        System.out.println(name + " attacks " + target.name + " for " + damage + " damage!");

        target.takeDamage(damage);
        System.out.println();

        if (target.health == 0){
            int xpGain = 1000;
            int goldGain = 25;
            //System.out.println();
            System.out.println("You killed the " + target.name +"!");
            this.addGold(goldGain);
            this.addXP(xpGain);
        }

    }

    // Take damage
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + amount + " damage!");
        isAlive();
        isHealthCritical();
    }

    // Healing the player
    void heal(int amount){
        System.out.println(name + " is using a potion to heal");
        System.out.println("Healing " + name + " for " + amount + " health");
        System.out.println();
        health += amount;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    // Health percent
    double getHealthPercentage() {
        double healthPercent = ((double) health / maxHealth) * 100;

        return healthPercent;
    }

    // Check health
    boolean isHealthCritical() {
        double healthPercent = getHealthPercentage();
        if (healthPercent <= 25 && healthPercent > 0) {
            System.out.println("WARNING: Health critical!");

            return true;
        }
        return false;
    }

    // Check alive
    boolean isAlive() {
        isAlive = health > 0;
        System.out.println("Alive: " + isAlive);
        return isAlive;
    }

    // Gold gain
    void addGold(double amount){
        gold += amount;
        System.out.println("You gained " + amount + " gold!");
    }

    // Gold remove // buy stuff
    boolean removeGold(double amount){
        if (amount < gold){
            gold -= amount;
            System.out.println("You have bought a item for " + amount + " gold");
            System.out.println("Remaining gold: " + gold);
            return true;
        }
        System.out.println("Not enough gold to buy this");
        return false;
    }

    // XP gain
    void addXP(int amount) {
        experience += amount;
        System.out.println("You gained " + amount + " XP!");
        levelUp();
    }

    // Level up metode
    void levelUp() {

        while (experience >= maxExperience && level < maxLevel) {
            experience -= maxExperience;
            level += 1;
            health += 10;
            maxHealth += 10;

            System.out.println();
            System.out.println("=== LEVEL UP! ===");
            System.out.println("You are now level " + level + "!");
            System.out.println("Your max health increased by 10!");

        }
        // Maximum level
        if (level >= maxLevel) {
            level = maxLevel;
            experience = 0;

            System.out.println("You reached the maximum level!");
        }
        System.out.println("XP: " + experience + "/" + maxExperience);
    }

}
