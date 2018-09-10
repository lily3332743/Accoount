package com.example.account;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.TextKeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.nightonke.jellytogglebutton.EaseTypes.Linear;
import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;

import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addAccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addAccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    FrameLayout frameLayout;
    private LinearLayout mLayout_input;
    private LinearLayout mLayout_output;
    private JellyToggleButton jellyToggleButton;


    ArrayList<ImageButton> childBtns_input=new ArrayList<ImageButton>();;
    ArrayList<ImageButton> childBtns_output=new ArrayList<ImageButton>();
    View rootView;


    int args[]={R.mipmap.fangdai,
            R.mipmap.feijipiao,
            R.mipmap.chuanpiao,
            R.mipmap.chashuikafei,
            R.mipmap.dianying,
            R.mipmap.dapai,
            R.mipmap.fangzu,R.mipmap.fanka,R.mipmap.fuwu,R.mipmap.haiwaidaigou,R.mipmap.gonggongqiche,
            R.mipmap.dianfei,R.mipmap.jiahao_bai};

    int args_input[]={R.mipmap.icon_shouru_type_gongzi,R.mipmap.icon_shouru_type_hongbao,R.mipmap.icon_shouru_type_jiangjin,
            R.mipmap.icon_shouru_type_jianzhiwaikuai,R.mipmap.icon_shouru_type_jieru,R.mipmap.icon_shouru_type_linghuaqian,
            R.mipmap.icon_shouru_type_qita,R.mipmap.icon_shouru_type_touzishouru,R.mipmap.jiahao_bai
    };




    private OnFragmentInteractionListener mListener;


    public addAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addAccountFragment newInstance(String param1, String param2) {
        addAccountFragment fragment = new addAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //全局view提取出来供我们更新布局
        view=inflater.inflate(R.layout.fragment_add_account, container, false);
        setHasOptionsMenu(true);
        refresh();
        return view;
    }

    public void refresh() {
        View addAccountContentLayout = view.findViewById(R.id.add_account_content);
        frameLayout=(FrameLayout)view.findViewById(R.id.add_account_content);
        addAccountContentLayout.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_add);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("");//将标题设置为空

        //分别提取支出和收入所对应的两个LinearLayout
        mLayout_input=view.findViewById(R.id.button_view_input);
        mLayout_output=view.findViewById(R.id.button_view_output);


        //这个是当我们点击了图标后跳出来的记账页面
        rootView = View.inflate(getActivity(), R.layout.my_edit_text, null);



        initOutputs();//支出页面按键的初始化
        initInputs();//收入页面按键的初始化
        //不管支出还是收入，给每个按键都注册点击事件，让他们能加载my_edit_text这个页面
        for(ImageButton i:childBtns_input){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.showCustomAlert(getActivity(), rootView, Gravity.BOTTOM,true,true).show();
                }
            });
        }
        for(ImageButton i:childBtns_output){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.showCustomAlert(getActivity(), rootView, Gravity.BOTTOM,true,true).show();
                }
            });
        }
        frameLayout.removeView(mLayout_input);//因为默认我们是位于支出页面，
        // 所以初始化完成之后要把已经加载的收入页面的LinearLayout给撤掉

        jellyToggleButton=(JellyToggleButton)view.findViewById(R.id.jellytogglebutton);
        jellyToggleButton.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            //头顶上那个切换的button的点击事件，分别对应要载入的不同LinearLayout
            public void onStateChange(float process, State state, JellyToggleButton jtb) {
                if (state.equals(State.LEFT)) {
                    frameLayout.removeView(mLayout_input);
                    frameLayout.removeView(mLayout_output);
                    frameLayout.addView(mLayout_output);
                }
                if (state.equals(State.RIGHT)) {
                    frameLayout.removeView(mLayout_output);
                    frameLayout.removeView(mLayout_input);
                    frameLayout.addView(mLayout_input);
                }
            }
        });


    }




    private void initInputs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout

        layoutParams.setMargins(10, 3, 10, 3);
        ArrayList<ImageButton> childBtns = new ArrayList<ImageButton>();
        int totoalBtns = 0;
        for(int i = 0; i < args_input.length; i++){
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            totoalBtns++;
            itemParams.setMargins(9, 5, 5, 5);

            ImageButton imageButton = new ImageButton(getActivity());
            imageButton.setBackgroundResource(args_input[i]);

            imageButton.setLayoutParams(itemParams);
            childBtns.add(imageButton);
            if(totoalBtns >= 5){
                LinearLayout  horizLL = new LinearLayout(getActivity());
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(ImageButton addBtn:childBtns){
                    horizLL.addView(addBtn);
                }
                mLayout_input.addView(horizLL);
                for(ImageButton button:childBtns){
                    childBtns_input.add(button);
                }
                childBtns.clear();
                totoalBtns = 0;
            }
        }
        //最后一行添加一下
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(getActivity());
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(layoutParams);

            for(ImageButton addBtn:childBtns){
                horizLL.addView(addBtn);
            }
            mLayout_input.addView(horizLL);
            for(ImageButton button:childBtns){
                childBtns_input.add(button);
            }
            childBtns.clear();
            totoalBtns = 0;
        }
    }



    private void initOutputs() {


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout

        layoutParams.setMargins(10, 3, 10, 3);
        ArrayList<ImageButton> childBtns = new ArrayList<ImageButton>();
        int totoalBtns = 0;
        for(int i = 0; i < args.length; i++){
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            totoalBtns++;
            itemParams.setMargins(9, 5, 5, 5);

            ImageButton imageButton = new ImageButton(getActivity());
            imageButton.setBackgroundResource(args[i]);

            imageButton.setLayoutParams(itemParams);
            childBtns.add(imageButton);
            if(totoalBtns >= 5){
                LinearLayout  horizLL = new LinearLayout(getActivity());
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(ImageButton addBtn:childBtns){
                    horizLL.addView(addBtn);
                    childBtns_output.add(addBtn);
                }
                mLayout_output.addView(horizLL);

                childBtns.clear();
                totoalBtns = 0;
            }
        }
        //最后一行添加一下
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(getActivity());
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(layoutParams);

            for(ImageButton addBtn:childBtns){
                horizLL.addView(addBtn);
                childBtns_output.add(addBtn);
            }
            mLayout_output.addView(horizLL);

            childBtns.clear();
            totoalBtns = 0;
        }


    }




//载入addAcount的toolbar
    public  void  onCreateOptionsMenu(Menu menu,MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.add_account_toolbar,menu);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
