package ro.antiprotv.ciuc.ringerfriend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import org.json.JSONException;

import ro.antiprotv.ciuc.ringerfriend.adapter.SectionsPagerAdapter;
import ro.antiprotv.ciuc.ringerfriend.profile.Profile;
import ro.antiprotv.ciuc.ringerfriend.schedule.Schedule;
import ro.antiprotv.ciuc.ringerfriend.schedule.action.RingerAction;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Files;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private static final String LOG_TAG = MainActivity.class.toString();
	private ViewPager m_viewPager;
	private SectionsPagerAdapter m_sectionsPagerAdapter;
	private ActionBar m_actionBar;
	public static final String PROFILES_FILENAME = "ro.antiprotv.ciuc.ringerfriend.profiles.json";

	private String[] tabs = { "Profiles", "Schedules" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();

		setContentView(R.layout.activity_main);

		m_viewPager = (ViewPager) findViewById(R.id.pager);
		m_actionBar = getActionBar();
		FragmentManager fm = getFragmentManager();
		m_sectionsPagerAdapter = new SectionsPagerAdapter(fm);

		m_viewPager.setAdapter(m_sectionsPagerAdapter);
		m_actionBar.setHomeButtonEnabled(false);
		m_actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tabName : tabs) {
			m_actionBar.addTab(m_actionBar.newTab().setText(tabName)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		m_viewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						// on changing the page
						// make respected tab selected
						m_actionBar.setSelectedNavigationItem(position);
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
				});
	}

	private void init() {
		SharedPreferences prefs = getSharedPreferences("antipro",
				Context.MODE_PRIVATE);
		boolean initialized = prefs.getBoolean("initialized", false);
		Log.d(LOG_TAG, "App initilaized: " + initialized);
		//if (!initialized) {
			// put initial data (default profile)
			Profile defaultProfile = new Profile("name");
			Schedule defaultSchedule = new Schedule();
			defaultSchedule.setDays(Arrays.asList(0, 1, 2, 3, 4, 5));
			defaultSchedule.setHours(Arrays.asList("09:00", "17:00"));
			defaultSchedule.addAction(new RingerAction(50));

			defaultProfile.addSchedule(defaultSchedule);

			if (isExternalStorageWritable()) {
				File file = new File(getExternalFilesDir(null), PROFILES_FILENAME);
				Log.d(LOG_TAG, file.getAbsolutePath());
				PrintWriter pw = null;
				try {
					FileOutputStream outputStream;

					outputStream = new FileOutputStream(file);

					pw = new PrintWriter(outputStream);
					
					String json = defaultProfile.toJson().toString();
					Log.d(LOG_TAG, json);
					pw.write(json);
				} catch (FileNotFoundException e) {
					Log.e("ERROR", e.getMessage());
					e.printStackTrace();
				} catch (JSONException e) {
					Log.e("ERROR", e.getMessage());
					e.printStackTrace();
				} finally {
					pw.close();
				}
			}

			Editor editor = prefs.edit();
			editor.putBoolean("initialized", true);
			editor.commit();
		//}

	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		m_viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
