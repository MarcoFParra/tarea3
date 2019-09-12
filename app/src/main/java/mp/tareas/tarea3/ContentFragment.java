package mp.tareas.tarea3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ContentFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(String param1) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);;
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(mParam1);

        view.setRotation(-90f);

        return view;
    }

    public String getmParam1() {
        return mParam1;
    }

}
