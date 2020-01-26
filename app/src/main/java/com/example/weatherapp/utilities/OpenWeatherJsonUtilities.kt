package com.example.weatherapp.utilities

import android.content.ContentValues
import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection

class OpenWeatherJsonUtilities {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     *
     *
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    @Throws(JSONException::class)
    fun getSimpleWeatherStringsFromJson(
        context: Context?,
        forecastJsonStr: String?
    ): Array<String?>? { /* Weather information. Each day's forecast info is an element of the "list" array */
        val OWM_LIST = "list"
        /* All temperatures are children of the "temp" object */
        val OWM_TEMPERATURE = "temp"
        /* Max temperature for the day */
        val OWM_MAX = "max"
        val OWM_MIN = "min"
        val OWM_WEATHER = "weather"
        val OWM_DESCRIPTION = "main"
        val OWM_MESSAGE_CODE = "cod"
        /* String array to hold each day's weather String */
        var parsedWeatherData: Array<String?>? = null
        val forecastJson = JSONObject(forecastJsonStr)
        /* Is there an error? */if (forecastJson.has(OWM_MESSAGE_CODE)) {
            val errorCode = forecastJson.getInt(OWM_MESSAGE_CODE)
            when (errorCode) {
                HttpURLConnection.HTTP_OK -> {
                }
                HttpURLConnection.HTTP_NOT_FOUND ->  /* Location invalid */return null
                else ->  /* Server probably down */return null
            }
        }
        val weatherArray = forecastJson.getJSONArray(OWM_LIST)
        parsedWeatherData = arrayOfNulls(weatherArray.length())
        val localDate = System.currentTimeMillis()
        val dateUtil = SunshineDateUtils()
        val utcDate: Long = dateUtil.getUTCDateFromLocal(localDate)
        val startDay: Long = dateUtil.normalizeDate(utcDate)
        for (i in 0 until weatherArray.length()) {
            var date: String
            var highAndLow: String
            /* These are the values that will be collected */
            var dateTimeMillis: Long
            var high: Double
            var low: Double
            var description: String
            /* Get the JSON object representing the day */
            val dayForecast = weatherArray.getJSONObject(i)
            /*
             * We ignore all the datetime values embedded in the JSON and assume that
             * the values are returned in-order by day (which is not guaranteed to be correct).
             */dateTimeMillis = startDay + dateUtil.DAY_IN_MILLIS * i
            date = dateUtil.getFriendlyDateString(context!!, dateTimeMillis, false)!!
            /*
             * Description is in a child array called "weather", which is 1 element long.
             * That element also contains a weather code.
             */
            val weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0)
            description = weatherObject.getString(OWM_DESCRIPTION)
            /*
             * Temperatures are sent by Open Weather Map in a child object called "temp".
             *
             * Editor's Note: Try not to name variables "temp" when working with temperature.
             * It confuses everybody. Temp could easily mean any number of things, including
             * temperature, temporary and is just a bad variable name.
             */
            val temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE)
            high = temperatureObject.getDouble(OWM_MAX)
            low = temperatureObject.getDouble(OWM_MIN)
            val weatherUtil = SunshineWeatherUtils()
            highAndLow = weatherUtil.formatHighLows(context, high, low)!!
            parsedWeatherData[i] = "$date - $description - $highAndLow"
        }
        return parsedWeatherData
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param forecastJsonStr The JSON to parse into ContentValues.
     *
     * @return An array of ContentValues parsed from the JSON.
     */
    fun getFullWeatherDataFromJson(
        context: Context?,
        forecastJsonStr: String?
    ): Array<ContentValues?>? {
        /** This will be implemented in a future lesson  */
        return null
    }
}