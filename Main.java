
public class Main {

    public static void solution(char[][] arr, String[] words, int vidx) {
        //write your code here
        if (vidx == words.length) {
            print(arr);
            return;
        }

        String word = words[vidx];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == '.') {

                    // Try Horizontal Placement
                    if (canPlaceWordHorizontally(arr, word, i, j)) {
                        boolean[] wePlaced = placeWordHorizontally(arr, word, i, j);
                        solution(arr, words, vidx + 1);
                        unplaceWordHorizontally(arr, wePlaced, i, j);
                    }

                    // Try Vertical Placement
                    if (canPlaceWordVertically(arr, word, i, j)) {
                        boolean[] wePlaced = placeWordVertically(arr, word, i, j);
                        solution(arr, words, vidx + 1);
                        unplaceWordVertically(arr, wePlaced, i, j);
                    }
                }
            }
        }
    }

    public static boolean canPlaceWordHorizontally(char[][] arr, String word, int i, int j) {
        if (j + word.length() > arr[0].length) {
            return false;
        }

        for (int k = 0; k < word.length(); k++) {
            char existing = arr[i][j + k];
            if (existing != '.' && existing != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceWordVertically(char[][] arr, String word, int i, int j) {
        if (i + word.length() > arr.length) {
            return false;
        }

        for (int k = 0; k < word.length(); k++) {
            char existing = arr[i + k][j];
            if (existing != '.' && existing != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeWordHorizontally(char[][] arr, String word, int i, int j) {
        boolean[] wePlaced = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (arr[i][j + k] == '.') {
                wePlaced[k] = true;
                arr[i][j + k] = word.charAt(k);
            } else {
                wePlaced[k] = false;
            }
        }
        return wePlaced;
    }

    public static boolean[] placeWordVertically(char[][] arr, String word, int i, int j) {
        boolean[] wePlaced = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (arr[i + k][j] == '.') {
                wePlaced[k] = true;
                arr[i + k][j] = word.charAt(k);
            } else {
                wePlaced[k] = false;
            }
        }
        return wePlaced;
    }

    public static void unplaceWordHorizontally(char[][] arr, boolean[] wePlaced, int i, int j) {
        for (int k = 0; k < wePlaced.length; k++) {
            if (wePlaced[k]) {
                arr[i][j + k] = '.';
            }
        }
    }

    public static void unplaceWordVertically(char[][] arr, boolean[] wePlaced, int i, int j) {
        for (int k = 0; k < wePlaced.length; k++) {
            if (wePlaced[k]) {
                arr[i + k][j] = '.';
            }
        }
    }

    public static void print(char[][] arr) {
        System.out.println("Solution Found:");
        for (char[] row : arr) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        // Sample partial grid (use '.' for empty slots)
        char[][] grid = {
            {'#', 'A', '#'},
            {'B', '.', '.'},
            {'#', 'C', '#'}
        };

        String[] words = {"BAT", "CAT"};

        System.out.println("Original Grid:");
        print(grid);

        System.out.println("Solving...\n");
        solution(grid, words, 0);
    }
}
