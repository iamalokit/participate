package com.alokit.participate.model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private Long id;

    private String eventName;

    private String eventType;

    private String participantType;

    private Long eventCategoryId;

    private Long eventLocationId;

    private String eventDate;

    private String eventCost;

    private String eventPic;

    private Date createTime;

    private Long createdBy;

    private Date updateTime;

    private Long updatedBy;

    private String eventDescription;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType == null ? null : participantType.trim();
    }

    public Long getEventCategoryId() {
        return eventCategoryId;
    }

    public void setEventCategoryId(Long eventCategoryId) {
        this.eventCategoryId = eventCategoryId;
    }

    public Long getEventLocationId() {
        return eventLocationId;
    }

    public void setEventLocationId(Long eventLocationId) {
        this.eventLocationId = eventLocationId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate == null ? null : eventDate.trim();
    }

    public String getEventCost() {
        return eventCost;
    }

    public void setEventCost(String eventCost) {
        this.eventCost = eventCost == null ? null : eventCost.trim();
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic == null ? null : eventPic.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription == null ? null : eventDescription.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventName=").append(eventName);
        sb.append(", eventType=").append(eventType);
        sb.append(", participantType=").append(participantType);
        sb.append(", eventCategoryId=").append(eventCategoryId);
        sb.append(", eventLocationId=").append(eventLocationId);
        sb.append(", eventDate=").append(eventDate);
        sb.append(", eventCost=").append(eventCost);
        sb.append(", eventPic=").append(eventPic);
        sb.append(", createTime=").append(createTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", eventDescription=").append(eventDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}