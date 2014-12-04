package ro.antiprotv.ciuc.ringerfriend.schedule;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.antiprotv.ciuc.ringerfriend.schedule.action.Action;
/**
 * A schedule; a schedule will have days, hours and actions;
 * @author ciuc
 *
 */
public class Schedule {

	private String m_name;
	private Collection<Action> m_actions;
	private Collection<Integer> m_days;
	private Collection<String> m_hours;

	public void addAction(Action action) {
		if (m_actions == null) {
			m_actions = new ArrayList<Action>();
		}
		m_actions.add(action);
	}

	/*
	 * {
	"profiles":[
	  {"name":"default",
	  schedules: [
	    {days:[0,1,2,3,4],hours:["0900","1700"],actions:[{name:ringer,value:50},{name:wifioff,value:true}]},
	    {days:[6,7],hours:["0000","2300"],actions:[{name:ringer,value:0},{name:wifioff,value:true}]},
	  ]
	]

	}
	 */
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", getName());
		json.put("days", new JSONArray(getDays()));
		json.put("hours", new JSONArray(getHours()));
		JSONArray actions = new JSONArray();
		for (Action a : getActions()) {
			actions.put(a.toJson());
		}
		json.put("actions", actions);
		return json;
	}
	
	/**
	 * Hours of day in which the actions take effect;
	 * @return
	 */
	public Collection<String> getHours() {
		return m_hours;
	}

	public void setHours(Collection<String> hours) {
		m_hours = hours;
	}

	/**
	 * days of week in which the actions take effect. 
	 * @return
	 */
	public Collection<Integer> getDays() {
		return m_days;
	}

	public void setDays(Collection<Integer> days) {
		m_days = days;
	}

	public Collection<Action> getActions() {
		return m_actions;
	}

	public void setActions(Collection<Action> actions) {
		m_actions = actions;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}
}
