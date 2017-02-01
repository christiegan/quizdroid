package layout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.washington.clgan.quizdroid.MainActivity;
import edu.washington.clgan.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "currentq";
    private static final String ARG_PARAM2 = "totalq";
    private static final String ARG_PARAM3 = "correctq";
    private static final String ARG_PARAM4 = "id";
    private static final String ARG_PARAM5 = "choiceid";

    // TODO: Rename and change types of parameters
    private int id;
    private int currentq; // current questions
    private int totalq;   // the number of answered questions
    private int correctq; // the number of correctly answer questions
    private int choiceid;

    private OnFragmentInteractionListener mListener;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(int currentq, int totalq, int correctq, int id, int choiceid) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, currentq);
        args.putInt(ARG_PARAM2, totalq);
        args.putInt(ARG_PARAM3, correctq);
        args.putInt(ARG_PARAM4, id);
        args.putInt(ARG_PARAM5, choiceid);
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
            choiceid = getArguments().getInt(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        Button nextorfinish = (Button) view.findViewById(R.id.button3);
        nextorfinish.setOnClickListener(this);

        TextView selectedAnswer = (TextView)view.findViewById(R.id.textView3);

        TextView answer = (TextView)view.findViewById(R.id.textView4);
        if(id == 0){
            if(choiceid == 0){
                selectedAnswer.setText("Your selected answer is choice A: 74.05");
            }else if(choiceid == 1){
                selectedAnswer.setText("Your selected answer is choice B: 75.85");
            }else if(choiceid == 2){
                selectedAnswer.setText("Your selected answer is choice C: 62.9");
            }else if(choiceid == 3){
                selectedAnswer.setText("Your selected answer is choice D: 67.3");
            }
            answer.setText("The correct answer is choice B: 75.85");
        }else if(id == 1){
            if(choiceid == 0){
                selectedAnswer.setText("Your selected answer is choice A: was using");
            }else if(choiceid == 1){
                selectedAnswer.setText("Your selected answer is choice B: using");
            }else if(choiceid == 2){
                selectedAnswer.setText("Your selected answer is choice C: use");
            }else if(choiceid == 3){
                selectedAnswer.setText("Your selected answer is choice D: am using");
            }
            answer.setText("The correct answer is choice D: am using");
        }else if(id == 2){
            if(choiceid == 0){
                selectedAnswer.setText("Your selected answer is choice A: Inertia");
            }else if(choiceid == 1){
                selectedAnswer.setText("Your selected answer is choice B: Force");
            }else if(choiceid == 2){
                selectedAnswer.setText("Your selected answer is choice C: Mechanical Equilibrium");
            }else if(choiceid == 3){
                selectedAnswer.setText("Your selected answer is choice D: Equilibrium rule");
            }
            answer.setText("The correct answer is choice A: Inertia");
        }


        TextView score = (TextView)view.findViewById(R.id.textView5);
        String displayScore = "You have " + correctq + " out of " + totalq + " correct";
        Button next = (Button)view.findViewById(R.id.button3);
        if(totalq == 5){
            next.setText("Finish");
        }
        score.setText(displayScore);

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
        if (totalq < 5) {
            QuestionFragment questionfrag = QuestionFragment.newInstance(++currentq, totalq, correctq, id);
            FragmentTransaction tx = getFragmentManager().beginTransaction();

            // http://stackoverflow.com/questions/17760299/android-fragmenttransaction-custom-animation-unknown-animator-name-translate
            tx.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
            tx.replace(R.id.fragment_placeholder, questionfrag);
            tx.commit();
        } else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }
}
