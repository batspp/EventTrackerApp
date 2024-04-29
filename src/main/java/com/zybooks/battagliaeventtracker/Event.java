package com.zybooks.battagliaeventtracker;

public class Event {
    private String name = "Enter name";
    private String date = "Enter date";
    private String time = "Enter time";
    private String description = "Enter description";

    // Constructor without default values
    public Event(String name, String date, String time, String description) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Event(String eventName) {
        this.name = eventName;
    }

    // Getters and setters for event properties
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    // Method to save the event to the database
    public long saveToDatabase(EventsDatabaseHelper dbHelper) {
        return dbHelper.addEvent(this.name);
    }

    // Method to update the event in the database
    public int updateInDatabase(EventsDatabaseHelper dbHelper, int eventId, String newName) {
        return dbHelper.updateEventName(eventId, newName);
    }

    // Method to delete the event from the database
    public int deleteFromDatabase(EventsDatabaseHelper dbHelper, int eventId) {
        return dbHelper.deleteEvent(eventId);
    }
}