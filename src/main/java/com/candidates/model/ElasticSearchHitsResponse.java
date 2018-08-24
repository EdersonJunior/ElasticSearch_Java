package com.candidates.model;

import java.io.Serializable;

public class ElasticSearchHitsResponse implements Serializable {

    private Object took;

    private Object timed_out;

    private Object _shards;

    private ElasticSearchHitsResponseHelper hits;

    public Object getTook() {
        return took;
    }

    public void setTook(Object took) {
        this.took = took;
    }

    public Object getTimed_out() {
        return timed_out;
    }

    public void setTimed_out(Object timed_out) {
        this.timed_out = timed_out;
    }

    public Object get_shards() {
        return _shards;
    }

    public void set_shards(Object _shards) {
        this._shards = _shards;
    }

    public ElasticSearchHitsResponseHelper getHits() {
        return hits;
    }

    public void setHits(ElasticSearchHitsResponseHelper hits) {
        this.hits = hits;
    }
}
