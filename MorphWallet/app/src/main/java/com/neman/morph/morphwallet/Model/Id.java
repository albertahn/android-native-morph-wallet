package com.neman.morph.morphwallet.Model;

/**
 * Created by albert on 3/4/18.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by reale on 28/09/2016.
 */

public class Id {
    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}