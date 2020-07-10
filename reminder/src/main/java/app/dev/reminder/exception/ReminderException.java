package app.dev.reminder.exception;

public class ReminderException extends Exception{
    public static int CONTEXT_NULL=5001,
                        CALENDAR_NULL=5002,
                        INPUT_MSG_NULL=5003,
                        CALLING_INTENT_NULL=5004,
                        CALLBACK_NULL=5005,
                        FAILED_TO_START=5006;
    private int code;

    public ReminderException(String message, int code) {
        super(message);
        this.code = code;
    }
}
