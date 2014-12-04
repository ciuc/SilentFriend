package ro.antiprotv.ciuc.ringerfriend.adapter;

import ro.antiprotv.ciuc.ringerfriend.fragments.ProfileFragment;
import ro.antiprotv.ciuc.ringerfriend.fragments.ProfileListFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new ProfileListFragment();
		case 1: 
			return new ProfileFragment();
		default:
			return new ProfileListFragment();
		}
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 2;
	}


}