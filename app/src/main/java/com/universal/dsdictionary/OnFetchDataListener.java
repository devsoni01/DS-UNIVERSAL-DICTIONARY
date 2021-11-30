package com.universal.dsdictionary;

import com.universal.dsdictionary.Models.Apiresponse;

public interface OnFetchDataListener {
    void onFetchData(Apiresponse apiresponse, String message);
    void onError(String message);
}
