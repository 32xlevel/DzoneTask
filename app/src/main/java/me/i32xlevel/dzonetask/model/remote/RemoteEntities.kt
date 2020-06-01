package me.i32xlevel.dzonetask.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    @SerializedName("response")
    val response: List<WorkerRemote>
)

data class WorkerRemote(
    @SerializedName("f_name")
    val firstName: String = "",

    @SerializedName("l_name")
    val lastName: String = "",

    @SerializedName("birthday")
    val birthday: String? = null,

    @SerializedName("avatr_url")
    val avatarUrl: String? = null,

    @SerializedName("specialty")
    val professions: List<ProfessionRemote> = emptyList()
)

data class ProfessionRemote(
    @SerializedName("specialty_id")
    val professionId: Int,

    @SerializedName("name")
    val name: String
)