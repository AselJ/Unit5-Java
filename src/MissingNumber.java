import java.util.*;
import java.util.stream.Collectors;
public class MissingNumber {
    public static int missingNumber(int[] nums, int maxNum) { //Step 1, Part 1

        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        for (int i = 1; i <= maxNum; i += 1) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }
    //Step 2
    public static int missingNumberSort(int[] nums, int maxNum) {
        Arrays.sort(nums);

        for (int i = 1; i < maxNum; i += 1) {
            if (i != nums[i - 1]) {
                return i;
            }
        }

        // If none is missing
        if (nums.length == maxNum && nums[maxNum - 1] == maxNum) {
            return 0;
        }

        return maxNum;
    }
    //Step 3
    public static int missingNumberSum(int[] nums, int maxNum) {
        // Alternate solution: int sum = (nums.length + 1)*(nums.length) / 2
        int expectedSum = 0;
        for (int i = 1; i <= maxNum; i += 1) {
            expectedSum += i;
        }

        int actualSum = Arrays.stream(nums).sum();

        return expectedSum - actualSum;
    }
//End Part 1
    //Step 1, Part 2
    public static boolean balancedParentheses(String s) {
        int numUnclosedOpenParens = 0;
        for (int i = 0; i < s.length(); i += 1) {
            if (s.charAt(i) == '(') {
                numUnclosedOpenParens += 1;
            }
            else if (s.charAt(i) == ')') {
                numUnclosedOpenParens -= 1;
            }

            if (numUnclosedOpenParens < 0) {
                return false;
            }
        }

        return numUnclosedOpenParens == 0;
    }

    public static boolean balancedBrackets(String s) {

        Stack<Character> stack = new Stack<Character>();
        HashMap<Character, Character> bracketPairs = new HashMap<Character, Character>();
        bracketPairs.put(')', '(');
        bracketPairs.put(']', '[');
        bracketPairs.put('}', '{');
        bracketPairs.put('>', '<');

        for (int i = 0; i < s.length(); i += 1) {

            // If character is opening bracket
            if (bracketPairs.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            }
            // Else, if character is closing bracket
            else if (bracketPairs.containsKey(s.charAt(i))) {
                // Closing bracket without matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                // Check that most recent bracket on stack matches
                Character mostRecentBracket = stack.pop();
                if (!mostRecentBracket.equals(bracketPairs.get(s.charAt(i)))) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
// Step 2
    public class ArrayStack {

        private String[] stack;
        private int top;

        public ArrayStack(int capacity) {
            stack = new String[capacity];
        }

        public void push(String s) {
            if (top == stack.length) {
                // need to resize the backing array
                String[] newArray = new String[2 * stack.length];
                System.arraycopy(stack, 0, newArray, 0, stack.length);
                stack = newArray;
            }

            stack[top++] = s;
        }

        public String pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }

            String s = stack[--top];
            stack[top] = null;
            return s;
        }

        public String peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }

            return stack[top - 1];
        }

        public int size() {
            return top;
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public void printStack() {
            for (int i = top - 1; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }

    }
// End Part 2
}
