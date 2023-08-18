package com.example.application.views.main;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@PageTitle("Main")
@Route(value = "")
public class DataBricksScheduler extends VerticalLayout {

    private ComboBox<String> timeUnitCombobox;
    private ComboBox<String> intervalsCombobox;
    private ComboBox<String> offsetCombobox;
    private ComboBox<String> minutesCombobox;
    private ComboBox<String> hoursCombobox;
    private ComboBox<String> dayOfTheweeksCombobox;
    private ComboBox<String> daysCombobox;
    private ComboBox<String> timeZonesCombobox;
    private TextField cronSyntaxTextField;
    private Checkbox showCronSyntaxCheckbox;

    private String[] cronSyntax;
    private HorizontalLayout components;
    DataBricksScheduler(){
        this.cronSyntax = new String[6];
        this.cronSyntax[0] = String.valueOf(LocalDateTime.now().getSecond());
        initComponents();
        setUI();
        setListeners();
    }
    public String getCronSyntax() {
        generateCronSyntax();
        return String.join(" ",cronSyntax);
    }

    public void setCronSyntax(String cronSyntax) {
//        this.cronSyntax = cronSyntax;
    }
    private void initComponents(){
        timeUnitCombobox = new ComboBox<>();
        intervalsCombobox = new ComboBox<>();
        offsetCombobox = new ComboBox<>();
        minutesCombobox = new ComboBox<>();
        hoursCombobox = new ComboBox<>();
        dayOfTheweeksCombobox = new ComboBox<>();
        daysCombobox = new ComboBox<>();
        timeZonesCombobox = new ComboBox<>();
    }
    private void setUI(){
        components = new HorizontalLayout();
        cronSyntaxTextField = new TextField();
        showCronSyntaxCheckbox = new Checkbox();
        /*components.add(cronSyntaxTextField);
        cronSyntaxTextField.setVisible(false);*/
        add(components, showCronSyntaxCheckbox);
        setUI_basedOn_timeUnit("Day");
    }
    private void setListeners(){
        /*timeUnitComboboxListener();
        intervalsComboboxListener();*/
        showCronSyntaxCheckboxListener();
    }
    private void setIntervalsComboboxForMinutes(String interval){
        List<String> intervals = new ArrayList<>(
                List.of("Every 1",
                        "Every 2",
                        "Every 3",
                        "Every 4",
                        "Every 5",
                        "Every 6",
                        "Every 10",
                        "Every 12",
                        "Every 15",
                        "Every 20",
                        "Every 30")
        );
        intervalsCombobox.setItems(intervals);
        intervalsCombobox.setValue(interval);
        intervalsComboboxListener();
    }
    private void setIntervalsComboboxForHour(String interval){
        List<String> intervals = new ArrayList<>(
                List.of("Every 1",
                        "Every 2",
                        "Every 3",
                        "Every 4",
                        "Every 5",
                        "Every 6",
                        "Every 8",
                        "Every 12")
        );
        intervalsCombobox.setItems(intervals);
        intervalsCombobox.setValue(interval);
        intervalsComboboxListener();
    }
    private void setTimeUnitCombobox(String timeUnit){
        List<String> timeUnits = new ArrayList<>(
                List.of("Minutes",
                        "Hours",
                        "Day",
                        "Week",
                        "Month")
        );
        timeUnitCombobox.setItems(timeUnits);
        timeUnitCombobox.setValue(timeUnit);
        timeUnitComboboxListener();
    }

