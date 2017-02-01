package layout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.washington.clgan.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "currentq";
    private static final String ARG_PARAM2 = "totalq";
    private static final String ARG_PARAM3 = "correctq";
    private static final String ARG_PARAM4 = "id";

    // TODO: Rename and change types of parameters
    private int currentq;
    private int totalq;
    private int correctq;
    private int id;

    private RadioGroup radioGroup;
    Button submitButton;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int currentq, int totalq, int correctq, int id) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, currentq);
        args.putInt(ARG_PARAM2, totalq);
        args.putInt(ARG_PARAM3, totalq);
        args.putInt(ARG_PARAM4, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentq = getArguments().getInt(ARG_PARAM1);
            totalq = getArguments().getInt(ARG_PARAM2);
            correctq = getArguments().getInt(ARG_PARAM3);
            id = getArguments().getInt(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        Button begin = (Button) view.findViewById(R.id.button2);
        begin.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView description = (TextView)view.findViewById(R.id.textView2);
        RadioButton answer1 = (RadioButton)view.findViewById(R.id.radioButton1);
        answer1.setChecked(false);
        RadioButton answer2 = (RadioButton)view.findViewById(R.id.radioButton2);
        answer2.setChecked(false);
        RadioButton answer3 = (RadioButton)view.findViewById(R.id.radioButton3);
        answer3.setChecked(false);
        RadioButton answer4 = (RadioButton)view.findViewById(R.id.radioButton4);
        answer4.setChecked(false);
        submitButton = (Button)view.findViewById(R.id.button2);
        submitButton.setEnabled(false);
        if(id == 0) {
            description.setText("QUESTION " + Integer.toString(currentq+1)+": Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?");
            answer1.setText("74.05");
            answer2.setText("75.85");
            answer3.setText("62.9");
            answer4.setText("67.3");
        }else if(id == 1){
            description.setText("QUESTION " + Integer.toString(currentq+1)+": Sorry, you can't borrow my pencil. I ..... it myself.");
            answer1.setText("was using");
            answer2.setText("using");
            answer3.setText("use");
            answer4.setText("am using");
        }else{
            description.setText("QUESTION " + Integer.toString(currentq+1)+": The tendency of objects to resist changes in motion.");
            answer1.setText("Inertia");
            answer2.setText("Force");
            answer3.setText("Mechanical Equilibrium");
            answer4.setText("Equilibrium rule");
        }
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                submitButton.setEnabled(true);
            }
        });
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View v) {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int userAnswer = radioGroup.indexOfChild(radioButton);

        totalq++;
        if(id == 0){
            if(userAnswer == 1){
                correctq++;
            }
        }else if(id == 1){
            if(userAnswer == 3){
                correctq++;
            }
        }else{
            if(userAnswer == 0){
                correctq++;
            }
        }
        AnswerFragment answerfrag = AnswerFragment.newInstance(currentq,totalq,correctq, id, userAnswer);
        FragmentTransaction tx = getFragmentManager().beginTransaction();

        // http://stackoverflow.com/questions/17760299/android-fragmenttransaction-custom-animation-unknown-animator-name-translate
        tx.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        tx.replace(R.id.fragment_placeholder, answerfrag);
        tx.commit();
    }
}
