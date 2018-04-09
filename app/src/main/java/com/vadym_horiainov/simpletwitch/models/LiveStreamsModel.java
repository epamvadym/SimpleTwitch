
package com.vadym_horiainov.simpletwitch.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveStreamsModel {

    @SerializedName("_total")
    private Integer total;

    @SerializedName("streams")
    private List<Stream> streams = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

}
