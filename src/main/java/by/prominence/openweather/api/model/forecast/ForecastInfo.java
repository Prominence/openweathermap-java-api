/*
 * Copyright (c) 2018 Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package by.prominence.openweather.api.model.forecast;

import by.prominence.openweather.api.model.*;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ForecastInfo {

    @JSONField(name = "dt")
    // Time of data forecasted, unix, UTC
    private long dataCalculationTime;

    @JSONField(name = "main")
    private MainInfo mainInfo;

    @JSONField(name = "weather")
    private List<Weather> weathers;

    private Clouds clouds;

    private Wind wind;

    private Snow snow;

    private Rain rain;

    @JSONField(name = "sys")
    private ForecastSystemInfo systemInfo;

    // Data/time of calculation, UTC
    private String dt_txt;

    public long getDataCalculationTime() {
        return dataCalculationTime;
    }

    public void setDataCalculationTime(long dataCalculationTime) {
        this.dataCalculationTime = dataCalculationTime;
    }

    public MainInfo getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(MainInfo mainInfo) {
        this.mainInfo = mainInfo;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public ForecastSystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(ForecastSystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "\nForecastInfo{" +
                "dataCalculationTime=" + new Date(dataCalculationTime * 1000) +
                ", mainInfo=" + mainInfo +
                ", weathers=" + weathers +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", snow=" + snow +
                ", rain=" + rain +
                ", systemInfo=" + systemInfo +
                ", dt_text='" + dt_txt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastInfo that = (ForecastInfo) o;
        return dataCalculationTime == that.dataCalculationTime &&
                Objects.equals(mainInfo, that.mainInfo) &&
                Objects.equals(weathers, that.weathers) &&
                Objects.equals(clouds, that.clouds) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(snow, that.snow) &&
                Objects.equals(rain, that.rain) &&
                Objects.equals(systemInfo, that.systemInfo) &&
                Objects.equals(dt_txt, that.dt_txt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dataCalculationTime, mainInfo, weathers, clouds, wind, snow, rain, systemInfo, dt_txt);
    }
}
