package io.github.dumijdev.restdb4j.server.application.core.domain.common;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public record Where(String symbol, Set<Condition> conditions) {
  public enum Operator {
    GREATER("greater"), LESSER("lesser"), EQUALS("equals"), NOT_EQUALS("not_equals"),
    GREATER_EQUALS("greater_equals"), LESSER_EQUALS("lesser_equals"), LIKE("like"),
    NOT("not"), NOT_NULL("not_null"), NULL("null"), UNKNOWN("");

    private final String symbol;

    public String symbol() {
      return symbol;
    }

    Operator(String symbol) {
      this.symbol = symbol;
    }
    
    public String value() {
      return switch (this.symbol) {
        case "greater" -> ">";
        case "lesser" -> "<";
        case "equals" -> "=";
        case "not_equals" -> "<>";
        case "greater_equals" -> ">=";
        case "lesser_equals" -> "<=";
        case "like" -> "LIKE";
        case "not_like" -> "NOT LIKE";
        case "null" -> "IS NULL";
        case "not_null" -> "IS NOT NULL";
        case "not" -> "NOT";
        default -> "";
      };
    }

    public static Operator from(String symbol) {
      Predicate<Operator> filterBySymbol = op -> op.symbol.equals(symbol);
      return stream(values()).filter(filterBySymbol).findFirst().orElse(UNKNOWN);
    }
  }

  public record Condition(String field, Optional<String> table, Operator operator, Object value) {
  }

  public enum Symbol {
    AND("AND"), OR("OR"), UNKNOWN("");

    private final String symbol;

    Symbol(String symbol) {
      this.symbol = symbol;
    }

    public static Symbol from(String symbol) {
      Predicate<Symbol> filterBySymbol = op -> op.symbol.equalsIgnoreCase(symbol);
      return stream(values()).filter(filterBySymbol).findFirst().orElse(UNKNOWN);
    }

    public static boolean validate(String symbol) {
      return from(symbol) != UNKNOWN;
    }
  }
}
