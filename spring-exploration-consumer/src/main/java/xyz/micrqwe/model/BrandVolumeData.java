package xyz.micrqwe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shinelon on 2017/1/5.
 * 品牌声量
 */
public class BrandVolumeData implements Serializable {
    /**
     * 品牌code
     */
    private String brandCd;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 媒体名称
     */
    private String mediaName;
    /**
     * 媒体名称
     */
    private String mediaCd;
    /**
     * 声量
     */
    private Integer volume = 0;
    /**
     * 好评数量
     */
    private Integer positive = 0;
    /**
     * 差评数量
     */
    private Integer negative = 0;
    /**
     * 分析时间
     */
    private Date anlsDate;

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Date getAnlsDate() {
        return anlsDate;
    }

    public void setAnlsDate(Date anlsDate) {
        this.anlsDate = anlsDate;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaCd() {
        return mediaCd;
    }

    public void setMediaCd(String mediaCd) {
        this.mediaCd = mediaCd;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    @Override
    public String toString() {
        return "BrandVolumeData{" +
                "brandCd='" + brandCd + '\'' +
                ", brandName='" + brandName + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", mediaCd='" + mediaCd + '\'' +
                ", volume=" + volume +
                ", positive=" + positive +
                ", negative=" + negative +
                ", anlsDate=" + anlsDate +
                '}';
    }
}
