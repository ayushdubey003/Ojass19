package ojass19.nitjsr.in.ojass19;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Second_Walkthrough_Fragment extends Fragment {

        public Second_Walkthrough_Fragment() {
                // Required empty public constructor
        }

        public static Second_Walkthrough_Fragment newInstance() {
                Second_Walkthrough_Fragment fragment = new Second_Walkthrough_Fragment();
                return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                return inflater.inflate(R.layout.walkthrough_fragment_second, container, false);
        }

}
