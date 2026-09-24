import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Creating a hero/enemy
        Character hero = askPlayer(input);
        Character enemy = new Character("Goblin", 'R',75);

        System.out.println();

        hero.printCharacterSheet();
        enemy.printCharacterSheet();

        // Combat
        System.out.println("=== Combat ===");
        enemy.attack(hero);
        hero.attack(enemy);

        System.out.println();

        System.out.println("=== SMALL SHOP ===");
        hero.removeGold(100);
        hero.removeGold(100);

        System.out.println();

        hero.heal(50);

        hero.printCharacterSheet();
        enemy.printCharacterSheet();

    }
    // Asking the player
    private static Character askPlayer(Scanner input) {
        System.out.println("Start Game By Pressing Enter");
        input.nextLine();

        System.out.println("Write character name:");
        String name = input.nextLine();

        System.out.println("Choose a role: (W)arrior, (M)age, (R)ogue");
        char role = input.nextLine().charAt(0);
        // return input from player
        return new Character(name, role,10);
    }
}

