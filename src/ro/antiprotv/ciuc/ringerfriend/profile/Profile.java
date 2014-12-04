package ro.antiprotv.ciuc.ringerfriend.profile;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.antiprotv.ciuc.ringerfriend.schedule.Schedule;

public class Profile {
	private String m_name;
	private Collection<Schedule> m_schedules;

	public Profile(String name) {
		m_name = name;
	}

	public void addSchedule(Schedule s){
		if (m_schedules == null) {
			m_schedules = new ArrayList<Schedule>();
		}
		m_schedules.add(s);
	}
	
	public String getName() {
		return m_name;
	}

	
	public void setName(String name) {
		m_name = name;
	}

	

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", getName());
		JSONArray schedules = new JSONArray();
		for (Schedule s : getSchedules()) {
			schedules.put(s.toJson());
		}
		json.put("schedules", schedules);
		return json;
	}
	
	public Collection<Schedule> getSchedules() {
		return m_schedules;
	}

	public void setSchedules(Collection<Schedule> schedules) {
		m_schedules = schedules;
	} 
}
