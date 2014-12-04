package ro.antiprotv.ciuc.ringerfriend.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

public class RingerAction implements Action {
	private int m_volume;

	public RingerAction(int volume) {
		m_volume = volume;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject toJson() throws JSONException {
		return new JSONObject().put(getName(), getValue());
	}

	@Override
	public String getName() {
		return "ringer";
	}

	@Override
	public String getValue() {
		return Integer.valueOf(m_volume).toString();
	}

	@Override
	public Object getTypedValue() {
		return Integer.valueOf(m_volume);
	}

}
