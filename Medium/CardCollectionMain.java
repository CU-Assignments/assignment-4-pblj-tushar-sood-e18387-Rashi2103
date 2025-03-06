import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        System.out.println("\nCard Collection:");
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

public class CardCollectionMain {
    public static void main(String[] args) {
        CardCollection collection = new CardCollection();

        // Adding some sample cards
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");
        collection.addCard("Clubs", "10");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Search Cards by Symbol\n2. Display All Cards\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    List<String> cards = collection.getCardsBySymbol(symbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for " + symbol);
                    } else {
                        System.out.println("Cards under " + symbol + ": " + cards);
                    }
                    break;
                case 2:
                    collection.displayAllCards();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
