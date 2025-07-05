import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the text file you want to work with (e.g., demo.txt): ");
        String fileName = scanner.nextLine().trim();

        while (true) {
            System.out.println("\n📂 File Handling Menu for " + fileName);
            System.out.println("1️⃣  Create & Write to file");
            System.out.println("2️⃣  Read file");
            System.out.println("3️⃣  Append to file");
            System.out.println("4️⃣  Overwrite file");
            System.out.println("5️⃣  Delete file");
            System.out.println("6️⃣  Exit");
            System.out.print("Choose an option (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createFile(fileName, scanner);
                    break;
                case 2:
                    readFile(fileName);
                    break;
                case 3:
                    appendToFile(fileName, scanner);
                    break;
                case 4:
                    overwriteFile(fileName, scanner);
                    break;
                case 5:
                    deleteFile(fileName);
                    break;
                case 6:
                    System.out.println("👋 Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please enter a number from 1-6.");
            }
        }
    }

    private static void createFile(String fileName, Scanner scanner) {
        try (FileWriter writer = new FileWriter(fileName)) {
            System.out.print("Enter text to write to the new file:\n> ");
            String text = scanner.nextLine();
            writer.write(text + "\n");
            System.out.println("✅ File created and written successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    private static void readFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("❌ File not found: " + fileName);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("📄 Contents of " + fileName + ":");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    private static void appendToFile(String fileName, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            System.out.print("Enter text to append to the file:\n> ");
            String text = scanner.nextLine();
            writer.write(text + "\n");
            System.out.println("✅ Text appended successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error appending to file: " + e.getMessage());
        }
    }

    private static void overwriteFile(String fileName, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            System.out.print("Enter new text to overwrite the file:\n> ");
            String text = scanner.nextLine();
            writer.write(text + "\n");
            System.out.println("✅ File overwritten successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error overwriting file: " + e.getMessage());
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.delete()) {
            System.out.println("🗑️ File deleted successfully: " + fileName);
        } else {
            System.out.println("❌ Failed to delete file or file does not exist.");
        }
    }
}
