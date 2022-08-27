package problems.interviews;

public class PolindronCheck {
    public static int polindromInSubstringNested(String input, int startSubstring, int endSubstring) {
        int count = 0;
        for (; startSubstring >= 0 && endSubstring < input.length(); --startSubstring, ++endSubstring) {
            if (input.charAt(startSubstring) != input.charAt(endSubstring)) {
                break;
            }
            if(input.substring(startSubstring, endSubstring + 1).length()>=3) {
                System.out.println(input.substring(startSubstring, endSubstring + 1));
                count++;

            }
        }
        return count;
    }

    public static int findPalindrome(String input) {
        int count = 0;
        for(int i = 0 ; i < input.length() ; ++i) {
            count+= polindromInSubstringNested(input, i-1, i+1);
            count+= polindromInSubstringNested(input, i, i+1);
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "aababaabce";
        int count = findPalindrome(str);
        System.out.println("Total palindrome substrings: " + count);
    }
}
