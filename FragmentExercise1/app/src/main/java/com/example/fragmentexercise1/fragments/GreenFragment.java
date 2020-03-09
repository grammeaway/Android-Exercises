package com.example.fragmentexercise1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.fragmentexercise1.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GreenFragment newInstance(String param1, String param2) {
        GreenFragment fragment = new GreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("fragtment_exercise", "GreenFragment onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("fragtment_exercise", "GreenFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("fragtment_exercise", "GreenFragment onCreateView");
        return inflater.inflate(R.layout.fragment_green, container, false);
    }


    @Override
    public void onAttach(Context context) {
        Log.i("fragtment_exercise", "GreenFragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.i("fragtment_exercise", "GreenFragment onDetach");
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        Log.i("fragtment_exercise", "GreenFragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("fragtment_exercise", "GreenFragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("fragtment_exercise", "GreenFragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("fragtment_exercise", "GreenFragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("fragtment_exercise", "GreenFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("fragtment_exercise", "GreenFragment onDestroy");
        super.onDestroy();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
