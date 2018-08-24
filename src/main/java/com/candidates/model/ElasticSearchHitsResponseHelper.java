package com.candidates.model;

import java.util.List;

public class ElasticSearchHitsResponseHelper {

    private long total;

    private long max_score;

    private Object hits;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getMax_score() {
        return max_score;
    }

    public void setMax_score(long max_score) {
        this.max_score = max_score;
    }

    public Object getHits() {
        return hits;
    }

    public void setHits(Object hits) {
        this.hits = hits;
    }
}
