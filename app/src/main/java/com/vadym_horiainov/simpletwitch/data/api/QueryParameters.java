package com.vadym_horiainov.simpletwitch.data.api;

import java.util.HashMap;

public class QueryParameters {
    private HashMap<String, String> map;

    private QueryParameters(final Builder builder) {
        this.map = builder.map;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public static class Builder {
        private HashMap<String, String> map = new HashMap<>();

        public Builder add(final String key, final Object value) {
            if (key == null || key.isEmpty() || value == null || String.valueOf(value).isEmpty()) {
                return this;
            }
            map.put(key, String.valueOf(value));
            return this;
        }

        public QueryParameters build() {
            return new QueryParameters(this);
        }
    }
}
