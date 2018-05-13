package infoHandler;

public enum ResponseEnum {
    $LOGIN_STATE$,
    $UPDATE_STATE$,
    $NEW_RECORD_REMIND$,
    $RES_DIR$,
    $RES_CONTENT$;

    @Override
    public String toString()
    {
        return ResponseEnum.values().toString() + "#";
    }
}