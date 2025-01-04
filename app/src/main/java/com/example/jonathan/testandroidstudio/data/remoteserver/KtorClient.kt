package com.example.jonathan.testandroidstudio.data.remoteserver

import android.util.Log
import com.example.jonathan.testandroidstudio.domain.datamodel.remoteserver.KeyValuePairs
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

private const val TAG = "TAS: KtorClient"

/**
 * This singleton class is the Ktor client that makes calls to RESTful APIs.
 */
object KtorClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            /*json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })*/

            // This is needed to make method 1 work:
            register(
                io.ktor.http.ContentType.Any, KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 10000 // 10 seconds
        }

        install(Logging) {
            level = LogLevel.INFO
        }
    }

    suspend fun getKeyValuePairs(fullUrlStr: String): KeyValuePairs {
        Log.d(TAG, "getKeyValuePairs: fullUrlStr=[$fullUrlStr]")

        // Handle response:
        try {
            val httpResponse = client.get(fullUrlStr)

            // Handle empty content:
            if (httpResponse.contentLength() == 0L) {
                Log.e(TAG, "getKeyValuePairs: contentLength == 0 !!")
                return KeyValuePairs()
            }

            val stringBody: String

            // Handle response status codes.
            when (httpResponse.status) {
                HttpStatusCode.OK -> {
                    stringBody = httpResponse.body<String>()
                    Log.v(TAG, "getKeyValuePairs: Status is OK: stringBody=[$stringBody]")
                }
                HttpStatusCode.BadRequest -> {
                    Log.e(TAG, "getKeyValuePairs: BadRequest !!")
                    return KeyValuePairs()
                }
                HttpStatusCode.InternalServerError -> {
                    Log.e(TAG, "getKeyValuePairs: InternalServerError !!")
                    return KeyValuePairs()
                }
                else -> {
                    Log.e(TAG, "getKeyValuePairs: ${httpResponse.status} !!")
                    return KeyValuePairs()
                }
            }

            var keyValuePairs: KeyValuePairs

            // Handle deserialization:
            try {
                // TODO: This may not work. More investigation will be needed:
                keyValuePairs = httpResponse.body()

                Log.v(TAG, "getKeyValuePairs: 1st method: keyValuePairs=[${keyValuePairs}]")
            } catch (e: Exception) {
                Log.e(TAG, "getKeyValuePairs: 1st method: stackTrace=\n${e.stackTraceToString()}")

                // TODO: This is a back-up 2nd method in case the above call failed:
                val json = Json {
                    ignoreUnknownKeys = true
                }

                // Handle deserialization with back-up 2nd method:
                try {
                    keyValuePairs = json.decodeFromString(stringBody)
                } catch (e: Exception) {
                    Log.e(TAG, "getKeyValuePairs: 2nd method: stackTrace=\n${e.stackTraceToString()}")
                    return KeyValuePairs()
                }

                Log.v(TAG, "getKeyValuePairs: 2nd method: keyValuePairs=[${keyValuePairs}]")
            }

            return keyValuePairs
        } catch (e: java.net.SocketException) {
            Log.e(TAG, "getKeyValuePairs: SocketException: stackTrace=\n${e.stackTraceToString()}")
            return KeyValuePairs()
        } catch (e: java.nio.channels.UnresolvedAddressException) {
            Log.e(TAG, "getKeyValuePairs: UnresolvedAddressException: stackTrace=\n${e.stackTraceToString()}")
            return KeyValuePairs()
        } catch (e: Exception) { // Other errors:
            Log.e(TAG, "getKeyValuePairs: other exception: stackTrace=\n${e.stackTraceToString()}")
            return KeyValuePairs()
        }
    }
}
