
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CountKeys{
    private static final Set<String> KEYWORDS = new HashSet<>();
    static {
        String[] keywords = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile",
            "while"
        };
        for (String keyword : keywords) {
            KEYWORDS.add(keyword);
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage java CountKeys <The full java file name being used>");
            System.exit(1);
        }

        String fileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isInBlockComment = false;
            int keywordCount = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (isInBlockComment) {
                    if (line.contains("*/")) {
                        isInBlockComment = false;
                        line = line.substring(line.indexOf("*/") + 2).trim();
                    } else {
                        continue;
                    }
                }

                if (line.contains("*/")) {
                    isInBlockComment = true;
                    line = line.substring(0, line.indexOf("/*")).trim();
                }

                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//")).trim();
                }

                line = line.replaceAll("\".*?\"", ""); //Remove string literals

                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r,.:;(){}[]\"'");
                while (tokenizer.hasMoreElements()) {
                    String token = tokenizer.nextToken();
                    if (KEYWORDS.contains(token)) {
                        keywordCount++;
                    }
                }

            }

            System.err.println("Number of keywords: " + keywordCount);
        } catch (IOException e) {
            System.err.println("Error reading this file: " + e.getMessage());
        }
    }
}