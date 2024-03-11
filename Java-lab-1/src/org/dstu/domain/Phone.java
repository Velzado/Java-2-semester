package org.dstu.domain;

public abstract class Phone implements IUniversityPerson {
    private String message;
    private String call;
    private String simcard;

    public Phone() {
    }

    public Phone(String message, String call, String simcard) {
        this.message = message;
        this.call = call;
        this.simcard = simcard;
    }

    public Phone(String[] line) {
        call = line[1];
        message = line[3];
        simcard = line[2];
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getSimcard() {
        return simcard;
    }

    public void setSimcard(String simcard) {
        this.simcard = simcard;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" | Звонок: ");
        builder.append(call);
        builder.append(" | Симкарта: ");
        builder.append(simcard);
        builder.append(" | Сообщения: ");
        builder.append(message);
        return builder.toString();
    }
}
