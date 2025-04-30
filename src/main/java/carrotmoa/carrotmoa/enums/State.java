package carrotmoa.carrotmoa.enums;


import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum State {
    NORMAL(0),
    DELETE(1),

    ;
    private static final Map<Integer, State> statusMap = new HashMap<>();

    static {
        for (State state : State.values()) {
            statusMap.put(state.getStatus(), state);
        }
    }

    private final int status;


    State(int status) {
        this.status = status;
    }

    public static String getStateName(int status) {
        State state = statusMap.get(status);
        return state != null ? state.name() : null;
    }
}