    private void setOffsetComboboxForMinutes(int minutes){
        List<String> minutesOffset = new ArrayList<>();
        for (int i = 0; i < minutes; i++) {
            minutesOffset.add(String.valueOf(i)+" minutes past the hour");
        }
        offsetCombobox.setItems(minutesOffset);
        offsetCombobox.setValue("0 minutes past the hour");
    }
    private void setMinutesCombobox(){
        List<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            minutes.add(String.valueOf(i));
        }
        minutesCombobox.setItems(minutes);
        minutesCombobox.setValue("0");
    }
    private void setHoursCombobox(){
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hours.add(String.valueOf(i));
        }
        hoursCombobox.setItems(hours);
        hoursCombobox.setValue("0");
    }
    private void setDayOfTheweeksCombobox(String day){
        List<String> days = new ArrayList<>(List.of(
                "SUNDAY",
                "MONDAY",
                "TUESDAY",
                "WEDNESDAY",
                "THURSDAY",
                "FRIDAY",
                "SATURDAY"
        ));
        dayOfTheweeksCombobox.setItems(days);
        dayOfTheweeksCombobox.setValue(day);
    }
    private void setDaysCombobox(){
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        daysCombobox.setItems(days);
        daysCombobox.setValue("0");
    }
    private void setTimeZonesCombobox(){
        List<String> timezones = new ArrayList<>();
        timezones.add("(UTC-11:00) Midway Island, Samoa");
        timezones.add("(UTC-10:00) Hawaii");
        timezones.add("(UTC-08:00) Alaska");
        timezones.add("(UTC-07:00) Pacific Time (US and Canada); Tijuana");
        timezones.add("(UTC-07:00) Arizona");
        timezones.add("(UTC-06:00) Mountain Time (US and Canada)");
        timezones.add("(UTC-06:00) Chihuahua, La Paz, Mazatlan");
        timezones.add("(UTC-06:00) Saskatchewan");
        timezones.add("(UTC-06:00) Guadalajara, Mexico City, Monterrey");
        timezones.add("(UTC-06:00) Central America");
        timezones.add("(UTC-05:00) Central Time (US and Canada)");
        timezones.add("(UTC-05:00) Bogota, Lima, Quito");
        timezones.add("(UTC-04:00) Eastern Time (US and Canada)");
        timezones.add("(UTC-04:00) Indiana (East)");
        timezones.add("(UTC-04:00) Caracas, La Paz");
        timezones.add("(UTC-04:00) Santiago");
        timezones.add("(UTC-03:00) Atlantic Time (Canada)");
        timezones.add("(UTC-03:00) Brasilia");
        timezones.add("(UTC-03:00) Buenos Aires, Georgetown");
        timezones.add("(UTC-02:30) Newfoundland and Labrador");
        timezones.add("(UTC-02:00) Greenland");
        timezones.add("(UTC-02:00) Mid-Atlantic");
        timezones.add("(UTC-01:00) Cape Verde Islands");
        timezones.add("(UTC+00:00) Azores");
        timezones.add("(UTC+00:00) UTC");
        timezones.add("(UTC+01:00) Dublin, Edinburgh, Lisbon, London");
        timezones.add("(UTC+01:00) Casablanca, Monrovia");
        timezones.add("(UTC+01:00) Canary Islands");
        timezones.add("(UTC+01:00) West Central Africa");
        timezones.add("(UTC+02:00) Belgrade, Bratislava, Budapest, Ljubljana, Prague");
        timezones.add("(UTC+02:00) Sarajevo, Skopje, Warsaw, Zagreb");
        timezones.add("(UTC+02:00) Brussels, Copenhagen, Madrid, Paris");
        timezones.add("(UTC+02:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna");
        timezones.add("(UTC+02:00) Harare, Pretoria");
        timezones.add("(UTC+03:00) Bucharest");
        timezones.add("(UTC+03:00) Cairo");
        timezones.add("(UTC+03:00) Helsinki, Kiev, Riga, Sofia, Tallinn, Vilnius");
        timezones.add("(UTC+03:00) Athens, Istanbul, Minsk");
        timezones.add("(UTC+03:00) Jerusalem");
        timezones.add("(UTC+03:00) Moscow, St. Petersburg, Volgograd");
        timezones.add("(UTC+03:00) Kuwait, Riyadh");
        timezones.add("(UTC+03:00) Nairobi");
        timezones.add("(UTC+03:00) Baghdad");
        timezones.add("(UTC+03:30) Tehran");
        timezones.add("(UTC+04:00) Abu Dhabi, Muscat");
        timezones.add("(UTC+04:00) Baku, Tbilisi, Yerevan");
        timezones.add("(UTC+04:30) Kabul");
        timezones.add("(UTC+05:00) Yekaterinburg");
        timezones.add("(UTC+05:00) Islamabad, Karachi, Tashkent");
        timezones.add("(UTC+05:30) Chennai, Kolkata, Mumbai, New Delhi");
        timezones.add("(UTC+05:30) Sri Jayawardenepura");
        timezones.add("(UTC+05:45) Kathmandu");
        timezones.add("(UTC+06:00) Astana, Dhaka");
        timezones.add("(UTC+06:00) Almaty, Novosibirsk");
        timezones.add("(UTC+06:30) Yangon Rangoon");
        timezones.add("(UTC+07:00) Bangkok, Hanoi, Jakarta");
        timezones.add("(UTC+07:00) Krasnoyarsk");
        timezones.add("(UTC+08:00) Beijing, Chongqing, Hong Kong SAR, Urumqi");
        timezones.add("(UTC+08:00) Kuala Lumpur, Singapore");
        timezones.add("(UTC+08:00) Taipei");
        timezones.add("(UTC+08:00) Perth");
        timezones.add("(UTC+08:00) Irkutsk, Ulaanbaatar");
        timezones.add("(UTC+09:00) Seoul");
        timezones.add("(UTC+09:00) Osaka, Sapporo, Tokyo");
        timezones.add("(UTC+09:00) Yakutsk");
        timezones.add("(UTC+09:30) Darwin");
        timezones.add("(UTC+09:30) Adelaide");
        timezones.add("(UTC+10:00) Canberra, Melbourne, Sydney");
        timezones.add("(UTC+10:00) Brisbane");
        timezones.add("(UTC+10:00) Hobart");
        timezones.add("(UTC+10:00) Vladivostok");
        timezones.add("(UTC+10:00) Guam, Port Moresby");
        timezones.add("(UTC+11:00) Magadan, Solomon Islands, New Caledonia");
        timezones.add("(UTC+12:00) Fiji Islands, Kamchatka, Marshall Islands");
        timezones.add("(UTC+12:00) Auckland, Wellington");
        timezones.add("(UTC+13:00) Nuku'alofa");
        timeZonesCombobox.setItems(timezones);
        timeZonesCombobox.setValue("(UTC+00:00) UTC");
    }
    private void setUI_basedOn_timeUnit(String timeUnit){

        switch (timeUnit) {
            case "Minutes", "Hours": {
                setUI_basedOn_Interval_And_timeUnit("Every 1",timeUnit);
                break;
            }
            case "Day":{
                components.removeAll();
                initComponents();
                setHoursCombobox();
                setMinutesCombobox();
                setTimeUnitCombobox("Day");
                setTimeZonesCombobox();
                components.add(timeUnitCombobox, new Label("at"), hoursCombobox, new Label(":"), minutesCombobox, timeZonesCombobox);
                break;
            }
            case "Week":{
                components.removeAll();
                initComponents();
                setDayOfTheweeksCombobox(String.valueOf(LocalDate.now().getDayOfWeek()));
                setTimeUnitCombobox("Week");
                setHoursCombobox();
                setMinutesCombobox();
                setTimeZonesCombobox();
                components.add(timeUnitCombobox, new Label("on"), dayOfTheweeksCombobox, new Label("at"), hoursCombobox, new Label(":"), minutesCombobox, timeZonesCombobox);
                break;
            }
            case "Month":{
                components.removeAll();
                initComponents();
                setTimeUnitCombobox("Month");
                setDaysCombobox();
                setHoursCombobox();
                setMinutesCombobox();
                setTimeZonesCombobox();
                components.add(timeUnitCombobox, new Label("on the"),daysCombobox, new Label("at"), hoursCombobox, new Label(":"), minutesCombobox, timeZonesCombobox);
            }
        }
    }
    private void setUI_basedOn_Interval_And_timeUnit(String interval, String timeUnit){
        switch (timeUnit){
            case "Minutes": {
                if (interval.equalsIgnoreCase("Every 1")) {
                    components.removeAll();
                    initComponents();
                    setIntervalsComboboxForMinutes("Every 1");
                    setTimeUnitCombobox("Minutes");
                    components.add(intervalsCombobox, timeUnitCombobox);
                } else {
                    components.removeAll();
                    initComponents();
                    setIntervalsComboboxForMinutes(interval);
                    setTimeUnitCombobox("Minutes");
                    setOffsetComboboxForMinutes(Integer.valueOf(getInterval(interval)));
                    components.add(intervalsCombobox, timeUnitCombobox, offsetCombobox);
                }
                break;
            }
            case "Hours":{
                if(interval.equalsIgnoreCase("Every 1")) {
                    components.removeAll();
                    initComponents();
                    setIntervalsComboboxForMinutes("Every 1");
                    setTimeUnitCombobox("Hours");
                    setOffsetComboboxForMinutes(60);
                    setTimeZonesCombobox();
                    components.add(intervalsCombobox, timeUnitCombobox,new Label("at"), offsetCombobox,timeZonesCombobox);
                }
                else{
                    components.removeAll();
                    initComponents();
                    setIntervalsComboboxForHour(interval);
                    setHoursCombobox();
                    setMinutesCombobox();
                    setTimeUnitCombobox("Hours");
                    setTimeZonesCombobox();
                    components.add(intervalsCombobox, timeUnitCombobox, new Label("from"),hoursCombobox, minutesCombobox, timeZonesCombobox);
                }
                break;
            }
        }
    }

    private void timeUnitComboboxListener(){
        timeUnitCombobox.addValueChangeListener(event -> {
            String timeUnit = event.getValue();
            setUI_basedOn_timeUnit(timeUnit);
        });
    }
    private void intervalsComboboxListener(){
        intervalsCombobox.addValueChangeListener(event -> {
            String interval = event.getValue();
            String timeUnit = timeUnitCombobox.getValue();
            setUI_basedOn_Interval_And_timeUnit(interval, timeUnit);
        });
    }
    private String getInterval(String input){
        return input.substring(input.indexOf(' ')+1);
    }

    private void showCronSyntaxCheckboxListener(){
        showCronSyntaxCheckbox.addValueChangeListener(event ->{
            cronSyntaxTextField.setValue(getCronSyntax());
           if(!event.getHasValue().isEmpty())
           {
               removeAll();
               add(cronSyntaxTextField, showCronSyntaxCheckbox);
           }
           else {
               removeAll();
               add(components, showCronSyntaxCheckbox);
           }
        });
    }
    private void generateCronSyntax() {
        try {
            String interval = "0",timeUnit= "",hour = "",offset = "", minutes = "",dayOfTheWeek = "",day = "";
            if(intervalsCombobox.getValue() != null){
                interval = intervalsCombobox.getValue();
                interval = interval.substring(interval.indexOf(' ') + 1);
            }
            if(timeUnitCombobox.getValue() != null)
                timeUnit = timeUnitCombobox.getValue();
            if(offsetCombobox.getValue() != null) {
                offset = offsetCombobox.getValue();
                offset = offset.substring(0, offset.indexOf(' '));
            }
            if(hoursCombobox.getValue() != null)
                hour = hoursCombobox.getValue();
            if(minutesCombobox.getValue() != null)
                minutes = minutesCombobox.getValue();
            if(dayOfTheweeksCombobox.getValue() != null) {
                dayOfTheWeek = dayOfTheweeksCombobox.getValue();
                dayOfTheWeek = dayOfTheWeek.substring(0, 1).toUpperCase() +
                        dayOfTheWeek.substring(1,3).toLowerCase();
            }
            if(daysCombobox.getValue() != null)
                day = daysCombobox.getValue();

            if (Integer.valueOf(interval) == 1) {
                if (timeUnit.equalsIgnoreCase("Minutes")) {
                    this.cronSyntax[1] = "*";
                    this.cronSyntax[2] = "*";
                    this.cronSyntax[3] = "*";
                    this.cronSyntax[4] = "*";
                    this.cronSyntax[5] = "?";
                } else if (timeUnit.equalsIgnoreCase("Hours")) {
                    this.cronSyntax[1] = offset;
                    this.cronSyntax[2] = "*";
                    this.cronSyntax[3] = "*";
                    this.cronSyntax[4] = "*";
                    this.cronSyntax[5] = "?";
                }
            } else if (Integer.valueOf(interval) > 1) {
                if (timeUnit.equalsIgnoreCase("Minutes")) {
                    this.cronSyntax[1] = offset + "/" + interval;
                    this.cronSyntax[2] = "*";
                    this.cronSyntax[3] = "*";
                    this.cronSyntax[4] = "*";
                    this.cronSyntax[5] = "?";
                } else if (timeUnit.equalsIgnoreCase("Hours")) {
                    this.cronSyntax[1] = minutes;
                    this.cronSyntax[2] = hour + "/" + interval;
                    this.cronSyntax[3] = "*";
                    this.cronSyntax[4] = "*";
                    this.cronSyntax[5] = "?";
                }
            } else if (timeUnit.equalsIgnoreCase("Day")) {
                this.cronSyntax[1] = minutes;
                this.cronSyntax[2] = hour;
                this.cronSyntax[3] = "*";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";
            } else if (timeUnit.equalsIgnoreCase("Week")) {
                this.cronSyntax[1] = minutes;
                this.cronSyntax[2] = hour;
                this.cronSyntax[3] = "?";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = dayOfTheWeek;
            } else if (timeUnit.equalsIgnoreCase("Month")) {
                this.cronSyntax[1] = minutes;
                this.cronSyntax[2] = hour;
                this.cronSyntax[3] = day;
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";
            }
        } catch (NullPointerException e) {
            System.out.println("INTERVAL: "+ intervalsCombobox.getValue());
            System.out.println("TIME UNIT: "+ timeUnitCombobox.getValue());
            System.out.println("OFFSET: "+offsetCombobox.getValue());
            System.out.println("HOURS: "+hoursCombobox.getValue());
            System.out.println("MINUTES: "+minutesCombobox.getValue());
            System.out.println("DAY OF THE WEEK: "+dayOfTheweeksCombobox.getValue());
            System.out.println("DAY: "+daysCombobox.getValue());
        }
    }
    private void setUI_basedOn_UserInput(String[] cronSyntax){
        /*String interval = "0",timeUnit= "",hour = "",offset = "", minutes = "",dayOfTheWeek = "",day = "";
        if(intervalsCombobox.getValue() != null){
            interval = intervalsCombobox.getValue();
            interval = interval.substring(interval.indexOf(' ') + 1);
        }
        if(timeUnitCombobox.getValue() != null)
            timeUnit = timeUnitCombobox.getValue();
        if(offsetCombobox.getValue() != null) {
            offset = offsetCombobox.getValue();
            offset = offset.substring(0, offset.indexOf(' '));
        }
        if(hoursCombobox.getValue() != null)
            hour = hoursCombobox.getValue();
        if(minutesCombobox.getValue() != null)
            minutes = minutesCombobox.getValue();
        if(dayOfTheweeksCombobox.getValue() != null) {
            dayOfTheWeek = dayOfTheweeksCombobox.getValue();
            dayOfTheWeek = dayOfTheWeek.substring(0, 1).toUpperCase() +
                    dayOfTheWeek.substring(1,3).toLowerCase();
        }
        if(daysCombobox.getValue() != null)
            day = daysCombobox.getValue();*/


        /*if (Integer.valueOf(interval) == 1) {
            if (timeUnit.equalsIgnoreCase("Minutes")) {*/
        if(cronSyntax[1].equalsIgnoreCase("*") &&
                cronSyntax[2].equalsIgnoreCase("*") &&
                cronSyntax[3].equalsIgnoreCase("*") &&
                cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")) {
            setUI_basedOn_timeUnit("Minutes");
                /*this.cronSyntax[1] = "*";
                this.cronSyntax[2] = "*";
                this.cronSyntax[3] = "*";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";*/
        }
        /*    } else if (timeUnit.equalsIgnoreCase("Hours")) {*/
        if(cronSyntax[2].equalsIgnoreCase("*") &&
                cronSyntax[3].equalsIgnoreCase("*") &&
                cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")){
            String offset = cronSyntax[1];
                setUI_basedOn_timeUnit("Hours");
                offsetCombobox.setValue(offset+" minutes past the hour");
                /*this.cronSyntax[1] = offset;
                this.cronSyntax[2] = "*";
                this.cronSyntax[3] = "*";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";*/
            }
        /*} else if (Integer.valueOf(interval) > 1) {
            if (timeUnit.equalsIgnoreCase("Minutes")) {*/
        if(cronSyntax[2].equalsIgnoreCase("*") &&
                cronSyntax[3].equalsIgnoreCase("*") &&
                cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")) {
            String offset = cronSyntax[1].substring(0, cronSyntax[1].indexOf("/"));
            String interval = cronSyntax[1].substring(cronSyntax[1].indexOf("/") + 1);
            setUI_basedOn_timeUnit("Hours");
            offsetCombobox.setValue(offset + " minutes past the hour");
            setUI_basedOn_timeUnit("Minutes");
            offsetCombobox.setValue(offset + " minutes past the hour");
            intervalsCombobox.setValue("Entry " + interval);
                /*this.cronSyntax[1] = offset + "/" + interval;
                this.cronSyntax[2] = "*";
                this.cronSyntax[3] = "*";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";*/
        }
            /*} else if (timeUnit.equalsIgnoreCase("Hours")) {*/
        if(cronSyntax[3].equalsIgnoreCase("*") &&
                cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")){
            String minutes = cronSyntax[1];
            String hour = cronSyntax[2].substring(0,cronSyntax[2].indexOf("/"));
            String interval = cronSyntax[2].substring(cronSyntax[2].indexOf("/")+1);
                setUI_basedOn_timeUnit("Hours");
                minutesCombobox.setValue(minutes);
                hoursCombobox.setValue(hour);
                intervalsCombobox.setValue("Entry "+interval);
                /*this.cronSyntax[1] = minutes;
                this.cronSyntax[2] = hour + "/" + interval;
                this.cronSyntax[3] = "*";
                this.cronSyntax[4] = "*";
                this.cronSyntax[5] = "?";*/
            }
        /*} else if (timeUnit.equalsIgnoreCase("Day")) {*/
        if(cronSyntax[3].equalsIgnoreCase("*") &&
                cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")) {
            String minutes = cronSyntax[1];
            String hour = cronSyntax[2];
            setUI_basedOn_timeUnit("Day");
            minutesCombobox.setValue(minutes);
            hoursCombobox.setValue(hour);
            /*this.cronSyntax[1] = minutes;
            this.cronSyntax[2] = hour;
            this.cronSyntax[3] = "*";
            this.cronSyntax[4] = "*";
            this.cronSyntax[5] = "?";*/
        }
        /*} else if (timeUnit.equalsIgnoreCase("Week")) {*/
        if(cronSyntax[3].equalsIgnoreCase("?") &&
                cronSyntax[4].equalsIgnoreCase("*")) {
            String minutes = cronSyntax[1];
            String hour = cronSyntax[2];
            String dayOfTheWeek = cronSyntax[5];
            setUI_basedOn_timeUnit("Week");
            minutesCombobox.setValue(minutes);
            hoursCombobox.setValue(hour);
            dayOfTheweeksCombobox.setValue(getMatchingDay(dayOfTheWeek));
            /*this.cronSyntax[1] = minutes;
            this.cronSyntax[2] = hour;
            this.cronSyntax[3] = "?";
            this.cronSyntax[4] = "*";
            this.cronSyntax[5] = dayOfTheWeek;*/
        }
        /*} else if (timeUnit.equalsIgnoreCase("Month")) {*/
        if(cronSyntax[4].equalsIgnoreCase("*") &&
                cronSyntax[5].equalsIgnoreCase("?")) {
            String minutes = cronSyntax[1];
            String hour = cronSyntax[2];
            String day = cronSyntax[3];
            setUI_basedOn_timeUnit("Month");
            minutesCombobox.setValue(minutes);
            hoursCombobox.setValue(hour);
            daysCombobox.setValue(day);
            /*this.cronSyntax[1] = minutes;
            this.cronSyntax[2] = hour;
            this.cronSyntax[3] = day;
            this.cronSyntax[4] = "*";
            this.cronSyntax[5] = "?";*/
        }
    }
    private String getMatchingDay(String userInput) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for (String day : daysOfWeek) {
            if (userInput.startsWith(day)) {
                return day.toUpperCase();
            }
        }

        return null;
    }

}
