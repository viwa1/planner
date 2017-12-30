package de.sartison.planner.model;

import java.util.Date;

public class Event {
	// Uniquely identifies the given event. Different instances of repeating
	// events should all have the same id.
	private Long id;

	// The text on an event's element
	private String title;

	// Whether an event occurs at a specific time-of-day. This property affects
	// whether an event's time is shown. Also, in the agenda views, determines
	// if it is displayed in the "all-day" section.
	// If this value is not explicitly specified, allDayDefault will be used if
	// it is defined.
	//
	// If all else fails, FullCalendar will try to guess. If either the start or
	// end value has a "T" as part of the ISO8601 date string, allDay will
	// become false. Otherwise, it will be true.
	//
	// Don't include quotes around your true/false. This value is a boolean, not
	// a string!
	private Boolean allDay;

	// The date/time an event begins. Required.

	// A Moment-ish input, like an ISO8601 string. Throughout the API this will
	// become a real Moment object.
	private Date start;

	// The exclusive date/time an event ends. Optional.
	//
	// A Moment-ish input, like an ISO8601 string. Throughout the API this will
	// become a real Moment object.
	//
	// It is the moment immediately after the event has ended. For example, if
	// the last full day of an event is Thursday, the exclusive end of the event
	// will be 00:00:00 on Friday!
	private Date end;

	// Overrides the master editable option for this single event.
	private Boolean editable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", allDay=" + allDay + ", start=" + start + ", end=" + end
				+ ", editable=" + editable + "]";
	}
}
