package ir.insurance.startup.web.rest.vm;

/**
 * View Model object for storing the user's key and password.
 */
public class KeyAndValueVM {

    private String key;
    private String value;


    public KeyAndValueVM(String key,String value){
        this.key=key;
        this.value=value;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
