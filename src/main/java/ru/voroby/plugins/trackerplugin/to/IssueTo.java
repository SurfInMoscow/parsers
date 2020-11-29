package ru.voroby.plugins.trackerplugin.to;

public class IssueTo {
    private String dateTime;
    private String name;
    private String priority;
    private String status;

    public IssueTo() {
    }

    public IssueTo(String dateTime, String name, String priority, String status) {
        this.dateTime = dateTime;
        this.name = name;
        this.priority = priority;
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IssueTo{" +
                "dateTime='" + dateTime + '\'' +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
