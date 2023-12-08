package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main2 {
        public static void main(String[] args) {
            String originalString = "Hello, World!";
            String stringToBeRemoved = ", ";

            // Menghapus karakter atau substring
            String modifiedString = originalString.replace(stringToBeRemoved, "");

            System.out.println("Original String: " + originalString);
            System.out.println("Modified String: " + modifiedString);
        }
    }
