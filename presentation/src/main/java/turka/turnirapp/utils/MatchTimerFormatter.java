package turka.turnirapp.utils;

import java.util.Date;

/**
 * Created by turka on 7/2/2017.
 */

public class MatchTimerFormatter {

    public static String getFormattedMatchTimer(Date startDate, Boolean isSecondHalf){
        Date now = new Date();

        long secondsBetween = Math.abs((now.getTime() - startDate.getTime()) / 1000);
        long hours = secondsBetween/3600;
        long minutes = (secondsBetween - (hours * 3600)) / 60;
        long seconds = secondsBetween - (hours * 3600) - (minutes * 60);


        String finalString = "";
        String hoursString = String.valueOf(hours);
        hoursString = hoursString.length() == 1 ? "0" + hoursString : hoursString;

        if(!hoursString.equals("00")){
            finalString += hoursString + ":";
        }

        String minuteString = String.valueOf(isSecondHalf != null && isSecondHalf ? minutes + 20 : minutes );
        minuteString = minuteString.length() == 1 ? "0" + minuteString : minuteString;
        finalString += minuteString  + ":";

        String secondsString = String.valueOf(seconds);
        secondsString = secondsString.length() == 1 ? "0" + secondsString : secondsString;
        finalString += secondsString;

        return finalString;
    }
}
