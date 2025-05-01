package utils;

public class GlobalState {
	
    private static GlobalState instance = new GlobalState();

    public static String currentUser;
    private int sessionId;

    public GlobalState() {}

    public static GlobalState getInstance() {
        return instance;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int id) {
        this.sessionId = id;
    }
}