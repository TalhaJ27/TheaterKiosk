package com.example.demo;


public class Booking {
    public Schedule schedule;
    public String customerId;
    public String showId;

    public Booking( String customerId, String scheduleString, String showId) {
        this.schedule = new Schedule(scheduleString);
        this.customerId = customerId;
        this.showId = showId;
    }

    @Override
    public String toString() {
        return "\nBooking{" + "schedule=" + schedule + ", customerId='" + customerId + '\'' + ", showId='" + showId + '\'' + "}\n";
    }

    private class Schedule {
        String day;
        String month;
        String year;
        String hour;
        String minute;
        String am_pm;

        public Schedule(String month, String day, String year, String hour, String minute, String am_pm) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.hour = hour;
            this.minute = minute;
            this.am_pm = am_pm;
        }

        public Schedule(String scheduleString) {
            System.out.println("scheduleString is: "+ scheduleString);
            String[] list = scheduleString.split("-");
            this.day = list[1];
            this.month = list[0];
            this.year = list[2];
            this.hour = list[3];
            this.minute = list[4];
            this.am_pm = list[5];
        }


        @Override
        public String toString() {
            return "Schedule{"+ month + '-' + day + '-' + year + " :: " + hour + ':' + minute + ':' + am_pm + '}';
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public String getAm_pm() {
            return am_pm;
        }

        public void setAm_pm(String am_pm) {
            this.am_pm = am_pm;
        }
    }
}
