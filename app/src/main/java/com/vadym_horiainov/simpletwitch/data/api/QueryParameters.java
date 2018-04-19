package com.vadym_horiainov.simpletwitch.data.api;

import java.util.HashMap;

public class QueryParameters {
    private HashMap<String, Object> map;

    private QueryParameters(final Builder builder) {
        this.map = builder.map;
    }

    public HashMap<String, Object> getMap() {
        return map;
    }

    public static class Builder {
        private HashMap<String, Object> map = new HashMap<>();

        public Builder add(final String key, final Object value) {
            if (key != null && !key.isEmpty() && value != null && !String.valueOf(value).isEmpty()) {
                map.put(key, value);
            }
            return this;
        }

        public QueryParameters build() {
            return new QueryParameters(this);
        }
    }
}
