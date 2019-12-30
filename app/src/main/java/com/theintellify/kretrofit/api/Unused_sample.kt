GlobalScope.launch(Main) {
            val retrofit = RetrofitFactory.makeRetrofitService()
            try {
                val res = withContext(IO) {
                    retrofit.getResultAsync(activityCode).await()
                }
                if (res.isSuccessful) {
//                    toast("completed")
                    onActivityScanned(barcode.rawValue)
                }/*else{
//                    toast("cancelled")
                }*/
            } catch (e: SocketTimeoutException) {
//                toast("0")
                e.printStackTrace()
            } catch (e: HttpException) {
//                toast("1")
                e.printStackTrace()
            } catch (e: IOException) {
//                toast("2")
                e.printStackTrace()
            } catch (e: Throwable) {
//                toast("3")
                e.printStackTrace()
            }
        }

/* API.kt
interface API {

    @GET("activity/{activityCode}")
    fun getResultAsync(
        @Path("activityCode") activityCode: String
    ): Deferred<Response<ActivityResponse>>
}
*/
