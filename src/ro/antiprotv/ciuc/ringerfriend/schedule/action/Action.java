package ro.antiprotv.ciuc.ringerfriend.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

public interface Action {

	public void run();
	public JSONObject toJson() throws JSONException;
	public String getName();
	public String getValue();
	public Object getTypedValue();
}
