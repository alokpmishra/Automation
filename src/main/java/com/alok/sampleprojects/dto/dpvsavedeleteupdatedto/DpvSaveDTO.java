package com.alok.sampleprojects.dto.dpvsavedeleteupdatedto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by alokprakash.p on 6/12/2015.
 */
public class DpvSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String _id;
    private String name;
    private String codeName;
    private String shortDesc;
    private Long totalRecordCount;
    private Long matchingRecordCount;
    private String queueName;
    private Date startTime;
    private Date endTime;

    public String get_id() {
       /* DateTime startTime = null;
        DateTime endTime = null;
        DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        startTime=dateTimeFormatter.parseDateTime(getStartTime());
        endTime=dateTimeFormatter.parseDateTime(getEndTime());*/

        Calendar startTime= Calendar.getInstance();
        startTime.setTime(getStartTime());
        Calendar endTime= Calendar.getInstance();
        endTime.setTime(getEndTime());
        String idField = getQueueName() + "_" + getCodeName() + "_" + startTime.getTimeInMillis() + "_" + endTime.getTimeInMillis();
        set_id(idField);
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public Long getMatchingRecordCount() {
        return matchingRecordCount;
    }

    public void setMatchingRecordCount(Long matchingRecordCount) {
        this.matchingRecordCount = matchingRecordCount;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpvSaveDTO that = (DpvSaveDTO) o;

        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;
        if (codeName != null ? !codeName.equals(that.codeName) : that.codeName != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (matchingRecordCount != null ? !matchingRecordCount.equals(that.matchingRecordCount) : that.matchingRecordCount != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (queueName != null ? !queueName.equals(that.queueName) : that.queueName != null) return false;
        if (shortDesc != null ? !shortDesc.equals(that.shortDesc) : that.shortDesc != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (totalRecordCount != null ? !totalRecordCount.equals(that.totalRecordCount) : that.totalRecordCount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        result = 31 * result + (shortDesc != null ? shortDesc.hashCode() : 0);
        result = 31 * result + (totalRecordCount != null ? totalRecordCount.hashCode() : 0);
        result = 31 * result + (matchingRecordCount != null ? matchingRecordCount.hashCode() : 0);
        result = 31 * result + (queueName != null ? queueName.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpvSaveDTO{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", codeName='" + codeName + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", totalRecordCount='" + totalRecordCount + '\'' +
                ", matchingRecordCount='" + matchingRecordCount + '\'' +
                ", queueName='" + queueName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
