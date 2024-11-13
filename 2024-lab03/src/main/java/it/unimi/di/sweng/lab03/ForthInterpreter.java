package it.unimi.di.sweng.lab03;

import java.util.*;
import java.util.stream.Collectors;

public class ForthInterpreter implements Interpreter {

    private interface Operation {
        void op();
    }

    private final Map<String, Operation> map = new HashMap<>();
    private final Stack<Integer> stack = new Stack<>();

    public ForthInterpreter() {
        map.put("+", () -> stack.push(stack.pop() + stack.pop()));
        map.put("*", () -> stack.push(stack.pop() * stack.pop()));
        map.put("-", () -> {
            Integer x = stack.pop();
            stack.push(stack.pop() - x);
        });
        map.put("/", () -> {
            Integer x = stack.pop();
            stack.push(stack.pop() / x);
        });
        map.put("dup", () -> stack.push(stack.peek()));
        map.put("swap", () -> {
            Integer x = stack.pop();
            Integer y = stack.pop();
            stack.push(x);
            stack.push(y);
        });
        map.put("drop", stack::pop);
    }

    @Override
    public void input(String program) {
        stack.clear();
        handleProgram(program);
    }

    private void handleProgram(String program) {
        try (Scanner sc = new Scanner(program)) {
            while (sc.hasNext()) {
                String token = sc.next();
                if (token.matches("[0-9]+")) handleNumber(token);
                else if (map.containsKey(token)) handleOperation(token);
                else if (token.equals(":")) handleDefinition(sc);
                else handleInvalidToken(token);
            }
        }
    }

    private String defineOperation(Scanner sc) {
        String def = sc.findInLine(".*?;");
        return def.substring(0, def.length()-1);
    }

    private void handleDefinition(Scanner sc) {
        String name = sc.next();
        String op = defineOperation(sc);
        map.put(name, () -> handleProgram(op));
    }

    private void handleNumber(String token) {
        stack.push(Integer.valueOf(token));
    }

    private void handleOperation(String token) {
        try {
            map.get(token).op();
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Stack Underflow");
        }
    }

    private void handleInvalidToken(String token) {
        throw new IllegalArgumentException(String.format("Token error '%s'", token));
    }


    @Override
    public String toString() {
        return stack.stream()
                .map(x -> x.toString() + " ")
                .collect(Collectors.joining()) + "<- Top";
    }

}
