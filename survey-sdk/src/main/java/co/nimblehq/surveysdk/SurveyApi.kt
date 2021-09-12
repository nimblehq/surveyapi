package co.nimblehq.surveysdk

import co.nimblehq.surveysdk.api.AppService
import co.nimblehq.surveysdk.entity.SurveyDetailEntity
import co.nimblehq.surveysdk.entity.SurveyListEntity
import co.nimblehq.surveysdk.network.NetworkBuilder
import co.nimblehq.surveysdk.request.BaseRequest


class SurveyApi : NetworkBuilder() {
    private lateinit var service: AppService
    private var version = "v1"
    private var clientId = ""
    private var clientSecret = ""

    companion object {
        private lateinit var instancce: SurveyApi
        fun getInstance(): SurveyApi = instancce

        fun setup(): SurveyApi {
            instancce = SurveyApi()
            return instancce
        }
    }

    fun withMode(debugMode: Boolean): SurveyApi {
        setDebugMode(debugMode)
        return this
    }

    fun withBaseUrl(url: String): SurveyApi {
        setBaseUrl(url)
        return this
    }

    fun withClientId(clientId: String): SurveyApi {
        this.clientId = clientId
        return this
    }

    fun withClientSecret(clientSecret: String): SurveyApi {
        this.clientSecret = clientSecret
        return this
    }

    fun setTokenApi(token: String): SurveyApi {
        super.setToken(token)
        return this
    }

    fun withConnectionTimeout(timeout: Long): SurveyApi {
        setConnectionTimeoutInSecond(timeout)
        return this
    }

    fun withReadTimeout(timeout: Long): SurveyApi {
        setReadTimeoutInSecond(timeout)
        return this
    }

    fun withVersion(version: String): SurveyApi {
        this.version = version
        return this
    }

    //IMPORTANT : need to be called after the configuration
    fun build() {
        service = buildAppService()
        BaseRequest.updateKey(clientId, clientSecret)
    }

    //public api
    suspend fun getSurveyList(page: Int, size: Int): SurveyListEntity {
        return service.getSurveyList(page, size, version)
    }

    suspend fun getSurveyDetail(id: String): SurveyDetailEntity {
        return service.getSurveyDetail(id, version)
    }

}
