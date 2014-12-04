package ro.antiprotv.ciuc.ringerfriend.fragments;

import ro.antiprotv.ciuc.ringerfriend.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileListFragment extends Fragment {
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_profile_list, container, false);
	         
	        return rootView;
	    }
}
