package com.shergill.sgpgym.view.activity.splace.Sealedclss

//sealed class ApiResponce<T>{
//    data class Success<T>(val data:T):ApiResponce<T>()
//    data class Error<T>(val data:Throwable):ApiResponce<T>()
//    object Loading : ApiResponce<T>()
//}
sealed class ApiResponse<T> {

    data class Success<T>(val data: T) : ApiResponse<T>()

    data class Error<T>(val error: String) : ApiResponse<T>()

    data class Loading<T>(val t: T?=null) : ApiResponse<T>()
}