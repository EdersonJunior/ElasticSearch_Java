package com.candidates.model;

import java.io.Serializable;

public class ElasticSearchHitResponse implements Serializable {

    private String _id;

    private ElasticSearchSource _source;

    public ElasticSearchHitResponse() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ElasticSearchSource get_source() {
        return _source;
    }

    public void set_source(ElasticSearchSource _source) {
        this._source = _source;
    }
}