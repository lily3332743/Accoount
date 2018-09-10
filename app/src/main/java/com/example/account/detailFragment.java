package com.example.account;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link detailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link detailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailFragment extends Fragment implements click_detail_itemFragment.OnFragmentInteractionListener,addAccountFragment.OnFragmentInteractionListener,
        AnalysisFragment.OnFragmentInteractionListener,societyFragment.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    DrawerLayout mDrawerLayout;
    TextView sort_text;
    TextView search_text;
    ImageButton calender;
    detailAdapter adapter;

    private List<Detail_Item> detail_item_List=new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public detailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailFragment newInstance(String param1, String param2) {
        detailFragment fragment = new detailFragment();
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

       view=inflater.inflate(R.layout.fragment_detail, container, false);
        refresh();


        return view;
    }

    public void refresh(){
        View detailContentLayout=view.findViewById(R.id.detail_content);
        detailContentLayout.setVisibility(View.VISIBLE);
        Toolbar toolbar=(Toolbar)view.findViewById(R.id.toolbar_detail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDrawerLayout=(DrawerLayout)view.findViewById(R.id.drawer_layout);

        ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("");//将标题设置为空
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }

        //分别为明细页面上的分类和搜索注册点击事件
        sort_text=view.findViewById(R.id.sort);
        search_text=view.findViewById(R.id.search);
        sort_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"你点击了分类",Toast.LENGTH_SHORT).show();
            }
        });

        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"你点击了搜索",Toast.LENGTH_SHORT).show();
            }
        });


        calender =view.findViewById(R.id.calendar);
        calender.setBackgroundResource(R.mipmap.home_detail_btn_p);

        
        initDetail_item();//初始化明细页面的Recyclerview

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
         adapter = new detailAdapter(R.layout.detail_item,detail_item_List);
        recyclerView.addItemDecoration(new MyPaddingDecoration(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.addData(detail_item_List);

//       Item的点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //position从零开始的

            }
        });


        //Item的长按事件
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }




    //下面这一坨被注释掉了的不知道要怎么实现在fragment中切换另一个fragment，想要替换掉activity_main的boss_content

//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        //创建点击事件
//        Button button = (Button) getActivity().findViewById(R.id.button_chant);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.setChantEssayFragment();
//            }
//        });
//    }




    private void initDetail_item() {
        detail_item_List.add(new Detail_Item(R.mipmap.icon_add_13,"包包","【高档品】","昨天10：59","￥10000"));
        detail_item_List.add(new Detail_Item(R.mipmap.icon_add_10,"爱心捐赠","【其他】","前天08：59","￥100"));
        detail_item_List.add(new Detail_Item(R.mipmap.icon_add_7,"火锅","【饮食】","8-7 16：44：00","￥233"));
        detail_item_List.add(new Detail_Item(R.mipmap.icon_add_6,"KTV","【娱乐】","前天04：59","￥20"));
        detail_item_List.add(new Detail_Item(R.mipmap.icon_add_3,"交水费","【生活】","8-6 08：19","￥123.11"));
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

    @Override
    public void onFragmentInteraction(Uri uri) {

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



