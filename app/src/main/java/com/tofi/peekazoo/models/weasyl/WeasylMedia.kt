package com.tofi.peekazoo.models.weasyl

import com.google.gson.annotations.SerializedName

/**
 * Created by Derek on 19/05/2017.
 * Api response for a single media unit for Weasyl
 */
data class WeasylMedia(@SerializedName("mediaid") var id: Long = -1,
                       var url: String? = null) {
}