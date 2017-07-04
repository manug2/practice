package extras;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PrefixToPostfix {

    String convert(String prefix) {
        List<String> terms = split(prefix);
        while (terms.size()>1)
            restack(terms);
        return terms.get(0);
    }

    private void restack(List<String> terms) {
        Stack<String> s = new Stack<>();

        final int numOfElems = terms.size();
        for(int i=0; i< numOfElems; i++) {
            String c = terms.get(i);
            if (isOp(c)) {
                s.push(c);
            } else {
                if (isOp(s.peek())) {
                    s.push(c);
                } else {
                    String postfix = toPostFix(c, s.pop(), s.pop());
                    terms.remove(i);
                    terms.remove(i-1);
                    terms.remove(i-2);
                    terms.add(i-2, postfix);
                    break;
                }
            }
        }
    }

    private String toPostFix(String r2, String r1, String op) {
        return r1 + r2 + op;
    }

    private boolean isOp(String c) {
        switch(c) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    private List<String> split(String prefix) {
        List<String> splitted = new ArrayList<>(prefix.length());
        for (char c : prefix.toCharArray())
            splitted.add("" + c);
        return splitted;
    }
}
