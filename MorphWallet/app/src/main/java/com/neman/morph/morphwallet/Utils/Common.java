package com.neman.morph.morphwallet.Utils;

import com.neman.morph.morphwallet.Model.User;

/**
 * Created by rocco on 3/5/18.
 */

public class Common {
    private static String DB_NAME="morphwallet";
    private static String COLLECTION_NAME = "user";
    public static String API_KEY="6D0eWBFJOgEvStu_kwLQQ0a70k2-OBF";

    public static String getAddressSingle(User user) {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME, COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+user.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME, COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}
