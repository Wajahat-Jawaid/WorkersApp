package com.wajahat.workersapp.api

import com.google.gson.annotations.SerializedName
import com.wajahat.workersapp.Constants
import com.wajahat.workersapp.models.Worker

class GetWorkersResponse {

    lateinit var message: String
    var data: Data? = null

    class Data {

        @SerializedName(Constants.ITEMS)
        var workers: ArrayList<Worker>? = null
    }
}