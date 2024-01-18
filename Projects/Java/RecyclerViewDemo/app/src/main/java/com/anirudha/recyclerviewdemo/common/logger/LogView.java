package com.anirudha.recyclerviewdemo.common.logger;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.app.AppCompatActivity;

/** Simple AppCompatTextView which is used to output log data received through the LogNode interface.
 */
public class LogView extends AppCompatTextView implements LogNode {

    public LogView(Context context) {
        super(context);
        init();
    }

    public LogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // Additional initialization if needed
    }

    /**
     * Formats the log data and prints it out to the LogView.
     *
     * @param priority Log level of the data being logged.  Verbose, Error, etc.
     * @param tag      Tag for for the log data.  Can be used to organize log statements.
     * @param msg      The actual message to be logged.
     * @param tr       If an exception was thrown, this can be sent along for the logging facilities
     *                 to extract and print useful information.
     */
    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        String priorityStr = getPriorityString(priority);

        // Handily, the Log class has a facility for converting a stack trace into a usable string.
        String exceptionStr = tr != null ? Log.getStackTraceString(tr) : null;

        // Take the priority, tag, message, and exception, and concatenate as necessary
        // into one usable line of text.
        final StringBuilder outputBuilder = new StringBuilder();

        String delimiter = "\t";
        appendIfNotNull(outputBuilder, priorityStr, delimiter);
        appendIfNotNull(outputBuilder, tag, delimiter);
        appendIfNotNull(outputBuilder, msg, delimiter);
        appendIfNotNull(outputBuilder, exceptionStr, delimiter);

        // In case this was originally called from an AsyncTask or some other off-UI thread,
        // make sure the update occurs within the UI thread.
        ((AppCompatActivity) getContext()).runOnUiThread(() -> {
            // Display the text we just generated within the LogView.
            appendToLog(outputBuilder.toString());
        });

        if (mNext != null) {
            mNext.println(priority, tag, msg, tr);
        }
    }

    private String getPriorityString(int priority) {
        switch (priority) {
            case Log.VERBOSE:
                return "VERBOSE";
            case Log.DEBUG:
                return "DEBUG";
            case Log.INFO:
                return "INFO";
            case Log.WARN:
                return "WARN";
            case Log.ERROR:
                return "ERROR";
            case Log.ASSERT:
                return "ASSERT";
            default:
                return "";
        }
    }

    public LogNode getNext() {
        return mNext;
    }

    public void setNext(LogNode node) {
        mNext = node;
    }

    /**
     * Takes a string and adds to it, with a separator, if the bit to be added isn't null. Since
     * the logger takes so many arguments that might be null, this method helps cut out some of the
     * agonizing tedium of writing the same 3 lines over and over.
     *
     * @param source    StringBuilder containing the text to append to.
     * @param addStr    The String to append
     * @param delimiter The String to separate the source and appended strings. A tab or comma,
     *                  for instance.
     * @return The fully concatenated String as a StringBuilder
     */
    private StringBuilder appendIfNotNull(StringBuilder source, String addStr, String delimiter) {
        if (addStr != null) {
            if (addStr.length() == 0) {
                delimiter = "";
            }

            return source.append(addStr).append(delimiter);
        }
        return source;
    }

    // The next LogNode in the chain.
    LogNode mNext;

    /**
     * Outputs the string as a new line of log data in the LogView.
     */
    public void appendToLog(String s) {
        append("\n" + s);
    }
}
