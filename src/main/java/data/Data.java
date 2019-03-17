package data;

public class Data {

    private static final String DEMO_CHAT_URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final String MESSAGE = "Aa((+_sФ  Dы в2312%:? *())_";
    private static final String EDITING_MESSAGE = "QqФЫВй 31 :?!(№!)";
    private static final String REMOVED_MESSAGE = "removed...";

    private static final String USER_NAME = "ASdas Фвым 123 %^@!(";
    private static final String EDITING_USER_NAME = "%^ *&ASD asd12 3";
    private static final String USER_EMAIL = "test@gmail.com";
    private static final String EDITING_USER_EMAIL = "test123@gmail.com";
    private static final String PHOTO_URL = "https://www.seleniumhq.org/images/big-logo.png";
    private static final String EDITING_PHOTO_URL = "https://focus.ua/modules/thumb.php?u=../files/SABi/2017/025/2615387" +
            ".jpg&m=c_large_wide";
    private static final String DEFAULT_NAME = "Anonymous";

    public static String getDefaultName() { return DEFAULT_NAME; }

    public static String getUserName() {
        return USER_NAME;
    }

    public static String getEditingUserName() {
        return EDITING_USER_NAME;
    }

    public static String getUserEmail() {
        return USER_EMAIL;
    }

    public static String getEditingUserEmail() {
        return EDITING_USER_EMAIL;
    }

    public static String getPhotoUrl() {
        return PHOTO_URL;
    }

    public static String getEditingPhotoUrl() {
        return EDITING_PHOTO_URL;
    }

    public static String getRemovedMessage() {
        return REMOVED_MESSAGE;
    }

    public static String getDemoChatUrl() {
        return DEMO_CHAT_URL;
    }

    public static String getMessage() {
        return MESSAGE;
    }

    public static String getEditingMessage() {
        return EDITING_MESSAGE;
    }
}
