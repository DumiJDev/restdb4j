package io.github.dumijdev.restdbserver.application.core.domain.select;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;

import java.util.Optional;
import java.util.Set;

import static java.util.Optional.ofNullable;

public record SelectParams(String table, Optional<String> alias, Optional<String> schema, Set<SelectField> fields, Optional<Where> where,
                           Set<Order> order, Set<SelectJoins> joins, Optional<Integer> limit,
                           Optional<Integer> offset, Optional<String> sqlQuery) {
  public static SelectParamsBuilder builder() {
    return new SelectParamsBuilder();
  }

  public static final class SelectParamsBuilder {
    private String table;
    private String schema;
    private Set<SelectField> fields;
    private Where where;
    private Set<Order> order;
    private Integer limit;
    private Integer offset;
    private Set<SelectJoins> joins;
    private String alias;
    private String sqlQuery;

    private SelectParamsBuilder() {
    }

    public SelectParamsBuilder table(String table) {
      this.table = table;
      return this;
    }

    public SelectParamsBuilder schema(String schema) {
      this.schema = schema;
      return this;
    }

    public SelectParamsBuilder fields(Set<SelectField> fields) {
      this.fields = fields;
      return this;
    }

    public SelectParamsBuilder where(Where where) {
      this.where = where;
      return this;
    }

    public SelectParamsBuilder order(Order... orders) {
      this.order = Set.of(orders);
      return this;
    }

    public SelectParamsBuilder joins(Set<SelectJoins> joins) {
      this.joins = joins;
      return this;
    }

    public SelectParamsBuilder order(Set<Order> orders) {
      this.order = orders;
      return this;
    }

    public SelectParamsBuilder limit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public SelectParamsBuilder offset(Integer offset) {
      this.offset = offset;
      return this;
    }

    public SelectParamsBuilder alias(String alias) {
      this.alias = alias;
      return this;
    }

    public SelectParamsBuilder sql(String sqlQuery) {
      this.sqlQuery = sqlQuery;
      return this;
    }

    public SelectParams build() {
      return new SelectParams(table, ofNullable(alias), ofNullable(schema), fields, ofNullable(where),
          order, joins, ofNullable(limit), ofNullable(offset), ofNullable(sqlQuery));
    }

  }
}
